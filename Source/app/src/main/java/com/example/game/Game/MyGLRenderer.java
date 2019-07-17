package com.example.game.Game;

import android.content.Context;
import android.opengl.GLES20;
import android.opengl.GLSurfaceView;
import android.opengl.Matrix;

import java.nio.FloatBuffer;
import java.util.Vector;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;


import static android.opengl.GLES20.GL_NEVER;
import static android.opengl.GLES20.GL_ONE_MINUS_SRC_ALPHA;
import static android.opengl.GLES20.glDepthFunc;

import static com.example.game.Game.GameResourceBinder.arrayOfFilteringExceptions;
import static com.example.game.Game.GameResourceBinder.arrayOfPlayerNoMobs;
import static com.example.game.Game.GameResourceBinder.gameLoaded;
import static com.example.game.Game.GameResourceBinder.arrayOfPlayers;
import static com.example.game.Game.UserSettingsBinder.displayScale;
import static com.example.game.Game.UserSettingsBinder.screenHeight;
import static com.example.game.Game.UserSettingsBinder.screenRatio;
import static com.example.game.Game.UserSettingsBinder.screenWidth;
import static com.example.game.Game.UserSettingsBinder.gameInLoadingState;

import static com.example.game.Game.GameResourceBinder.arrayOfTriangles;


public class MyGLRenderer implements GLSurfaceView.Renderer {


    private final float[] vPMatrix = new float[16];
    private final float[] mProjectionMatrix = new float[16];
    private final float[] mViewMatrix = new float[16];
    private final float[] mModelMatrix = new float[16];

    private TriangleShaderCreator triangleShader;
    private FloatBuffer mVertexBufferTriangle;
    private FloatBuffer mTriangleNormals;
    private FloatBuffer mTriangleColors;
    private FloatBuffer mTriangleTextureCoordinates;


    private int mProgramHandle;
    private int mLightHandle;

    private int mTextureUniformHandle;
    private int mTextureCoordinateHandle;


    final float eyeX = 0.0f;
    final float eyeY = 0.0f;
    final float eyeZ = 0.0f;


    final float lookX = 0.0f;
    final float lookY = 0.0f;
    final float lookZ = -5f;

    final float upX = 0.0f;
    final float upY = 1.0f;
    final float upZ = 0.0f;


    private int positionHandle;
    private int mLightPosHandle;
    private int mNormalHandle;
    private int mColorHandle;
    private int mPMatrixHandle;
    private int mvMatrixHandle;


    private final float[] mLightPosInWorldSpace = new float[4];
    private final float[] mLightPosInEyeSpace = new float[4];
    private float[] mLightModelMatrix = new float[16];

    private final float[] mLightPosInModelSpace = new float[]{0.0f, -12.0f, 0.0f, 70f};


    //---EDIT SECTION

    //Objects
    GameResourceBinder resourceBinder;

    //Variables
    private float ratio;

    private Context gameActivityContext;
    private boolean blending=false;
    //---EDIT ENDS

