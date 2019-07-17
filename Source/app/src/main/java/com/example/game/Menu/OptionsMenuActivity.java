package com.example.game.Menu;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import static com.example.game.Game.UserSettingsBinder.soundTurnedOn;
import com.example.game.R;

public class OptionsMenuActivity extends AppCompatActivity implements View.OnClickListener {

    private Button button_back_play;
    private TextView textview_settings,textview_sound;
    private CheckBox checkbox_sound;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings_menu);
        new ScreenModifier(this.getWindow());
        textview_settings=findViewById(R.id.textView_settings);
        textview_settings=findViewById(R.id.textView2_sound);
        checkbox_sound=findViewById(R.id.checkBox_sound);
        button_back_play=findViewById(R.id.button_back_play_2);
        button_back_play.setOnClickListener(this);
        checkbox_sound.setOnClickListener(this);
        startingSwitchPosition();
    }


    private void backMenu(){
        Intent goingBack = new Intent();
        setResult(RESULT_OK, goingBack);
        finish();
    }

    private void startingSwitchPosition(){
        if(soundTurnedOn ==true){
            checkbox_sound.setChecked(true);
        }
        else{
            checkbox_sound.setChecked(false);
        }
    }

    private void soundSwitch(){
        if (checkbox_sound.isChecked()){
            soundTurnedOn =true;
        }
        else{
            soundTurnedOn =false;
        }
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.checkBox_sound:{
                soundSwitch();
            }
            break;
            case R.id.button_back_play_2:
                backMenu();
                break;
        }
    }

}
