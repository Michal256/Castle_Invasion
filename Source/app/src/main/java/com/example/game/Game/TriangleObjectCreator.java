package com.example.game.Game;


import android.content.Context;

import com.example.game.R;

import java.util.Vector;

import static com.example.game.Game.GameResourceBinder.arrayOfBars;
import static com.example.game.Game.GameResourceBinder.arrayOfCastlesElementsID;
import static com.example.game.Game.GameResourceBinder.arrayOfElementsWithText;
import static com.example.game.Game.GameResourceBinder.arrayOfMagicBalls;
import static com.example.game.Game.GameResourceBinder.arrayOfMobs;
import static com.example.game.Game.GameResourceBinder.arrayOfMovableObjectsBars;
import static com.example.game.Game.GameResourceBinder.arrayOfPads;
import static com.example.game.Game.GameResourceBinder.arrayOfPlayerNoMobs;
import static com.example.game.Game.GameResourceBinder.arrayOfPlayers;
import static com.example.game.Game.GameResourceBinder.arrayOfTriangles;
import static com.example.game.Game.GameResourceBinder.arrayOfUIButtons;
import static com.example.game.Game.GameResourceBinder.arrayOfDynamicBackgroundElementsID;
import static com.example.game.Game.GameResourceBinder.arrayOfSynchronizedElementsID;
import static com.example.game.Game.GameResourceBinder.arrayOfFilteringExceptions;


public class TriangleObjectCreator {


    //DEV OPTION CAN NOT BE 0!
    //SCALE FROM 0.1 to 2.0, increment value by 0.1
    private float objectsScaleFactor=2.0f;
    private float uiScaleFactor=2.0f;

    public TriangleObjectCreator(Context context){

        createBackground();
        createElementsOfBoard();
        createElementsOfUI();
        createTextForUIElements(context);
        setValuesAfterGameCreation();
    }

    private void createBackground(){
        drawSky();
        drawGrass();

    }

    private void createElementsOfBoard()
    {



        //ELEMENT HERO/Camera
        //We divide because this will be position of camera in real
        //We just zoom to this object to see it and other objects are just far away in x.
        //This object is just pointer to certain place on screen.
        //DO NOT DELETE
//        createGameObject(-1f/(objectsScaleFactor),0.0f,-13.0f,true,R.drawable.grass);

        //Textures

        Vector<Integer> playerTextures=new Vector<>();
        playerTextures.add(R.drawable.mp1);
        playerTextures.add(R.drawable.mp2);
        playerTextures.add(R.drawable.mp3);
        playerTextures.add(R.drawable.mp4);
        playerTextures.add(R.drawable.mp5);
        playerTextures.add(R.drawable.mp6);
        playerTextures.add(R.drawable.mp7);
        playerTextures.add(R.drawable.mp8);
        playerTextures.add(R.drawable.mp9);
        playerTextures.add(R.drawable.mp10);

        playerTextures.add(R.drawable.ap1);
        playerTextures.add(R.drawable.ap2);
        playerTextures.add(R.drawable.ap3);
        playerTextures.add(R.drawable.ap4);
        playerTextures.add(R.drawable.ap5);
        playerTextures.add(R.drawable.ap6);
        playerTextures.add(R.drawable.ap7);
        playerTextures.add(R.drawable.ap8);
        playerTextures.add(R.drawable.ap9);
        playerTextures.add(R.drawable.ap10);

        playerTextures.add(R.drawable.fp1);
        playerTextures.add(R.drawable.fp2);
        playerTextures.add(R.drawable.fp3);
        playerTextures.add(R.drawable.fp4);
        playerTextures.add(R.drawable.fp5);
        playerTextures.add(R.drawable.fp6);
        playerTextures.add(R.drawable.fp7);
        playerTextures.add(R.drawable.fp8);
        playerTextures.add(R.drawable.fp9);
        playerTextures.add(R.drawable.fp10);


        Vector<Integer> noMobTextures=new Vector<>();
        noMobTextures.add(R.drawable.grass);

        Vector<Integer> goblinMobTextures=new Vector<>();
        goblinMobTextures.add(R.drawable.mg1);
        goblinMobTextures.add(R.drawable.mg2);
        goblinMobTextures.add(R.drawable.mg3);
        goblinMobTextures.add(R.drawable.mg4);
        goblinMobTextures.add(R.drawable.mg5);
        goblinMobTextures.add(R.drawable.mg6);
        goblinMobTextures.add(R.drawable.mg7);
        goblinMobTextures.add(R.drawable.mg8);

        goblinMobTextures.add(R.drawable.ag1);
        goblinMobTextures.add(R.drawable.ag2);
        goblinMobTextures.add(R.drawable.ag3);

        Vector<Integer> spectreMobTextures=new Vector<>();
        spectreMobTextures.add(R.drawable.g1);

        Vector<Integer> magicBallTextures=new Vector<>();
        magicBallTextures.add(R.drawable.b1);
        magicBallTextures.add(R.drawable.b2);


        //OBJECT CREATION
        createPlayer(playerTextures);
        createPlayerNoMob(noMobTextures);

        for(int i=0;i<10;i++) {
            createLeftGoblin(goblinMobTextures);
        }
        for(int i=0;i<10;i++) {
            createLeftSpectre(spectreMobTextures);
        }

        for(int i=0;i<10;i++) {
            createRightGoblin(goblinMobTextures);
        }
        for(int i=0;i<10;i++) {
            createRightSpectre(spectreMobTextures);
        }

        createCastles();
        createSpecialAttackEffects(magicBallTextures);


    }