    public MyGLRenderer(Context gameActivityContext)
    {
        //Reset arrays
        this.resourceBinder=new GameResourceBinder();
        this.gameActivityContext=gameActivityContext;

    }


    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig config) {
        GLES20.glClearColor(0.5f, 0.5f, 0.5f, 1.0f);
        GLES20.glDisable(GLES20.GL_CULL_FACE);
        glDepthFunc(GL_NEVER);
        GLES20.glDisable(GLES20.GL_DEPTH_TEST);
        switchMode();

        //Create objects
        new TriangleObjectCreator(gameActivityContext);

        triangleShader=new TriangleShaderCreator();
        mVertexBufferTriangle=triangleShader.getVertexBufferTriangle();
        mTriangleNormals=triangleShader.getmTriangleNormals();
        mTriangleColors=triangleShader.getmTriangleColors();
        mTriangleTextureCoordinates=triangleShader.getmTriangleTextureCoordinates();

        mProgramHandle = triangleShader.getmProgramHandle();
        mLightHandle = triangleShader.getmLightHandle();


        for(Triangle triangleObject: arrayOfTriangles) {
            for(Integer textureId: triangleObject.getTextureIdArray()) {
                Vector<BitmapTextHolder> tempBitmapArrayOfTextHolders=triangleObject.getBitmapArrayOfTextHolders();
                if(!(tempBitmapArrayOfTextHolders.isEmpty())) {
                    for(int i=0;i<tempBitmapArrayOfTextHolders.size();i++) {
                        triangleObject.getmTextureDataHandleArray().add(TextureLoader.loadTexture(tempBitmapArrayOfTextHolders.get(i)));
                        GLES20.glGenerateMipmap(GLES20.GL_TEXTURE_2D);
                    }
                    break;
                }
                else
                {
                    triangleObject.getmTextureDataHandleArray().add(TextureLoader.loadTexture(gameActivityContext, textureId,false));
                    GLES20.glGenerateMipmap(GLES20.GL_TEXTURE_2D);

                    if(triangleObject.isPossibleToFlip()==true)
                    {
                        triangleObject.getmTextureDataHandleArray().add(TextureLoader.loadTexture(gameActivityContext, textureId,true));
                        GLES20.glGenerateMipmap(GLES20.GL_TEXTURE_2D);
                    }
                }
            }
        }

        setMagFilter();
        setMinFilter();
        gameInLoadingState=false;
        gameLoaded=true;

    }


    @Override
    public void onSurfaceChanged(GL10 gl, int width, int height) {
        GLES20.glViewport(0, 0, width, height);
        ratio = (float) width / height;

        screenWidth=width;
        screenHeight=height;
        screenRatio=ratio;
        final float left = -ratio;
        final float right = ratio;
        final float bottom = -1.0f;
        final float top = 1.0f;
        final float near = 1.0f;
        final float far = 22.0f;
        Matrix.frustumM(mProjectionMatrix, 0, left, right, bottom, top, near, far);
    }

    @Override
    public void onDrawFrame(GL10 gl) {
        GLES20.glClear(GLES20.GL_DEPTH_BUFFER_BIT | GLES20.GL_COLOR_BUFFER_BIT);

        Matrix.setLookAtM(mViewMatrix, 0, eyeX, eyeY, eyeZ, lookX, lookY, lookZ, upX, upY, upZ);

        positionHandle = GLES20.glGetAttribLocation(mProgramHandle, "a_Position");
        mPMatrixHandle = GLES20.glGetUniformLocation(mProgramHandle, "uMVPMatrix");
        mvMatrixHandle = GLES20.glGetUniformLocation(mProgramHandle, "uMVMatrix");
        mLightPosHandle = GLES20.glGetUniformLocation(mProgramHandle, "u_LightPos");
        mNormalHandle = GLES20.glGetAttribLocation(mProgramHandle, "a_Normal");
        mColorHandle = GLES20.glGetAttribLocation(mProgramHandle, "a_Color");
        mTextureUniformHandle=GLES20.glGetUniformLocation(mProgramHandle,"u_Texture");
        mTextureCoordinateHandle=GLES20.glGetAttribLocation(mProgramHandle,"a_TexCoordinate");
        GLES20.glUseProgram(mProgramHandle);

        Matrix.setIdentityM(mLightModelMatrix, 0);
        Matrix.translateM(mLightModelMatrix, 0, 0.0f, 0.0f, -5.0f);
        //Rotate
        Matrix.rotateM(mLightModelMatrix, 0, 0, 0.0f, 1.0f, 0.0f);
        Matrix.translateM(mLightModelMatrix, 0, 0.0f, 0.0f, 1.0f);


        Matrix.multiplyMV(mLightPosInWorldSpace, 0, mLightModelMatrix, 0, mLightPosInModelSpace, 0);
        Matrix.multiplyMV(mLightPosInEyeSpace, 0, mViewMatrix, 0, mLightPosInWorldSpace, 0);


        drawElements();

        GLES20.glUseProgram(mLightHandle);
        drawLight();
    }

    private void drawElements()
    {
        GLES20.glActiveTexture(GLES20.GL_TEXTURE);
        int i=0;
        int idOfPlayerTriangle=arrayOfPlayers.get(0).getObjectId();
        int idOfPlayerNoMobTriangle=arrayOfPlayerNoMobs.get(0).getObjectId();
        for(Triangle triangleObject: arrayOfTriangles) {
            if(idOfPlayerTriangle!=i&&idOfPlayerNoMobTriangle!=i) {
                if (triangleObject.isVisible() == true) {
                    int actualTextureSet = triangleObject.getActualTextureSet();
                    GLES20.glBindTexture(GLES20.GL_TEXTURE_2D, triangleObject.getmTextureDataHandleArray().get(actualTextureSet));
                    GLES20.glUniform1i(mTextureUniformHandle, 0);

                    float[] arrayOfTranslations = triangleObject.getArrayOfTranslations();
                    float xTranslate = arrayOfTranslations[0];
                    float yTranslate = arrayOfTranslations[1];
                    float zTranslate = arrayOfTranslations[2];

                    float[] arrayOfRotations = triangleObject.getArrayOfRotations();
                    float aRotation = arrayOfRotations[0];
                    float xRotation = arrayOfRotations[1];
                    float yRotation = arrayOfRotations[2];
                    float zRotation = arrayOfRotations[3];

                    float xScaleFactor = screenRatio;
                    float yScaleFactor = screenRatio;

                    float widthOfObjectInPoints = 2.0f;
                    float heightOfObjectInPoints = 2.0f;

                    float z = ((-1 * zTranslate) + 1);
                    Matrix.setIdentityM(mModelMatrix, 0);

                    displayScale = 0;

                    float objectScaleFactor = triangleObject.getObjectsScaleFactor();
                    Matrix.scaleM(mModelMatrix, 0, xScaleFactor, yScaleFactor, 1);
                    Matrix.scaleM(mModelMatrix, 0, objectScaleFactor / 2.0f, objectScaleFactor / 2.0f, 1);

                    Matrix.translateM(mModelMatrix, 0, (-screenRatio * z) / xScaleFactor + (widthOfObjectInPoints * z / 2) + xTranslate * z, (-1) * (-1.0f * z + (heightOfObjectInPoints * z / 2)) / yScaleFactor + z * yTranslate, zTranslate + displayScale);
                    Matrix.rotateM(mModelMatrix, 0, aRotation, xRotation, yRotation, zRotation + 1.0f);

                    draw();
                }
            }
            i++;
        }
        drawPlayer(arrayOfTriangles.get(idOfPlayerTriangle));
        drawPlayer(arrayOfTriangles.get(idOfPlayerNoMobTriangle));
    }

    private void drawPlayer(Triangle triangleObject)
    {
        if (triangleObject.isVisible() == true) {
            int actualTextureSet = triangleObject.getActualTextureSet();
            GLES20.glBindTexture(GLES20.GL_TEXTURE_2D, triangleObject.getmTextureDataHandleArray().get(actualTextureSet));
            GLES20.glUniform1i(mTextureUniformHandle, 0);

            float[] arrayOfTranslations = triangleObject.getArrayOfTranslations();
            float xTranslate = arrayOfTranslations[0];
            float yTranslate = arrayOfTranslations[1];
            float zTranslate = arrayOfTranslations[2];

            float[] arrayOfRotations = triangleObject.getArrayOfRotations();
            float aRotation = arrayOfRotations[0];
            float xRotation = arrayOfRotations[1];
            float yRotation = arrayOfRotations[2];
            float zRotation = arrayOfRotations[3];

            float xScaleFactor = screenRatio;
            float yScaleFactor = screenRatio;

            float widthOfObjectInPoints = 2.0f;
            float heightOfObjectInPoints = 2.0f;

            float z = ((-1 * zTranslate) + 1);
            Matrix.setIdentityM(mModelMatrix, 0);

            displayScale = 0.0f;

            float objectScaleFactor = triangleObject.getObjectsScaleFactor();
            Matrix.scaleM(mModelMatrix, 0, xScaleFactor, yScaleFactor, 1);
            Matrix.scaleM(mModelMatrix, 0, objectScaleFactor / 2.0f, objectScaleFactor / 2.0f, 1);

            Matrix.translateM(mModelMatrix, 0, (-screenRatio * z) / xScaleFactor + (widthOfObjectInPoints * z / 2) + xTranslate * z, (-1) * (-1.0f * z + (heightOfObjectInPoints * z / 2)) / yScaleFactor + z * yTranslate, zTranslate + displayScale);
            Matrix.rotateM(mModelMatrix, 0, aRotation, xRotation, yRotation, zRotation + 1.0f);

            draw();
        }
    }


    final int numberOfVertices = 6;
    final int COORDS_PER_VERTEX = 3;
    final int mColorDataSize = 4;
    final int mTextureCoordinateDataSize = 2;

    public void draw(){

        //-------------
        mVertexBufferTriangle.position(0);
        GLES20.glVertexAttribPointer(positionHandle,COORDS_PER_VERTEX,GLES20.GL_FLOAT,false,0,mVertexBufferTriangle);
        GLES20.glEnableVertexAttribArray(positionHandle);

        //------------
        mTriangleColors.position(0);
        GLES20.glVertexAttribPointer(mColorHandle, mColorDataSize, GLES20.GL_FLOAT, false,
                0, mTriangleColors);
        GLES20.glEnableVertexAttribArray(mColorHandle);

        //------------
        mTriangleNormals.position(0);
        GLES20.glVertexAttribPointer(mNormalHandle, 3, GLES20.GL_FLOAT, false,
                0,mTriangleNormals);
        GLES20.glEnableVertexAttribArray(mNormalHandle);

        //-----------
        mTriangleTextureCoordinates.position(0);
        GLES20.glVertexAttribPointer(mTextureCoordinateHandle,mTextureCoordinateDataSize,GLES20.GL_FLOAT,
                false,0,mTriangleTextureCoordinates);

        GLES20.glEnableVertexAttribArray(mTextureCoordinateHandle);




        Matrix.multiplyMM(vPMatrix,0,mViewMatrix,0,mModelMatrix,0);


        GLES20.glUniformMatrix4fv(mvMatrixHandle,1,false,vPMatrix,0);


        Matrix.multiplyMM(vPMatrix, 0, mProjectionMatrix, 0, vPMatrix, 0);

        GLES20.glUniformMatrix4fv(mPMatrixHandle,1,false,vPMatrix,0);

        GLES20.glUniform3f(mLightPosHandle,mLightPosInEyeSpace[0],mLightPosInEyeSpace[1],mLightPosInEyeSpace[2]);

        GLES20.glDrawArrays(GLES20.GL_TRIANGLES,0,numberOfVertices);

    }

    public void drawLight()
    {

        final int pointMVPMatrixHandle=GLES20.glGetUniformLocation(mProgramHandle,"uMVPMatrix");
        final int pointPositionHandle=GLES20.glGetAttribLocation(mLightHandle,"a_Position");

        GLES20.glVertexAttrib3f(pointPositionHandle,mLightPosInModelSpace[0],mLightPosInModelSpace[1],mLightPosInModelSpace[2]);
        GLES20.glDisableVertexAttribArray(pointPositionHandle);

        Matrix.multiplyMM(vPMatrix,0,mViewMatrix,0,mLightModelMatrix,0);
        Matrix.multiplyMV(vPMatrix,0,mProjectionMatrix,0,vPMatrix,0);
        GLES20.glUniformMatrix4fv(pointMVPMatrixHandle,1,false,vPMatrix,0);
        GLES20.glDrawArrays(GLES20.GL_POINTS,0,1);
    }

    public  void switchMode()
    {
        blending=!blending;
        if(blending)
        {
            GLES20.glEnable(GLES20.GL_BLEND);
            GLES20.glBlendFunc(GLES20.GL_SRC_ALPHA,GL_ONE_MINUS_SRC_ALPHA);

        }
        else
        {
            GLES20.glDisable(GLES20.GL_BLEND);
        }
    }

    public void setMinFilter() {
        for(Triangle triangleObject: arrayOfTriangles)
        {
            for(int tempTextureHandle: triangleObject.getmTextureDataHandleArray()) {
                final int mTextureDataHandle = tempTextureHandle;
                if (mTextureDataHandle != 0) {
                    GLES20.glBindTexture(GLES20.GL_TEXTURE_2D, mTextureDataHandle);
                    GLES20.glTexParameteri(GLES20.GL_TEXTURE_2D, GLES20.GL_TEXTURE_MIN_FILTER, GLES20.GL_LINEAR_MIPMAP_LINEAR);

                }
            }
        }

    }

    public void setMagFilter() {
        int x=0;
        for(Triangle triangleObject: arrayOfTriangles)
        {
            boolean isException=false;
            for(int i = 0; i< arrayOfFilteringExceptions.size(); i++) {
                if(x== arrayOfFilteringExceptions.get(i))
                {
                    isException=true;
                    break;
                }
            }

            if(isException==true) {
                for (int tempTextureHandle : triangleObject.getmTextureDataHandleArray()) {
                    final int mTextureDataHandle = tempTextureHandle;

                    if (mTextureDataHandle != 0) {
                        GLES20.glBindTexture(GLES20.GL_TEXTURE_2D, mTextureDataHandle);
                        GLES20.glTexParameteri(GLES20.GL_TEXTURE_2D, GLES20.GL_TEXTURE_MAG_FILTER, GLES20.GL_NEAREST);
                    }
                }
            }
            else
            {
                for (int tempTextureHandle : triangleObject.getmTextureDataHandleArray()) {
                    final int mTextureDataHandle = tempTextureHandle;

                    if (mTextureDataHandle != 0) {
                        GLES20.glBindTexture(GLES20.GL_TEXTURE_2D, mTextureDataHandle);
                        GLES20.glTexParameteri(GLES20.GL_TEXTURE_2D, GLES20.GL_TEXTURE_MAG_FILTER, GLES20.GL_LINEAR);
                    }
                }
            }
            x++;
        }
    }




}

