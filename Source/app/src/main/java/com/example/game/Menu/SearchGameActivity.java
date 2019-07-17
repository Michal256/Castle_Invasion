package com.example.game.Menu;

import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ConfigurationInfo;
import android.opengl.GLSurfaceView;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.example.game.Game.EventGameSynchronizator;
import com.example.game.Game.MyGLSurfaceView;
import static com.example.game.Game.GameResourceBinder.gameIsOn;
import static com.example.game.Game.GameResourceBinder.gameIsLost;
import static com.example.game.Game.GameResourceBinder.gameLoaded;
import static com.example.game.Game.GameResourceBinder.gameState;

import java.util.concurrent.ScheduledThreadPoolExecutor;

import static java.util.concurrent.TimeUnit.MILLISECONDS;

public class SearchGameActivity extends AppCompatActivity {
    private static GLSurfaceView gLView;

    private static ScheduledThreadPoolExecutor eventPool;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        new ScreenModifier(this.getWindow());
        final ActivityManager activityManager=(ActivityManager)getSystemService(Context.ACTIVITY_SERVICE);
        final ConfigurationInfo configurationInfo=activityManager.getDeviceConfigurationInfo();
        final boolean supportsES2=configurationInfo.reqGlEsVersion>=0x20000;

        if(supportsES2) {

            gLView = new MyGLSurfaceView(this);
            setContentView(gLView);
            addThreadsToPool();
        }
        else
        {
            return;
        }


    }

    @Override
    protected void onPause() {
        super.onPause();

    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        eventPool.shutdown();

        gameState=0;
        gameIsOn=false;
        gameLoaded=false;
        gameIsLost=true;


    }


    public static void addThreadsToPool()
    {
        eventPool=new ScheduledThreadPoolExecutor(1);
        eventPool.scheduleAtFixedRate(new EventGameSynchronizator(gLView),0,20,MILLISECONDS);
    }
}
