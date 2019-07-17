package com.example.game.Game;

import static com.example.game.Game.GameResourceBinder.arrayOfCastlesElementsID;
import static com.example.game.Game.GameResourceBinder.arrayOfMagicBalls;
import static com.example.game.Game.GameResourceBinder.arrayOfMovableObjectsBars;
import static com.example.game.Game.GameResourceBinder.arrayOfPlayerNoMobs;
import static com.example.game.Game.GameResourceBinder.arrayOfPlayers;
import static com.example.game.Game.GameResourceBinder.arrayOfSynchronizedElementsID;
import static com.example.game.Game.GameResourceBinder.arrayOfTriangles;
import static com.example.game.Game.GameResourceBinder.gameIsLost;
import static com.example.game.Game.GameResourceBinder.gameIsOn;
import static com.example.game.Game.GameResourceBinder.gameState;
import static com.example.game.Game.SoundReproducer.playSoundAttackCastle;
import static com.example.game.Game.SoundReproducer.playSoundAttackGoblin;
import static com.example.game.Game.SoundReproducer.playSoundAttackPlayer;
import static com.example.game.Game.SoundReproducer.playSoundAttackSpectre;
import static com.example.game.Game.SoundReproducer.playSoundDeathGoblin;
import static com.example.game.Game.SoundReproducer.playSoundDeathSpectre;
import static com.example.game.Game.SoundReproducer.playSoundLevelUp;
import static com.example.game.Game.SoundReproducer.playSoundResourceGain;

public abstract class GameMovableObject extends GameObject {
    private float objectMovementSpeed;
    private float objectJumpSpeed;
    private boolean isFrozen;
    private boolean isAlive;
    private boolean isAttacking;
    private boolean isDying;
    private float positionBeforeJump;

    private boolean isInAir;
    private boolean isGettingHeight;

    private float actualHealth;
    private float maxMovementSpeed;
    private float attack;

    private float remainingTimeToAttack;
    private float reloadTimeToNextAttack;

    private boolean specialAttackEffectTurnOn;
    private int specialEffectAttackType;

    private float agility;
    private float vitality;
    private float resourceOfObject;
    private float resourceExpOfObject;
    private float level;
    private float attackRange;

    private boolean objectInCollision;


    private float experience;
    private float actualExperience;

    private int idOfBarOfMovableObjectInArrayOfMovableBars;
    private int idOfEmptyBarOfMovableObjectInArrayOfMovableBars;

    private int whichPlayerIDTeam;

    public GameMovableObject(int objectId)
    {
        super(objectId);

        this.objectMovementSpeed=0.0f;
        this.objectJumpSpeed=0.0f;



        this.isInAir=false;
        this.isFrozen=false;
        this.isAlive=true;
        this.isAttacking=false;
        this.isDying=false;


        this.maxMovementSpeed=0.0f;
        this.reloadTimeToNextAttack =1000.0f;
        this.remainingTimeToAttack =0.0f;
        this.attack =0.0f;
        this.agility=0.0f;
        this.vitality=0.0f;
        this.resourceOfObject =0.0f;
        this.resourceExpOfObject=0.0f;
        this.level=0.0f;
        this.actualHealth =0.0f;
        this.attackRange=0.0f;

        this.experience =100f;
        this.actualExperience =0f;

        this.objectInCollision=false;


        this.idOfBarOfMovableObjectInArrayOfMovableBars =-1;

        this.whichPlayerIDTeam =-1;

        this.specialEffectAttackType =-1;

    }

