package com.example.game.Game;

import static com.example.game.Game.GameResourceBinder.arrayOfElementsWithText;
import static com.example.game.Game.GameResourceBinder.arrayOfPlayers;
import static com.example.game.Game.GameResourceBinder.arrayOfTriangles;
import static com.example.game.Game.GameResourceBinder.gameIsLost;
import static com.example.game.Game.GameResourceBinder.gameTimeInSeconds;
import static com.example.game.Game.SoundReproducer.playSoundGameBegin;
import static com.example.game.Game.SoundReproducer.playSoundGameCount;

public class UIBitmapTextHandler {
    public UIBitmapTextHandler() {

    }

    public static void uiResourceTextBarHandler() {
        makeAllBitmapTextResourceNotVisible();
        int totalPlayerResourceAfterAdd = (int) Math.round(arrayOfPlayers.get(0).getResourceOfObject());
        String totalPlayerResourceString = Integer.toString(totalPlayerResourceAfterAdd);

        int timesOfTotalResource = findProperAliquotForNumber(totalPlayerResourceAfterAdd);
        //1(1-9) //2(10-99) //3(100-999)


        if (timesOfTotalResource <= 6) {
            for (int i = 0; i < timesOfTotalResource; i++) {
                int numberToSet = Integer.parseInt(totalPlayerResourceString.substring(i, i + 1));
                Triangle tempResObject = arrayOfTriangles.get(arrayOfElementsWithText.get(i + 13));
                tempResObject.setActualTextureSet(numberToSet);
                tempResObject.setVisible(true);

                //SET G ON LAST ELEMENT
                if ((i + 1) >= timesOfTotalResource) {
                    Triangle tempResTextGObject = arrayOfTriangles.get(arrayOfElementsWithText.get((i + 1) + 13));
                    if (i + 1 >= 7) {
                        tempResTextGObject.setActualTextureSet(0);
                        tempResTextGObject.setVisible(true);
                    } else {
                        tempResTextGObject.setActualTextureSet(10);
                        tempResTextGObject.setVisible(true);
                    }
                }
            }
        }


    }

    private static void makeAllBitmapTextResourceNotVisible() {
        Triangle tempResObject0 = arrayOfTriangles.get(arrayOfElementsWithText.get(13));
        tempResObject0.setVisible(false);
        Triangle tempResObject00 = arrayOfTriangles.get(arrayOfElementsWithText.get(14));
        tempResObject00.setVisible(false);
        Triangle tempResObject000 = arrayOfTriangles.get(arrayOfElementsWithText.get(15));
        tempResObject000.setVisible(false);
        Triangle tempResObject0000 = arrayOfTriangles.get(arrayOfElementsWithText.get(16));
        tempResObject0000.setVisible(false);
        Triangle tempResObject00000 = arrayOfTriangles.get(arrayOfElementsWithText.get(17));
        tempResObject00000.setVisible(false);
        Triangle tempResObject000000 = arrayOfTriangles.get(arrayOfElementsWithText.get(18));
        tempResObject000000.setVisible(false);
        Triangle tempResObjectR = arrayOfTriangles.get(arrayOfElementsWithText.get(19));
        tempResObjectR.setVisible(false);
    }

    public static void uiAttribAttackTextBarHandler() {

        Player tempPlayer = arrayOfPlayers.get(0);
        //20-25
        float playerAttack = tempPlayer.getAttack();
        int totalPlayerAttackAfterAdd = (int) Math.round(tempPlayer.getAttack());
        String totalPlayerAttackString = Integer.toString(totalPlayerAttackAfterAdd);

        int idFirstAttackTextInArrayOfTexts = 21;
        int howManyZeros = 5;

        //Set Attack Bar

        int timesOfTotalAttack = findProperAliquotForNumber(playerAttack);
        if (timesOfTotalAttack <= howManyZeros) {
            for (int i = 0; i < timesOfTotalAttack; i++) {
                int numberToSet = Integer.parseInt(totalPlayerAttackString.substring(i, i + 1));
                Triangle tempAttackObject = arrayOfTriangles.get(arrayOfElementsWithText.get(idFirstAttackTextInArrayOfTexts + howManyZeros - (timesOfTotalAttack - 1) + i - 1));
                tempAttackObject.setActualTextureSet(numberToSet);
            }
        } else {
            for (int i = 0; i < howManyZeros; i++) {
                Triangle tempAttackObject = arrayOfTriangles.get(arrayOfElementsWithText.get(idFirstAttackTextInArrayOfTexts + i));
                tempAttackObject.setActualTextureSet(9);
            }
        }

    }

    public static void uiAttribAgilityTextBarHandler() {

        Player tempPlayer = arrayOfPlayers.get(0);

        float playerAgility = tempPlayer.getAgility();
        int totalPlayerAgilityAfterAdd = (int) Math.round(tempPlayer.getAgility());
        String totalPlayerAgilityString = Integer.toString(totalPlayerAgilityAfterAdd);

        int idFirstAgilityTextInArrayOfTexts = 27;
        int howManyZeros = 5;

        int timesOfTotalAgility = findProperAliquotForNumber(playerAgility);
        if (timesOfTotalAgility <= howManyZeros) {
            for (int i = 0; i < timesOfTotalAgility; i++) {
                int numberToSet = Integer.parseInt(totalPlayerAgilityString.substring(i, i + 1));
                Triangle tempAgilityObject = arrayOfTriangles.get(arrayOfElementsWithText.get(idFirstAgilityTextInArrayOfTexts + howManyZeros - (timesOfTotalAgility - 1) + i - 1));
                tempAgilityObject.setActualTextureSet(numberToSet);
            }
        } else {
            for (int i = 0; i < howManyZeros; i++) {
                Triangle tempAgilityObject = arrayOfTriangles.get(arrayOfElementsWithText.get(idFirstAgilityTextInArrayOfTexts + i));
                tempAgilityObject.setActualTextureSet(9);
            }
        }

    }

