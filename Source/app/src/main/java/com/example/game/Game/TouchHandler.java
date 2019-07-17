package com.example.game.Game;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import static android.app.Activity.RESULT_OK;
import static com.example.game.Game.GameResourceBinder.arrayOfMobs;
import static com.example.game.Game.GameResourceBinder.arrayOfPads;
import static com.example.game.Game.GameResourceBinder.arrayOfPlayers;
import static com.example.game.Game.GameResourceBinder.arrayOfTriangles;
import static com.example.game.Game.GameResourceBinder.arrayOfUIButtons;


public class TouchHandler {
    public TouchHandler(Context context,float xInPoints, float yInPoints, boolean movable, boolean buttonInputUp)
    {
        if(movable==true) {
            if (buttonInputUp==false) {
                if (checkForPads(xInPoints, yInPoints) == true) {

                }
            }
                else if(buttonInputUp==true){
                    if (checkForPadsUp() == true) {

                    }
                }

        }
        else if(checkForMobs(xInPoints,yInPoints)==true)
        {

        }
        else if(checkForPlayers(xInPoints,yInPoints)==true)
        {

        }
        else if(checkForUIButtons(context,xInPoints,yInPoints)==true)
        {

        }
    }

    private boolean checkForPads(float xInPoints, float yInPoints) {
        Pad pad1 = arrayOfPads.get(0);

        Triangle tempPadInTriangleArray = arrayOfTriangles.get(pad1.getObjectId());

        padForPlayerValue(tempPadInTriangleArray,xInPoints,yInPoints,pad1);
        return true;

    }

    void padForPlayerValue(Triangle tempPadInTriangleArray ,float xInPoints, float yInPoints,Pad pad){

        Player player1 = arrayOfPlayers.get(0);
        if(player1.isPlayerMoving()==false) {
            float[] arrayOfTranslationsPads = tempPadInTriangleArray.getArrayOfTranslations();
            float centerPadx = pad.getCenterPadx();
            float centerPady = pad.getCenterPady();
            float padMoveZone = pad.getPadMoveZone();
            pad.moveBall(arrayOfTranslationsPads, xInPoints, yInPoints);


            if (arrayOfTranslationsPads[0] > centerPadx + padMoveZone / 2.0f) {
                if(arrayOfTriangles.get(player1.getObjectId()).isTextureFlipped()==true)
                {
                    arrayOfTriangles.get(player1.getObjectId()).setActualTextureSet(0);
                }
                arrayOfTriangles.get(player1.getObjectId()).setTextureFlipped(false);
                player1.setObjectMovementSpeed(0.0003f);
                player1.setPlayerMoving(true);


            } else if (arrayOfTranslationsPads[0] > centerPadx && arrayOfTranslationsPads[0] < centerPadx + padMoveZone / 2.0f) {
                if(arrayOfTriangles.get(player1.getObjectId()).isTextureFlipped()==true)
                {
                    arrayOfTriangles.get(player1.getObjectId()).setActualTextureSet(0);
                }
                arrayOfTriangles.get(player1.getObjectId()).setTextureFlipped(false);
                player1.setObjectMovementSpeed(0.0002f);
                player1.setPlayerMoving(true);

            } else if (arrayOfTranslationsPads[0] < centerPadx - padMoveZone / 2.0f) {
                if(arrayOfTriangles.get(player1.getObjectId()).isTextureFlipped()==false)
                {
                    arrayOfTriangles.get(player1.getObjectId()).setActualTextureSet(1);
                }
                arrayOfTriangles.get(player1.getObjectId()).setTextureFlipped(true);
                player1.setObjectMovementSpeed((-1.0f)* 0.0003f);
                player1.setPlayerMoving(true);
            } else if (arrayOfTranslationsPads[0] < centerPadx && arrayOfTranslationsPads[0] > centerPadx - padMoveZone / 2.0f) {
                if(arrayOfTriangles.get(player1.getObjectId()).isTextureFlipped()==false)
                {
                    arrayOfTriangles.get(player1.getObjectId()).setActualTextureSet(1);
                }
                arrayOfTriangles.get(player1.getObjectId()).setTextureFlipped(true);
                player1.setObjectMovementSpeed((-1.0f)* 0.0002f);
                player1.setPlayerMoving(true);

            }

            if (arrayOfTranslationsPads[1] > centerPady + padMoveZone / 2.0f) {
                if(player1.isInAir()==false) {
                    player1.setObjectJumpSpeed(0.03f);
                    player1.setPlayerMoving(true);
                }

            }

        }


    }



   private boolean checkForPadsUp() {

    Pad pad1=arrayOfPads.get(0);
    Player player1 = arrayOfPlayers.get(0);
    float centerPadx=pad1.getCenterPadx();
    float centerPady=pad1.getCenterPady();
    Triangle tempPadInTriangleArray = arrayOfTriangles.get(pad1.getObjectId());
    float[] arrayOfTranslations = tempPadInTriangleArray.getArrayOfTranslations();

    arrayOfTranslations[0] = centerPadx;
    arrayOfTranslations[1] = centerPady;

    player1.setObjectMovementSpeed((-1.0f)*player1.getObjectMovementSpeed());
    resetTextureSetForPlayer(player1);
    return true;

   }

   private void resetTextureSetForPlayer(Player tempPlayer)
   {
       Triangle tempPlayerTriangle=arrayOfTriangles.get(tempPlayer.getObjectId());
       if(tempPlayerTriangle.isTextureFlipped()==false) {
           tempPlayerTriangle.setActualTextureSet(0);
       }
       else if(tempPlayerTriangle.isTextureFlipped()==true)
       {
           tempPlayerTriangle.setActualTextureSet(1);
       }
   }

