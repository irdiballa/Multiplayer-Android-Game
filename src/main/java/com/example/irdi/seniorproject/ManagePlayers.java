package com.example.irdi.seniorproject;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;


public class ManagePlayers extends ActionBarActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_players);

    }

    public void addPlayer(View view) {

        startActivity(new Intent(this, AddPlayer.class));
    }
    public void deletePlayer(View view) {

            startActivity(new Intent(this, DeletePlayer.class));
    }

}
