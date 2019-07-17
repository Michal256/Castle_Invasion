package com.example.game.Game;


import java.util.Vector;

import static com.example.game.Game.GameResourceBinder.arrayOfBars;
import static com.example.game.Game.GameResourceBinder.arrayOfCastlesElementsID;
import static com.example.game.Game.GameResourceBinder.arrayOfDynamicBackgroundElementsID;
import static com.example.game.Game.GameResourceBinder.arrayOfMagicBalls;
import static com.example.game.Game.GameResourceBinder.arrayOfMobs;
import static com.example.game.Game.GameResourceBinder.arrayOfMovableObjectsBars;
import static com.example.game.Game.GameResourceBinder.arrayOfPlayerNoMobs;
import static com.example.game.Game.GameResourceBinder.arrayOfTriangles;
import static com.example.game.Game.GameResourceBinder.arrayOfUIButtons;
import static com.example.game.Game.UIBitmapTextHandler.uiAttribAgilityTextBarHandler;
import static com.example.game.Game.UIBitmapTextHandler.uiAttribAttackTextBarHandler;
import static com.example.game.Game.UIBitmapTextHandler.uiAttribVitalityTextBarHandler;
import static com.example.game.Game.UIBitmapTextHandler.uiEXPBarHandler;
import static com.example.game.Game.UIBitmapTextHandler.uiHPTextBarHandler;
import static com.example.game.Game.UIBitmapTextHandler.uiPlayerLevelTextHandler;
import static com.example.game.Game.UIBitmapTextHandler.uiResourceTextBarHandler;

public class Player extends GameMovableObject implements TouchAvailability {

    private float scalarPositionOfObjectX;
    private float scalarPositionOfObjectY;
    private boolean playerMoving;

    private Vector<Integer> arrayOfObjectIDAllyMobs;
    private Vector<Integer> arrayOfObjectIDEnemyMobs;

    public Player(int objectIdInArrayOfTriangles)
    {
        super(objectIdInArrayOfTriangles);


        float tempX= arrayOfTriangles.get(objectIdInArrayOfTriangles).getArrayOfTranslations()[0];
        float tempY= arrayOfTriangles.get(objectIdInArrayOfTriangles).getArrayOfTranslations()[1];

        this.scalarPositionOfObjectX=tempX;
        this.scalarPositionOfObjectY=tempY;
        this.playerMoving=false;
        this.arrayOfObjectIDEnemyMobs=new Vector<>();

        this.arrayOfObjectIDAllyMobs=new Vector<>();
        this.arrayOfObjectIDEnemyMobs=new Vector<>();
    }


