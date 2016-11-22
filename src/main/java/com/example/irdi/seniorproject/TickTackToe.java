package com.example.irdi.seniorproject;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;


public class TickTackToe extends ActionBarActivity {

    String player1,player2;

    int c[][];
    int i, j, k = 0;
    Button b[][];
    TextView textView;
    int turn;
    Random t;
    boolean flag = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tick_tack_toe);

        player1 = getIntent().getExtras().getString("player1");
        player2 = getIntent().getExtras().getString("player2");
        t = new Random();
        turn= t.nextInt(2);
        setBoard();
    }


    // Set up the game board.
    private void setBoard() {
        b = new Button[4][4];
        c = new int[4][4];


        textView = (TextView) findViewById(R.id.dialogue);


        b[1][3] = (Button) findViewById(R.id.one);
        b[1][2] = (Button) findViewById(R.id.two);
        b[1][1] = (Button) findViewById(R.id.three);


        b[2][3] = (Button) findViewById(R.id.four);
        b[2][2] = (Button) findViewById(R.id.five);
        b[2][1] = (Button) findViewById(R.id.six);


        b[3][3] = (Button) findViewById(R.id.seven);
        b[3][2] = (Button) findViewById(R.id.eight);
        b[3][1] = (Button) findViewById(R.id.nine);

        for (i = 1; i <= 3; i++) {
            for (j = 1; j <= 3; j++)
                c[i][j] = 2;
        }

    if(turn == 0)
    {
        textView.setText(player1 + " starts the game!");
    }
        else
    {
        textView.setText(player2 + " starts the game!");
    }



        // add the click listeners for each button
        for (i = 1; i <= 3; i++) {
            for (j = 1; j <= 3; j++) {
                b[i][j].setOnClickListener(new MyClickListener(i, j));
                if(!b[i][j].isEnabled()) {
                    b[i][j].setText(" ");
                    b[i][j].setEnabled(true);
                }
            }
        }
    }


    class MyClickListener implements View.OnClickListener {
        int x;
        int y;


        public MyClickListener(int x, int y) {
            this.x = x;
            this.y = y;
        }


        public void onClick(View view) {
            if (b[x][y].isEnabled()) {
                b[x][y].setEnabled(false);
                if (turn == 0)
                {
                    b[x][y].setText("O");
                    c[x][y] = 0;
                    textView.setText("It is " + player2+"'s turn!");
                }
                else
                {
                    b[x][y].setText("X");
                    c[x][y] = 1;
                    textView.setText("It is " + player1+"'s turn!");
                }

                if (!checkBoard()) {
                    turn = (turn-1)*(-1);
                }
            }
        }
    }

    // check the board to see if someone has won
    private boolean checkBoard() {
        boolean gameOver = false;
        if ((c[1][1] == 0 && c[2][2] == 0 && c[3][3] == 0)
                || (c[1][3] == 0 && c[2][2] == 0 && c[3][1] == 0)
                || (c[1][2] == 0 && c[2][2] == 0 && c[3][2] == 0)
                || (c[1][3] == 0 && c[2][3] == 0 && c[3][3] == 0)
                || (c[1][1] == 0 && c[1][2] == 0 && c[1][3] == 0)
                || (c[2][1] == 0 && c[2][2] == 0 && c[2][3] == 0)
                || (c[3][1] == 0 && c[3][2] == 0 && c[3][3] == 0)
                || (c[1][1] == 0 && c[2][1] == 0 && c[3][1] == 0)) {
            textView.setText("Game over. " + player1 + " won!");
            gameOver = true;
            Intent resultIntent = new Intent();
            resultIntent.putExtra("winner", player1);
            setResult(RESULT_OK,resultIntent);
            finish();
        } else if ((c[1][1] == 1 && c[2][2] == 1 && c[3][3] == 1)
                || (c[1][3] == 1 && c[2][2] == 1 && c[3][1] == 1)
                || (c[1][2] == 1 && c[2][2] == 1 && c[3][2] == 1)
                || (c[1][3] == 1 && c[2][3] == 1 && c[3][3] == 1)
                || (c[1][1] == 1 && c[1][2] == 1 && c[1][3] == 1)
                || (c[2][1] == 1 && c[2][2] == 1 && c[2][3] == 1)
                || (c[3][1] == 1 && c[3][2] == 1 && c[3][3] == 1)
                || (c[1][1] == 1 && c[2][1] == 1 && c[3][1] == 1)) {
            textView.setText("Game over. " + player2 + " won!");
            gameOver = true;
            Intent resultIntent = new Intent();
            resultIntent.putExtra("winner", player2);
            setResult(RESULT_OK,resultIntent);
            finish();
        } else {
            boolean empty = false;
            for(i=1; i<=3; i++) {
                for(j=1; j<=3; j++) {
                    if(c[i][j]==2) {
                        empty = true;
                        break;
                    }
                }
            }
            if(!empty) {
                gameOver = true;
                textView.setText("Game over. It's a draw!");
                Intent resultIntent = new Intent();
                resultIntent.putExtra("winner", "no");
                setResult(RESULT_OK,resultIntent);
                finish();
            }
        }
        return gameOver;
    }

    @Override
    public void onBackPressed() {
        if (flag) {
            super.onBackPressed();
            return;
        }

        this.flag = true;
        Toast.makeText(this, "Press BACK again to exit", Toast.LENGTH_SHORT).show();

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                flag = false;
            }
        }, 2000);
    }


}
