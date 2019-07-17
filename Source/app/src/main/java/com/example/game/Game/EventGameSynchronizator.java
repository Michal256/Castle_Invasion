package com.example.game.Game;

import android.opengl.GLSurfaceView;

import static com.example.game.Game.GameResourceBinder.arrayOfElementsWithText;
import static com.example.game.Game.GameResourceBinder.arrayOfMagicBalls;
import static com.example.game.Game.GameResourceBinder.arrayOfMobs;
import static com.example.game.Game.GameResourceBinder.arrayOfPlayers;
import static com.example.game.Game.GameResourceBinder.arrayOfTriangles;
import static com.example.game.Game.GameResourceBinder.gameIsOn;
import static com.example.game.Game.GameResourceBinder.gameLoaded;
import static com.example.game.Game.GameResourceBinder.gameState;
import static com.example.game.Game.GameResourceBinder.gameTimeInMilliseconds;
import static com.example.game.Game.GameResourceBinder.gameTimeInSeconds;
import static com.example.game.Game.GameResourceBinder.goblinDelayLeftRespawn;

import static com.example.game.Game.GameResourceBinder.goblinDelayRightRespawn;
import static com.example.game.Game.GameResourceBinder.numberOfGoblinsLeftToRespawn;
import static com.example.game.Game.GameResourceBinder.numberOfGoblinsRightToRespawn;

import static com.example.game.Game.GameResourceBinder.numberOfSpectresLeftToRespawn;
import static com.example.game.Game.GameResourceBinder.numberOfSpectresRightToRespawn;

import static com.example.game.Game.GameResourceBinder.spectreDelayLeftRespawn;

import static com.example.game.Game.GameResourceBinder.spectreDelayRightRespawn;
import static com.example.game.Game.GameResourceBinder.timeToNextWaveMilliseconds;
import static com.example.game.Game.GameResourceBinder.waveNumber;
import static com.example.game.Game.TextureObjectHandler.changeGoblinTexture;
import static com.example.game.Game.TextureObjectHandler.changePlayerTexture;
import static com.example.game.Game.TextureObjectHandler.changeTimerTexture;
import static com.example.game.Game.UIBitmapTextHandler.uiEndMessageHandler;
import static com.example.game.Game.UIBitmapTextHandler.uiWelcomeMessageHandler;
import static com.example.game.Game.UserSettingsBinder.gameTactic;

public class EventGameSynchronizator implements Runnable {
    GLSurfaceView MyGLSurfaceView;
    public EventGameSynchronizator(GLSurfaceView MyGLSurfaceView)
    {
        this.MyGLSurfaceView=MyGLSurfaceView;
    }
    public void run()
    {
        try
        {
            MyGLSurfaceView.queueEvent(new Runnable() {
                @Override
                public void run() {
                    if(gameLoaded==true) {
                        if (gameIsOn == true&&gameState==1) {
                            if (!arrayOfPlayers.isEmpty()) {
                                Player tempPlayer = arrayOfPlayers.get(0);
                                Triangle tempTriangle = arrayOfTriangles.get(tempPlayer.getObjectId());
                                if (tempPlayer.isAlive() == true && tempPlayer.isFrozen() == false) {
                                    if (tempPlayer.isAttacking() == true) {
                                        prepareTextureToAttack(tempTriangle);
                                        changePlayerTexture(tempPlayer, true, false);
                                        tempPlayer.checkForEnemies();

                                    }
                                    movePlayer(tempPlayer);
                                    tempPlayer.decayReloadTime();
                                }

                            }

                            if (!arrayOfMobs.isEmpty()) {
                                for (Mob tempMob : arrayOfMobs) {
                                    if (tempMob.isAlive() == true) {
                                        tempMob.checkForEnemies();
                                        if (tempMob.isAttacking() == true) {
                                            changeGoblinTexture(tempMob, true, false);
                                        } else if (tempMob.getObjectMovementSpeed() != 0.0f) {
                                            changeGoblinTexture(tempMob, false, false);
                                        }
                                        tempMob.moveMob();
                                        tempMob.decayReloadTime();
                                    }
                                }

                            }

                            if (!arrayOfMagicBalls.isEmpty()) {
                                for (MagicBall tempMagicBall : arrayOfMagicBalls) {
                                    if (tempMagicBall.isFrozen() == false&&tempMagicBall.getAttackSourceTriangleObjectId()!=-1&&tempMagicBall.getAttackTargetTriangleObjectId()!=-1) {
                                        tempMagicBall.handleMagicBall();
                                    }
                                }

                            }

                            handleMobRespawnDelayTime();
                            handleLeftWave();
                            handleRightWave();
                            handleMobRespawn();


                        }
                        else if(gameIsOn==false&&gameState==2)
                        {
                            arrayOfTriangles.get(arrayOfElementsWithText.get(40)).setVisible(true);
                            uiEndMessageHandler();
                        }
                        else if(gameIsOn==false&&gameState==0)
                        {
                            uiWelcomeMessageHandler();
                            if(gameTimeInSeconds>=13)
                            {
                                gameIsOn=true;
                                gameState=1;
                                arrayOfTriangles.get(arrayOfElementsWithText.get(39)).setVisible(false);
                            }
                        }
                        if(gameState<2) {
                            addGameTime(20);
                        }
                    }
                }
            });

            Thread.sleep(20);
        }
        catch(InterruptedException e)
        {
            System.out.println(e);
        }

    }