    private boolean checkForMobs(float xInPoints,float yInPoints)
    {
        for(Mob tempMob: arrayOfMobs)
        {
            Triangle tempMobInTriangleArray= arrayOfTriangles.get(tempMob.getObjectId());
            float[] arrayOfTranslations=tempMobInTriangleArray.getArrayOfTranslations();

            float sizeOfObject=tempMobInTriangleArray.getSizeOfObjectOnZ();

                if (((arrayOfTranslations[0]-(sizeOfObject/2.0f))<=xInPoints)&&
                        ((arrayOfTranslations[0]+(sizeOfObject/2.0f))>=xInPoints)&&
                        ((arrayOfTranslations[1]-(sizeOfObject/2.0f))<=yInPoints)&&
                        ((arrayOfTranslations[1]+(sizeOfObject/2.0f))>=yInPoints))
                {

                    return true;
                }

        }
        return false;
    }

    private boolean checkForPlayers(float xInPoints,float yInPoints)
    {
        for(Player tempPlayer: arrayOfPlayers)
        {
            Triangle tempPlayerInTriangleArray= arrayOfTriangles.get(tempPlayer.getObjectId());
            float[] arrayOfTranslations=tempPlayerInTriangleArray.getArrayOfTranslations();

            float sizeOfObject=tempPlayerInTriangleArray.getSizeOfObjectOnZ();

            if (((arrayOfTranslations[0]-(sizeOfObject/2.0f))<=xInPoints)&&
                    ((arrayOfTranslations[0]+(sizeOfObject/2.0f))>=xInPoints)&&
                    ((arrayOfTranslations[1]-(sizeOfObject/2.0f))<=yInPoints)&&
                    ((arrayOfTranslations[1]+(sizeOfObject/2.0f))>=yInPoints))
            {
                return true;
            }

        }

        return false;
    }

    private boolean checkForUIButtons(Context context,float xInPoints,float yInPoints)
    {
        for(UIButton uiButton: arrayOfUIButtons)
        {
            Triangle tempUIButtonInTriangleArray= arrayOfTriangles.get(uiButton.getObjectId());
            float[] arrayOfTranslations=tempUIButtonInTriangleArray.getArrayOfTranslations();

            float sizeOfObject=tempUIButtonInTriangleArray.getSizeOfObjectOnZ();

            if (((arrayOfTranslations[0]-(sizeOfObject/2.0f))<=xInPoints)&&
                    ((arrayOfTranslations[0]+(sizeOfObject/2.0f))>=xInPoints)&&
                    ((arrayOfTranslations[1]-(sizeOfObject/2.0f))<=yInPoints)&&
                    ((arrayOfTranslations[1]+(sizeOfObject/2.0f))>=yInPoints))
            {
                Player player1= arrayOfPlayers.get(0);


                if(arrayOfUIButtons.indexOf(uiButton)==0) {

                   if(player1.isAttacking()==false&&player1.getRemainingTimeToAttack()<=0.0f)
                   {
                   player1.setAttacking(true);
                   }
                }
                else if(arrayOfUIButtons.indexOf(uiButton)==1) {
                    //here if

                        float buttonY = 0.06f;
                        float buttonX = 0.24f;
                        float buttonAddStartX = 0.20202637f, buttonAddStartY1 = -0.20623f, buttonAddStartY2 = -0.2791f, buttonAddStartY3 = -0.3555f;
                        if (xInPoints > buttonAddStartX && xInPoints < buttonAddStartX + buttonX && yInPoints < buttonAddStartY1 && yInPoints > buttonAddStartY1 - buttonY) {
                            boolean isReadyToUpgrade=resourceSubtractForUpgrade(player1);
                            if(isReadyToUpgrade) {
                                player1.setAttack(player1.getAttack() + 10.0f);
                            }
                        } else if (xInPoints > buttonAddStartX && xInPoints < buttonAddStartX + buttonX && yInPoints < buttonAddStartY2 && yInPoints > buttonAddStartY2 - buttonY) {
                            boolean isReadyToUpgrade=resourceSubtractForUpgrade(player1);
                            if(isReadyToUpgrade) {
                                player1.setAgility(player1.getAgility() + 10.0f);
                            }
                        } else if (xInPoints > buttonAddStartX && xInPoints < buttonAddStartX + buttonX && yInPoints < buttonAddStartY3 && yInPoints > buttonAddStartY3 - buttonY) {
                            boolean isReadyToUpgrade=resourceSubtractForUpgrade(player1);
                            if(isReadyToUpgrade) {
                                player1.setVitality(player1.getVitality() + 50.0f);

                                float playerActualHealth=player1.getActualHealth();
                                player1.setActualHealth(playerActualHealth+50.0f);
                            }
                        }

                }
                else if(arrayOfUIButtons.indexOf(uiButton)==2){
                    Intent myIntent = new Intent();
                    myIntent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                    ((Activity)context).overridePendingTransition(0, 0);
                    ((Activity)context).setResult(RESULT_OK, myIntent);
                    ((Activity)context).finish();

               }
                return true;
            }

        }

        return false;
    }

    /**
     * Check if possible to upgrade for resources
     * @param player1 Player object
     * @return true if possible, false if not
     */
    private boolean resourceSubtractForUpgrade(Player player1)
    {
        float resourceAfterUpgrade=player1.getResourceOfObject()-50.0f*player1.getLevel();
        if(resourceAfterUpgrade>=0) {
            player1.setResourceOfObject(resourceAfterUpgrade);
            return true;
        }
        return false;
    }




}
