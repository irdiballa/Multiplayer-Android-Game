package com.example.irdi.seniorproject;

import static com.example.irdi.seniorproject.Constants.PLAYER_COLUMN;
import static com.example.irdi.seniorproject.Constants.WON_COLUMN;
import static com.example.irdi.seniorproject.Constants.LOST_COLUMN;
import static com.example.irdi.seniorproject.Constants.PERCENT_COLUMN;

import android.content.Context;
import android.database.Cursor;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Statistics extends ActionBarActivity {

    private ArrayList<HashMap<String, String>> list;
    ListView listView;
    Context ctx = this;
    Cursor cr;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistics);

        listView = (ListView)findViewById(R.id.stats_list);
        DatabaseHandler dbh = new DatabaseHandler(ctx);
        cr = dbh.getStatistics(dbh);
        populateList();
        StatisticsListViewAdapter adapter = new StatisticsListViewAdapter(this,list);


        listView.setAdapter(adapter);
        }

    private void populateList() {

        list = new ArrayList<HashMap<String, String>>();

        HashMap<String,String> temp = new HashMap<String,String>();
        temp.put(PLAYER_COLUMN, "Player");
        temp.put(WON_COLUMN, "W");
        temp.put(LOST_COLUMN, "L");
        temp.put(PERCENT_COLUMN, "%");
        list.add(temp);

        if (cr.moveToFirst())
        {
            do {
                HashMap<String,String> temp1 = new HashMap<String,String>();
                String username,win,lost,percent;

                username = cr.getString(0);
                win = cr.getString(1);
                lost = cr.getString(2);
                percent = cr.getString(3);

                temp1.put(PLAYER_COLUMN, username);
                temp1.put(WON_COLUMN, win);
                temp1.put(LOST_COLUMN, lost);
                temp1.put(PERCENT_COLUMN, percent);
                list.add(temp1);
            }while(cr.moveToNext());

        } else
        {
            Toast.makeText(getApplicationContext(), "No players to show", Toast.LENGTH_LONG).show();
            finish();
        }




    }

}
