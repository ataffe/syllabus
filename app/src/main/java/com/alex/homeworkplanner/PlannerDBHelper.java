package com.alex.homeworkplanner;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

/**
 * Created by Alexxx on 1/11/2017.
 */

//// TODO: 1/12/2017 FINISH IMPLEMENTING NEDDED METHODS
public class PlannerDBHelper extends SQLiteOpenHelper {

    public ArrayList<String> classes;
    public Planner planner;
    public boolean classesRead;
    public String classname;
    public int PRIMARY_KEY;

    public static final int     DATABASE_VERSION  = 1;
    public static final String  DATABASE_NAME = "Planner.db";
    public static final String  COLUMN_NAME_ASSIGNMENTS = "assignments";
    public static final String  COLUMN_NAME_DUE_DATE = "due_date";
    public static final String  COLUMN_NAME_TYPE = "type";
    public static final String  COLUMN_NAME_PTS = "pts";
    public static final String  COLUMN_NAME_ID = "id";


    public PlannerDBHelper(Context context, Planner input){
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
        planner = input;
        classesRead =false;
        readInClasses();
        PRIMARY_KEY = 0;
    }

    private void readInClasses(){
        if(null != planner) {
            for (int i = 0; i < planner.getNumCourses(); i++) {
                classes.add(planner.getCourse(i).getClassName().replaceAll(" ","_"));
            }
            classesRead =true;
        }
    }

    public void onCreate(SQLiteDatabase db){
        if(classesRead) {
            createTables(db);
        }
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        //warning on upgrade deletes all the data and starts over with new tables
        deleteAllTables(db);
        createTables(db);
    }

    private void createTables(SQLiteDatabase db){

        for(int i = 0; i < classes.size(); i++){
            db.execSQL("CREATE TABLE " + classes.get(i) + "("
            + COLUMN_NAME_ASSIGNMENTS + " TEXT,"
            + COLUMN_NAME_DUE_DATE + " TEXT,"
            + COLUMN_NAME_TYPE + " TEXT,"
            + COLUMN_NAME_PTS + " INTEGER,"
            + COLUMN_NAME_ID + " INTEGER PRIMARY KEY)");
        }
    }

    private void deleteAllTables(SQLiteDatabase db){
        ArrayList deleteCommands = new ArrayList();

        for (int i = 0; i < classes.size(); i++){
            db.execSQL("DROP TABLE IF EXISTS " + classes.get(i));
        }
    }

    public void updateDatabase(){
        //Gets the data repository in write mode
        SQLiteDatabase database = this.getWritableDatabase();

        // create a new map of values, where column names are the keys
        ContentValues values = new ContentValues();

        for(int i =0; i < classes.size(); i++){

            for (int j = 0; j < planner.getCourse(i).getNumAssignments(); j++){
                values.put(COLUMN_NAME_ASSIGNMENTS,);
            }
        }

    }


}
