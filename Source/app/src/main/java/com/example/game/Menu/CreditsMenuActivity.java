package com.example.game.Menu;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.game.R;

public class CreditsMenuActivity extends AppCompatActivity implements View.OnClickListener {
    private Button button_back_play;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_credits_menu);
        new ScreenModifier(this.getWindow());

        button_back_play=findViewById(R.id.button_back_play_3);
        button_back_play.setOnClickListener(this);
    }


    private void backMenu(){
        Intent goingBack = new Intent();
        setResult(RESULT_OK, goingBack);
        finish();
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){

            case R.id.button_back_play_3:
                backMenu();
                break;
        }
    }

}
