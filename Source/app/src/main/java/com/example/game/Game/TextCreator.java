package com.example.game.Game;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v4.content.res.ResourcesCompat;


import com.example.game.R;

import java.util.Vector;

import static com.example.game.Game.GameResourceBinder.arrayOfTriangles;

public class TextCreator {

    public TextCreator()
    {

    }


    public static void createTimerTextObject(final int objectId, Context context)
    {
        Vector<BitmapTextHolder> tempBitmapArrayOfHolders=arrayOfTriangles.get(objectId).getBitmapArrayOfTextHolders();
        Vector<Integer> fontColor=new Vector<>();

        //R                     G                    B                   A
        fontColor.add(0);fontColor.add(0);fontColor.add(0);fontColor.add(255);
        Typeface typeOfFont = ResourcesCompat.getFont(context, R.font.latolight);

        BitmapTextHolder tempBitmapTextHolder;
        for(int i=0;i<=9;i++)
        {
            tempBitmapTextHolder=new BitmapTextHolder(""+i,24,typeOfFont,fontColor,64);
            tempBitmapArrayOfHolders.add(tempBitmapTextHolder);
        }


    }
    public static void createTimeTextObject(final int objectId, Context context)
    {
        Vector<BitmapTextHolder> tempBitmapArrayOfHolders=arrayOfTriangles.get(objectId).getBitmapArrayOfTextHolders();
        Vector<Integer> fontColor=new Vector<>();

        //R                     G                    B                   A
        fontColor.add(0);fontColor.add(0);fontColor.add(0);fontColor.add(255);
        Typeface typeOfFont = ResourcesCompat.getFont(context, R.font.latoregular);

        BitmapTextHolder tempBitmapTextHolder;
        tempBitmapTextHolder=new BitmapTextHolder("Time:",48,typeOfFont,fontColor,128);
        tempBitmapArrayOfHolders.add(tempBitmapTextHolder);
    }

    public static void createTimerTextColonObject(final int objectId, Context context)
    {
        Vector<BitmapTextHolder> tempBitmapArrayOfHolders=arrayOfTriangles.get(objectId).getBitmapArrayOfTextHolders();
        Vector<Integer> fontColor=new Vector<>();

        //R                     G                    B                   A
        fontColor.add(0);fontColor.add(0);fontColor.add(0);fontColor.add(255);
        Typeface typeOfFont = ResourcesCompat.getFont(context, R.font.latolight);

        BitmapTextHolder tempBitmapTextHolder;
        tempBitmapTextHolder=new BitmapTextHolder(":",48,typeOfFont,fontColor,128);
        tempBitmapArrayOfHolders.add(tempBitmapTextHolder);
    }

    public static void createLevelTextObject(final int objectId, Context context)
    {
        Vector<BitmapTextHolder> tempBitmapArrayOfHolders=arrayOfTriangles.get(objectId).getBitmapArrayOfTextHolders();
        Vector<Integer> fontColor=new Vector<>();

        //R                     G                    B                   A
        fontColor.add(0);fontColor.add(0);fontColor.add(0);fontColor.add(255);
        Typeface typeOfFont = ResourcesCompat.getFont(context, R.font.latobold);

        BitmapTextHolder tempBitmapTextHolder;
        tempBitmapTextHolder=new BitmapTextHolder("Level:",50,typeOfFont,fontColor,128);
        tempBitmapArrayOfHolders.add(tempBitmapTextHolder);

    }

    public static void createPlayerLevelTextObject(final int objectId, Context context)
    {
        Vector<BitmapTextHolder> tempBitmapArrayOfHolders=arrayOfTriangles.get(objectId).getBitmapArrayOfTextHolders();
        Vector<Integer> fontColor=new Vector<>();

        //R                     G                    B                   A
        fontColor.add(19);fontColor.add(150);fontColor.add(94);fontColor.add(255);
        Typeface typeOfFont = ResourcesCompat.getFont(context, R.font.latobold);

        BitmapTextHolder tempBitmapTextHolder;
        for(int i=0;i<31;i++) {
            tempBitmapTextHolder=new BitmapTextHolder(i+"",24,typeOfFont,fontColor,64);
            tempBitmapArrayOfHolders.add(tempBitmapTextHolder);
        }

    }

