package com.example.game.Game;

import java.util.Vector;

import static com.example.game.Game.GameResourceBinder.arrayOfMobs;
import static com.example.game.Game.GameResourceBinder.arrayOfTriangles;

public class PlayerNoMob extends Mob {

    private Vector<Integer> arrayOfObjectIDAllyMobs;
    private Vector<Integer> arrayOfObjectIDEnemyMobs;

    public PlayerNoMob(int objectIdInArrayOfTriangles) {
        super(objectIdInArrayOfTriangles);
        this.arrayOfObjectIDAllyMobs=new Vector<>();
        this.arrayOfObjectIDEnemyMobs=new Vector<>();
    }

    @Override
    public void moveMob()
    {
        Triangle playerNoMobTemp = arrayOfTriangles.get(getObjectId());
        float mobArrayOfTranslations[] = playerNoMobTemp.getArrayOfTranslations();
        mobArrayOfTranslations[0]=addTwoValues(mobArrayOfTranslations[0],getObjectMovementSpeed());

    }

    public void checkForEnemies()
    {
        boolean objectDetectedEnemy=false;
        for(Mob foreignMob:arrayOfMobs)
        {
            if(foreignMob.getObjectId()!=this.getObjectId())
            {
                if(this.getWhichPlayerIDTeam()!=foreignMob.getWhichPlayerIDTeam()&&foreignMob.getWhichPlayerIDTeam()>-1)
                {
                    float actualMobxAxis=arrayOfTriangles.get(this.getObjectId()).getArrayOfTranslations()[0];
                    float foreignMobxAxis=arrayOfTriangles.get(foreignMob.getObjectId()).getArrayOfTranslations()[0];
                    float distanceBetween=addTwoValues(actualMobxAxis,-foreignMobxAxis);
                    if(distanceBetween<=0&&Math.abs(distanceBetween)<=this.getAttackRange())
                    {

                        if((arrayOfTriangles.get(this.getObjectId()).isTextureFlipped()==false)||distanceBetween==0) {
                            this.setObjectMovementSpeed(this.getObjectMovementSpeed()*(-1.0f));
                            this.fireAttack(foreignMob);
                            objectDetectedEnemy=true;
                        }
                    }
                    else if(distanceBetween>0&&Math.abs(distanceBetween)<=this.getAttackRange())
                    {
                        if(arrayOfTriangles.get(this.getObjectId()).isTextureFlipped()==true) {
                            this.setObjectMovementSpeed(this.getObjectMovementSpeed()*(-1.0f));
                            this.fireAttack(foreignMob);
                            objectDetectedEnemy=true;
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


    public Vector<Integer> getArrayOfObjectIDAllyMobs() {
        return arrayOfObjectIDAllyMobs;
    }

    public Vector<Integer> getArrayOfObjectIDEnemyMobs() {
        return arrayOfObjectIDEnemyMobs;
    }

}
