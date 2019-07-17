package com.example.game.Game;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Typeface;
import android.opengl.GLES20;
import android.opengl.GLUtils;
import android.text.TextPaint;

import java.util.Vector;



public class TextureLoader {
    public static int loadTexture(final Context context, final int resourceId,final boolean isTextureFlipped)
    {
        final int[] textureHandle = new int[1];

        GLES20.glGenTextures(1, textureHandle, 0);

        if (textureHandle[0] != 0)
        {
            final BitmapFactory.Options options = new BitmapFactory.Options();
            options.inScaled = false;
            Matrix matrix=new Matrix();
            if(isTextureFlipped==true) {
                matrix.preScale(-1f, 1f);
            }
            else
            {
                matrix.preScale(1f, 1f);
            }

            final Bitmap bitmapSource = BitmapFactory.decodeResource(context.getResources(), resourceId, options);
            final Bitmap bitmap= Bitmap.createBitmap(bitmapSource, 0, 0, bitmapSource.getWidth(), bitmapSource.getHeight(), matrix, true);

            GLES20.glBindTexture(GLES20.GL_TEXTURE_2D, textureHandle[0]);

            GLES20.glTexParameteri(GLES20.GL_TEXTURE_2D, GLES20.GL_TEXTURE_MIN_FILTER, GLES20.GL_NEAREST);
            GLES20.glTexParameteri(GLES20.GL_TEXTURE_2D, GLES20.GL_TEXTURE_MAG_FILTER, GLES20.GL_NEAREST);

            GLUtils.texImage2D(GLES20.GL_TEXTURE_2D, 0, bitmap, 0);

            bitmapSource.recycle();
            bitmap.recycle();



        }

        if (textureHandle[0] == 0)
        {
            throw new RuntimeException("Error gameInLoadingState texture.");
        }

        return textureHandle[0];
    }

    public static int loadTexture(final BitmapTextHolder bitmapTextHolder)
    {
        final int[] textureHandle = new int[1];

        GLES20.glGenTextures(1, textureHandle, 0);

        if (textureHandle[0] != 0)
        {
            final BitmapFactory.Options options = new BitmapFactory.Options();
            options.inScaled = false;

            final Bitmap bitmap = createBitmapText(bitmapTextHolder.getText(),bitmapTextHolder.getTextSize(),bitmapTextHolder.getTypeOfFont(),bitmapTextHolder.getFontColor(),bitmapTextHolder.getBitmapSize());

            GLES20.glBindTexture(GLES20.GL_TEXTURE_2D, textureHandle[0]);

            GLES20.glTexParameteri(GLES20.GL_TEXTURE_2D, GLES20.GL_TEXTURE_MIN_FILTER, GLES20.GL_NEAREST);
            GLES20.glTexParameteri(GLES20.GL_TEXTURE_2D, GLES20.GL_TEXTURE_MAG_FILTER, GLES20.GL_NEAREST);

            GLUtils.texImage2D(GLES20.GL_TEXTURE_2D, 0, bitmap, 0);

            bitmap.recycle();

        }

        if (textureHandle[0] == 0)
        {
            throw new RuntimeException("Error gameInLoadingState texture.");
        }

        return textureHandle[0];
    }

    private static Bitmap createBitmapText(final String text, final int textSize, final Typeface typeOfFont, final Vector<Integer> fontColor, final int bitmapSize)
    {
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inScaled = false;

        final Bitmap bitmap=Bitmap.createBitmap(bitmapSize,bitmapSize,Bitmap.Config.ARGB_4444);

        final Canvas canvas=new Canvas(bitmap);

        bitmap.eraseColor(0);


        final TextPaint textPainter=new TextPaint();
        textPainter.setTextSize(textSize);
        textPainter.setAntiAlias(true);

        textPainter.setTypeface(typeOfFont);

        textPainter.setARGB(fontColor.get(3),fontColor.get(0),fontColor.get(1),fontColor.get(2));
        canvas.drawText(text,0,textSize,textPainter);


        return bitmap;


    }
}
