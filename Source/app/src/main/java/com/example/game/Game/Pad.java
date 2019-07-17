package com.example.game.Game;

public class Pad extends GameObject implements TouchAvailability {

    public Pad(int objectId)
    {
        super(objectId);
    }

    private float centerPadx=-0.75714284f;
    private float centerPady=-0.30714285f;
    private float padMoveZone=0.07f;
    private float padRestrictionZone=0.07f;
    private float padRadius=0.01f;

public void moveBall(float[]arrayOfTranslations,float xInPoints,float yInPoints) {

    if (yInPoints - centerPady < Math.sqrt(padRadius - Math.pow(xInPoints - centerPadx, 2)) && yInPoints - centerPady > -(Math.sqrt(padRadius - Math.pow(xInPoints - centerPadx, 2)))) {



            arrayOfTranslations[1] = yInPoints;
            arrayOfTranslations[0] = xInPoints;
            arrayOfTranslations[1] = yInPoints;//I don't know why but it works

        }

     else if(yInPoints - centerPady > Math.sqrt(padRadius - Math.pow(xInPoints - centerPadx, 2)) && yInPoints - centerPady < Math.sqrt(padRestrictionZone - Math.pow(xInPoints - centerPadx, 2))&&xInPoints<centerPadx+padMoveZone&&xInPoints>centerPadx-padMoveZone)
     {

             arrayOfTranslations[1] = centerPady+(float)Math.sqrt(padRadius - Math.pow(xInPoints - centerPadx, 2));
            arrayOfTranslations[0] =xInPoints;
             arrayOfTranslations[1] =centerPady+(float)Math.sqrt(padRadius - Math.pow(xInPoints - centerPadx, 2));

    }

    else if(yInPoints - centerPady < -(Math.sqrt(padRadius - Math.pow(xInPoints - centerPadx, 2)))&&yInPoints - centerPady > -(Math.sqrt(padRestrictionZone - Math.pow(xInPoints - centerPadx, 2)))&&xInPoints<centerPadx+padMoveZone&&xInPoints>centerPadx-padMoveZone)
    {


        arrayOfTranslations[1] =  centerPady-((float)Math.sqrt(padRadius - Math.pow(xInPoints - centerPadx, 2)));
        arrayOfTranslations[0] =xInPoints;
        arrayOfTranslations[1] = centerPady-((float)Math.sqrt(padRadius - Math.pow(xInPoints - centerPadx, 2)));

    }

    else if(xInPoints - centerPadx > Math.sqrt(padRadius - Math.pow(yInPoints - centerPady, 2)) && xInPoints - centerPadx < Math.sqrt(padRestrictionZone - Math.pow(yInPoints - centerPady, 2))&&yInPoints<centerPady+padMoveZone&&yInPoints>centerPady-padMoveZone)
    {


        arrayOfTranslations[1] = yInPoints;
        arrayOfTranslations[0] =centerPadx+(float)Math.sqrt(padRadius - Math.pow(yInPoints- centerPady, 2));
        arrayOfTranslations[1] =yInPoints;

    }

    else if(xInPoints - centerPadx <-( Math.sqrt(padRadius - Math.pow(yInPoints - centerPady, 2)) )&& xInPoints - centerPadx > -(Math.sqrt(padRestrictionZone - Math.pow(yInPoints - centerPady, 2)))&&yInPoints<centerPady+padMoveZone&&yInPoints>centerPady-padMoveZone)
    {


        arrayOfTranslations[1] = yInPoints;
        arrayOfTranslations[0] =centerPadx-((float)Math.sqrt(padRadius - Math.pow(yInPoints- centerPady, 2)));
        arrayOfTranslations[1] =yInPoints;

    }

    }



    @Override
    public void onTouchAction() {
    }

    public float getCenterPady() {
        return centerPady;
    }
    public float getPadMoveZone() {
        return padMoveZone;
    }
    public float getCenterPadx() {
        return centerPadx;
    }


}