    /**
     *
     * @return Returns true, if collision appeared
     */
    public boolean checkElementForCollision(int actualMovableElementID,boolean checkForXAxis)
    {
        float[] tempArrayOfTranslationsActual =arrayOfTriangles.get(actualMovableElementID).getArrayOfTranslations();
        float sizeOfActual=arrayOfTriangles.get(actualMovableElementID).getSizeOfObjectOnZ()/2.0f;
        float sizeOfActualCorrection=sizeOfActual/2.0f;



        float additionalValueForDetection=0.0f;
        if(checkForXAxis==true)
        {
            additionalValueForDetection=sizeOfActual/5.0f;
        }

        for(int tempSynchElementID:arrayOfSynchronizedElementsID)
        {
            if(actualMovableElementID!=tempSynchElementID&&arrayOfTriangles.get(tempSynchElementID).isPossibleCollision()==true) {
                float[] tempArrayOfTranslationsForeign = arrayOfTriangles.get(tempSynchElementID).getArrayOfTranslations();
                float sizeOfForeign=arrayOfTriangles.get(tempSynchElementID).getSizeOfObjectOnZ()/2.0f;


                if(((addTwoValues(tempArrayOfTranslationsActual[0],sizeOfActual))-sizeOfActualCorrection)>addTwoValues(tempArrayOfTranslationsForeign[0],-sizeOfForeign)&&
                        (addTwoValues(tempArrayOfTranslationsActual[0],-sizeOfActual)+sizeOfActualCorrection)<addTwoValues(tempArrayOfTranslationsForeign[0],sizeOfForeign)&&
                        (addTwoValues(tempArrayOfTranslationsActual[1],sizeOfActual)-additionalValueForDetection)>addTwoValues(tempArrayOfTranslationsForeign[1],-sizeOfForeign)&&
                        (addTwoValues(tempArrayOfTranslationsActual[1],-sizeOfActual)+additionalValueForDetection)<addTwoValues(tempArrayOfTranslationsForeign[1],sizeOfForeign))
                {
                    if(tempArrayOfTranslationsActual[1]-sizeOfActual<tempArrayOfTranslationsForeign[1]+sizeOfForeign)
                    {
                        //fix for players
                        for(Player tempPlayer:arrayOfPlayers) {
                            if(tempPlayer.getObjectId()==actualMovableElementID) {
                                tempPlayer.setInAir(true);
                                tempPlayer.setObjectInCollision(false);
                            }
                        }
                    }
                    return true;
                }
            }
        }
        return false;
    }


    protected float addTwoValues(float x1, float x2)
    {
        return ((Math.round(Math.round(x1*10000.0f)*1.0f+Math.round(x2*10000.0f)*1.0f))*1.0f)/10000.0f;
    }

    public void fireAttack(Mob mobToAttack)
    {
        if(this.getRemainingTimeToAttack()<=0f&&mobToAttack.getActualHealth()>0.0f)
        {
        mobToAttack.setActualHealth(mobToAttack.getActualHealth()-this.getAttack());
        mobToAttack.updatePositionOfMovableBar();

        if(mobToAttack.getActualHealth()<=0.0f)
        {
            mobToAttack.setAlive(false);
            giveRewardGExp(mobToAttack.getResourceOfObject(),mobToAttack.getResourceExpOfObject());
            if(mobToAttack.getActualHealth()<0.0f)
            {
                mobToAttack.setActualHealth(0.0f);
            }
            playProperDeathSound(mobToAttack);
        }
        playProperDirectAttackSound();

        this.setRemainingTimeToAttack(this.getReloadTimeToNextAttack());
        }
    }

    protected void playProperDeathSound(Mob mobToAttack)
    {
        if(mobToAttack.isSpecialAttackEffectTurnOn()==true)
        {
            playSoundDeathSpectre();
        }
        else
        {
            playSoundDeathGoblin();
        }
    }

    protected void playProperDirectAttackSound()
    {
        if(this.getObjectId()==arrayOfPlayers.get(0).getObjectId()||getObjectId()==arrayOfPlayerNoMobs.get(0).getObjectId())
        {
            playSoundAttackPlayer();
        }
        else if(specialAttackEffectTurnOn==false)
        {
            playSoundAttackGoblin();
        }
    }

    protected void playProperDistanceAttackSound()
    {
        if(this.getObjectId()==arrayOfCastlesElementsID.get(0)||this.getObjectId()==arrayOfCastlesElementsID.get(1))
        {
            playSoundAttackCastle();
        }
        else if(specialAttackEffectTurnOn==true) {
            playSoundAttackSpectre();
        }

    }


    public void decayReloadTime()
    {
        if(getRemainingTimeToAttack()>0.0f) {
            setRemainingTimeToAttack(getRemainingTimeToAttack() - 20f * (agility / 20.0f));
            if(getRemainingTimeToAttack()<0.0f)
            {
                setRemainingTimeToAttack(0.0f);
            }
        }
    }

