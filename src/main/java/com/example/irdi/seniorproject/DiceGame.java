package com.example.irdi.seniorproject;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;


public class DiceGame extends ActionBarActivity {

    String player1,player2;
    boolean flag = false;
    int turn;
    Random t;
    TextView player1name,player1score,player2name,player2score,currScore,description;
    ImageButton dice;
    int score1,score2,currentScore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dice_game);
        Log.d("Success","1");
        player1 = getIntent().getExtras().getString("player1");
        player2 = getIntent().getExtras().getString("player2");
        t= new Random();
        turn = t.nextInt(2);
        score1=0;
        score2=0;
        currentScore=0;
        player1name = (TextView) findViewById(R.id.player1name);
        player1score = (TextView) findViewById(R.id.player1score);
        player2name = (TextView) findViewById(R.id.player2name);
        player2score = (TextView) findViewById(R.id.player2score);
        currScore = (TextView) findViewById(R.id.currentscore);
        description = (TextView) findViewById(R.id.description);
        dice = (ImageButton) findViewById(R.id.dice);
        player1name.setText(player1);
        player2name.setText(player2);
        changeTexts();
    }

    private void changeTexts(){
        player1score.setText(String.valueOf(score1));
        player2score.setText(String.valueOf(score2));
        currScore.setText(String.valueOf(currentScore));
        if(turn == 0)
        {
            description.setText("It is "+player1+"'s turn!");
        }
        else{
            description.setText("It is "+player2+"'s turn!");
        }
        if(score1 >= 100)
        {
            description.setText(player1+" won!");
            Intent resultIntent = new Intent();
            resultIntent.putExtra("winner", player1);
            setResult(RESULT_OK,resultIntent);
            finish();
        }
        if(score2 >= 100)
        {
            description.setText(player2+" won!");
            Intent resultIntent = new Intent();
            resultIntent.putExtra("winner", player2);
            setResult(RESULT_OK,resultIntent);
            finish();
        }
    }





    public void rollDice(View view) {
        Random dice_nr = new Random();
        switch (dice_nr.nextInt(6) + 1) {
            case 1:
                dice.setBackground(getResources().getDrawable(R.drawable.one));
                currentScore=0;



                turn = (turn-1)*(-1);




                changeTexts();
                break;
            case 2:
                dice.setBackground(getResources().getDrawable(R.drawable.two));
                currentScore+=2;
                changeTexts();
                break;
            case 3:
                dice.setBackground(getResources().getDrawable(R.drawable.three));
                currentScore+=3;
                changeTexts();
                break;
            case 4:
                dice.setBackground(getResources().getDrawable(R.drawable.four));
                currentScore+=4;
                changeTexts();
                break;
            case 5:
                dice.setBackground(getResources().getDrawable(R.drawable.five));
                currentScore+=5;
                changeTexts();
                break;
            case 6:
                dice.setBackground(getResources().getDrawable(R.drawable.six));
                currentScore+=6;
                changeTexts();
                break;
            default:
        }
    }

    public void holdScore(View view){
        if(turn==0){
            score1 += currentScore;
            turn = (turn-1)*(-1);
            currentScore = 0;
            changeTexts();
        }
        else{
            score2 += currentScore;
            turn = (turn-1)*(-1);
            currentScore = 0;
            changeTexts();
        }
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