    private void createCastles() {
        Vector<Integer> castleTextures=new Vector<>();
        castleTextures.add(R.drawable.castle_left);

        createLeftCastle(castleTextures);
        createRightCastle(castleTextures);
    }

    private void createPlayer(Vector<Integer> playerTextures)
    {
        createGameObject(-1f,-0.1f,-9f,false,playerTextures,false,false,true);
        arrayOfTriangles.get(arrayOfTriangles.size()-1).setPossibleCollision(false);
        arrayOfFilteringExceptions.add(arrayOfTriangles.size()-1);
        arrayOfTriangles.get(arrayOfTriangles.size()-1).setPossibleToFlip(true);
        Player player1=new Player(arrayOfTriangles.size()-1);

        arrayOfPlayers.add(player1);
    }

    private void createPlayerNoMob(Vector<Integer> noMobTextures)
    {
        createGameObject(7.0f,-0.1f,-9f,false,noMobTextures,false,false,true);
        arrayOfTriangles.get(arrayOfTriangles.size()-1).setPossibleCollision(true);
        arrayOfFilteringExceptions.add(arrayOfTriangles.size()-1);
        PlayerNoMob playerNoMob=new PlayerNoMob(arrayOfTriangles.size()-1);

        setObjectAttributes(playerNoMob,10.0f,20.0f,100f,1,0.5f,100f,400f,0.01f,1);
        playerNoMob.setFrozen(true);
        arrayOfPlayerNoMobs.add(playerNoMob);
        arrayOfTriangles.get(playerNoMob.getObjectId()).setVisible(false);

    }

    private void createLeftGoblin(Vector<Integer> goblinMobTextures)
    {
        createGameObject(-1.0f,-0.1f,-9f,false,goblinMobTextures,false,false,true);
        arrayOfTriangles.get(arrayOfTriangles.size()-1).setPossibleCollision(false);
        arrayOfFilteringExceptions.add(arrayOfTriangles.size()-1);
        Mob mob=new Mob(arrayOfTriangles.size()-1);
        addHpBarsForMobs(mob,-13.0f);
        addHpEmptyBarsForMobs(mob,-13.0f);

        setObjectAttributes(mob,10.0f,30.0f,150f,1,0.002f,30f,30f,0.2f,0);
        arrayOfMobs.add(mob);
        mob.setAlive(false);
    }

    private void createRightGoblin(Vector<Integer> goblinMobTextures)
    {
        createGameObject(6.0f,-0.1f,-9f,false,goblinMobTextures,false,false,true);
        arrayOfTriangles.get(arrayOfTriangles.size()-1).setPossibleCollision(true);
        arrayOfFilteringExceptions.add(arrayOfTriangles.size()-1);
        arrayOfTriangles.get(arrayOfTriangles.size()-1).setPossibleToFlip(true);
        arrayOfTriangles.get(arrayOfTriangles.size()-1).setTextureFlipped(true);

        Mob mob=new Mob(arrayOfTriangles.size()-1);

        addHpBarsForMobs(mob,-13.0f);
        addHpEmptyBarsForMobs(mob,-13.0f);

        setObjectAttributes(mob,15.0f,30.0f,250f,1,0.002f,30f,30f,0.2f,1);
        arrayOfMobs.add(mob);
        mob.setAlive(false);
    }

    private void createLeftSpectre(Vector<Integer> spectreMobTextures)
    {
        createGameObject(-1.0f,-0.15f,-7f,false,spectreMobTextures,false,false,true);
        arrayOfTriangles.get(arrayOfTriangles.size()-1).setPossibleCollision(false);
        arrayOfFilteringExceptions.add(arrayOfTriangles.size()-1);
        Mob mob=new Mob(arrayOfTriangles.size()-1);
        mob.setSpecialAttackEffect(true,0);

        addHpBarsForMobs(mob,-13.0f);
        addHpEmptyBarsForMobs(mob,-13.0f);

        setObjectAttributes(mob,10.0f,10.0f,150f,1,0.004f,40f,40f,0.5f,0);

        arrayOfMobs.add(mob);
        mob.setAlive(false);
    }

    private void createRightSpectre(Vector<Integer> spectreMobTextures)
    {
        createGameObject(6.0f,-0.15f,-7f,false,spectreMobTextures,false,false,true);
        arrayOfTriangles.get(arrayOfTriangles.size()-1).setPossibleCollision(true);
        arrayOfFilteringExceptions.add(arrayOfTriangles.size()-1);
        arrayOfTriangles.get(arrayOfTriangles.size()-1).setPossibleToFlip(true);
        arrayOfTriangles.get(arrayOfTriangles.size()-1).setTextureFlipped(true);

        Mob mob=new Mob(arrayOfTriangles.size()-1);
        mob.setSpecialAttackEffect(true,0);


        addHpBarsForMobs(mob,-13.0f);
        addHpEmptyBarsForMobs(mob,-13.0f);

        setObjectAttributes(mob,20.0f,15.0f,250f,1,0.004f,40f,40f,0.5f,1);
        arrayOfMobs.add(mob);
        mob.setAlive(false);
    }