    public void fireAttack(Player playerToAttack)
    {
        if(this.getRemainingTimeToAttack()<=0f&&playerToAttack.getActualHealth()>0.0f)
        {
            playerToAttack.setActualHealth(playerToAttack.getActualHealth()-this.getAttack());
            playerToAttack.updatePositionOfHPBar();

            if(playerToAttack.getActualHealth()<=0.0f)
            {
                playerToAttack.setAlive(false);
                giveRewardGExp(playerToAttack.getResourceOfObject(),playerToAttack.getResourceExpOfObject());

                if(playerToAttack.getActualHealth()<0.0f)
                {
                    playerToAttack.setActualHealth(0.0f);
                }
            }
            playProperDirectAttackSound();
            this.setRemainingTimeToAttack(this.getReloadTimeToNextAttack());
        }
    }

    private void giveRewardGExp(float resourceG,float resourceExp)
    {
        setResourceOfObject(getResourceOfObject()+resourceG);
        setActualExperience(getActualExperience()+resourceExp);
        if(checkIfPlayer()==true)
        {
            playSoundResourceGain();
        }
    }

    public void updatePositionOfMovableBar()
    {
        if(idOfBarOfMovableObjectInArrayOfMovableBars>-1) {
            //NORMAL BAR
            int idOfBarInArrayOfTriangles = arrayOfMovableObjectsBars.get(getIdOfBarOfMovableObjectInArrayOfMovableBars()).getObjectId();
            Triangle tempBarTriangle = arrayOfTriangles.get(idOfBarInArrayOfTriangles);

            float arrayOfBarTranslations[] = tempBarTriangle.getArrayOfTranslations();
            float sizeOfBar = tempBarTriangle.getSizeOfObjectOnZ();

            //EMPTY IS ALWAYS AFTER NORMAL BAR
            int idOfEmptyBarInArrayOfTriangles = arrayOfMovableObjectsBars.get(getIdOfEmptyBarOfMovableObjectInArrayOfMovableBars()).getObjectId();
            float arrayOfEmptyBarTranslations[] = arrayOfTriangles.get(idOfEmptyBarInArrayOfTriangles).getArrayOfTranslations();

            Triangle tempActualTriangle = arrayOfTriangles.get(this.getObjectId());
            float arrayOfActualObjectTranslations[] = tempActualTriangle.getArrayOfTranslations();
            float sizeOfActualObject = tempActualTriangle.getSizeOfObjectOnZ();

            //Normal
            arrayOfBarTranslations[0] = arrayOfActualObjectTranslations[0];
            arrayOfBarTranslations[1] = arrayOfActualObjectTranslations[1] + (sizeOfActualObject / 2.0f)+0.03f;

            //Empty
            arrayOfEmptyBarTranslations[0] = addTwoValues(arrayOfActualObjectTranslations[0], sizeOfBar*(getActualHealthInPercents()/100.0f));
            arrayOfEmptyBarTranslations[1] = arrayOfActualObjectTranslations[1] + (sizeOfActualObject / 2.0f)+0.03f;

        }

    }


    public int getIdOfBarOfMovableObjectInArrayOfMovableBars() {
        return idOfBarOfMovableObjectInArrayOfMovableBars;
    }

    public void setIdOfBarOfMovableObjectInArrayOfMovableBars(int idOfBarOfMovableObject) {
        this.idOfBarOfMovableObjectInArrayOfMovableBars = idOfBarOfMovableObject;
    }

    public int getIdOfEmptyBarOfMovableObjectInArrayOfMovableBars() {
        return idOfEmptyBarOfMovableObjectInArrayOfMovableBars;
    }

    public void setIdOfEmptyBarOfMovableObjectInArrayOfMovableBars(int idOfEmptyBarOfMovableObjectInArrayOfMovableBars) {
        this.idOfEmptyBarOfMovableObjectInArrayOfMovableBars = idOfEmptyBarOfMovableObjectInArrayOfMovableBars;
    }

    public boolean isAlive() {
        return isAlive;
    }

