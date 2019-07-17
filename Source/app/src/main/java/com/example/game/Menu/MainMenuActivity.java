package com.example.game.Menu;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import static com.example.game.Game.UserSettingsBinder.soundTurnedOn;
import com.example.game.R;
/**
 * @author Michał Ziółek, Patryk Wyczesany
 */
public class MainMenuActivity extends AppCompatActivity implements android.view.View.OnClickListener {
    private Button button_play, button_options, button_credits, button_exit;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
        new ScreenModifier(this.getWindow());
        soundTurnedOn =true;
        button_play = findViewById(R.id.button_play);
        button_options = findViewById(R.id.button_options);
        button_credits = findViewById(R.id.button_credits);
        button_exit = findViewById(R.id.button_exit);
        button_play.setOnClickListener(this);
        button_options.setOnClickListener(this);
        button_credits.setOnClickListener(this);
    }

    private void playGame() {
        Intent intent;
        intent = new Intent(this, PlayMenuActivity.class);
        startActivity(intent);
    }

    private void optionsMenu() {
        Intent intent;
        intent = new Intent(this, OptionsMenuActivity.class);
        startActivity(intent);
    }

    private void creditsMenu() {
        Intent intent;
        intent = new Intent(this, CreditsMenuActivity.class);
        startActivity(intent);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.button_credits:
                creditsMenu();
                break;

            case R.id.button_options:
                optionsMenu();
                break;

            case R.id.button_play:
                playGame();
                break;
            case R.id.button_exit:
                finish();
                System.exit(0);
                break;
        }
    }
}