    private void createLeftCastle(Vector<Integer> castleTextures)
    {
        createGameObject(-1.0f,-0.1f,-3f,false,castleTextures,false,false,true);
        arrayOfCastlesElementsID.add(arrayOfTriangles.size()-1);
        arrayOfTriangles.get(arrayOfTriangles.size()-1).setPossibleCollision(false);
        arrayOfFilteringExceptions.add(arrayOfTriangles.size()-1);
        Mob castleMobLeft=new Mob(arrayOfTriangles.size()-1);
        castleMobLeft.setSpecialAttackEffect(true,0);

        addHpBarsForMobs(castleMobLeft,-9.0f);
        addHpEmptyBarsForMobs(castleMobLeft,-9.0f);

        setObjectAttributes(castleMobLeft,30.0f,20.0f,2000f,1,0.0f,1000f,200f,0.9f,0);
        arrayOfMobs.add(castleMobLeft);
        castleMobLeft.updatePositionOfMovableBar();

    }

    private void createRightCastle(Vector<Integer> castleTextures)
    {
        createGameObject(6.0f,-0.1f,-3f,false,castleTextures,false,false,true);
        arrayOfCastlesElementsID.add(arrayOfTriangles.size()-1);
        arrayOfTriangles.get(arrayOfTriangles.size()-1).setPossibleCollision(true);
        arrayOfFilteringExceptions.add(arrayOfTriangles.size()-1);
        arrayOfTriangles.get(arrayOfTriangles.size()-1).setPossibleToFlip(true);
        arrayOfTriangles.get(arrayOfTriangles.size()-1).setTextureFlipped(true);
        arrayOfTriangles.get(arrayOfTriangles.size()-1).setActualTextureSet(1);


        Mob castleMobRight=new Mob(arrayOfTriangles.size()-1);
        castleMobRight.setSpecialAttackEffect(true,0);

        addHpBarsForMobs(castleMobRight,-9.0f);
        addHpEmptyBarsForMobs(castleMobRight,-9.0f);

        setObjectAttributes(castleMobRight,30.0f,20.0f,2000f,1,0.0f,1000f,200f,0.9f,1);
        arrayOfMobs.add(castleMobRight);
        castleMobRight.updatePositionOfMovableBar();


        castleMobRight.checkForEnemies();
    }

    private void createSpecialAttackEffects(Vector<Integer> magicBallTextures)
    {
        //MagicBalls
        for(int i=0;i<22;i++) {
            createGameObject(-5.0f, 0.0f, -19f, false, magicBallTextures, false, false, true);
            arrayOfTriangles.get(arrayOfTriangles.size() - 1).setVisible(false);

            MagicBall tempMagicBall = new MagicBall((arrayOfTriangles.size() - 1), -1, -1, 0.02f);
            arrayOfMagicBalls.add(tempMagicBall);
        }
    }


    private void addHpBarsForMobs(Mob tempMob,float sizeOfBarOnZ) {
        Vector<Integer> uiHpTextures=new Vector<>();
        uiHpTextures.add(R.drawable.ui_hp);

        //We set Bar on object below
        Triangle tempObjectTriangle=arrayOfTriangles.get(tempMob.getObjectId());
        float xCordOfObject=tempObjectTriangle.getArrayOfTranslations()[0];
        float yCordOfObject=tempObjectTriangle.getArrayOfTranslations()[1];

        createGameObject(xCordOfObject, yCordOfObject, sizeOfBarOnZ,false,uiHpTextures,false,false,true);
        arrayOfFilteringExceptions.add(arrayOfTriangles.size()-1);
        BarOfMovableObject hpBar=new BarOfMovableObject(arrayOfTriangles.size()-1);
        arrayOfMovableObjectsBars.add(hpBar);

        tempMob.setIdOfBarOfMovableObjectInArrayOfMovableBars(arrayOfMovableObjectsBars.size()-1);



    }

    private void addHpEmptyBarsForMobs(Mob tempMob,float sizeOfBarOnZ)
    {
        Vector<Integer> uiHpEmptyTextures=new Vector<>();
        uiHpEmptyTextures.add(R.drawable.hp_empty_bar_fix2);

        //We set Bar on object below
        Triangle tempObjectTriangle=arrayOfTriangles.get(tempMob.getObjectId());
        float xCordOfObject=tempObjectTriangle.getArrayOfTranslations()[0];
        float yCordOfObject=tempObjectTriangle.getArrayOfTranslations()[1];

        createGameObject(xCordOfObject+getSizeOfObjectOnZ(sizeOfBarOnZ), yCordOfObject, sizeOfBarOnZ,false,uiHpEmptyTextures,false,false,true);
        arrayOfFilteringExceptions.add(arrayOfTriangles.size()-1);
        BarOfMovableObject hpEmptyBar=new BarOfMovableObject(arrayOfTriangles.size()-1);
        arrayOfMovableObjectsBars.add(hpEmptyBar);

        tempMob.setIdOfEmptyBarOfMovableObjectInArrayOfMovableBars(arrayOfMovableObjectsBars.size()-1);

    }


