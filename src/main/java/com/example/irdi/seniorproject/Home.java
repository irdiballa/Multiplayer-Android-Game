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

public class Home extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

    }

    public void newGame(View view) {

        startActivity(new Intent(this, NewGame.class));
    }

    public void statistics(View view) {

        startActivity(new Intent(this, Statistics.class));
    }

    public void managePlayers(View view) {

        startActivity(new Intent(this, ManagePlayers.class));
    }

}
