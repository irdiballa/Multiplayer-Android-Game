package com.example.irdi.seniorproject;

/**
 * Created by Irdi on 4/23/2015.
 */
public class Game {

    private String player1,player2;
    private int pl1_score, pl2_score;

    Game(String pl1, String pl2){
        player1 = pl1;
        player2 = pl2;
        pl1_score = 0;
        pl2_score = 0;
    }

    public int player1Points(){

        return  pl1_score;
    }

    public int player2Points(){

        return  pl2_score;
    }

    public boolean isGameOver(){
        if(player1Points() == 3 || player2Points() == 3){
            return true;
        }
        else{
            return false;
        }
    }

    public void increasePlayer1Points(){

        pl1_score++;
    }

    public void increasePlayer2Points(){

        pl2_score++;
    }

    public String getWinner(){
        if (player1Points() ==3){
            return  player1;
        }
        else {
            return player2;
        }
    }

}
