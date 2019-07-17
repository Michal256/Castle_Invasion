package com.example.game.Menu;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.example.game.R;

import java.util.ArrayList;

public class SelectRoomMenuActivity extends AppCompatActivity implements View.OnClickListener {

    private Button button_back_play,button_play;
    private ListView server_chooser;
    private EditText search_text;
    private ArrayAdapter list_adapter;
    ArrayList<String> servers;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_room_menu);
        new ScreenModifier(this.getWindow());

        button_back_play=findViewById(R.id.button_back_play_4);
        button_play=findViewById(R.id.button_play_2);
        search_text=findViewById(R.id.editText_search);
        server_chooser=findViewById(R.id.server_list);
        button_back_play.setOnClickListener(this);
        button_play.setOnClickListener(this);

        servers= new ArrayList<>();
        for(int i=1;i<50;i++){
            servers.add("server "+i);
        }
        arrayFilter();
    }

    private  void arrayFilter(){
        list_adapter= new ArrayAdapter(this,android.R.layout.simple_list_item_1,servers);
        server_chooser.setAdapter(list_adapter);
        search_text.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                (SelectRoomMenuActivity.this).list_adapter.getFilter().filter(s);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    private void backMenu(){
        Intent goingBack = new Intent();
        setResult(RESULT_OK, goingBack);
        finish();
    }
    private void selectedGame(){
        Intent intent;
        Log.d("new", "playGame: click11 ");
        intent = new Intent(this, SearchGameActivity.class);
        startActivity(intent);
    }


    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.button_play_2:
                selectedGame();
                break;
            case R.id.button_back_play_4:
                backMenu();
                break;
        }
    }

}
