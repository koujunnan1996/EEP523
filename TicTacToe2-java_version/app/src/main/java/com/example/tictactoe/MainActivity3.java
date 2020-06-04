package com.example.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class MainActivity3 extends AppCompatActivity {
    private Button button_X;
    private Button button_O;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        button_X = (Button) findViewById(R.id.Player_choose_X);
        button_X.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iconChooseX();
            }
        });

        button_O = (Button) findViewById(R.id.Player_choose_O);
        button_O.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iconChooseO();
            }
        });
    }

    public void iconChooseX(){
        MainActivity.playerTurn = true;
        Intent intent1 = new Intent(this,MainActivity2.class);
        startActivity(intent1);
    }

    public void iconChooseO(){
        MainActivity.playerTurn = false;
        Intent intent2 = new Intent(this,MainActivity2.class);
        startActivity(intent2);
    }
}
