package com.example.game.Game;

import java.util.Vector;

public class Triangle {

    private float[] arrayOfTranslations;
    private float[] arrayOfRotations;
    private boolean isPossibleToFlip;
    private boolean isTextureFlipped;
    private float objectsScaleFactor;
    private Vector<Integer> textureIdArray;
    private int actualTextureSet;
    private Vector<Integer> mTextureDataHandleArray;

    private boolean isVisible;
    private boolean isPossibleCollision;
    private Vector<BitmapTextHolder> bitmapArrayOfTextHolders;


    public Triangle(float[] arrayOfTranslations,float[] arrayOfRotations,float objectsScaleFactor,Vector<Integer> textureIdArray){

        this.arrayOfTranslations=arrayOfTranslations;
        this.arrayOfRotations=arrayOfRotations;
        this.isPossibleToFlip=false;
        this.isTextureFlipped=false;
        this.textureIdArray=textureIdArray;
        this.objectsScaleFactor=objectsScaleFactor;
        this.mTextureDataHandleArray=new Vector<>();
        this.actualTextureSet=0;
        this.bitmapArrayOfTextHolders=new Vector<>();

        this.isVisible=true;
        this.isPossibleCollision=false;


    }

    public float getSizeOfObjectOnZ()
    {
        return(1.0f/((arrayOfTranslations[2]*(-1)+1)/2.0f));
    }

    public float[] getArrayOfTranslations() {
        return arrayOfTranslations;
    }


    public float[] getArrayOfRotations() {
        return arrayOfRotations;
    }

    public boolean isTextureFlipped() {
        return isTextureFlipped;
    }



    public boolean isPossibleToFlip() {
        return isPossibleToFlip;
    }

    public void setPossibleToFlip(boolean possibleToFlip) {
        isPossibleToFlip = possibleToFlip;
    }

    public void setTextureFlipped(boolean textureFlipped) {

        if(this.isPossibleToFlip()==true) {
            isTextureFlipped = textureFlipped;
        }
    }

    public Vector<Integer> getTextureIdArray() {
        return textureIdArray;
    }

    public Vector<Integer> getmTextureDataHandleArray() {
        return mTextureDataHandleArray;
    }

    public float getObjectsScaleFactor() {
        return objectsScaleFactor;
    }

    public int getActualTextureSet() {
        return actualTextureSet;
    }

    public void setActualTextureSet(int actualTextureSet) {
        this.actualTextureSet = actualTextureSet;
    }

    public boolean isVisible() {
        return isVisible;
    }

    public void setVisible(boolean visible) {
        isVisible = visible;
    }

    public boolean isPossibleCollision() {
        return isPossibleCollision;
    }

    public void setPossibleCollision(boolean possibleCollision) {
        isPossibleCollision = possibleCollision;
    }

    public Vector<BitmapTextHolder> getBitmapArrayOfTextHolders() {
        return bitmapArrayOfTextHolders;
    }


}