    public void movePlayer() {
        Triangle playerObject = arrayOfTriangles.get(getObjectId());
        float[] tempArrayOfPlayerTranslations = playerObject.getArrayOfTranslations();

        if (getScalarPositionOfObjectX() >= -0.9f && getScalarPositionOfObjectX() <= 0.0f) {
            if (addTwoValues(getScalarPositionOfObjectX(),getObjectMovementSpeed())< -0.9f) {
                tempArrayOfPlayerTranslations[0] = -0.9f;
                changeScalarPositionOfObject(-0.9f, tempArrayOfPlayerTranslations[1], true);
                setObjectMovementSpeed((-1)*getObjectMovementSpeed());
            }
            else if (addTwoValues(getScalarPositionOfObjectX(),getObjectMovementSpeed()) >= 0.0f)
            {
                float movementSpeedBefore = getObjectMovementSpeed();
                setObjectMovementSpeed((-1.0f)* (addTwoValues(getScalarPositionOfObjectX(),getObjectMovementSpeed())));
                tempArrayOfPlayerTranslations[0] =addTwoValues(tempArrayOfPlayerTranslations[0],getObjectMovementSpeed());
                changeScalarPositionOfObject(getObjectMovementSpeed(), 0f, false);

                setObjectMovementSpeed((-1)*getObjectMovementSpeed());
                setObjectMovementSpeed(movementSpeedBefore);

                changeScalarPositionOfObject(getObjectMovementSpeed(), 0f, false);
                moveDynamicBackgroundElements();
                moveSynchronizedElements();



            } else
                {
                tempArrayOfPlayerTranslations[0] = addTwoValues(tempArrayOfPlayerTranslations[0],getObjectMovementSpeed());
                changeScalarPositionOfObject(getObjectMovementSpeed(), 0f, false);
            }

        }
        else if (getScalarPositionOfObjectX() > 0.0f && (getScalarPositionOfObjectX() < 5.0f))
        {
            if (addTwoValues(getScalarPositionOfObjectX(),getObjectMovementSpeed()) < 0.0f) {

                float movementSpeedBefore = getObjectMovementSpeed();
                setObjectMovementSpeed((-1.0f)* (addTwoValues(getScalarPositionOfObjectX(),getObjectMovementSpeed())));

                changeScalarPositionOfObject(getObjectMovementSpeed(), 0f, false);
                moveDynamicBackgroundElements();
                moveSynchronizedElements();

                //Less than 0
                setObjectMovementSpeed((-1)*getObjectMovementSpeed());
                setObjectMovementSpeed(movementSpeedBefore);
                tempArrayOfPlayerTranslations[0] =addTwoValues(tempArrayOfPlayerTranslations[0],getObjectMovementSpeed());

                changeScalarPositionOfObject(getObjectMovementSpeed(), 0f, false);


            } else if (getScalarPositionOfObjectX() < 5.0f && (addTwoValues(getScalarPositionOfObjectX(),getObjectMovementSpeed())) > 5.0f) {

                float movementSpeedBefore = getObjectMovementSpeed();


                setObjectMovementSpeed((-1)*getObjectMovementSpeed());
                float v1=addTwoValues(getScalarPositionOfObjectX(),movementSpeedBefore);

                setObjectMovementSpeed((-1)*(getScalarPositionOfObjectX()-v1+addTwoValues(v1,(-1)*5.0f)));
                changeScalarPositionOfObject(getObjectMovementSpeed(), 0f, false);



                moveDynamicBackgroundElements();
                moveSynchronizedElements();

                setObjectMovementSpeed((-1)*getObjectMovementSpeed());
                setObjectMovementSpeed(movementSpeedBefore);

                tempArrayOfPlayerTranslations[0] =addTwoValues(tempArrayOfPlayerTranslations[0],getObjectMovementSpeed());
                changeScalarPositionOfObject(getObjectMovementSpeed(), 0f, false);

            } else {
                changeScalarPositionOfObject(getObjectMovementSpeed(), 0f, false);
                moveDynamicBackgroundElements();
                moveSynchronizedElements();

            }
        }
        else if (getScalarPositionOfObjectX() >= 5.0f) {

            if (addTwoValues(getScalarPositionOfObjectX(), getObjectMovementSpeed()) > 5.9f) {
                tempArrayOfPlayerTranslations[0] = 0.9f;
                changeScalarPositionOfObject(5.9f, tempArrayOfPlayerTranslations[1], true);
                setObjectMovementSpeed((-1) * getObjectMovementSpeed());

            } else if (addTwoValues(getScalarPositionOfObjectX(), getObjectMovementSpeed()) <= 5.0f) {
                float movementSpeedBefore = getObjectMovementSpeed();

                setObjectMovementSpeed((-1) * getObjectMovementSpeed());
                float v1 = addTwoValues(getScalarPositionOfObjectX(), movementSpeedBefore);

                setObjectMovementSpeed((-1) * (getScalarPositionOfObjectX() - v1 + addTwoValues(v1, (-1) * 5.0f)));

                tempArrayOfPlayerTranslations[0] = addTwoValues(tempArrayOfPlayerTranslations[0], getObjectMovementSpeed());

                changeScalarPositionOfObject(getObjectMovementSpeed(), 0f, false);


                setObjectMovementSpeed((-1) * getObjectMovementSpeed());
                setObjectMovementSpeed(movementSpeedBefore);


                changeScalarPositionOfObject(getObjectMovementSpeed(), 0f, false);
                moveDynamicBackgroundElements();
                moveSynchronizedElements();


            } else {
                tempArrayOfPlayerTranslations[0] = addTwoValues(tempArrayOfPlayerTranslations[0], getObjectMovementSpeed());
                changeScalarPositionOfObject(getObjectMovementSpeed(), 0f, false);
            }

        }

    }

