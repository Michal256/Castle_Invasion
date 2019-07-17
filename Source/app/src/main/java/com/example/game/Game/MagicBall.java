package com.example.game.Game;

import static com.example.game.Game.GameResourceBinder.arrayOfMobs;
import static com.example.game.Game.GameResourceBinder.arrayOfPlayers;
import static com.example.game.Game.GameResourceBinder.arrayOfTriangles;
public class MagicBall extends GameObject {
    private int attackSourceTriangleObjectId;
    private int attackTargetTriangleObjectId;
    private float objectSpeed;
    private float lifeTime;

    private boolean frozen;

    public MagicBall(int objectId,int attackSourceTriangleObjectId,int attackTargetTriangleObjectId,float objectSpeed) {
        super(objectId);
        this.attackSourceTriangleObjectId=attackSourceTriangleObjectId;
        this.attackTargetTriangleObjectId=attackTargetTriangleObjectId;
        this.objectSpeed=objectSpeed;
        this.frozen=true;
        this.lifeTime=0.0f;
    }

    public void handleMagicBall()
    {
        if(isFrozen()==false) {
            moveMagicBallToTheTarget();
            checkElementForCollision();
            handleLifeTimeOfBall();
        }

    }

    private void handleLifeTimeOfBall()
    {
        lifeTime=lifeTime-20.0f;
        if(lifeTime<=0.0f)
        {
            Triangle tempMagicBallTriangleObject=arrayOfTriangles.get(getObjectId());
            destroyBall(tempMagicBallTriangleObject,tempMagicBallTriangleObject.getArrayOfTranslations());
        }
    }

    private boolean checkElementForCollision()
    {
        Triangle tempMagicBallTriangleObject=arrayOfTriangles.get(getObjectId());
        Triangle tempTargetTriangleObject=arrayOfTriangles.get(attackTargetTriangleObjectId);

        float[] tempArrayOfTranslationsMagicBall=tempMagicBallTriangleObject.getArrayOfTranslations();
        float[] tempArrayOfTranslationsTarget=tempTargetTriangleObject.getArrayOfTranslations();

        float sizeOfMagicBall=tempMagicBallTriangleObject.getSizeOfObjectOnZ()/2.0f;
        float sizeOfTarget=tempTargetTriangleObject.getSizeOfObjectOnZ()/2.0f;

        float sizeOfMagicBallCorrection=sizeOfMagicBall/2.0f;

        float additionalValueForDetection=sizeOfMagicBall/8.0f;

        if((addTwoValues(addTwoValues(tempArrayOfTranslationsMagicBall[0],sizeOfMagicBall),-sizeOfMagicBallCorrection))>addTwoValues(tempArrayOfTranslationsTarget[0],-sizeOfTarget)&&
                (addTwoValues(addTwoValues(tempArrayOfTranslationsMagicBall[0],-sizeOfMagicBall),sizeOfMagicBallCorrection))<addTwoValues(tempArrayOfTranslationsTarget[0],sizeOfTarget)&&
                (addTwoValues(addTwoValues(tempArrayOfTranslationsMagicBall[1],sizeOfMagicBall),-additionalValueForDetection))>addTwoValues(tempArrayOfTranslationsTarget[1],-sizeOfTarget)&&
                (addTwoValues(addTwoValues(tempArrayOfTranslationsMagicBall[1],-sizeOfMagicBall),additionalValueForDetection))<addTwoValues(tempArrayOfTranslationsTarget[1],sizeOfTarget))
        {
            if(tempArrayOfTranslationsMagicBall[1]-sizeOfMagicBall<tempArrayOfTranslationsTarget[1]+sizeOfTarget)
            {
                fireAttack();
                destroyBall(tempMagicBallTriangleObject,tempArrayOfTranslationsMagicBall);
            }
            return true;
        }
        return false;
    }

    public void destroyBall(Triangle tempMagicBallTriangleObject,float[] tempArrayOfTranslationsMagicBall)
    {
        this.setFrozen(true);
        tempArrayOfTranslationsMagicBall[0]=-5.0f;
        tempArrayOfTranslationsMagicBall[1]=0.0f;
        tempMagicBallTriangleObject.setVisible(false);


        attackSourceTriangleObjectId=-1;
        attackTargetTriangleObjectId=-1;

        lifeTime=0.0f;

    }

