package com.example.game.Game;

import android.graphics.Typeface;

import java.util.Vector;

public class BitmapTextHolder {
    final private String text;
    final private int textSize;
    final private Typeface typeOfFont;
    final private int bitmapSize;
    final private Vector<Integer> fontColor;
    public BitmapTextHolder(final String text,final int textSize,final Typeface typeOfFont,final Vector<Integer> fontColor,final int bitmapSize)
    {
        this.text=text;
        this.textSize=textSize;
        this.typeOfFont=typeOfFont;
        this.bitmapSize=bitmapSize;
        this.fontColor=fontColor;

    }

    public String getText() {
        return text;
    }

    public int getTextSize() {
        return textSize;
    }

    public Typeface getTypeOfFont() {
        return typeOfFont;
    }

    public Vector<Integer> getFontColor() {
        return fontColor;
    }

    public int getBitmapSize() {
        return bitmapSize;
    }

}