    private void handleMobRespawnDelayTime()
    {
        if(goblinDelayLeftRespawn>0.0f) {
            goblinDelayLeftRespawn -= 20f;
        }

        if(spectreDelayLeftRespawn>0.0f)
        {
            spectreDelayLeftRespawn-=20f;
        }

        if(goblinDelayRightRespawn>0.0f) {
            goblinDelayRightRespawn -= 20f;
        }

        if(spectreDelayRightRespawn>0.0f)
        {
            spectreDelayRightRespawn-=20f;
        }
    }

    private void handleMobRespawn()
    {
        if(timeToNextWaveMilliseconds>0.0f) {
            timeToNextWaveMilliseconds -= 20.0f;
        }

        if(timeToNextWaveMilliseconds<=0)
        {
            waveNumber++;
            timeToNextWaveMilliseconds=30000;

            leftMobsWaveHandle();
            rightMobsWaveHandle();


        }

    }

    private void leftMobsWaveHandle()
    {
        if(gameTactic==1)
        {
            if(waveNumber>=1&&waveNumber<=2)
            {
                numberOfGoblinsLeftToRespawn+=2;
                numberOfSpectresLeftToRespawn=0;

            }
            else if(waveNumber>=3&&waveNumber<=6)
            {
                numberOfGoblinsLeftToRespawn+=3;
                numberOfSpectresLeftToRespawn=0;

            }
            else if(waveNumber>=7&&waveNumber<=10)
            {
                numberOfGoblinsLeftToRespawn+=4;
                numberOfSpectresLeftToRespawn+=1;
            }
            else if(waveNumber>=11&&waveNumber<=15)
            {
                numberOfGoblinsLeftToRespawn+=4;
                numberOfSpectresLeftToRespawn+=2;
            }
            else if(waveNumber>=16)
            {
                numberOfGoblinsLeftToRespawn+=5;
                numberOfSpectresLeftToRespawn+=5;
            }
        }
        else if(gameTactic==2)
        {
            if(waveNumber>=1&&waveNumber<=2)
            {
                numberOfGoblinsLeftToRespawn=0;
                numberOfSpectresLeftToRespawn+=2;

            }
            else if(waveNumber>=3&&waveNumber<=6)
            {
                numberOfGoblinsLeftToRespawn+=1;
                numberOfSpectresLeftToRespawn+=3;

            }
            else if(waveNumber>=7&&waveNumber<=10)
            {
                numberOfGoblinsLeftToRespawn+=2;
                numberOfSpectresLeftToRespawn+=4;
            }
            else if(waveNumber>=11&&waveNumber<=15)
            {
                numberOfGoblinsLeftToRespawn+=3;
                numberOfSpectresLeftToRespawn+=5;
            }
            else if(waveNumber>=16)
            {
                numberOfGoblinsLeftToRespawn+=5;
                numberOfSpectresLeftToRespawn+=5;
            }
        }
        else
        {
            if(waveNumber>=1&&waveNumber<=2)
            {
                numberOfGoblinsLeftToRespawn+=1;
                numberOfSpectresLeftToRespawn+=1;

            }
            else if(waveNumber>=3&&waveNumber<=6)
            {
                numberOfGoblinsLeftToRespawn+=2;
                numberOfSpectresLeftToRespawn+=2;
            }
            else if(waveNumber>=7&&waveNumber<=10)
            {
                numberOfGoblinsLeftToRespawn+=3;
                numberOfSpectresLeftToRespawn+=3;
            }
            else if(waveNumber>=11&&waveNumber<=15)
            {
                numberOfGoblinsLeftToRespawn+=4;
                numberOfSpectresLeftToRespawn+=4;
            }
            else if(waveNumber>=16)
            {
                numberOfGoblinsLeftToRespawn+=5;
                numberOfSpectresLeftToRespawn+=5;
            }
        }
    }