    private void drawSky()
    {
        //Textures
        Vector<Integer> skyTextures=new Vector<>();
        skyTextures.add(R.drawable.sky);

        float sizeOfSingleSkyObject=getSizeOfObjectOnZ(-9f);
        if(sizeOfSingleSkyObject!=0) {
            int numberOfElementsX = ((int) Math.ceil((double)2.0f / sizeOfSingleSkyObject)) + 1;

            for (int y = 0; y < 4; y++) {
                float yPosition=(0.5f+sizeOfSingleSkyObject)-y*sizeOfSingleSkyObject;

                for (int x = 0; x < numberOfElementsX+1; x++) {
                    createGameObject(-1f + x * sizeOfSingleSkyObject, yPosition, -9f, false, skyTextures,false,true,false);
                    arrayOfFilteringExceptions.add(arrayOfTriangles.size()-1);
                }
            }

        }
    }

    private void drawGrass()
    {
        Vector<Integer> grassTextures=new Vector<>();
        grassTextures.add(R.drawable.grass);

        float sizeOfSingleGrassObject=getSizeOfObjectOnZ(-4f);
        if(sizeOfSingleGrassObject!=0) {
            int numberOfElementsX = ((int) Math.ceil((double)2.0f / sizeOfSingleGrassObject)) + 1;

            for (int y = 0; y < 2; y++) {
                float yPosition=(-0.5f-sizeOfSingleGrassObject)+y*sizeOfSingleGrassObject;

                //+1 because after moving player, map will also move, so the last will disappear and new one will be on left, already rendered
                for (int x = 0; x < numberOfElementsX+1; x++) {
                    createGameObject(-1f + x * sizeOfSingleGrassObject, yPosition, -4f, false, grassTextures,false,true,false);

                }
            }

        }

    }

    private void createElementsOfUI()
    {

        //Textures

        Vector<Integer> uiButtonTextures=new Vector<>();
        uiButtonTextures.add(R.drawable.grass);

        Vector<Integer> uiJoystickTextures=new Vector<>();
        uiJoystickTextures.add(R.drawable.ui_joystick);

        Vector<Integer> uiJoystickInsideTextures=new Vector<>();
        uiJoystickInsideTextures.add(R.drawable.ui_joystick_inside);

        Vector<Integer> uiButtonAtUpTextures=new Vector<>();
        uiButtonAtUpTextures.add(R.drawable.ui_button_at_up);

        Vector<Integer> uiHpTextures=new Vector<>();
        uiHpTextures.add(R.drawable.ui_hp);

        Vector<Integer> uiExpTextures=new Vector<>();
        uiExpTextures.add(R.drawable.ui_exp);

        Vector<Integer> uiHpEmptyTextures=new Vector<>();
        uiHpEmptyTextures.add(R.drawable.ui_empty_bar);

        Vector<Integer> uiExpEmptyTextures=new Vector<>();
        uiExpEmptyTextures.add(R.drawable.ui_empty_bar);

        Vector<Integer> uiStatsTextures=new Vector<>();
        uiStatsTextures.add(R.drawable.ui_stats);

        Vector<Integer> uiStatsAddTextures=new Vector<>();
        uiStatsAddTextures.add(R.drawable.ui_stats_add_grey);
        uiStatsAddTextures.add(R.drawable.ui_stats_add);

        Vector<Integer> uiHpExpTextures=new Vector<>();
        uiHpExpTextures.add(R.drawable.ui_hp_exp);

        Vector<Integer> uiLevelTextures=new Vector<>();
        uiLevelTextures.add(R.drawable.ui_level);

        Vector<Integer> uiTimerTextures=new Vector<>();
        uiTimerTextures.add(R.drawable.ui_timer);





        createGameObject(-0.9f, -0.45f, -6f,false,uiJoystickTextures,true,false,false);

        createGameObject(-0.9f, -0.45f, -6f,false,uiJoystickInsideTextures,true,false,false);

        Pad uiPad=new Pad(arrayOfTriangles.size()-1);
        arrayOfPads.add(uiPad);


        createGameObject(0.9f, -0.45f, -6f,false,uiButtonAtUpTextures,true,false,false);
        UIButton buttonAttackUp=new UIButton(arrayOfTriangles.size()-1);
        arrayOfUIButtons.add(buttonAttackUp);


        createGameObject(-0.15f, -0.235f, -5f,true,uiHpTextures,true,false,false);
        arrayOfFilteringExceptions.add(arrayOfTriangles.size()-1);
        UIBar hpBar=new UIBar(arrayOfTriangles.size()-1);
        arrayOfBars.add(hpBar);

        createGameObject(-0.15f, -0.308f, -5f,true,uiExpTextures,true,false,false);
        arrayOfFilteringExceptions.add(arrayOfTriangles.size()-1);
        UIBar expBar=new UIBar(arrayOfTriangles.size()-1);
        arrayOfBars.add(expBar);

        createGameObject(0.1833334f, -0.235f, -5f,true,uiHpEmptyTextures,true,false,false);
        arrayOfFilteringExceptions.add(arrayOfTriangles.size()-1);
        UIBar hpEmptyBar=new UIBar(arrayOfTriangles.size()-1);
        arrayOfBars.add(hpEmptyBar);

        createGameObject(0.1833334f, -0.308f, -5f,true,uiExpEmptyTextures,true,false,false);
        arrayOfFilteringExceptions.add(arrayOfTriangles.size()-1);
        UIBar expEmptyBar=new UIBar(arrayOfTriangles.size()-1);
        arrayOfBars.add(expEmptyBar);

        createGameObject(0.2f, -0.2f, -3f,true,uiStatsTextures,true,false,false);
        arrayOfFilteringExceptions.add(arrayOfTriangles.size()-1);

        createGameObject(0.2f, -0.2f, -3f,true,uiStatsAddTextures,true,false,false);


        UIButton buttonAddStats=new UIButton(arrayOfTriangles.size()-1);
        arrayOfUIButtons.add(buttonAddStats);


        createGameObject(-0.2f, -0.2f, -3f,true,uiHpExpTextures,true,false,false);
        createGameObject(0.0f, 0.62f, -3f,true,uiLevelTextures,true,false,false);
        createGameObject(0.5f, 0.54f, -8f,false,uiTimerTextures,true,false,false);
        createGameObject(-0.5f, 0.54f, -8f,false,uiTimerTextures,true,false,false);
        UIButton buttonMenu=new UIButton(arrayOfTriangles.size()-1);
        arrayOfUIButtons.add(buttonMenu);
    }