    public void jumpPlayer()
    {
        Triangle playerObject = arrayOfTriangles.get(getObjectId());
        float[] tempArrayOfPlayerTranslations = playerObject.getArrayOfTranslations();

        if (getObjectJumpSpeed() > 0.0f && isInAir() == false&&isObjectInCollision()==false) {
            setPositionBeforeJump(tempArrayOfPlayerTranslations[1]);
            tempArrayOfPlayerTranslations[1] += getObjectJumpSpeed();
            changeScalarPositionOfObject(0f, getObjectJumpSpeed(), false);
            setGettingHeight(true);
            setInAir(true);

        }
        else if (isGettingHeight() == true && isInAir() == true) {
            if (tempArrayOfPlayerTranslations[1] <= 0.3f) {
                tempArrayOfPlayerTranslations[1] += getObjectJumpSpeed();
                changeScalarPositionOfObject(0f, getObjectJumpSpeed(), false);
            } else {
                setGettingHeight(false);
            }
        }
        else if (isInAir() == true) {
            tempArrayOfPlayerTranslations[1] -= 0.001f * (1.0f / (-1.0f) * tempArrayOfPlayerTranslations[2]);
            changeScalarPositionOfObject(0f, -0.001f * (1.0f / (-1.0f) * tempArrayOfPlayerTranslations[2]), false);
            if (tempArrayOfPlayerTranslations[1] <= getPositionBeforeJump()) {
                tempArrayOfPlayerTranslations[1] = getPositionBeforeJump();
                changeScalarPositionOfObject(getScalarPositionOfObjectX(), getPositionBeforeJump(), true);
                setObjectJumpSpeed(0.0f);
                setInAir(false);
            }
        }
    }


    private void moveDynamicBackgroundElements()
    {
            for(int tempDynamicElementID: arrayOfDynamicBackgroundElementsID)
            {
                Triangle tempDynamicElementInTriangleArray= arrayOfTriangles.get(tempDynamicElementID);
                float [] tempArrayOfTranslations=tempDynamicElementInTriangleArray.getArrayOfTranslations();

                float widthOfObject=tempDynamicElementInTriangleArray.getSizeOfObjectOnZ();

                if(getObjectMovementSpeed()>0.0f) {
                    float positionOfObjectAfterPlayerMove = tempArrayOfTranslations[0] + widthOfObject / 2.0f - getObjectMovementSpeed();

                    if (positionOfObjectAfterPlayerMove < -1.0f) {
                        //Add size of object, because there is one more not visible
                        tempArrayOfTranslations[0] += 2.0f + widthOfObject;
                    }
                    tempArrayOfTranslations[0] -= getObjectMovementSpeed();
                }
                else if(getObjectMovementSpeed()<0.0f) {
                    float positionOfObjectAfterPlayerMove = tempArrayOfTranslations[0] - (widthOfObject / 2.0f) - getObjectMovementSpeed();

                    if (positionOfObjectAfterPlayerMove>1.0f) {
                        tempArrayOfTranslations[0] -= (2.0f + widthOfObject);

                    }
                    tempArrayOfTranslations[0] -= getObjectMovementSpeed();
                }

            }
    }

