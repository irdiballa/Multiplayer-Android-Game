package com.example.irdi.seniorproject;

import android.app.AlertDialog;
import android.app.ListActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import java.lang.String;

import java.util.ArrayList;


public class DeletePlayer extends ListActivity {

    ArrayList<String> PLAYERS = new ArrayList<String>();
    Context ctx = this;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_player);
        DatabaseHandler dbh = new DatabaseHandler(ctx);
        Cursor cr = dbh.getAllPlayers(dbh);

        String username = "";

        if (cr.moveToFirst())
        {
            do {
                username = cr.getString(0);
                PLAYERS.add(username);
            }while(cr.moveToNext());

        } else
        {
            Toast.makeText(getApplicationContext(), "In order to delete a player you must create a player first", Toast.LENGTH_LONG).show();
            finish();
        }

        setListAdapter(new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, PLAYERS));
        getListView().setTextFilterEnabled(true);
    }

    @Override
    protected void onListItemClick(ListView l, View v, final int position, long id) {
        // TODO Auto-generated method stub
        super.onListItemClick(l, v, position, id);

        new AlertDialog.Builder(this)
                .setTitle("Delete")
                .setMessage("Are you sure you want to delete  " + getListView().getItemAtPosition(position))
                .setPositiveButton("No",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                            }
                        })
                .setNegativeButton("Yes",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                Context context = getApplicationContext();
                                DatabaseHandler dbh = new DatabaseHandler(context);
                                String username = "";
                                username = getListView().getItemAtPosition(position).toString();
                                dbh.deletePlayer(dbh,username);
                                String text = username +" was successfully deleted";
                                int duration = Toast.LENGTH_SHORT;
                                Toast toast = Toast.makeText(context, text, duration);
                                toast.show();
                                finish();
                            }
                        })
                .show();
    }

}