    private void createTextForUIElements(Context context)
    {

        Vector<Integer> uiBackgroundTextTextures=new Vector<>();
        uiBackgroundTextTextures.add(R.drawable.grass);

        // 0.5f, 0.54f, -8f
        //TIMER
        //SEC 00  ---(0)---
        createGameObject(0.505f, 0.407f, -21f,true,uiBackgroundTextTextures,true,false,false);
        TextCreator.createTimerTextObject(arrayOfTriangles.size()-1,context);
        arrayOfElementsWithText.add(arrayOfTriangles.size()-1);
        // SEC 0  ---(1)---
        createGameObject(0.485f, 0.407f, -21f,true,uiBackgroundTextTextures,true,false,false);
        TextCreator.createTimerTextObject(arrayOfTriangles.size()-1,context);
        arrayOfElementsWithText.add(arrayOfTriangles.size()-1);

        //:       ---(2)---
        createGameObject(0.48f, 0.407f, -21f,true,uiBackgroundTextTextures,true,false,false);
        TextCreator.createTimerTextColonObject(arrayOfTriangles.size()-1,context);
        arrayOfElementsWithText.add(arrayOfTriangles.size()-1);

        //MIN 0   ---(3)---
        createGameObject(0.46f, 0.407f, -21f,true,uiBackgroundTextTextures,true,false,false);
        TextCreator.createTimerTextObject(arrayOfTriangles.size()-1,context);
        arrayOfElementsWithText.add(arrayOfTriangles.size()-1);

        //MIN 00  ---(4)---
        createGameObject(0.44f, 0.407f, -21f,true,uiBackgroundTextTextures,true,false,false);
        TextCreator.createTimerTextObject(arrayOfTriangles.size()-1,context);
        arrayOfElementsWithText.add(arrayOfTriangles.size()-1);

        //Time:   ---(5)---
        createGameObject(0.347f, 0.407f, -21f,true,uiBackgroundTextTextures,true,false,false);
        TextCreator.createTimeTextObject(arrayOfTriangles.size()-1,context);
        arrayOfElementsWithText.add(arrayOfTriangles.size()-1);

        //LEVEL   ---(6)---
        createGameObject(-0.018f, 0.408f, -21f,true,uiBackgroundTextTextures,true,false,false);
        TextCreator.createLevelTextObject(arrayOfTriangles.size()-1,context);
        arrayOfElementsWithText.add(arrayOfTriangles.size()-1);

        //LEVEL PLAYER  ---(7)---
        createGameObject(0.082f, 0.4062f, -21f,true,uiBackgroundTextTextures,true,false,false);
        TextCreator.createPlayerLevelTextObject(arrayOfTriangles.size()-1,context);
        arrayOfElementsWithText.add(arrayOfTriangles.size()-1);

        //HP      ---(8)---
        createGameObject(-0.35f, -0.2525f, -21f,true,uiBackgroundTextTextures,true,false,false);
        TextCreator.createHpTextObject(arrayOfTriangles.size()-1,context);
        arrayOfElementsWithText.add(arrayOfTriangles.size()-1);

        //HP BAR   ---(9)---
        createGameObject(-0.255f, -0.2565f, -21f,true,uiBackgroundTextTextures,true,false,false);
        TextCreator.createHpBarTextObject(arrayOfTriangles.size()-1,context);
        arrayOfElementsWithText.add(arrayOfTriangles.size()-1);


        //EXP     ---(10)---
        createGameObject(-0.372f, -0.325f, -21f,true,uiBackgroundTextTextures,true,false,false);
        TextCreator.createExpTextObject(arrayOfTriangles.size()-1,context);
        arrayOfElementsWithText.add(arrayOfTriangles.size()-1);

        //EXP BAR ---(11)---
        createGameObject(-0.255f, -0.3305f, -21f,true,uiBackgroundTextTextures,true,false,false);
        TextCreator.createExpBarTextObject(arrayOfTriangles.size()-1,context);
        arrayOfElementsWithText.add(arrayOfTriangles.size()-1);

        //RESOURCE  ---(12)---
        createGameObject(-0.3675f, -0.4f, -21f,true,uiBackgroundTextTextures,true,false,false);
        TextCreator.createResTextObject(arrayOfTriangles.size()-1,context);
        arrayOfElementsWithText.add(arrayOfTriangles.size()-1);

        //RESOURCE BAR 0  ---(13)---
        createGameObject(-0.255f, -0.4042f, -21f,true,uiBackgroundTextTextures,true,false,false);
        TextCreator.createResBarTextObject(arrayOfTriangles.size()-1,context);
        arrayOfElementsWithText.add(arrayOfTriangles.size()-1);

        //RESOURCE BAR 00  ---(14)---
        createGameObject(-0.235f, -0.4042f, -21f,true,uiBackgroundTextTextures,true,false,false);
        TextCreator.createResBarTextObject(arrayOfTriangles.size()-1,context);
        arrayOfElementsWithText.add(arrayOfTriangles.size()-1);

        //RESOURCE BAR 000  ---(15)---
        createGameObject(-0.215f, -0.4042f, -21f,true,uiBackgroundTextTextures,true,false,false);
        TextCreator.createResBarTextObject(arrayOfTriangles.size()-1,context);
        arrayOfElementsWithText.add(arrayOfTriangles.size()-1);


        //RESOURCE BAR 0000  ---(16)---
        createGameObject(-0.195f, -0.4042f, -21f,true,uiBackgroundTextTextures,true,false,false);
        TextCreator.createResBarTextObject(arrayOfTriangles.size()-1,context);
        arrayOfElementsWithText.add(arrayOfTriangles.size()-1);


        //RESOURCE BAR 00000  ---(17)---
        createGameObject(-0.175f, -0.4042f, -21f,true,uiBackgroundTextTextures,true,false,false);
        TextCreator.createResBarTextObject(arrayOfTriangles.size()-1,context);
        arrayOfElementsWithText.add(arrayOfTriangles.size()-1);


        //RESOURCE BAR 000000  ---(18)---
        createGameObject(-0.155f, -0.4042f, -21f,true,uiBackgroundTextTextures,true,false,false);
        TextCreator.createResBarTextObject(arrayOfTriangles.size()-1,context);
        arrayOfElementsWithText.add(arrayOfTriangles.size()-1);


        //RESOURCE BAR G  ---(19)---
        createGameObject(-0.135f, -0.4042f, -21f,true,uiBackgroundTextTextures,true,false,false);
        TextCreator.createResBarTextRObject(arrayOfTriangles.size()-1,context);
        arrayOfElementsWithText.add(arrayOfTriangles.size()-1);


        //Attack    ---(20)---
        createGameObject(0.144f, -0.265f, -17f,true,uiBackgroundTextTextures,true,false,false);
        TextCreator.createAttackTextObject(arrayOfTriangles.size()-1,context);
        arrayOfElementsWithText.add(arrayOfTriangles.size()-1);

        //Attack Bar 0   ---(21)---
        createGameObject(0.2635f, -0.2565f, -21f,true,uiBackgroundTextTextures,true,false,false);
        TextCreator.createAttribBarTextObject(arrayOfTriangles.size()-1,context);
        arrayOfElementsWithText.add(arrayOfTriangles.size()-1);

        //Attack Bar 00 ---(22)---
        createGameObject(0.2835f, -0.2565f, -21f,true,uiBackgroundTextTextures,true,false,false);
        TextCreator.createAttribBarTextObject(arrayOfTriangles.size()-1,context);
        arrayOfElementsWithText.add(arrayOfTriangles.size()-1);

        //Attack Bar 000 ---(23)---
        createGameObject(0.3035f, -0.2565f, -21f,true,uiBackgroundTextTextures,true,false,false);
        TextCreator.createAttribBarTextObject(arrayOfTriangles.size()-1,context);
        arrayOfElementsWithText.add(arrayOfTriangles.size()-1);

        //Attack Bar 0000 ---(24)---
        createGameObject(0.3235f, -0.2565f, -21f,true,uiBackgroundTextTextures,true,false,false);
        TextCreator.createAttribBarTextObject(arrayOfTriangles.size()-1,context);
        arrayOfElementsWithText.add(arrayOfTriangles.size()-1);

        //Attack Bar 00000  ---(25)---
        createGameObject(0.3435f, -0.2565f, -21f,true,uiBackgroundTextTextures,true,false,false);
        TextCreator.createAttribBarTextObject(arrayOfTriangles.size()-1,context);
        arrayOfElementsWithText.add(arrayOfTriangles.size()-1);


        //Agility  ---(26)---
        createGameObject(0.144f, -0.339f, -17f,true,uiBackgroundTextTextures,true,false,false);
        TextCreator.createAgilityTextObject(arrayOfTriangles.size()-1,context);
        arrayOfElementsWithText.add(arrayOfTriangles.size()-1);

        //Agility Bar 0  ---(27)---
        createGameObject(0.2635f, -0.3305f, -21f,true,uiBackgroundTextTextures,true,false,false);
        TextCreator.createAttribBarTextObject(arrayOfTriangles.size()-1,context);
        arrayOfElementsWithText.add(arrayOfTriangles.size()-1);

        //Agility Bar 00  ---(28)---
        createGameObject(0.2835f, -0.3305f, -21f,true,uiBackgroundTextTextures,true,false,false);
        TextCreator.createAttribBarTextObject(arrayOfTriangles.size()-1,context);
        arrayOfElementsWithText.add(arrayOfTriangles.size()-1);

        //Agility Bar 000 ---(29)---
        createGameObject(0.3035f, -0.3305f, -21f,true,uiBackgroundTextTextures,true,false,false);
        TextCreator.createAttribBarTextObject(arrayOfTriangles.size()-1,context);
        arrayOfElementsWithText.add(arrayOfTriangles.size()-1);

        //Agility Bar 0000  ---(30)---
        createGameObject(0.3235f, -0.3305f, -21f,true,uiBackgroundTextTextures,true,false,false);
        TextCreator.createAttribBarTextObject(arrayOfTriangles.size()-1,context);
        arrayOfElementsWithText.add(arrayOfTriangles.size()-1);

        //Agility Bar 00000  ---(31)---
        createGameObject(0.3435f, -0.3305f, -21f,true,uiBackgroundTextTextures,true,false,false);
        TextCreator.createAttribBarTextObject(arrayOfTriangles.size()-1,context);
        arrayOfElementsWithText.add(arrayOfTriangles.size()-1);


        //Vitality  ---(32)---
        createGameObject(0.131f, -0.4124f, -17f,true,uiBackgroundTextTextures,true,false,false);
        TextCreator.createVitalityTextObject(arrayOfTriangles.size()-1,context);
        arrayOfElementsWithText.add(arrayOfTriangles.size()-1);

        //Vitality Bar 0  ---(33)---
        createGameObject(0.2635f, -0.4042f, -21f,true,uiBackgroundTextTextures,true,false,false);
        TextCreator.createAttribBarTextObject(arrayOfTriangles.size()-1,context);
        arrayOfElementsWithText.add(arrayOfTriangles.size()-1);

        //Vitality Bar 00  ---(34)---
        createGameObject(0.2835f, -0.4042f, -21f,true,uiBackgroundTextTextures,true,false,false);
        TextCreator.createAttribBarTextObject(arrayOfTriangles.size()-1,context);
        arrayOfElementsWithText.add(arrayOfTriangles.size()-1);

        //Vitality Bar 000  ---(35)---
        createGameObject(0.3035f, -0.4042f, -21f,true,uiBackgroundTextTextures,true,false,false);
        TextCreator.createAttribBarTextObject(arrayOfTriangles.size()-1,context);
        arrayOfElementsWithText.add(arrayOfTriangles.size()-1);

        //Vitality Bar 0000  ---(36)---
        createGameObject(0.3235f, -0.4042f, -21f,true,uiBackgroundTextTextures,true,false,false);
        TextCreator.createAttribBarTextObject(arrayOfTriangles.size()-1,context);
        arrayOfElementsWithText.add(arrayOfTriangles.size()-1);

        //Vitality Bar 00000  ---(37)---
        createGameObject(0.3435f, -0.4042f, -21f,true,uiBackgroundTextTextures,true,false,false);
        TextCreator.createAttribBarTextObject(arrayOfTriangles.size()-1,context);
        arrayOfElementsWithText.add(arrayOfTriangles.size()-1);

        //Menu   ---(38)---
        createGameObject(-0.3855f, 0.404f, -19f,true,uiBackgroundTextTextures,true,false,false);
        TextCreator.createMenuTextObject(arrayOfTriangles.size()-1,context);
        arrayOfElementsWithText.add(arrayOfTriangles.size()-1);

        //Welcome Message   ---(39)---
        createGameObject(-0.45f, 0.304f, -1f,false,uiBackgroundTextTextures,true,false,false);
        TextCreator.welcomeMessageTextObject(arrayOfTriangles.size()-1,context);
        arrayOfElementsWithText.add(arrayOfTriangles.size()-1);

        //End Message   ---(40)---
        createGameObject(-0.45f, 0.304f, -1f,false,uiBackgroundTextTextures,true,false,false);
        TextCreator.endMessageTextObject(arrayOfTriangles.size()-1,context);
        arrayOfElementsWithText.add(arrayOfTriangles.size()-1);
        arrayOfTriangles.get(arrayOfTriangles.size()-1).setVisible(false);

    }

