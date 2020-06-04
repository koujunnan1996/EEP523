
package com.example.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    public static boolean playerTurn;
    private Button button_Instruction;
    private Button button_Start;
    private Button button_choose;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        button_Start = (Button) findViewById(R.id.Start_Game);
        button_Start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GameStart();
            }
        });

        button_choose = (Button) findViewById(R.id.Player_choose);
        button_choose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PlayerChoose();
            }
        });

        button_Instruction = (Button) findViewById(R.id.game_instruction);
        button_Instruction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShowInstruction();
            }
        });
    }

    public void GameStart(){
        Intent intent2 = new Intent(this,MainActivity2.class);
        startActivity(intent2);
    }

    public void PlayerChoose(){
        Intent intent = new Intent(this, MainActivity3.class);
        startActivity(intent);
    }

    public void ShowInstruction(){
        Intent intent3 = new Intent(this, Main4Activity.class);
        startActivity(intent3);
    }
}