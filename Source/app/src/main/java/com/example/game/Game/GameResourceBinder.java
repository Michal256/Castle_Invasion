package com.example.game.Game;

import java.util.Vector;

public class GameResourceBinder {

    public static Vector<Triangle> arrayOfTriangles =new Vector<>();
    public static Vector<Player> arrayOfPlayers =new Vector<>();
    public static Vector<Mob> arrayOfMobs =new Vector<>();
    public static Vector<PlayerNoMob> arrayOfPlayerNoMobs =new Vector<>();
    public static Vector<UIButton> arrayOfUIButtons =new Vector<>();
    public static Vector<Pad> arrayOfPads =new Vector<>();
    public static Vector<UIBar> arrayOfBars =new Vector<>();
    public static Vector<BarOfMovableObject> arrayOfMovableObjectsBars=new Vector<>();
    public static Vector<Integer> arrayOfDynamicBackgroundElementsID =new Vector<>();
    public static Vector<Integer> arrayOfSynchronizedElementsID =new Vector<>();
    public static Vector<Integer> arrayOfSkyElementsID =new Vector<>();
    public static Vector<Integer> arrayOfGrassElementsID =new Vector<>();

    public static Vector<Integer> arrayOfFilteringExceptions =new Vector<>();
    public static Vector<Integer> arrayOfElementsWithText=new Vector<>();
    public static Vector<MagicBall> arrayOfMagicBalls=new Vector<>();

    public static Vector<Integer> arrayOfCastlesElementsID=new Vector<>();


    public static int numberOfGoblinsLeftToRespawn;
    public static float goblinDelayLeftRespawn;

    public static int numberOfSpectresLeftToRespawn;
    public static float spectreDelayLeftRespawn;

    public static int numberOfGoblinsRightToRespawn;
    public static float goblinDelayRightRespawn;

    public static int numberOfSpectresRightToRespawn;
    public static float spectreDelayRightRespawn;

    public static int gameTimeInMilliseconds;
    public static int gameTimeInSeconds;
    public static float timeToNextWaveMilliseconds;

    public static int waveNumber;

    public static boolean gameIsOn;
    public static boolean gameLoaded=false;
    public static boolean gameIsLost;

    //STATE 0-START TEXT 1-PLAY 2-END
    public static int gameState;

    public GameResourceBinder()
    {
        arrayOfTriangles =new Vector<>();
        arrayOfPlayers =new Vector<>();
        arrayOfMobs =new Vector<>();
        arrayOfPlayerNoMobs=new Vector<>();
        arrayOfUIButtons =new Vector<>();
        arrayOfPads =new Vector<>();
        arrayOfBars=new Vector<>();
        arrayOfDynamicBackgroundElementsID =new Vector<>();
        arrayOfSynchronizedElementsID =new Vector<>();
        arrayOfSkyElementsID =new Vector<>();
        arrayOfGrassElementsID =new Vector<>();
        arrayOfFilteringExceptions =new Vector<>();
        arrayOfElementsWithText=new Vector<>();
        arrayOfMovableObjectsBars=new Vector<>();
        arrayOfMagicBalls=new Vector<>();
        arrayOfCastlesElementsID=new Vector<>();



        gameTimeInSeconds=0;
        gameTimeInMilliseconds=0;
        timeToNextWaveMilliseconds=1000.0f;
        waveNumber=0;
        gameIsOn=false;
        gameIsLost=true;
        gameState=0;
        numberOfGoblinsLeftToRespawn=0;
        numberOfSpectresLeftToRespawn=0;

        numberOfGoblinsRightToRespawn=0;
        numberOfSpectresRightToRespawn=0;

        goblinDelayRightRespawn=0.0f;
        spectreDelayRightRespawn=0.0f;

        goblinDelayLeftRespawn=0.0f;
        spectreDelayLeftRespawn=0.0f;

    }

}