    public static void createHpTextObject(final int objectId, Context context)
    {
        Vector<BitmapTextHolder> tempBitmapArrayOfHolders=arrayOfTriangles.get(objectId).getBitmapArrayOfTextHolders();
        Vector<Integer> fontColor=new Vector<>();

        //R                     G                    B                   A
        fontColor.add(0);fontColor.add(0);fontColor.add(0);fontColor.add(255);
        Typeface typeOfFont = ResourcesCompat.getFont(context, R.font.latobold);

        BitmapTextHolder tempBitmapTextHolder;
        tempBitmapTextHolder=new BitmapTextHolder("HP:",56,typeOfFont,fontColor,128);
        tempBitmapArrayOfHolders.add(tempBitmapTextHolder);

    }

    public static void createHpBarTextObject(final int objectId, Context context)
    {
        Vector<BitmapTextHolder> tempBitmapArrayOfHolders=arrayOfTriangles.get(objectId).getBitmapArrayOfTextHolders();
        Vector<Integer> fontColor=new Vector<>();

        //R                     G                    B                   A
        fontColor.add(230);fontColor.add(230);fontColor.add(230);fontColor.add(255);
        Typeface typeOfFont = ResourcesCompat.getFont(context, R.font.latobold);

        BitmapTextHolder tempBitmapTextHolder;
        for(int i=0;i<101;i++) {
            tempBitmapTextHolder = new BitmapTextHolder(i+"%", 24, typeOfFont, fontColor, 64);
            tempBitmapArrayOfHolders.add(tempBitmapTextHolder);
        }

    }

    public static void createExpTextObject(final int objectId, Context context)
    {
        Vector<BitmapTextHolder> tempBitmapArrayOfHolders=arrayOfTriangles.get(objectId).getBitmapArrayOfTextHolders();
        Vector<Integer> fontColor=new Vector<>();

        //R                     G                    B                   A
        fontColor.add(0);fontColor.add(0);fontColor.add(0);fontColor.add(255);
        Typeface typeOfFont = ResourcesCompat.getFont(context, R.font.latobold);

        BitmapTextHolder tempBitmapTextHolder;
        tempBitmapTextHolder=new BitmapTextHolder("EXP:",56,typeOfFont,fontColor,128);
        tempBitmapArrayOfHolders.add(tempBitmapTextHolder);

    }

    public static void createExpBarTextObject(final int objectId, Context context)
    {
        Vector<BitmapTextHolder> tempBitmapArrayOfHolders=arrayOfTriangles.get(objectId).getBitmapArrayOfTextHolders();
        Vector<Integer> fontColor=new Vector<>();

        //R                     G                    B                   A
        fontColor.add(230);fontColor.add(230);fontColor.add(230);fontColor.add(255);
        Typeface typeOfFont = ResourcesCompat.getFont(context, R.font.latobold);

        BitmapTextHolder tempBitmapTextHolder;
        for(int i=0;i<101;i++) {
            tempBitmapTextHolder = new BitmapTextHolder(i+"%", 24, typeOfFont, fontColor, 64);
            tempBitmapArrayOfHolders.add(tempBitmapTextHolder);
        }

    }

    public static void createResTextObject(final int objectId, Context context)
    {
        Vector<BitmapTextHolder> tempBitmapArrayOfHolders=arrayOfTriangles.get(objectId).getBitmapArrayOfTextHolders();
        Vector<Integer> fontColor=new Vector<>();

        //R                     G                    B                   A
        fontColor.add(0);fontColor.add(0);fontColor.add(0);fontColor.add(255);
        Typeface typeOfFont = ResourcesCompat.getFont(context, R.font.latobold);

        BitmapTextHolder tempBitmapTextHolder;
        tempBitmapTextHolder=new BitmapTextHolder("RES:",56,typeOfFont,fontColor,128);
        tempBitmapArrayOfHolders.add(tempBitmapTextHolder);

    }

