package com.example.game.Game;

import static com.example.game.Game.GameResourceBinder.arrayOfCastlesElementsID;
import static com.example.game.Game.GameResourceBinder.arrayOfMagicBalls;
import static com.example.game.Game.GameResourceBinder.arrayOfMobs;
import static com.example.game.Game.GameResourceBinder.arrayOfPlayerNoMobs;
import static com.example.game.Game.GameResourceBinder.arrayOfPlayers;
import static com.example.game.Game.GameResourceBinder.arrayOfTriangles;

public class Mob extends GameMovableObject implements TouchAvailability {

    public Mob(int objectIdInArrayOfTriangles)
    {
        super(objectIdInArrayOfTriangles);
    }

    public void checkForEnemies()
    {
        boolean objectDetectedEnemy=false;
        for(Mob foreignMob:arrayOfMobs)
        {
            if(foreignMob.isFrozen()==false) {
                if (foreignMob.getObjectId() != this.getObjectId()) {
                    if (this.getWhichPlayerIDTeam() != foreignMob.getWhichPlayerIDTeam() && foreignMob.getWhichPlayerIDTeam() > -1) {
                        float actualMobxAxis = arrayOfTriangles.get(this.getObjectId()).getArrayOfTranslations()[0];
                        float foreignMobxAxis = arrayOfTriangles.get(foreignMob.getObjectId()).getArrayOfTranslations()[0];
                        float distanceBetween = addTwoValues(actualMobxAxis, -foreignMobxAxis);

                        float fixDistanceForCastle = 0.18f;
                        float attackRange = this.getAttackRange();
                        for (Integer tempCastleId : arrayOfCastlesElementsID) {
                            if (foreignMob.getObjectId() == tempCastleId) {
                                attackRange += fixDistanceForCastle;
                                break;
                            }
                        }

                        if (distanceBetween <= 0 && Math.abs(distanceBetween) <= attackRange) {

                            if ((arrayOfTriangles.get(this.getObjectId()).isTextureFlipped() == false) || distanceBetween == 0) {
                                this.setObjectMovementSpeed(this.getObjectMovementSpeed() * (-1.0f));
                                if (this.getRemainingTimeToAttack() <= 0.0f) {
                                    this.setAttacking(true);

                                    boolean fireIsOn = checkForSpecialAttackEffect(foreignMob.getObjectId());
                                    if (fireIsOn == false) {
                                        this.fireAttack(foreignMob);
                                    }
                                }

                                objectDetectedEnemy = true;

                                break;
                            }
                        } else if (distanceBetween > 0 && Math.abs(distanceBetween) <= attackRange) {
                            if (arrayOfTriangles.get(this.getObjectId()).isTextureFlipped() == true) {
                                this.setObjectMovementSpeed(this.getObjectMovementSpeed() * (-1.0f));
                                if (this.getRemainingTimeToAttack() <= 0.0f) {
                                    this.setAttacking(true);
                                    boolean fireIsOn = checkForSpecialAttackEffect(foreignMob.getObjectId());
                                    if (fireIsOn == false) {
                                        this.fireAttack(foreignMob);
                                    }
                                }

                                objectDetectedEnemy = true;
                                break;
                            }
                        }
                    }
                }
            }
        }
        for(Player foreignTempPlayer: arrayOfPlayers)
        {
            if(foreignTempPlayer.isFrozen()==false) {
                if (this.getWhichPlayerIDTeam() != foreignTempPlayer.getWhichPlayerIDTeam() && foreignTempPlayer.getWhichPlayerIDTeam() > -1) {
                    float actualMobxAxis = arrayOfTriangles.get(this.getObjectId()).getArrayOfTranslations()[0];
                    float foreignPlayerxAxis = arrayOfTriangles.get(foreignTempPlayer.getObjectId()).getArrayOfTranslations()[0];
                    float distanceBetween = addTwoValues(actualMobxAxis, -foreignPlayerxAxis);


                    if (distanceBetween <= 0 && Math.abs(distanceBetween) <= this.getAttackRange()) {

                        if ((arrayOfTriangles.get(this.getObjectId()).isTextureFlipped() == false) || distanceBetween == 0) {
                            this.setObjectMovementSpeed(this.getObjectMovementSpeed() * (-1.0f));
                            if (this.getRemainingTimeToAttack() <= 0.0f) {
                                this.setAttacking(true);
                                boolean fireIsOn = checkForSpecialAttackEffect(foreignTempPlayer.getObjectId());
                                if (fireIsOn == false) {
                                    this.fireAttack(foreignTempPlayer);
                                }
                            }
                            objectDetectedEnemy = true;

                            break;
                        }
                    } else if (distanceBetween > 0 && Math.abs(distanceBetween) <= this.getAttackRange()) {
                        if (arrayOfTriangles.get(this.getObjectId()).isTextureFlipped() == true) {
                            this.setObjectMovementSpeed(this.getObjectMovementSpeed() * (-1.0f));
                            if (this.getRemainingTimeToAttack() <= 0.0f) {
                                this.setAttacking(true);
                                boolean fireIsOn = checkForSpecialAttackEffect(foreignTempPlayer.getObjectId());
                                if (fireIsOn == false) {
                                    this.fireAttack(foreignTempPlayer);
                                }
                            }
                            objectDetectedEnemy = true;

                            break;
                        }
                    }
                }
            }
        }

        for(PlayerNoMob foreignTempPlayerNoMob: arrayOfPlayerNoMobs)
        {
            if(foreignTempPlayerNoMob.isFrozen()==false) {
                if (this.getWhichPlayerIDTeam() != foreignTempPlayerNoMob.getWhichPlayerIDTeam() && foreignTempPlayerNoMob.getWhichPlayerIDTeam() > -1) {
                    float actualMobxAxis = arrayOfTriangles.get(this.getObjectId()).getArrayOfTranslations()[0];
                    float foreignPlayerxAxis = arrayOfTriangles.get(foreignTempPlayerNoMob.getObjectId()).getArrayOfTranslations()[0];
                    float distanceBetween = addTwoValues(actualMobxAxis, -foreignPlayerxAxis);

                    if (distanceBetween <= 0 && Math.abs(distanceBetween) <= this.getAttackRange()) {

                        if ((arrayOfTriangles.get(this.getObjectId()).isTextureFlipped() == false) || distanceBetween == 0) {
                            this.setObjectMovementSpeed(this.getObjectMovementSpeed() * (-1.0f));
                            if (this.getRemainingTimeToAttack() <= 0.0f) {
                                this.setAttacking(true);
                                boolean fireIsOn = checkForSpecialAttackEffect(foreignTempPlayerNoMob.getObjectId());
                                if (fireIsOn == false) {
                                    this.fireAttack(foreignTempPlayerNoMob);
                                }
                            }
                            objectDetectedEnemy = true;

                            break;
                        }
                    } else if (distanceBetween > 0 && Math.abs(distanceBetween) <= this.getAttackRange()) {
                        if (arrayOfTriangles.get(this.getObjectId()).isTextureFlipped() == true) {
                            this.setObjectMovementSpeed(this.getObjectMovementSpeed() * (-1.0f));
                            if (this.getRemainingTimeToAttack() <= 0.0f) {
                                this.setAttacking(true);
                                boolean fireIsOn = checkForSpecialAttackEffect(foreignTempPlayerNoMob.getObjectId());
                                if (fireIsOn == false) {
                                    this.fireAttack(foreignTempPlayerNoMob);
                                }
                            }
                            objectDetectedEnemy = true;

                            break;
                        }
                    }
                }
            }
        }

        if(objectDetectedEnemy==false) {
            this.setObjectMovementSpeed(this.getObjectMovementSpeed() * (-1.0f));
            this.setObjectMovementSpeed(this.getMaxMovementSpeed());
        }
    }

