package com.example.irdi.seniorproject;

import android.provider.BaseColumns;

/**
 * Created by Irdi on 4/14/2015.
 */
public class DatabaseTable {

    public DatabaseTable(){}

    public static abstract class PlayerStatistics implements BaseColumns {
        public static final String DATABASE_NAME = "playerStatistics";
        public static final String TABLE_NAME = "PLAYER_STATS";
        public static final String COLUMN_NAME_PLAYER = "PLAYER";
        public static final String COLUMN_NAME_WON = "WON";
        public static final String COLUMN_NAME_LOST = "LOST";
        public static final String COLUMN_NAME_PERCENT = "PERCENT";
        private static final String TEXT_TYPE = " TEXT";
        private static final String INTEGER_TYPE = " INTEGER";
        private static final String COMMA_SEP = ",";
        public static final String SQL_CREATE_TABLE=
                "CREATE TABLE " + PlayerStatistics.TABLE_NAME + " (" +
                       // PlayerStatistics._ID + " INTEGER PRIMARY KEY," +
                        COLUMN_NAME_PLAYER + TEXT_TYPE + COMMA_SEP +
                        COLUMN_NAME_WON + INTEGER_TYPE + COMMA_SEP +
                        COLUMN_NAME_LOST + INTEGER_TYPE + COMMA_SEP +
                        COLUMN_NAME_PERCENT + INTEGER_TYPE +
                " );";

        private static final String SQL_DELETE_ENTRIES =
                "DROP TABLE IF EXISTS " + PlayerStatistics.TABLE_NAME;
    }


}