    private void setValuesAfterGameCreation()
    {
        setObjectAttributes(arrayOfPlayers.get(0),10f,20.0f,200f,1f,0.5f,0f,400f,0.2f,0);
        arrayOfPlayers.get(0).updatePositionOfHPBar();
        arrayOfPlayers.get(0).updatePositionOfExpBar();
    }




    private void createGameObject(float xPosition, float yPosition, float zPosition,boolean fixByCenter,final Vector<Integer> textureIdArray,boolean isUIElement,boolean isDynamicBackgroundElement,boolean isSynchronized) {
        float[] arrayOfTranslations = new float[3];
        float[] arrayOfRotations = new float[4];


        //          X
        arrayOfTranslations[0] = fixCoordinateToZ(xPosition, zPosition);
        if (fixByCenter == false) {
            if (xPosition >= 0) {
                arrayOfTranslations[0] -= getSizeOfObjectOnZ(zPosition) / 2.0f;
            } else if (xPosition < 0) {
                arrayOfTranslations[0] += getSizeOfObjectOnZ(zPosition) / 2.0f;
            }

        }
        //          Y
        arrayOfTranslations[1] = fixCoordinateToZ(yPosition, zPosition);
        if (fixByCenter == false) {
            if (yPosition >= 0) {
                arrayOfTranslations[1] -= getSizeOfObjectOnZ(zPosition) / 2.0f;
            } else if (yPosition < 0) {
                arrayOfTranslations[1] += getSizeOfObjectOnZ(zPosition) / 2.0f;
            }
        }
        //          Z
        arrayOfTranslations[2] = zPosition;

        //          a                       X                       Y                       Z
        arrayOfRotations[0] = 0f;
        arrayOfRotations[1] = 0.0f;
        arrayOfRotations[2] = 0.0f;
        arrayOfRotations[3] = 0.0f;

        float tempObjectsScaleFactor = objectsScaleFactor;

        if (isUIElement == true) {
            tempObjectsScaleFactor = uiScaleFactor;
        }
        Triangle triangleObject = new Triangle(arrayOfTranslations, arrayOfRotations, tempObjectsScaleFactor, textureIdArray);
        arrayOfTriangles.add(triangleObject);

        int idOfLastElementInArrayOfTriangles= arrayOfTriangles.size()-1;

        if (isDynamicBackgroundElement == true){
            arrayOfDynamicBackgroundElementsID.add(idOfLastElementInArrayOfTriangles);
        }

        if(isSynchronized==true)
        {
            arrayOfSynchronizedElementsID.add(idOfLastElementInArrayOfTriangles);
        }

    }