    private void rightMobsWaveHandle()
    {
        if(waveNumber>=1&&waveNumber<=2)
        {
            numberOfGoblinsRightToRespawn+=1;
            numberOfSpectresRightToRespawn+=1;

        }
        else if(waveNumber>=3&&waveNumber<=6)
        {
            numberOfGoblinsRightToRespawn+=2;
            numberOfSpectresRightToRespawn+=2;
        }
        else if(waveNumber>=7&&waveNumber<=10)
        {
            numberOfGoblinsRightToRespawn+=3;
            numberOfSpectresRightToRespawn+=3;
        }
        else if(waveNumber>=11&&waveNumber<=15)
        {
            numberOfGoblinsRightToRespawn+=4;
            numberOfSpectresRightToRespawn+=4;
        }
        else if(waveNumber>=16)
        {
            numberOfGoblinsRightToRespawn+=5;
            numberOfSpectresRightToRespawn+=5;
        }

    }

    private void handleLeftWave()
    {

        if(numberOfGoblinsLeftToRespawn>0&&goblinDelayLeftRespawn<=0.0f) {
            boolean leftGoblinRespawned=false;
            Mob tempMobLeft=arrayOfMobs.get(0);
            //Select Goblin LEFT for wave
            for (int i = 0; i < 10; i++) {
                tempMobLeft = arrayOfMobs.get(i);
                if (tempMobLeft.isAlive() == false) {
                    leftGoblinRespawned=true;
                    break;
                }

            }

            if(leftGoblinRespawned==true)
            {
                numberOfGoblinsLeftToRespawn--;
                goblinDelayLeftRespawn=1000f;
                tempMobLeft.setAlive(true);
            }
        }

        if(numberOfSpectresLeftToRespawn>0&&spectreDelayLeftRespawn<=0.0f) {
            boolean leftSpectreRespawned=false;
            Mob tempMobLeft=arrayOfMobs.get(0);
            //Select Goblin LEFT for wave
            for (int i = 10; i < 20; i++) {
                tempMobLeft = arrayOfMobs.get(i);
                if (tempMobLeft.isAlive() == false) {
                    leftSpectreRespawned=true;
                    break;
                }

            }


            if(leftSpectreRespawned==true)
            {
                numberOfSpectresLeftToRespawn--;
                spectreDelayLeftRespawn=1000f;
                tempMobLeft.setAlive(true);
            }
        }
    }

