package com.example.irdi.seniorproject;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by Irdi on 4/14/2015.
 */
public class DatabaseHandler extends SQLiteOpenHelper {

    public static final int database_version = 1;

    public DatabaseHandler(Context context){
        super(context, DatabaseTable.PlayerStatistics.DATABASE_NAME,null, database_version);
    }
        @Override
    public void onCreate(SQLiteDatabase sdb){
            sdb.execSQL(DatabaseTable.PlayerStatistics.SQL_CREATE_TABLE);
        }

    public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2){

    }

    public void CreateNewPlayer(DatabaseHandler dbh, String player){
        SQLiteDatabase SQ = dbh.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(DatabaseTable.PlayerStatistics.COLUMN_NAME_PLAYER, player);
        cv.put(DatabaseTable.PlayerStatistics.COLUMN_NAME_WON, 0);
        cv.put(DatabaseTable.PlayerStatistics.COLUMN_NAME_LOST, 0);
        cv.put(DatabaseTable.PlayerStatistics.COLUMN_NAME_PERCENT, 0);
        SQ.insert(DatabaseTable.PlayerStatistics.TABLE_NAME, null, cv);
    }

    public Cursor getAllPlayers(DatabaseHandler dbh){
        SQLiteDatabase SQ = dbh.getReadableDatabase();
        String[] columns = {DatabaseTable.PlayerStatistics.COLUMN_NAME_PLAYER};
        Cursor CR = SQ.query(DatabaseTable.PlayerStatistics.TABLE_NAME, columns, null,null,null,null,null);
        return CR;
    }

    public Cursor getStatistics(DatabaseHandler dbh){
        SQLiteDatabase SQ = dbh.getReadableDatabase();
        String[] columns = {DatabaseTable.PlayerStatistics.COLUMN_NAME_PLAYER, DatabaseTable.PlayerStatistics.COLUMN_NAME_WON, DatabaseTable.PlayerStatistics.COLUMN_NAME_LOST, DatabaseTable.PlayerStatistics.COLUMN_NAME_PERCENT};
        Cursor CR = SQ.query(DatabaseTable.PlayerStatistics.TABLE_NAME, columns, null,null,null,null, DatabaseTable.PlayerStatistics.COLUMN_NAME_PERCENT+" DESC");
        return CR;
    }

    public void deletePlayer(DatabaseHandler dbh, String player){
        String selection = DatabaseTable.PlayerStatistics.COLUMN_NAME_PLAYER+ " LIKE ?";
        String args[] = {player};
        SQLiteDatabase SQ = dbh.getWritableDatabase();
        SQ.delete(DatabaseTable.PlayerStatistics.TABLE_NAME,selection,args);
    }

    public void updateWinner(DatabaseHandler dbh, String winner){
        int wins = 0;
        int loss = 0;
        int percent = 0;
        SQLiteDatabase sq = dbh.getReadableDatabase();
        String[] columns = {DatabaseTable.PlayerStatistics.COLUMN_NAME_WON,
                DatabaseTable.PlayerStatistics.COLUMN_NAME_LOST, DatabaseTable.PlayerStatistics.COLUMN_NAME_PERCENT};
        String sel = DatabaseTable.PlayerStatistics.COLUMN_NAME_PLAYER+ " LIKE ?";
        String arg[] = {winner};
        Cursor CR = sq.query(DatabaseTable.PlayerStatistics.TABLE_NAME,columns,sel,arg,null,null,null);
        CR.moveToFirst();
        wins = CR.getInt(0)+1;
        loss = CR.getInt(1);
        percent=(100*wins)/(wins+loss);
        sq.close();

        sq = dbh.getWritableDatabase();
        String selection = DatabaseTable.PlayerStatistics.COLUMN_NAME_PLAYER+" LIKE ?";
        String args[] = {winner};
        ContentValues values = new ContentValues();
        values.put(DatabaseTable.PlayerStatistics.COLUMN_NAME_WON,wins);
        values.put(DatabaseTable.PlayerStatistics.COLUMN_NAME_LOST,loss);
        values.put(DatabaseTable.PlayerStatistics.COLUMN_NAME_PERCENT,percent);
        sq.update(DatabaseTable.PlayerStatistics.TABLE_NAME,values,selection,args);

    }

    public void updateLoser(DatabaseHandler dbh, String loser){
        int wins = 0;
        int loss = 0;
        int percent = 0;
        SQLiteDatabase sq = dbh.getReadableDatabase();
        String[] columns = {DatabaseTable.PlayerStatistics.COLUMN_NAME_WON, DatabaseTable.PlayerStatistics.COLUMN_NAME_LOST, DatabaseTable.PlayerStatistics.COLUMN_NAME_PERCENT};
        String sel = DatabaseTable.PlayerStatistics.COLUMN_NAME_PLAYER+ " LIKE ?";
        String arg[] = {loser};
        Cursor CR = sq.query(DatabaseTable.PlayerStatistics.TABLE_NAME,columns,sel,arg,null,null,null);
        CR.moveToFirst();
        wins = CR.getInt(0);
        loss = CR.getInt(1)+1;
        percent=(100*wins)/(wins+loss);
        sq.close();

        sq = dbh.getWritableDatabase();
        String selection = DatabaseTable.PlayerStatistics.COLUMN_NAME_PLAYER+" LIKE ?";
        String args[] = {loser};
        ContentValues values = new ContentValues();
        values.put(DatabaseTable.PlayerStatistics.COLUMN_NAME_WON,wins);
        values.put(DatabaseTable.PlayerStatistics.COLUMN_NAME_LOST,loss);
        values.put(DatabaseTable.PlayerStatistics.COLUMN_NAME_PERCENT,percent);
        sq.update(DatabaseTable.PlayerStatistics.TABLE_NAME,values,selection,args);

    }
}