    public static void uiAttribVitalityTextBarHandler() {

        Player tempPlayer = arrayOfPlayers.get(0);
        //26-31
        float playerVitality = tempPlayer.getVitality();
        int totalPlayerVitalityAfterAdd = (int) Math.round(tempPlayer.getVitality());
        String totalPlayerVitalityString = Integer.toString(totalPlayerVitalityAfterAdd);

        //32-37
        int idFirstVitalityTextInArrayOfTexts = 33;
        int howManyZeros = 5;

        int timesOfTotalVitality = findProperAliquotForNumber(playerVitality);
        if (timesOfTotalVitality <= howManyZeros) {
            for (int i = 0; i < timesOfTotalVitality; i++) {
                int numberToSet = Integer.parseInt(totalPlayerVitalityString.substring(i, i + 1));
                Triangle tempVitalityObject = arrayOfTriangles.get(arrayOfElementsWithText.get(idFirstVitalityTextInArrayOfTexts + howManyZeros - (timesOfTotalVitality - 1) + i - 1));
                tempVitalityObject.setActualTextureSet(numberToSet);
            }
        } else {
            for (int i = 0; i < howManyZeros; i++) {
                Triangle tempVitalityObject = arrayOfTriangles.get(arrayOfElementsWithText.get(idFirstVitalityTextInArrayOfTexts + i));
                tempVitalityObject.setActualTextureSet(9);
            }
        }

    }

    public static void uiPlayerLevelTextHandler() {
        Player tempPlayer = arrayOfPlayers.get(0);

        int idOfTextPlayerLevel = 7;

        int playerLevel = (int) Math.round(tempPlayer.getLevel());
        if (playerLevel >= 0 && playerLevel <= 30) {
            arrayOfTriangles.get(arrayOfElementsWithText.get(idOfTextPlayerLevel)).setActualTextureSet(playerLevel);
        }
    }

    public static void uiHPTextBarHandler() {
        Player tempPlayer = arrayOfPlayers.get(0);

        int idOfTextHPBar = 9;

        int playerHealth = (int) Math.round(tempPlayer.getActualHealthInPercents());
        if (playerHealth >= 0 && playerHealth <= 100) {
            arrayOfTriangles.get(arrayOfElementsWithText.get(idOfTextHPBar)).setActualTextureSet(playerHealth);
        }
    }


    public static void uiEXPBarHandler() {
        Player tempPlayer = arrayOfPlayers.get(0);

        int idOfTextExpBar = 11;

        int playerEXP = (int) Math.round(tempPlayer.getActualExperienceInPercents());
        if (playerEXP >= 0 && playerEXP <= 100) {
            arrayOfTriangles.get(arrayOfElementsWithText.get(idOfTextExpBar)).setActualTextureSet(playerEXP);
        }
    }


    private static int findProperAliquotForNumber(float number) {
        float aliquot = 10.0f;
        int times = 0;
        boolean aliquotIsProper = false;
        do {
            if (aliquot != 0) {
                float result = number / aliquot;
                if (result < 1.0f) {
                    aliquotIsProper = true;
                } else {
                    aliquot = aliquot * 10.0f;
                }
            } else {
                aliquot = 10.0f;
            }
            times++;
        } while (aliquotIsProper == false);

        return times;
    }

    public static void uiWelcomeMessageHandler() {
        int tempInformMessageID = arrayOfElementsWithText.get(39);
        Triangle tempTriangle = arrayOfTriangles.get(tempInformMessageID);

        if (tempTriangle.getBitmapArrayOfTextHolders().size() >= 8) {
            if (gameTimeInSeconds >= 0 && gameTimeInSeconds <= 4) {
                tempTriangle.setActualTextureSet(0);
            } else if (gameTimeInSeconds > 5 && gameTimeInSeconds <= 6) {
                tempTriangle.setActualTextureSet(1);
            } else if (gameTimeInSeconds > 6 && gameTimeInSeconds <= 7) {
                tempTriangle.setActualTextureSet(2);
            } else if (gameTimeInSeconds > 7 && gameTimeInSeconds <= 8) {
                tempTriangle.setActualTextureSet(3);
            } else if (gameTimeInSeconds == 9) {
                if(tempTriangle.getActualTextureSet()!=4)
                {
                    tempTriangle.setActualTextureSet(4);
                    playSoundGameCount();
                }
            } else if (gameTimeInSeconds == 10) {
                if(tempTriangle.getActualTextureSet()!=5)
                {
                    tempTriangle.setActualTextureSet(5);
                    playSoundGameCount();
                }
            } else if (gameTimeInSeconds == 11) {
                if(tempTriangle.getActualTextureSet()!=6)
                {
                    tempTriangle.setActualTextureSet(6);
                    playSoundGameCount();
                }
            } else if (gameTimeInSeconds >= 12 && gameTimeInSeconds < 13) {
                if(tempTriangle.getActualTextureSet()!=7)
                {
                    tempTriangle.setActualTextureSet(7);
                    playSoundGameBegin();
                }
            }
        }
    }

    public static void uiEndMessageHandler() {
        int tempInformMessageID = arrayOfElementsWithText.get(40);
        Triangle tempTriangle = arrayOfTriangles.get(tempInformMessageID);

        if (tempTriangle.getBitmapArrayOfTextHolders().size() >= 2) {
            if(gameIsLost==false)
            {
                tempTriangle.setActualTextureSet(1);
            }
            else
            {
                tempTriangle.setActualTextureSet(0);
            }
        }
    }
}