    public static void createResBarTextObject(final int objectId, Context context)
    {
        Vector<BitmapTextHolder> tempBitmapArrayOfHolders=arrayOfTriangles.get(objectId).getBitmapArrayOfTextHolders();
        Vector<Integer> fontColor=new Vector<>();

        //R                     G                    B                   A
        fontColor.add(19);fontColor.add(150);fontColor.add(94);fontColor.add(255);
        Typeface typeOfFont = ResourcesCompat.getFont(context, R.font.latobold);

        BitmapTextHolder tempBitmapTextHolder;
        for(int i=0;i<10;i++) {
            tempBitmapTextHolder = new BitmapTextHolder(i+"", 24, typeOfFont, fontColor, 64);
            tempBitmapArrayOfHolders.add(tempBitmapTextHolder);
        }
        tempBitmapTextHolder = new BitmapTextHolder("G", 48, typeOfFont, fontColor, 128);
        tempBitmapArrayOfHolders.add(tempBitmapTextHolder);

    }

    public static void createResBarTextRObject(final int objectId,Context context)
    {
        Vector<BitmapTextHolder> tempBitmapArrayOfHolders=arrayOfTriangles.get(objectId).getBitmapArrayOfTextHolders();
        Vector<Integer> fontColor=new Vector<>();

        //R                     G                    B                   A
        fontColor.add(19);fontColor.add(150);fontColor.add(94);fontColor.add(255);
        Typeface typeOfFont = ResourcesCompat.getFont(context, R.font.latobold);

        BitmapTextHolder tempBitmapTextHolder;

        tempBitmapTextHolder = new BitmapTextHolder("G", 48, typeOfFont, fontColor, 128);
        tempBitmapArrayOfHolders.add(tempBitmapTextHolder);
    }

    public static void createAttackTextObject(final int objectId, Context context)
    {
        Vector<BitmapTextHolder> tempBitmapArrayOfHolders=arrayOfTriangles.get(objectId).getBitmapArrayOfTextHolders();
        Vector<Integer> fontColor=new Vector<>();

        //R                     G                    B                   A
        fontColor.add(0);fontColor.add(0);fontColor.add(0);fontColor.add(255);
        Typeface typeOfFont = ResourcesCompat.getFont(context, R.font.latobold);

        BitmapTextHolder tempBitmapTextHolder;

        tempBitmapTextHolder = new BitmapTextHolder("Attack:", 49, typeOfFont, fontColor, 176);
        tempBitmapArrayOfHolders.add(tempBitmapTextHolder);


    }

    public static void createAgilityTextObject(final int objectId, Context context)
    {
        Vector<BitmapTextHolder> tempBitmapArrayOfHolders=arrayOfTriangles.get(objectId).getBitmapArrayOfTextHolders();
        Vector<Integer> fontColor=new Vector<>();

        //R                     G                    B                   A
        fontColor.add(0);fontColor.add(0);fontColor.add(0);fontColor.add(255);
        Typeface typeOfFont = ResourcesCompat.getFont(context, R.font.latobold);

        BitmapTextHolder tempBitmapTextHolder;

        tempBitmapTextHolder = new BitmapTextHolder("Agility:", 49, typeOfFont, fontColor, 176);
        tempBitmapArrayOfHolders.add(tempBitmapTextHolder);


    }

    public static void createVitalityTextObject(final int objectId, Context context)
    {
        Vector<BitmapTextHolder> tempBitmapArrayOfHolders=arrayOfTriangles.get(objectId).getBitmapArrayOfTextHolders();
        Vector<Integer> fontColor=new Vector<>();

        //R                     G                    B                   A
        fontColor.add(0);fontColor.add(0);fontColor.add(0);fontColor.add(255);
        Typeface typeOfFont = ResourcesCompat.getFont(context, R.font.latobold);

        BitmapTextHolder tempBitmapTextHolder;

        tempBitmapTextHolder = new BitmapTextHolder("Vitality:", 49, typeOfFont, fontColor, 176);
        tempBitmapArrayOfHolders.add(tempBitmapTextHolder);


    }

    public static void createAttribBarTextObject(final int objectId, Context context)
    {
        Vector<BitmapTextHolder> tempBitmapArrayOfHolders=arrayOfTriangles.get(objectId).getBitmapArrayOfTextHolders();
        Vector<Integer> fontColor=new Vector<>();

        //R                     G                    B                   A
        fontColor.add(60);fontColor.add(100);fontColor.add(255);fontColor.add(255);
        Typeface typeOfFont = ResourcesCompat.getFont(context, R.font.latobold);

        BitmapTextHolder tempBitmapTextHolder;
        for(int i=0;i<10;i++) {
            tempBitmapTextHolder = new BitmapTextHolder(i+"", 24, typeOfFont, fontColor, 64);
            tempBitmapArrayOfHolders.add(tempBitmapTextHolder);
        }


    }