    public void setAlive(boolean alive) {
        isAlive = alive;
        if (alive == false) {
            Triangle tempTriangle=arrayOfTriangles.get(getObjectId());
            setDying(true);
            setFrozen(true);
            setAfterDeathPosition(tempTriangle);
            updatePositionOfMovableBar();
            setVisibilityOfHPBar(false);
            tempTriangle.setVisible(false);
            if(specialAttackEffectTurnOn==true)
            {
                for(MagicBall tempMagicBall:arrayOfMagicBalls)
                {
                    if(tempMagicBall.getAttackSourceTriangleObjectId()==getObjectId())
                    {
                        Triangle tempMagicBallTriangleObject=arrayOfTriangles.get(tempMagicBall.getObjectId());
                        float[] tempArrayOfTranslationsMagicBall=tempMagicBallTriangleObject.getArrayOfTranslations();
                        tempMagicBall.destroyBall(tempMagicBallTriangleObject,tempArrayOfTranslationsMagicBall);
                        break;
                    }
                }
            }

            if(checkIfRightCastleDestroyed()==true)
            {
                gameIsOn=false;
                gameIsLost=false;
                gameState=2;
            }

            if(checkIfLeftCastleDestroyed()==true|| checkIfPlayer()==true)
            {
                gameIsOn=false;
                gameIsLost=true;
                gameState=2;
            }

        }
        else if(alive==true)
        {
                Triangle tempTriangle=arrayOfTriangles.get(getObjectId());
                setAfterDeathPosition(tempTriangle);
                setDying(false);
                setFrozen(false);
                setActualHealth(getVitality());
                tempTriangle.setVisible(true);
                setVisibilityOfHPBar(true);

        }
    }

    private void setVisibilityOfHPBar(boolean visible)
    {
        if(idOfBarOfMovableObjectInArrayOfMovableBars>-1&&idOfEmptyBarOfMovableObjectInArrayOfMovableBars>-1) {
            int triangleIDHpBar = arrayOfMovableObjectsBars.get(idOfBarOfMovableObjectInArrayOfMovableBars).getObjectId();
            int triangleIDHpEmptyBar = arrayOfMovableObjectsBars.get(idOfEmptyBarOfMovableObjectInArrayOfMovableBars).getObjectId();
            arrayOfTriangles.get(triangleIDHpBar).setVisible(visible);
            arrayOfTriangles.get(triangleIDHpEmptyBar).setVisible(visible);
        }
    }

    private boolean checkIfRightCastleDestroyed()
    {
        for(int tempCastleID: arrayOfCastlesElementsID)
        {
            if(tempCastleID==getObjectId()&&getWhichPlayerIDTeam()%2.0!=0)
            {
                return true;
            }
        }
        return false;
    }

    private boolean checkIfLeftCastleDestroyed()
    {
        for(int tempCastleID: arrayOfCastlesElementsID)
        {
            if(tempCastleID==getObjectId()&&getWhichPlayerIDTeam()%2.0==0)
            {
                return true;
            }
        }
        return false;
    }

    private boolean checkIfPlayer()
    {
        if(arrayOfPlayers.get(0).getObjectId()==getObjectId())
        {
            return true;
        }
        return false;
    }

    private void setAfterDeathPosition(Triangle tempTriangle)
    {
        float [] tempTranslationsArray=tempTriangle.getArrayOfTranslations();
        if(getWhichPlayerIDTeam()%2==0) {
            tempTranslationsArray[0] = -1.0f;
        }
        else
        {
            tempTranslationsArray[0] = 6.0f;
        }
        updatePositionOfMovableBar();


    }

    public float getReloadTimeToNextAttack() {
        return reloadTimeToNextAttack;
    }


    public boolean isAttacking() {
        return isAttacking;
    }

    public void setAttacking(boolean attacking) {
        isAttacking = attacking;
    }

    public void setDying(boolean dying) {
        isDying = dying;
    }

    public float getActualHealth() {
        return actualHealth;
    }

    public void setActualHealth(float actualHealth) {
            this.actualHealth = actualHealth;
    }

    public float getActualHealthInPercents()
    {
        return ((float)Math.round((actualHealth / this.vitality)*100.0f));
    }

    public float getActualExperienceInPercents()
    {
        return ((float)Math.round((actualExperience / this.experience)*100.0f));
    }

    public float getMaxMovementSpeed() {
        return maxMovementSpeed;
    }

    public void setMaxMovementSpeed(float maxMovementSpeed) {
        this.maxMovementSpeed = maxMovementSpeed;
    }

    public float getAttack() {
        return attack;
    }

