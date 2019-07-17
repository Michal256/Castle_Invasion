package com.example.game.Game;

import static com.example.game.Game.GameResourceBinder.arrayOfElementsWithText;
import static com.example.game.Game.GameResourceBinder.arrayOfTriangles;

public class TextureObjectHandler {

    public static void changeTimerTexture()
    {
        if(arrayOfElementsWithText.size()>=5) {
            Triangle tempTriangleSecondsOne = arrayOfTriangles.get(arrayOfElementsWithText.get(0));
            Triangle tempTriangleSecondsTwo = arrayOfTriangles.get(arrayOfElementsWithText.get(1));
            Triangle tempTriangleMinutesOne = arrayOfTriangles.get(arrayOfElementsWithText.get(3));
            Triangle tempTriangleMinutesTwo = arrayOfTriangles.get(arrayOfElementsWithText.get(4));
            if (tempTriangleSecondsOne.getActualTextureSet() >= 9) {
                tempTriangleSecondsOne.setActualTextureSet(0);
                if(tempTriangleSecondsTwo.getActualTextureSet()>=5)
                {
                    tempTriangleSecondsTwo.setActualTextureSet(0);
                    if(tempTriangleMinutesOne.getActualTextureSet()>=9)
                    {
                        tempTriangleMinutesOne.setActualTextureSet(0);
                        if(tempTriangleMinutesTwo.getActualTextureSet()>=9)
                        {
                            tempTriangleMinutesTwo.setActualTextureSet(0);
                            tempTriangleSecondsOne.setActualTextureSet(0);
                            tempTriangleSecondsTwo.setActualTextureSet(0);
                        }
                        else
                        {
                            tempTriangleMinutesTwo.setActualTextureSet(tempTriangleMinutesTwo.getActualTextureSet() + 1);
                        }
                    }
                    else
                    {
                        tempTriangleMinutesOne.setActualTextureSet(tempTriangleMinutesOne.getActualTextureSet() + 1);
                    }
                }
                else
                {
                    tempTriangleSecondsTwo.setActualTextureSet(tempTriangleSecondsTwo.getActualTextureSet() + 1);
                }
            } else {
                tempTriangleSecondsOne.setActualTextureSet(tempTriangleSecondsOne.getActualTextureSet() + 1);
            }

        }
    }

    public static void changePlayerTexture(Player tempPlayer, boolean isAttacking,boolean isDying)
    {
        Triangle tempTriangle=arrayOfTriangles.get(tempPlayer.getObjectId());
        int actualTextureSet=tempTriangle.getActualTextureSet();

        if(isAttacking==false&&isDying==false) {
            if (tempTriangle.isPossibleToFlip() == false) {
                if (actualTextureSet < ((tempTriangle.getmTextureDataHandleArray().size() - 1)-20)) {
                    tempTriangle.setActualTextureSet(actualTextureSet + 1);
                } else {
                    tempTriangle.setActualTextureSet(0);
                }
            } else if (tempTriangle.isPossibleToFlip() == true && tempTriangle.isTextureFlipped() == false) {
                if (actualTextureSet < ((tempTriangle.getmTextureDataHandleArray().size() - 2)-40)) {
                    tempTriangle.setActualTextureSet(actualTextureSet + 2);
                } else {
                    tempTriangle.setActualTextureSet(0);
                }
            } else if (tempTriangle.isPossibleToFlip() == true && tempTriangle.isTextureFlipped() == true) {
                if (actualTextureSet + 2 < ((tempTriangle.getmTextureDataHandleArray().size() - 1)-40)) {
                    tempTriangle.setActualTextureSet(actualTextureSet + 2);
                } else {
                    tempTriangle.setActualTextureSet(1);
                }
            }
        }
        else if(isAttacking==true&&isDying==false)
        {
                actualTextureSet=tempTriangle.getActualTextureSet();
                if (tempTriangle.isPossibleToFlip() == false) {
                    if (actualTextureSet < ((tempTriangle.getmTextureDataHandleArray().size() - 1)-10)) {
                        tempTriangle.setActualTextureSet(actualTextureSet + 1);
                    } else {
                        tempTriangle.setActualTextureSet(9);
                        tempPlayer.setAttacking(false);

                    }
                } else if (tempTriangle.isPossibleToFlip() == true && tempTriangle.isTextureFlipped() == false) {
                    if (actualTextureSet < ((tempTriangle.getmTextureDataHandleArray().size() - 2))-20) {
                        tempTriangle.setActualTextureSet(actualTextureSet + 2);
                    } else {
                        tempTriangle.setActualTextureSet(18);
                        tempPlayer.setAttacking(false);
                    }
                } else if (tempTriangle.isPossibleToFlip() == true && tempTriangle.isTextureFlipped() == true) {
                    if (actualTextureSet + 2 < ((tempTriangle.getmTextureDataHandleArray().size() - 1))-20) {
                        tempTriangle.setActualTextureSet(actualTextureSet + 2);
                    } else {
                        tempTriangle.setActualTextureSet(19);
                        tempPlayer.setAttacking(false);
                    }
                }

        }
        else if(isDying==true)
        {
            actualTextureSet=tempTriangle.getActualTextureSet();
            if (tempTriangle.isPossibleToFlip() == false) {
                if (actualTextureSet < (tempTriangle.getmTextureDataHandleArray().size() - 1)) {
                    tempTriangle.setActualTextureSet(actualTextureSet + 1);
                } else {
                    tempTriangle.setActualTextureSet(20);
                    tempPlayer.setAttacking(false);
                    tempPlayer.setDying(false);
                }
            } else if (tempTriangle.isPossibleToFlip() == true && tempTriangle.isTextureFlipped() == false) {
                if (actualTextureSet < (tempTriangle.getmTextureDataHandleArray().size() - 2)) {
                    tempTriangle.setActualTextureSet(actualTextureSet + 2);
                } else {
                    tempTriangle.setActualTextureSet(28);
                    tempPlayer.setAttacking(false);
                    tempPlayer.setDying(false);
                }
            } else if (tempTriangle.isPossibleToFlip() == true && tempTriangle.isTextureFlipped() == true) {
                if (actualTextureSet + 2 < (tempTriangle.getmTextureDataHandleArray().size() - 1)) {
                    tempTriangle.setActualTextureSet(actualTextureSet + 2);
                } else {
                    tempTriangle.setActualTextureSet(29);
                    tempPlayer.setAttacking(false);
                    tempPlayer.setDying(false);
                }
            }
        }
    }


