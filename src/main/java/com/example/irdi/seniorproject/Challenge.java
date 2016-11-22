package com.example.irdi.seniorproject;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;


public class Challenge extends ActionBarActivity {
    Game game;
    String player1,player2;
    TextView player1name,player2name,player1score,player2score;
    ArrayList<String> PLAYERS = new ArrayList<String>();
    Context ctx = this;
    DatabaseHandler dbh;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_challenge);

        player1 = getIntent().getExtras().getString("player1");
        player2 = getIntent().getExtras().getString("player2");
        game = new Game(player1,player2);
        player1name = (TextView)findViewById(R.id.challenge_player1name);
        player2name = (TextView)findViewById(R.id.challenge_player2name);
        player1score = (TextView)findViewById(R.id.challenge_player1score);
        player2score = (TextView)findViewById(R.id.challenge_player2score);
        player1name.setText(player1);
        player2name.setText(player2);
        player1score.setText(String.valueOf(game.player1Points()));
        player2score.setText(String.valueOf(game.player2Points()));
        dbh = new DatabaseHandler(ctx);


    }

    @Override
    public void onResume(){
        super.onResume();
        if (game.isGameOver()) {
            Toast.makeText(getApplicationContext(), "Player " +game.getWinner()+" won the challenge "+game.player1Points()+" to "+game.player2Points() ,Toast.LENGTH_LONG).show();
            onGameOver();
            finish();
        }

    }

    public void changeResult(){
        player1score.setText(String.valueOf(game.player1Points()));
        player2score.setText(String.valueOf(game.player2Points()));
    }

    public void startTickTackToe(View view){
        Intent intent = new Intent(this,TickTackToe.class);
        intent.putExtra("player1",player1);
        intent.putExtra("player2",player2);
        startActivityForResult(intent, 1);
    }

    public void startDiceGame(View view){
        Intent intent = new Intent(this,DiceGame.class);
        intent.putExtra("player1",player1);
        intent.putExtra("player2",player2);
        startActivityForResult(intent, 1);
    }

    public void startComingSoon(View view){
        Toast.makeText(getApplicationContext(), "This game will be created soon!",Toast.LENGTH_LONG).show();
    }

    public void onGameOver(){
        if(game.player1Points() == 3){
            dbh.updateWinner(dbh,player1);
            dbh.updateLoser(dbh,player2);
        }
        if(game.player2Points() == 3){
            dbh.updateWinner(dbh,player2);
            dbh.updateLoser(dbh,player1);
        }
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == 1) {
            if(resultCode == RESULT_OK){
                String winner=data.getStringExtra("winner");
                if(player1.equals(winner)){
                    game.increasePlayer1Points();
                }
                else if(player2.equals(winner)){
                    game.increasePlayer2Points();
                }

                changeResult();
            }
            if (resultCode == RESULT_CANCELED) {
            }
        }
    }

}