    public void setAttack(float attack) {
        this.attack = attack;
    }

    public float getRemainingTimeToAttack() {
        return remainingTimeToAttack;
    }

    public void setRemainingTimeToAttack(float remainingTimeToAttack) {
        this.remainingTimeToAttack = remainingTimeToAttack;
    }

    public float getAgility() {
        return agility;
    }

    public void setAgility(float agility) {
        this.agility = agility;
    }

    public float getVitality() {
        return vitality;
    }

    public void setVitality(float vitality) {
        this.vitality = vitality;
    }

    public float getResourceOfObject() {
        return resourceOfObject;
    }

    public void setResourceOfObject(float resourceOfObject) {
        this.resourceOfObject = resourceOfObject;
    }

    public float getResourceExpOfObject() {
        return resourceExpOfObject;
    }

    public void setResourceExpOfObject(float resourceExpOfObject) {
        this.resourceExpOfObject = resourceExpOfObject;
    }

    public float getAttackRange() {
        return attackRange;
    }

    public void setAttackRange(float attackRange) {
        this.attackRange = attackRange;
    }

    public float getActualExperience() {
        return actualExperience;
    }

    public void setActualExperience(float actualExperience) {
        if(((int)Math.round(getLevel()))<30) {
            this.actualExperience = actualExperience;
            if (actualExperience - experience >= 0f) {
                setActualExperience(actualExperience - experience);
                setLevel(getLevel() + 1.0f);
                setLevelUpAttributeBonus();
                if(checkIfPlayer()==true)
                {
                    playSoundLevelUp();
                }
            }
        }

    }

    public void setExperience(float experience) {
        this.experience = experience;
    }

    public float getLevel() {
        return level;
    }

    public void setLevel(float level) {
        this.level = level;
        setProperExperienceNeedForLevel(level);
    }

    private void setLevelUpAttributeBonus()
    {
        setAttack(getAttack()+5*(getLevel()-1.0f));
        setAgility(getAgility()+5f*(getLevel()-1.0f));
        setVitality(getVitality()+50f*(getLevel()-1.0f));
        setActualHealth(getVitality());
        updatePositionOfMovableBar();
    }

    public boolean isObjectInCollision() {
        return objectInCollision;
    }

    public int getSpecialEffectAttackType() {
        return specialEffectAttackType;
    }

    public void setObjectInCollision(boolean objectInCollision) {
        this.objectInCollision = objectInCollision;
    }

    public boolean isFrozen() {
        return isFrozen;
    }

    public void setFrozen(boolean frozen) {
        isFrozen = frozen;
    }

    public float getPositionBeforeJump() {
        return positionBeforeJump;
    }

    public void setPositionBeforeJump(float positionBeforeJump) {
        this.positionBeforeJump = positionBeforeJump;
    }

    public float getObjectMovementSpeed() {
        return objectMovementSpeed;
    }

    public void setObjectMovementSpeed(float addToObjectMovementSpeed) {
        this.objectMovementSpeed=addTwoValues(getObjectMovementSpeed(),addToObjectMovementSpeed);
    }

    public float getObjectJumpSpeed() {
        return objectJumpSpeed;
    }

    public void setObjectJumpSpeed(float objectJumpSpeed) {
        this.objectJumpSpeed = objectJumpSpeed;
    }

    public boolean isSpecialAttackEffectTurnOn() {
        return specialAttackEffectTurnOn;
    }

    public void setSpecialAttackEffect(boolean specialAttackEffectTurnOn, int specialEffectType) {
        this.specialAttackEffectTurnOn = specialAttackEffectTurnOn;
        this.specialEffectAttackType =specialEffectType;
    }

    public boolean isInAir() {
        return isInAir;
    }

    public void setInAir(boolean inAir) {
        isInAir = inAir;
    }

    public boolean isGettingHeight() {
        return isGettingHeight;
    }

    public void setGettingHeight(boolean gettingHeight) {
        isGettingHeight = gettingHeight;
    }

    public int getWhichPlayerIDTeam() {
        return whichPlayerIDTeam;
    }

    public void setWhichPlayerIDTeam(int whichPlayerIDTeam) {
        this.whichPlayerIDTeam = whichPlayerIDTeam;
    }

    abstract void setProperExperienceNeedForLevel(float level);




}