    private void handleRightWave()
    {
        if(numberOfGoblinsRightToRespawn>0&&goblinDelayRightRespawn<=0.0f) {
            boolean rightGoblinRespawned = false;
            Mob tempMobRight = arrayOfMobs.get(20);

            for (int i = 20; i < 30; i++) {
                tempMobRight = arrayOfMobs.get(i);
                if (tempMobRight.isAlive() == false) {
                    rightGoblinRespawned = true;
                    break;
                }
            }

            if (rightGoblinRespawned == true) {
                numberOfGoblinsRightToRespawn--;
                goblinDelayRightRespawn = 1000f;
                tempMobRight.setAlive(true);
            }

        }
        if(numberOfSpectresRightToRespawn>0&&spectreDelayRightRespawn<=0.0f) {

            boolean rightSpectreRespawned=false;
            Mob tempMobRight=arrayOfMobs.get(30);

            for (int i = 30; i < 40; i++) {
                tempMobRight=arrayOfMobs.get(i);
                if (tempMobRight.isAlive() == false) {
                    rightSpectreRespawned=true;
                    break;
                }

            }

            if(rightSpectreRespawned==true)
            {
                numberOfSpectresRightToRespawn--;
                spectreDelayRightRespawn=1000f;
                tempMobRight.setAlive(true);
            }
        }
    }


    private void prepareTextureToAttack(Triangle tempTriangle)
    {
        if(tempTriangle.isTextureFlipped()==true&&tempTriangle.isPossibleToFlip()==true) {
            if(tempTriangle.getActualTextureSet()<19)
            {
                tempTriangle.setActualTextureSet(19);
            }
        }
        else if(tempTriangle.isTextureFlipped()==false&&tempTriangle.isPossibleToFlip()==true)
        {
            if(tempTriangle.getActualTextureSet()<18)
            {
                tempTriangle.setActualTextureSet(18);
            }
        }
        else
        {
            if(tempTriangle.getActualTextureSet()<9)
            {
                tempTriangle.setActualTextureSet(9);
            }
        }
    }

    private void movePlayer(Player tempPlayer)
   {
       if (tempPlayer.isFrozen() == false&&tempPlayer.getObjectMovementSpeed()!=0.0f) {
           if(tempPlayer.isAttacking()==false) {
               changePlayerTexture(tempPlayer, false,false);
           }
           tempPlayer.movePlayer();
           if(tempPlayer.checkElementForCollision(tempPlayer.getObjectId(),true)==true)
           {
               int safePoint=5;
               do {
                   tempPlayer.setObjectMovementSpeed(tempPlayer.getObjectMovementSpeed() * (-1.0f) - tempPlayer.getObjectMovementSpeed());
                   tempPlayer.movePlayer();
                   safePoint--;
                   tempPlayer.setObjectMovementSpeed(tempPlayer.getObjectMovementSpeed()*(-1.0f));
               }while(tempPlayer.checkElementForCollision(tempPlayer.getObjectId(),true)==true&&safePoint>0);
           }
       }

       if(tempPlayer.isFrozen()==false)
       {
           tempPlayer.jumpPlayer();

           if(tempPlayer.checkElementForCollision(tempPlayer.getObjectId(),false)==true)
           {
               tempPlayer.setGettingHeight(false);
               tempPlayer.setInAir(false);
               tempPlayer.setObjectInCollision(true);
           }
           else if(arrayOfTriangles.get(tempPlayer.getObjectId()).getArrayOfTranslations()[1]>tempPlayer.getPositionBeforeJump()&&tempPlayer.isGettingHeight()==false&&tempPlayer.isInAir()==false&&tempPlayer.isObjectInCollision()==true)
           {
               tempPlayer.setInAir(true);
               tempPlayer.setObjectInCollision(false);
           }

       }
       tempPlayer.setPlayerMoving(false);
   }


    private void addGameTime(int milliseconds)
    {
        gameTimeInMilliseconds=gameTimeInMilliseconds+milliseconds;

        if(gameTimeInMilliseconds>=1000)
        {
            gameTimeInSeconds++;
            gameTimeInMilliseconds=0;
            changeTimerTexture();
        }
    }




}