    private void fireAttack()
    {
        int sourceArrayID=-1;
        int targetArrayID=-1;

        boolean sourceIsPlayer=false;
        boolean targetIsPlayer=false;

        boolean sourceIsMob=false;
        boolean targetIsMob=false;


        int i=0;
        for(Mob tempMob:arrayOfMobs)
        {
            if(tempMob.getObjectId()==attackSourceTriangleObjectId)
            {
                sourceArrayID=i;
                sourceIsMob=true;
            }

            if(tempMob.getObjectId()==attackTargetTriangleObjectId)
            {
                targetArrayID=i;
                targetIsMob=true;
            }
            i++;
        }

        i=0;
        for(Player tempPlayer:arrayOfPlayers)
        {
            if(tempPlayer.getObjectId()==attackSourceTriangleObjectId)
            {
                sourceArrayID=i;
                sourceIsPlayer=true;
            }

            if(tempPlayer.getObjectId()==attackTargetTriangleObjectId)
            {
                targetArrayID=i;
                targetIsPlayer=true;
            }
            i++;
        }


        if(sourceArrayID!=-1&&targetArrayID!=-1)
        {
            if(sourceIsMob==true&&targetIsMob==true) {
                arrayOfMobs.get(sourceArrayID).fireAttack(arrayOfMobs.get(targetArrayID));
            }
            else if(sourceIsMob==true&&targetIsPlayer==true)
            {
                arrayOfMobs.get(sourceArrayID).fireAttack(arrayOfPlayers.get(targetArrayID));
            }
            else if(sourceIsPlayer==true&&targetIsPlayer==true)
            {
                arrayOfPlayers.get(sourceArrayID).fireAttack(arrayOfPlayers.get(targetArrayID));
            }
            else if(sourceIsPlayer==true&&targetIsMob==true)
            {
                arrayOfPlayers.get(sourceArrayID).fireAttack(arrayOfMobs.get(targetArrayID));
            }
        }
    }

    private void moveMagicBallToTheTarget()
    {
        Triangle tempMagicBallTriangleObject=arrayOfTriangles.get(getObjectId());
        Triangle tempTargetTriangleObject=arrayOfTriangles.get(attackTargetTriangleObjectId);

        float[] tempArrayOfTranslationsMagicBall=tempMagicBallTriangleObject.getArrayOfTranslations();
        float[] tempArrayOfTranslationsTarget=tempTargetTriangleObject.getArrayOfTranslations();

        float sizeOfMagicBall=tempMagicBallTriangleObject.getSizeOfObjectOnZ()/2.0f;

        if(tempArrayOfTranslationsMagicBall[0]+sizeOfMagicBall<tempArrayOfTranslationsTarget[0])
        {
            tempArrayOfTranslationsMagicBall[0]=addTwoValues(tempArrayOfTranslationsMagicBall[0],getObjectSpeed());
        }
        else if(tempArrayOfTranslationsMagicBall[0]-sizeOfMagicBall>tempArrayOfTranslationsTarget[0])
        {
            tempArrayOfTranslationsMagicBall[0]=addTwoValues(tempArrayOfTranslationsMagicBall[0],(-1.0f)*getObjectSpeed());
        }

        if(tempArrayOfTranslationsMagicBall[1]+sizeOfMagicBall<tempArrayOfTranslationsTarget[1])
        {
            tempArrayOfTranslationsMagicBall[1]=addTwoValues(tempArrayOfTranslationsMagicBall[1],getObjectSpeed());
        }
        else if(tempArrayOfTranslationsMagicBall[1]-sizeOfMagicBall>tempArrayOfTranslationsTarget[1])
        {
            tempArrayOfTranslationsMagicBall[1]=addTwoValues(tempArrayOfTranslationsMagicBall[1],(-1.0f)*getObjectSpeed());
        }
    }

    public void setFirstPositionToTheSource()
    {
        float[] tempArrayOfTranslations=arrayOfTriangles.get(getObjectId()).getArrayOfTranslations();

        Triangle tempSourceTriangleObject=arrayOfTriangles.get(attackSourceTriangleObjectId);

        tempArrayOfTranslations[0]=tempSourceTriangleObject.getArrayOfTranslations()[0];
        tempArrayOfTranslations[1]=tempSourceTriangleObject.getArrayOfTranslations()[1];

    }

    private float addTwoValues(float x1, float x2)
    {
        return ((Math.round(Math.round(x1*10000.0f)*1.0f+Math.round(x2*10000.0f)*1.0f))*1.0f)/10000.0f;
    }

    public int getAttackSourceTriangleObjectId() {
        return attackSourceTriangleObjectId;
    }

    public void setAttackSourceTriangleObjectId(int attackSourceTriangleObjectId) {
        this.attackSourceTriangleObjectId = attackSourceTriangleObjectId;
    }

    public int getAttackTargetTriangleObjectId() {
        return attackTargetTriangleObjectId;
    }

    public void setAttackTargetTriangleObjectId(int attackTargetTriangleObjectId) {
        this.attackTargetTriangleObjectId = attackTargetTriangleObjectId;
    }

    public float getObjectSpeed() {
        return objectSpeed;
    }

    public boolean isFrozen() {
        return frozen;
    }

    public float getLifeTime() {
        return lifeTime;
    }

    public void setLifeTime(float lifeTime) {
        this.lifeTime = lifeTime;
    }

    public void setFrozen(boolean frozen) {
        this.frozen = frozen;
    }
}