    public static void changeGoblinTexture(Mob tempMob,boolean isAttacking,boolean isDying)
    {
        Triangle tempTriangle=arrayOfTriangles.get(tempMob.getObjectId());
        int actualTextureSet=tempTriangle.getActualTextureSet();

        if(isAttacking==false&&isDying==false) {
            if (tempTriangle.isPossibleToFlip() == false) {
                if (actualTextureSet < (tempTriangle.getmTextureDataHandleArray().size() - 1-3)) {
                    tempTriangle.setActualTextureSet(actualTextureSet + 1);
                } else {
                    tempTriangle.setActualTextureSet(0);
                }
            } else if (tempTriangle.isPossibleToFlip() == true && tempTriangle.isTextureFlipped() == false) {
                if (actualTextureSet < (tempTriangle.getmTextureDataHandleArray().size() - 2-6)) {
                    tempTriangle.setActualTextureSet(actualTextureSet + 2);
                } else {
                    tempTriangle.setActualTextureSet(0);
                }
            } else if (tempTriangle.isPossibleToFlip() == true && tempTriangle.isTextureFlipped() == true) {
                if (actualTextureSet + 2 < (tempTriangle.getmTextureDataHandleArray().size() - 1-6)) {
                    tempTriangle.setActualTextureSet(actualTextureSet + 2);
                } else {
                    tempTriangle.setActualTextureSet(1);
                }
            }
        }
        else if(isAttacking==true&&isDying==false)
        {
                actualTextureSet = tempTriangle.getActualTextureSet();
                if (tempTriangle.isPossibleToFlip() == false) {
                    if (actualTextureSet < (tempTriangle.getmTextureDataHandleArray().size() - 1)) {
                        tempTriangle.setActualTextureSet(actualTextureSet + 1);
                    } else {
                        tempTriangle.setActualTextureSet(0);
                        tempMob.setAttacking(false);
                    }
                } else if (tempTriangle.isPossibleToFlip() == true && tempTriangle.isTextureFlipped() == false) {
                    if (actualTextureSet < (tempTriangle.getmTextureDataHandleArray().size() - 2)) {
                        tempTriangle.setActualTextureSet(actualTextureSet + 2);
                    } else {
                        tempTriangle.setActualTextureSet(0);
                        tempMob.setAttacking(false);
                    }
                } else if (tempTriangle.isPossibleToFlip() == true && tempTriangle.isTextureFlipped() == true) {
                    if (actualTextureSet + 2 < (tempTriangle.getmTextureDataHandleArray().size() - 1)) {
                        tempTriangle.setActualTextureSet(actualTextureSet + 2);
                    } else {
                        tempTriangle.setActualTextureSet(1);
                        tempMob.setAttacking(false);
                    }
                }
        }
        else if(isDying==true)
        {

        }

    }




}
