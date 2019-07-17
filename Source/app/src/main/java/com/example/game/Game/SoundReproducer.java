package com.example.game.Game;

import android.content.Context;
import android.media.MediaPlayer;

import com.example.game.R;

import static com.example.game.Game.UserSettingsBinder.soundTurnedOn;

public class SoundReproducer
{
    private static MediaPlayer timeCountSound;
    private static MediaPlayer gameBeginSound;

    private static MediaPlayer playerAttack;
    private static MediaPlayer goblinAttack;
    private static MediaPlayer spectreAttack;
    private static MediaPlayer castleAttack;
    private static MediaPlayer resourceGain;
    private static MediaPlayer levelUp;

    private static MediaPlayer goblinDeath;
    private static MediaPlayer spectreDeath;
    private static boolean soundsImplemented=false;

    public SoundReproducer(Context context)
    {
        try {
            goblinAttack = MediaPlayer.create(context, R.raw.goblin_attack);
            playerAttack = MediaPlayer.create(context, R.raw.player_attack);
            spectreAttack = MediaPlayer.create(context, R.raw.spectre_attack);
            castleAttack = MediaPlayer.create(context, R.raw.castle_attack);

            goblinDeath= MediaPlayer.create(context, R.raw.goblin_death);
            spectreDeath= MediaPlayer.create(context, R.raw.spectre_death);

            levelUp = MediaPlayer.create(context, R.raw.levelup);
            resourceGain = MediaPlayer.create(context, R.raw.coin);

            timeCountSound = MediaPlayer.create(context, R.raw.count);
            gameBeginSound = MediaPlayer.create(context, R.raw.begin);

            soundsImplemented=true;
        }
        catch(Exception ex)
        {
            soundsImplemented=false;
        }
    }

    public static void playSoundAttackGoblin() {
        if(soundsImplemented==true&&soundTurnedOn==true) {
            goblinAttack.start();
        }
    }

    public static void playSoundDeathGoblin() {
        if(soundsImplemented==true&&soundTurnedOn==true) {
            goblinDeath.start();
        }
    }

    public static void playSoundAttackPlayer() {
        if(soundsImplemented==true&&soundTurnedOn==true) {
            playerAttack.start();
        }
    }

    public static void playSoundAttackSpectre() {
        if(soundsImplemented==true&&soundTurnedOn==true) {
            spectreAttack.start();
        }
    }

    public static void playSoundDeathSpectre() {
        if(soundsImplemented==true&&soundTurnedOn==true) {
            spectreDeath.start();
        }
    }

    public static void playSoundAttackCastle() {
        if(soundsImplemented==true&&soundTurnedOn==true) {
            castleAttack.start();
        }
    }

    public static void playSoundResourceGain() {
        if(soundsImplemented==true&&soundTurnedOn==true) {
            resourceGain.start();
        }
    }

    public static void playSoundLevelUp() {
        if(soundsImplemented==true&&soundTurnedOn==true) {
            levelUp.start();
        }
    }

    public static void playSoundGameBegin() {
        if(soundsImplemented==true&&soundTurnedOn==true) {
            gameBeginSound.start();
        }
    }

    public static void playSoundGameCount() {
        if(soundsImplemented==true&&soundTurnedOn==true) {
            timeCountSound.start();
        }
    }

}
