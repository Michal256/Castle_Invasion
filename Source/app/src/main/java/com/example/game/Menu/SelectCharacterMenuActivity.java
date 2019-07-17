package com.example.game.Menu;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.game.R;

public class SelectCharacterMenuActivity extends AppCompatActivity implements View.OnClickListener {
    private Button button_back_play,button_swordsman,button_archer;
    private TextView textview_hero,textview_stats,textview_stats2,textview_stats3;
   private String current_hero,current_heroDMG,current_heroA,current_heroV;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_character_menu);
        new ScreenModifier(this.getWindow());

        button_back_play=findViewById(R.id.button_back_play_5);
        button_swordsman=findViewById(R.id.button_swordzman);
      //  button_archer=findViewById(R.id.button_archer);
        textview_hero=findViewById(R.id.textView_hero);
        textview_stats=findViewById(R.id.textView_stats);
        textview_stats2=findViewById(R.id.textView_stats_2);
        textview_stats3=findViewById(R.id.textView_stats_3);
       // textview_hero.setText(current_hero);
        button_back_play.setOnClickListener(this);
        button_swordsman.setOnClickListener(this);
        //button_archer.setOnClickListener(this);
    }

    private void backMenu(){
        Intent goingBack = new Intent();
        goingBack.putExtra(Intent.EXTRA_TEXT, current_hero);
        setResult(RESULT_OK, goingBack);
        finish();
    }
    private void choiceSwordsman(){
        current_hero="Swordsman";
        current_heroDMG="Attack: 10";
        current_heroA="Agility:  20";
        current_heroV="Vitality:  200";

        textview_hero.setText(current_hero);
        textview_stats.setText(current_heroDMG);
        textview_stats2.setText(current_heroA);
        textview_stats3.setText(current_heroV);
    }
    private void choiceArcher(){
        current_hero="Archer";
        current_heroDMG="Attack: 600";
        current_heroA="Agility:  200";
        current_heroV="Vitality: 200";

        textview_hero.setText(current_hero);
        textview_stats.setText(current_heroDMG);
        textview_stats2.setText(current_heroA);
        textview_stats3.setText(current_heroV);
    }




    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.button_swordzman:
                choiceSwordsman();
                break;
          //  case R.id.button_archer:
            //    choiceArcher();
            //    break;

            case R.id.button_back_play_5:
                backMenu();
                break;
        }
    }



}


