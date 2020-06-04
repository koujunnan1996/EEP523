package com.example.tictactoe;

        import androidx.annotation.NonNull;
        import androidx.appcompat.app.AppCompatActivity;

        import android.content.DialogInterface;
        import android.os.Bundle;
        import android.service.autofill.OnClickAction;
        import android.view.View;
        import android.widget.Button;
        import android.widget.TextView;
        import android.widget.Toast;
        import com.example.tictactoe.MainActivity3;

public class MainActivity2 extends AppCompatActivity implements View.OnClickListener {

    private Button[][] buttons = new Button[3][3];
    private boolean winFlag;

    private int roundCount;

    private int player_1points;
    private int player_2points;

    private TextView textViewPlayer1;
    private TextView textViewPlayer2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        if (MainActivity.playerTurn){
            winFlag = true;
        }else{
            winFlag = false;
        }
        textViewPlayer1 = findViewById(R.id.text_view_p1);
        textViewPlayer2 = findViewById(R.id.text_view_p2);

        for (int i = 0 ; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                String buttonID = "button_" + i + j;
                int resID = getResources().getIdentifier(buttonID, "id", getPackageName());
                buttons[i][j] = findViewById(resID);
                buttons[i][j].setOnClickListener(this);
            }
        }

        Button buttonReset = findViewById(R.id.button_reset);
        buttonReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GameRestart();
            }
        });
    }

    @Override
    public void onClick(View v) {
        if (!((Button) v).getText().toString().equals("")) {
            return;
        }

        if (MainActivity.playerTurn) {
            ((Button) v).setText("X");
        }else{
            ((Button) v).setText("O");
        }

        roundCount++;

        if (checkWin()){
            if (MainActivity.playerTurn == winFlag) {
                player1Win();
            }else{
                player2Win();
            }
        }else if (roundCount == 9) {
            NoWinner();
        }else{
            MainActivity.playerTurn = !MainActivity.playerTurn;
        }

    }

    private boolean checkWin() {
        String[][] chessBoard = new String[3][3];

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                chessBoard[i][j] = buttons[i][j].getText().toString();
            }
        }

        for(int i = 0; i < 3; i ++){
            if (chessBoard[i][0].equals(chessBoard[i][1]) &&chessBoard[i][0].equals(chessBoard[i][2]) && !chessBoard[i][0].equals("")){
                return true;
            }
        }

        for(int i = 0; i < 3; i ++){
            if (chessBoard[0][i].equals(chessBoard[1][i]) && chessBoard[0][i].equals(chessBoard[2][i]) && !chessBoard[0][i].equals("")){
                return true;
            }
        }

        if (chessBoard[0][0].equals(chessBoard[1][1]) && chessBoard[0][0].equals(chessBoard[2][2]) && !chessBoard[0][0].equals("")){
            return true;
        }

        if (chessBoard[0][2].equals(chessBoard[1][1]) && chessBoard[0][2].equals(chessBoard[2][0]) && !chessBoard[0][2].equals("")){
            return true;
        }

        return false;

    }

    private void player1Win() {
        player_1points++;
        Toast.makeText(this,"player 1 win !",Toast.LENGTH_SHORT).show();
        changePoints();
        reset();
    }

    private void player2Win() {
        player_2points++;
        Toast.makeText(this,"player 2 win !",Toast.LENGTH_SHORT).show();
        changePoints();
        reset();
    }

    private void NoWinner () {
        Toast.makeText(this,"No winner !",Toast.LENGTH_SHORT).show();
        reset();
    }

    private void changePoints(){
        textViewPlayer1.setText("Player 1's score : " + player_1points);
        textViewPlayer2.setText("Player 2's score : " + player_2points);
    }
    private void reset(){
        for (int i = 0; i < 3; i++){
            for (int j = 0; j < 3; j ++){
                buttons[i][j].setText("");
            }
        }
        roundCount = 0;
        //MainActivity.playerTurn = true;
    }

    private void GameRestart(){
        player_1points = 0;
        player_2points = 0;
        changePoints();
        reset();
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putInt("roundCount",roundCount);
        outState.putInt("player_1points",player_1points);
        outState.putInt("player_2points",player_2points);
        outState.putBoolean("player_1Turn",MainActivity.playerTurn);
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        roundCount = savedInstanceState.getInt("roundCount");
        player_1points = savedInstanceState.getInt("player_1points");
        player_2points = savedInstanceState.getInt("player_2points");
        MainActivity.playerTurn = savedInstanceState.getBoolean("player_1Trun");
    }
}