    private float fixCoordinateToZ(float coordinate,float zPosition)
    {
        if(coordinate>0) {
            return coordinate - (1.0f / ((zPosition * (-1) + 1) / 2.0f)/2.0f)+(1.0f / ((zPosition * (-1) + 1) / 2.0f)/2.0f);
        }
        else if(coordinate<0)
        {
            return coordinate + (1.0f / ((zPosition * (-1) + 1) / 2.0f)/2.0f)-(1.0f / ((zPosition * (-1) + 1) / 2.0f)/2.0f);
        }
        else
        {
            return 0.0f;
        }
    }

    private float getSizeOfObjectOnZ(float zPosition)
    {
        return(1.0f/((zPosition*(-1)+1)/2.0f));
    }

    private void setObjectAttributes(Player playerObject,float strength,float agility,float vitality,float level,float maxMovementSpeed,float resource,float resourceExp,float attackRange,int whichPlayerIDTeam)
    {
        playerObject.setAttack(strength);
        playerObject.setAgility(agility);
        playerObject.setVitality(vitality);
        //FIX
        playerObject.setActualHealth(vitality);
        playerObject.setLevel(level);
        playerObject.setMaxMovementSpeed(maxMovementSpeed);
        playerObject.setResourceOfObject(resource);
        playerObject.setResourceExpOfObject(resourceExp);
        playerObject.setAttackRange(attackRange);
        playerObject.setWhichPlayerIDTeam(whichPlayerIDTeam);
    }

