package com.example.game.Game;

import android.opengl.GLES20;


import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import static com.example.game.Game.ShaderBinder.compileShader;
import static com.example.game.Game.ShaderBinder.createAndLinkProgram;
import static com.example.game.Game.ShaderBinder.fragmentShaderCode;
import static com.example.game.Game.ShaderBinder.pointFragmentShader;
import static com.example.game.Game.ShaderBinder.pointVertexShader;
import static com.example.game.Game.ShaderBinder.vertexShaderCode;

public class TriangleShaderCreator {
    private int mProgramHandle;
    private int mLightHandle;


    private FloatBuffer vertexBufferTriangle;
    private FloatBuffer mTriangleNormals;
    private FloatBuffer mTriangleColors;
    private FloatBuffer mTriangleTextureCoordinates;

    private final int mBytesPerFloat=4;


    private float zCord=-1.0f;
    private float zCordNormal=-45.0f;

    final float triangleData[] = {
            -1.0f, 1f, zCord,
            -1.0f, -1f, zCord,
            1.0f, -1f, zCord,
            1.0f, -1f, zCord,
            1.0f, 1f, zCord,
            -1.0f, 1f, zCord};


    final float[] triangleNormalData = {
            0.0f, 0.0f, zCordNormal,
            0.0f, 0.0f, zCordNormal,
            0.0f, 0.0f, zCordNormal,
            0.0f, 0.0f, zCordNormal,
            0.0f, 0.0f, zCordNormal,
            0.0f, 0.0f, zCordNormal

    };

    final float[] triangleColorData =
            {
                    0.5f, 0.5f, 0.5f, 1.0f,
                    0.5f, 0.5f, 0.5f, 1.0f,
                    0.5f, 0.5f, 0.5f, 1.0f,
                    0.5f, 0.5f, 0.5f, 1.0f,
                    0.5f, 0.5f, 0.5f, 1.0f,
                    0.5f, 0.5f, 0.5f, 1.0f
            };


    final float[] triangleTextureCoordinateData=
            {
                    0.0f,0.0f,
                    0.0f,1.0f,
                    1.0f,1.0f,
                    1.0f,1.0f,
                    1.0f,0.0f,
                    0.0f,0.0f
            };



    public TriangleShaderCreator()
    {
        ByteBuffer bb=ByteBuffer.allocateDirect(triangleData.length*mBytesPerFloat);
        bb.order(ByteOrder.nativeOrder());
        vertexBufferTriangle=bb.asFloatBuffer();

        vertexBufferTriangle.put(triangleData);
        vertexBufferTriangle.position(0);

        ByteBuffer bb2 = ByteBuffer.allocateDirect(triangleColorData.length * mBytesPerFloat);
        bb2.order(ByteOrder.nativeOrder());
        mTriangleColors=bb2.asFloatBuffer();

        mTriangleColors.put(triangleColorData);
        mTriangleColors.position(0);

        ByteBuffer bb3 = ByteBuffer.allocateDirect(triangleNormalData.length * mBytesPerFloat);
        bb3.order(ByteOrder.nativeOrder());
        mTriangleNormals=bb3.asFloatBuffer();

        mTriangleNormals.put(triangleNormalData);
        mTriangleNormals.position(0);

        ByteBuffer bb4 = ByteBuffer.allocateDirect(triangleTextureCoordinateData.length * mBytesPerFloat);
        bb4.order(ByteOrder.nativeOrder());
        mTriangleTextureCoordinates=bb4.asFloatBuffer();

        mTriangleTextureCoordinates.put(triangleTextureCoordinateData);
        mTriangleTextureCoordinates.position(0);



        int vertexShaderHandle=compileShader(GLES20.GL_VERTEX_SHADER,vertexShaderCode);
        int fragmentShaderHandle=compileShader(GLES20.GL_FRAGMENT_SHADER,fragmentShaderCode);

        int pointVertexShaderHandle=compileShader(GLES20.GL_VERTEX_SHADER,pointVertexShader);
        int pointFragmentShaderHandle=compileShader(GLES20.GL_FRAGMENT_SHADER,pointFragmentShader);

        mProgramHandle=createAndLinkProgram(vertexShaderHandle,fragmentShaderHandle,new String[]{"a_Position","a_Color","a_Normal","a_TexCoordinate"});
        mLightHandle=createAndLinkProgram(pointVertexShaderHandle,pointFragmentShaderHandle,new String[]{"a_Position"});

    }


    public FloatBuffer getVertexBufferTriangle() {
        return vertexBufferTriangle;
    }

    public FloatBuffer getmTriangleNormals() {
        return mTriangleNormals;
    }

    public FloatBuffer getmTriangleColors() {
        return mTriangleColors;
    }

    public FloatBuffer getmTriangleTextureCoordinates() {
        return mTriangleTextureCoordinates;
    }

    public int getmProgramHandle() {
        return mProgramHandle;
    }

    public int getmLightHandle() {
        return mLightHandle;
    }



}
