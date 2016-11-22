package com.example.irdi.seniorproject;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;


public class NewGame extends ActionBarActivity {
   ArrayList<String> PLAYERS = new ArrayList<String>();
   Context ctx = this;
    Spinner player1, player2;
    Button play;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_game);
        player1 = (Spinner) findViewById(R.id.spn_player1);
        player2 = (Spinner) findViewById(R.id.spn_player2);
        play = (Button) findViewById(R.id.btn_play);

        DatabaseHandler dbh = new DatabaseHandler(ctx);
        Cursor cr = dbh.getAllPlayers(dbh);
        String username = "";

        if (cr.moveToFirst())
        {
            do {
                username = cr.getString(0);
                PLAYERS.add(username);
            }while(cr.moveToNext());

            ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(this,  android.R.layout.simple_spinner_item, PLAYERS);
            spinnerArrayAdapter.setDropDownViewResource( android.R.layout.simple_spinner_dropdown_item );

            player1.setAdapter(spinnerArrayAdapter);
            player2.setAdapter(spinnerArrayAdapter);
            play.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v)
                {
                    String pl1 = player1.getSelectedItem().toString();
                    String pl2 = player2.getSelectedItem().toString();
                    if (pl1 == pl2)
                    {
                        Toast.makeText(getApplicationContext(), "Please select different players!",Toast.LENGTH_LONG).show();
                    }
                    else{
                        Intent intent = new Intent(getApplicationContext(), Challenge.class);
                        intent.putExtra("player1",pl1);
                        intent.putExtra("player2",pl2);
                        startActivity(intent);
                    }

                }
            });

        } else
        {
            Toast.makeText(getApplicationContext(),"Please create players first", Toast.LENGTH_LONG).show();
            finish();
        }





    }
}