    private void moveSynchronizedElements()
    {
            for(Mob tempMob: arrayOfMobs)
            {
                Triangle tempSynchronizedElementInTriangleArray = arrayOfTriangles.get(tempMob.getObjectId());
                tempSynchronizedElementInTriangleArray.getArrayOfTranslations()[0]=addTwoValues(tempSynchronizedElementInTriangleArray.getArrayOfTranslations()[0],(-1)*this.getObjectMovementSpeed());
            }
            for(PlayerNoMob tempPlayerNoMob:arrayOfPlayerNoMobs)
            {
                Triangle tempSynchronizedElementInTriangleArray = arrayOfTriangles.get(tempPlayerNoMob.getObjectId());
                tempSynchronizedElementInTriangleArray.getArrayOfTranslations()[0]=addTwoValues(tempSynchronizedElementInTriangleArray.getArrayOfTranslations()[0],(-1)*this.getObjectMovementSpeed());
            }

            for(BarOfMovableObject tempBar: arrayOfMovableObjectsBars)
            {
                Triangle tempBarTriangle=arrayOfTriangles.get(tempBar.getObjectId());
                tempBarTriangle.getArrayOfTranslations()[0]=addTwoValues(tempBarTriangle.getArrayOfTranslations()[0],(-1)*this.getObjectMovementSpeed());

            }

            for(MagicBall tempMagicBall: arrayOfMagicBalls)
            {
                Triangle tempMagicBallTriangle=arrayOfTriangles.get(tempMagicBall.getObjectId());
                tempMagicBallTriangle.getArrayOfTranslations()[0]=addTwoValues(tempMagicBallTriangle.getArrayOfTranslations()[0],(-1)*this.getObjectMovementSpeed());

            }


    }

    protected void changeScalarPositionOfObject(float x, float y, boolean setObjectCoordinatesToBeEqual)
    {
        if(setObjectCoordinatesToBeEqual==true) {
            setScalarPositionOfObjectX(x);
            setScalarPositionOfObjectY(y);
        }
        else
        {
            setScalarPositionOfObjectX(addTwoValues(getScalarPositionOfObjectX(),x));
            setScalarPositionOfObjectY(addTwoValues(getScalarPositionOfObjectY(),y));
        }

    }

