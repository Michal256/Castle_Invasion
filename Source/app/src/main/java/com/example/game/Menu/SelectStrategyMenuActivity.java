package com.example.game.Menu;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import static com.example.game.Game.UserSettingsBinder.gameTactic;
import com.example.game.R;

public class SelectStrategyMenuActivity extends AppCompatActivity implements View.OnClickListener {
    private Button button_back_play,button_normal,button_more_warriors,button_more_archers;
    private TextView textView_choice_window;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_strategy_menu);
        new ScreenModifier(this.getWindow());

        button_back_play=findViewById(R.id.button_back_play_6);
        button_normal=findViewById(R.id.button_normal);
        button_more_warriors=findViewById(R.id.button_warriors);
        button_more_archers=findViewById(R.id.button_archers);
        textView_choice_window=findViewById(R.id.textView_tactic);

        if(gameTactic==0){
            choiceNormal();
        }
        else if(gameTactic==1){
            choiceMoreWarriors();
        }
        else if(gameTactic==2){
            choiceMoreArchers();
        }

        button_back_play.setOnClickListener(this);
        button_normal.setOnClickListener(this);
        button_more_warriors.setOnClickListener(this);
        button_more_archers.setOnClickListener(this);
    }


    private void backMenu(){
        Intent goingBack = new Intent();
        setResult(RESULT_OK, goingBack);
        finish();
    }
    private void choiceNormal(){
        gameTactic=0;
        textView_choice_window.setText("Normal");
    }
    private void choiceMoreWarriors(){
        gameTactic=1;
        textView_choice_window.setText("More Warriors");
    }
    private void choiceMoreArchers(){
        gameTactic=2;
        textView_choice_window.setText("More Archers");
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.button_archers:
                choiceMoreArchers();
                break;
            case R.id.button_warriors:
                choiceMoreWarriors();
                break;
            case R.id.button_normal:
                choiceNormal();
                break;
            case R.id.button_back_play_6:
                backMenu();
                break;
        }
    }

}


