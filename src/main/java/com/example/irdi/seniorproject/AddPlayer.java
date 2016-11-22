package com.example.irdi.seniorproject;

import android.content.Context;
import android.database.Cursor;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;


public class AddPlayer extends ActionBarActivity {

    EditText USERNAME;
    String username;
    Button ADD_PLAYER;
    Context ctx;
    ArrayList<String> PLAYERS = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_player);
        USERNAME = (EditText) findViewById(R.id.txt_username);
        ADD_PLAYER = (Button) findViewById(R.id.btn_save_player);
        ctx = this;

        DatabaseHandler dbh = new DatabaseHandler(ctx);
        Cursor cr = dbh.getAllPlayers(dbh);

        String username = "";

        if (cr.moveToFirst())
        {
            do {
                username = cr.getString(0);
                PLAYERS.add(username);
            }while(cr.moveToNext());

        }
    }

    public void savePlayer(View view){


        username = USERNAME.getText().toString();
        username = username.replace(" ", "");
        boolean flag= true;

        if(PLAYERS.size() == 0) {
            if (username.length() >= 4 && username.length() <= 16) {
                DatabaseHandler DB = new DatabaseHandler(ctx);
                DB.CreateNewPlayer(DB, username);
                Toast.makeText(getBaseContext(), "The player was added", Toast.LENGTH_LONG).show();
                finish();
            } else {

                Toast.makeText(getBaseContext(), "Please enter a username between 4 and 16 characters", Toast.LENGTH_LONG).show();
                USERNAME.setText("");

            }
        }
        else{
            for (int i=0;i<PLAYERS.size();i++)
            {
                if(PLAYERS.get(i).equals(username) ){
                    flag = false;
                    break;
                }
            }
            if(flag)
            {
                if (username.length() >= 4 && username.length() <= 16) {
                    DatabaseHandler DB = new DatabaseHandler(ctx);
                    DB.CreateNewPlayer(DB, username);
                    Toast.makeText(getBaseContext(), "The player was added", Toast.LENGTH_LONG).show();
                    finish();
                } else {

                    Toast.makeText(getBaseContext(), "Please enter a username between 4 and 16 characters", Toast.LENGTH_LONG).show();
                    USERNAME.setText("");

                }
            }
            else{
                Toast.makeText(getBaseContext(), "This player already exists", Toast.LENGTH_LONG).show();
            }
        }
    }
}