    public void checkForEnemies()
    {
        for(Mob foreignMob:arrayOfMobs)
        {
            if(foreignMob.getObjectId()!=this.getObjectId())
            {
                if(this.getWhichPlayerIDTeam()!=foreignMob.getWhichPlayerIDTeam()&&foreignMob.getWhichPlayerIDTeam()>-1)
                {
                    Triangle tempPlayer=arrayOfTriangles.get(this.getObjectId());

                    float actualPlayerxAxisX=tempPlayer.getArrayOfTranslations()[0];
                    float actualPlayerxAxisY=tempPlayer.getArrayOfTranslations()[1];

                    Triangle tempMob=arrayOfTriangles.get(foreignMob.getObjectId());

                    float foreignMobxAxisX=tempMob.getArrayOfTranslations()[0];
                    float foreignMobxAxisY=tempMob.getArrayOfTranslations()[1];

                    float distanceBetweenX=addTwoValues(actualPlayerxAxisX,-foreignMobxAxisX);
                    float distanceBetweenYTopOfMobAndMiddleOfPlayer=(foreignMobxAxisY+tempMob.getSizeOfObjectOnZ()/2.0f)-actualPlayerxAxisY;

                    float fixDistanceForCastle=0.18f;
                    float attackRange=this.getAttackRange();
                    for(Integer tempCastleId:arrayOfCastlesElementsID)
                    {
                        if(foreignMob.getObjectId()==tempCastleId)
                        {
                            attackRange+=fixDistanceForCastle;
                            break;
                        }
                    }

                    if(distanceBetweenYTopOfMobAndMiddleOfPlayer>=0) {
                        if (distanceBetweenX <= 0 && Math.abs(distanceBetweenX) <= attackRange) {

                            if ((arrayOfTriangles.get(this.getObjectId()).isTextureFlipped() == false) || distanceBetweenX == 0) {
                                if(this.getRemainingTimeToAttack()<=0.0f) {
                                    this.fireAttack(foreignMob);
                                }
                            }
                        } else if (distanceBetweenX > 0 && Math.abs(distanceBetweenX) <= attackRange) {
                            if (arrayOfTriangles.get(this.getObjectId()).isTextureFlipped() == true) {
                                if(this.getRemainingTimeToAttack()<=0.0f) {
                                    this.fireAttack(foreignMob);
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    /**
     * Set bar health after player HP change
     */
    public void updatePositionOfHPBar()
    {
        Triangle tempHPEmptyBar= arrayOfTriangles.get(arrayOfBars.get(2).getObjectId());
        float sizeOfBar=tempHPEmptyBar.getSizeOfObjectOnZ();

        Triangle tempHPBar= arrayOfTriangles.get(arrayOfBars.get(0).getObjectId());

        float[] tempArrayOfBarTranslations=tempHPEmptyBar.getArrayOfTranslations();
        float[] tempArrayOfHealthBarFullTranslations=tempHPBar.getArrayOfTranslations();
        tempArrayOfBarTranslations[0]=addTwoValues(tempArrayOfHealthBarFullTranslations[0], sizeOfBar*(getActualHealthInPercents()/100.0f));

    }


    /**
     * Set bar Exp after player Exp change
     */
    public void updatePositionOfExpBar()
    {
        Triangle tempExpEmptyBar= arrayOfTriangles.get(arrayOfBars.get(3).getObjectId());
        float sizeOfBar=tempExpEmptyBar.getSizeOfObjectOnZ();

        Triangle tempExpBar= arrayOfTriangles.get(arrayOfBars.get(1).getObjectId());

        float[] tempArrayOfBarTranslations=tempExpEmptyBar.getArrayOfTranslations();
        float[] tempArrayOfExpBarFullTranslations=tempExpBar.getArrayOfTranslations();
        tempArrayOfBarTranslations[0]=addTwoValues(tempArrayOfExpBarFullTranslations[0], sizeOfBar*(getActualExperienceInPercents()/100.0f));

    }

    @Override
    public void onTouchAction() {

    }

    @Override
    public void setResourceOfObject(float resourceOfObject) {
        super.setResourceOfObject(resourceOfObject);
        uiResourceTextBarHandler();
        changeUpgradeButtonTextures();
    }

    private void changeUpgradeButtonTextures()
    {
        if(getResourceOfObject()>=50.0f*getLevel())
        {
            arrayOfTriangles.get(arrayOfUIButtons.get(1).getObjectId()).setActualTextureSet(1);
        }
        else
        {
            arrayOfTriangles.get(arrayOfUIButtons.get(1).getObjectId()).setActualTextureSet(0);
        }
    }
    @Override
    public void setAttack(float attack) {
        super.setAttack(attack);
        uiAttribAttackTextBarHandler();
    }

    @Override
    public void setAgility(float agility) {
        super.setAgility(agility);
        uiAttribAgilityTextBarHandler();
    }

    @Override
    public void setVitality(float vitality) {
        super.setVitality(vitality);
        uiAttribVitalityTextBarHandler();
    }

    @Override
    public void setLevel(float level) {
        super.setLevel(level);
        uiPlayerLevelTextHandler();
    }



    @Override
    void setProperExperienceNeedForLevel(float level) {
        setExperience(level*100f);
    }

    @Override
    public void setActualHealth(float actualHealth) {
        super.setActualHealth(actualHealth);
        uiHPTextBarHandler();
        updatePositionOfHPBar();
    }

    @Override
    public void setActualExperience(float actualExperience) {
        super.setActualExperience(actualExperience);
        uiEXPBarHandler();
        updatePositionOfExpBar();
    }

    public float getScalarPositionOfObjectX() {
        return scalarPositionOfObjectX;
    }

    public void setScalarPositionOfObjectX(float scalarPositionOfObjectX) {
        this.scalarPositionOfObjectX = scalarPositionOfObjectX;
    }

    public float getScalarPositionOfObjectY() {
        return scalarPositionOfObjectY;
    }

    public void setScalarPositionOfObjectY(float scalarPositionOfObjectY) {
        this.scalarPositionOfObjectY = scalarPositionOfObjectY;
    }

    public boolean isPlayerMoving() {
        return playerMoving;
    }

    public void setPlayerMoving(boolean playerMoving) {
        this.playerMoving = playerMoving;
    }


    public Vector<Integer> getArrayOfObjectIDAllyMobs() {
        return arrayOfObjectIDAllyMobs;
    }

    public Vector<Integer> getArrayOfObjectIDEnemyMobs() {
        return arrayOfObjectIDEnemyMobs;
    }

}