    private void setObjectAttributes(Mob mobObject,float strength,float agility,float vitality,float level,float maxMovementSpeed,float resource,float resourceExp,float attackRange,int whichPlayerIDTeam)
    {

        mobObject.setAttack(strength);
        mobObject.setAgility(agility);
        mobObject.setVitality(vitality);
        mobObject.setActualHealth(vitality);
        mobObject.setLevel(level);
        mobObject.setMaxMovementSpeed(maxMovementSpeed);
        mobObject.setResourceOfObject(resource);
        mobObject.setResourceExpOfObject(resourceExp);
        mobObject.setAttackRange(attackRange);
        mobObject.setWhichPlayerIDTeam(whichPlayerIDTeam);

        //If Players==2
        int i=0;
        for(Player tempPlayer: arrayOfPlayers)
        {
            if(i==whichPlayerIDTeam) {
                tempPlayer.getArrayOfObjectIDAllyMobs().add(mobObject.getObjectId());
            }
            else if(whichPlayerIDTeam>-1&&i!=whichPlayerIDTeam)
            {
                tempPlayer.getArrayOfObjectIDEnemyMobs().add(mobObject.getObjectId());
            }
            i++;
        }

        i=0;
        for(PlayerNoMob tempPlayerNoMob: arrayOfPlayerNoMobs)
        {
            if(i==whichPlayerIDTeam) {
                tempPlayerNoMob.getArrayOfObjectIDAllyMobs().add(mobObject.getObjectId());
            }
            else if(whichPlayerIDTeam>-1&&i!=whichPlayerIDTeam)
            {
                tempPlayerNoMob.getArrayOfObjectIDEnemyMobs().add(mobObject.getObjectId());
            }
            i++;
        }

        mobObject.setObjectMovementSpeed(maxMovementSpeed);
    }
}