    @Override
    void setProperExperienceNeedForLevel(float level) {
        setExperience(level*100f);
    }

    private boolean checkForSpecialAttackEffect(int tempTargetTriangleObjectId)
    {
        if(this.isSpecialAttackEffectTurnOn()==true)
        {
            int specialEffectType=this.getSpecialEffectAttackType();

            //if it's MagicBall
            if(specialEffectType==0)
            {
                if(!(arrayOfMagicBalls.isEmpty())) {
                    for(MagicBall tempMagicBallBefore: arrayOfMagicBalls)
                    {
                        if(getObjectId()==tempMagicBallBefore.getAttackSourceTriangleObjectId()&&tempTargetTriangleObjectId==tempMagicBallBefore.getAttackTargetTriangleObjectId())
                        {
                            return true;
                        }
                    }

                        for (MagicBall tempMagicBall : arrayOfMagicBalls) {
                            if (tempMagicBall.getAttackSourceTriangleObjectId() == -1 && tempMagicBall.getAttackTargetTriangleObjectId() == -1) {
                                tempMagicBall.setAttackSourceTriangleObjectId(this.getObjectId());
                                tempMagicBall.setAttackTargetTriangleObjectId(tempTargetTriangleObjectId);
                                tempMagicBall.setFirstPositionToTheSource();

                                setProperActualTextureForMagicBall(tempMagicBall);

                                arrayOfTriangles.get(tempMagicBall.getObjectId()).setVisible(true);
                                tempMagicBall.setFrozen(false);
                                tempMagicBall.setLifeTime(1400.0f);
                                playProperDistanceAttackSound();
                                return true;
                            }
                    }
                }
            }
        }
        return false;
    }

    private void setProperActualTextureForMagicBall(MagicBall tempMagicBall)
    {
        boolean itIsCastle=false;
        for(Integer tempCastleID: arrayOfCastlesElementsID)
        {
            if(tempCastleID==getObjectId())
            {
                arrayOfTriangles.get(tempMagicBall.getObjectId()).setActualTextureSet(1);
                itIsCastle=true;
            }
        }

        if(itIsCastle==false)
        {
            arrayOfTriangles.get(tempMagicBall.getObjectId()).setActualTextureSet(0);
        }
    }

    public void moveMob()
    {
        Triangle mobTemp = arrayOfTriangles.get(getObjectId());
        float mobArrayOfTranslations[] = mobTemp.getArrayOfTranslations();

        //If Team is 0,2,4... it will go on right, if not it will go left
        if(getWhichPlayerIDTeam()%2==0) {
            mobArrayOfTranslations[0] = addTwoValues(mobArrayOfTranslations[0], getObjectMovementSpeed());
        }
        else
        {
            mobArrayOfTranslations[0] = addTwoValues(mobArrayOfTranslations[0], (-1.0f)*getObjectMovementSpeed());
        }
       this.updatePositionOfMovableBar();

    }


    @Override
    public void onTouchAction() {

    }

}
