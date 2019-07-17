package com.example.game.Menu;

import android.content.Intent;
import android.opengl.GLSurfaceView;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import static com.example.game.Game.UserSettingsBinder.gameInLoadingState;
import com.example.game.R;

public class PlayMenuActivity extends AppCompatActivity implements View.OnClickListener {

    private GLSurfaceView gLView;
    private Button button_search, button_select, button_back_play, button_character, button_strategy;
    private ImageView loadingImage;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_menu);
        new ScreenModifier(this.getWindow());
        gameInLoadingState =false;
        button_search = findViewById(R.id.button_search);
      //  button_select = findViewById(R.id.button_select);
        button_character = findViewById(R.id.button_character);
        button_strategy = findViewById(R.id.button_strategy);
        button_back_play = findViewById(R.id.button_back_play);
        loadingImage=findViewById(R.id.LoadingImage);
        setElements();
        button_search.setOnClickListener(this);
       // button_select.setOnClickListener(this);
        button_character.setOnClickListener(this);
        button_strategy.setOnClickListener(this);
        button_back_play.setOnClickListener(this);
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        setElements();
    }

    private void searchGame() {
        gameInLoadingState =true;
        setElements();
        Intent intent;
        Log.d("new", "playGame: click11 ");
        intent = new Intent(this, SearchGameActivity.class);
        startActivity(intent);
    }

    private void selectGame() {
        Intent intent;
        intent = new Intent(this, SelectRoomMenuActivity.class);
        startActivity(intent);
    }

    private void selectCharacter() {
        Intent intent;
        intent = new Intent(this, SelectCharacterMenuActivity.class);
        startActivity(intent);
    }

    private void selectStrategy() {
        Intent intent;
        intent = new Intent(this, SelectStrategyMenuActivity.class);
        startActivity(intent);
    }

    private void backMenu() {
        Intent goingBack = new Intent();
        setResult(RESULT_OK, goingBack);
        finish();
    }
private void setElements(){
        if(gameInLoadingState ==true) {
            button_search.setVisibility(View.INVISIBLE);
            button_character.setVisibility(View.INVISIBLE);
            button_strategy.setVisibility(View.INVISIBLE);
            button_back_play.setVisibility(View.INVISIBLE);
            loadingImage.setVisibility(View.VISIBLE);
        }
        else{
            button_search.setVisibility(View.VISIBLE);
            button_character.setVisibility(View.VISIBLE);
            button_strategy.setVisibility(View.VISIBLE);
            button_back_play.setVisibility(View.VISIBLE);
            loadingImage.setVisibility(View.INVISIBLE);
        }

}

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button_strategy:
                selectStrategy();
                break;
            case R.id.button_character:
                selectCharacter();
                break;
           // case R.id.button_select:
             //   selectGame();
               // break;
            case R.id.button_search:
                searchGame();
                break;
            case R.id.button_back_play:
                backMenu();
                break;
        }
    }
}