    public static void createMenuTextObject(final int objectId, Context context)
    {
        Vector<BitmapTextHolder> tempBitmapArrayOfHolders=arrayOfTriangles.get(objectId).getBitmapArrayOfTextHolders();
        Vector<Integer> fontColor=new Vector<>();

        //R                     G                    B                   A
        fontColor.add(0);fontColor.add(0);fontColor.add(0);fontColor.add(255);
        Typeface typeOfFont = ResourcesCompat.getFont(context, R.font.latoregular);

        BitmapTextHolder tempBitmapTextHolder;
        tempBitmapTextHolder=new BitmapTextHolder("Menu",48,typeOfFont,fontColor,128);
        tempBitmapArrayOfHolders.add(tempBitmapTextHolder);
    }

    public static void welcomeMessageTextObject(final int objectId, Context context)
    {
        Vector<BitmapTextHolder> tempBitmapArrayOfHolders=arrayOfTriangles.get(objectId).getBitmapArrayOfTextHolders();
        Vector<Integer> fontColor=new Vector<>();

        //R                     G                    B                   A
        fontColor.add(0);fontColor.add(0);fontColor.add(0);fontColor.add(255);
        Typeface typeOfFont = ResourcesCompat.getFont(context, R.font.latoregular);

        BitmapTextHolder tempBitmapTextHolder;
        tempBitmapTextHolder=new BitmapTextHolder("Witaj! Twoim celem jest zniszczenie zamku przeciwnika. ",28,typeOfFont,fontColor,768);
        tempBitmapArrayOfHolders.add(tempBitmapTextHolder);

        tempBitmapTextHolder=new BitmapTextHolder("Pamiętaj, by ulepszać swoją postać. ",28,typeOfFont,fontColor,768);
        tempBitmapArrayOfHolders.add(tempBitmapTextHolder);

        tempBitmapTextHolder=new BitmapTextHolder("Koszt ulepszenia atrybutu: 50G*Poziom ",28,typeOfFont,fontColor,768);
        tempBitmapArrayOfHolders.add(tempBitmapTextHolder);

        tempBitmapTextHolder=new BitmapTextHolder("Powodzenia! ",28,typeOfFont,fontColor,768);
        tempBitmapArrayOfHolders.add(tempBitmapTextHolder);

        tempBitmapTextHolder=new BitmapTextHolder("Gra zaczyna się za: 3",28,typeOfFont,fontColor,768);
        tempBitmapArrayOfHolders.add(tempBitmapTextHolder);

        tempBitmapTextHolder=new BitmapTextHolder("Gra zaczyna się za: 2",28,typeOfFont,fontColor,768);
        tempBitmapArrayOfHolders.add(tempBitmapTextHolder);

        tempBitmapTextHolder=new BitmapTextHolder("Gra zaczyna się za: 1",28,typeOfFont,fontColor,768);
        tempBitmapArrayOfHolders.add(tempBitmapTextHolder);

        tempBitmapTextHolder=new BitmapTextHolder("Start!",28,typeOfFont,fontColor,768);
        tempBitmapArrayOfHolders.add(tempBitmapTextHolder);

    }

    public static void endMessageTextObject(final int objectId, Context context)
    {
        Vector<BitmapTextHolder> tempBitmapArrayOfHolders=arrayOfTriangles.get(objectId).getBitmapArrayOfTextHolders();
        Vector<Integer> fontColor=new Vector<>();

        //R                     G                    B                   A
        fontColor.add(0);fontColor.add(0);fontColor.add(0);fontColor.add(255);
        Typeface typeOfFont = ResourcesCompat.getFont(context, R.font.latoregular);

        BitmapTextHolder tempBitmapTextHolder;
        tempBitmapTextHolder=new BitmapTextHolder("Przegrana! Kliknij Menu, aby wyjść.",28,typeOfFont,fontColor,768);
        tempBitmapArrayOfHolders.add(tempBitmapTextHolder);

        tempBitmapTextHolder=new BitmapTextHolder("Wygrana! Kliknij Menu, aby wyjść.",28,typeOfFont,fontColor,768);
        tempBitmapArrayOfHolders.add(tempBitmapTextHolder);

    }




}
