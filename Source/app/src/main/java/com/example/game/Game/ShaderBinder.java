package com.example.game.Game;

import android.opengl.GLES20;

public final class ShaderBinder {

    final static String vertexShaderCode =
            "uniform mat4 uMVPMatrix;"
                    + "uniform mat4 uMVMatrix;"
                    + "attribute vec4 a_Position;"
                    + "attribute vec4 a_Color;"
                    + "attribute vec3 a_Normal;"
                    + "attribute vec2 a_TexCoordinate;"
                    + "varying vec3 v_Position;"
                    + "varying vec4 v_Color;"
                    + "varying vec3 v_Normal;"
                    + "varying vec2 v_TexCoordinate;"
                    + "void main()"
                    + "{"
                    + "v_TexCoordinate=a_TexCoordinate;"
                    + "v_Position = vec3(uMVMatrix*a_Position);"
                    + "v_Color = a_Color;"
                    + "v_Normal = vec3(uMVMatrix*vec4(a_Normal,0.0));"
                    + "gl_Position = uMVPMatrix* a_Position;"
                    + "}";

    final static String fragmentShaderCode =
            "precision mediump float;"
                    + "uniform vec3 u_LightPos;"
                    + "uniform sampler2D u_Texture;"
                    + "varying vec3 v_Position;"
                    + "varying vec4 v_Color;"
                    + "varying vec3 v_Normal;"
                    + "varying vec2 v_TexCoordinate;"
                    + "void main()"
                    + "{"
                    + "float distance=length(u_LightPos-v_Position);"
                    + "vec3 lightVector=normalize(u_LightPos-v_Position);"
                    + "float diffuse=max(dot(v_Normal,lightVector),0.0);"
                    + "diffuse=diffuse*(1.0/(1.0+(0.10*distance)));"
                    + "diffuse=diffuse+0.3;"
                    + "gl_FragColor = v_Color*(diffuse+0.3/2.0)*texture2D(u_Texture,v_TexCoordinate);"
                    + "}";

    final static String pointVertexShader=
            "uniform mat4 uMVPMatrix;"
                    +"attribute vec4 a_Position;"
                    +"void main()"
                    +"{"
                    +"gl_Position=uMVPMatrix*a_Position;"
                    +"gl_PointSize=0.0;"
                    +"}";

    final static String pointFragmentShader=
            "precision mediump float;"
                    +"void main()"
                    +"{"
                    +"gl_FragColor=vec4(1.0,1.0,1.0,1.0);"
                    +"}";



    final static int createAndLinkProgram(final int vertexShaderHandle,final int fragmentShaderHandle,final String[] attributes)
    {
        int programHandle= GLES20.glCreateProgram();
        if(programHandle!=0)
        {
            GLES20.glAttachShader(programHandle,vertexShaderHandle);
            GLES20.glAttachShader(programHandle,fragmentShaderHandle);

            if(attributes!=null)
            {
                final int size=attributes.length;
                for(int i=0;i<size;i++)
                {
                    GLES20.glBindAttribLocation(programHandle,i,attributes[i]);

                }
            }

            GLES20.glLinkProgram(programHandle);

            final int[] linkStatus=new int[1];
            GLES20.glGetProgramiv(programHandle,GLES20.GL_LINK_STATUS,linkStatus,0);
            if(linkStatus[0]==0)
            {
                GLES20.glDeleteProgram(programHandle);
                programHandle=0;
            }
        }
        if(programHandle==0)
        {
            throw new RuntimeException("Creating program Error");
        }
        return programHandle;
    }

    final static int compileShader(final int shaderType,final String shaderSource)
    {
        int shaderHandle=GLES20.glCreateShader(shaderType);
        if(shaderHandle!=0)
        {
            GLES20.glShaderSource(shaderHandle,shaderSource);
            GLES20.glCompileShader(shaderHandle);

            final int[] compileStatus=new int[1];
            GLES20.glGetShaderiv(shaderHandle,GLES20.GL_COMPILE_STATUS,compileStatus,0);

            if(compileStatus[0]==0)
            {
                GLES20.glDeleteShader(shaderHandle);
                shaderHandle=0;
            }
        }
        if(shaderHandle==0)
        {
            throw new RuntimeException("Creating shader error");
        }
        return shaderHandle;
    }

}
