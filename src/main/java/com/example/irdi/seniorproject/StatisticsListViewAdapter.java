package com.example.irdi.seniorproject;

import static com.example.irdi.seniorproject.Constants.PLAYER_COLUMN;
import static com.example.irdi.seniorproject.Constants.WON_COLUMN;
import static com.example.irdi.seniorproject.Constants.LOST_COLUMN;
import static com.example.irdi.seniorproject.Constants.PERCENT_COLUMN;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Irdi on 4/25/2015.
 */
public class StatisticsListViewAdapter extends BaseAdapter {

    public ArrayList<HashMap<String,String>> list;
    Activity activity;

    public StatisticsListViewAdapter(Activity activity,ArrayList<HashMap<String,String>> list){
        super();
        this.activity = activity;
        this.list = list;
    }


    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position){
        return list.get(position);
    }

    @Override
    public long getItemId(int position){
        return 0;
    }

    private class ViewHolder{
        TextView player;
        TextView won;
        TextView lost;
        TextView percent;
    }
    @Override
    public  View getView(int position, View convertView, ViewGroup parent){

        ViewHolder holder;
        LayoutInflater inflater = activity.getLayoutInflater();

        if (convertView == null){
            convertView = inflater.inflate(R.layout.line_statistics, null);
            holder = new ViewHolder();

            holder.player = (TextView) convertView.findViewById(R.id.player);
            holder.won = (TextView) convertView.findViewById(R.id.won);
            holder.lost = (TextView) convertView.findViewById(R.id.lost);
            holder.percent = (TextView) convertView.findViewById(R.id.percent);

            convertView.setTag(holder);
        }
        else{

            holder = (ViewHolder) convertView.getTag();

        }
        HashMap <String, String> map = list.get(position);
        holder.player.setText(map.get(PLAYER_COLUMN));
        holder.won.setText(map.get(WON_COLUMN));
        holder.lost.setText(map.get(LOST_COLUMN));
        holder.percent.setText(map.get(PERCENT_COLUMN));

        return convertView;
    }

}
