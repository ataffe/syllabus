package com.alex.homeworkplanner;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.alex.homeworkplanner.PlannerContract.PlannerEntry;
import java.util.ArrayList;


/**
 * Created by Alexxx on 1/11/2017.
 */

//// TODO: 1/12/2017 FINISH IMPLEMENTING NEDDED METHODS
public class PlannerDBHelper extends SQLiteOpenHelper {

    public ArrayList<String> classes;
    public Planner planner;
    public boolean classesRead;
    public static final int     DATABASE_VERSION  = 1;
    public static final String  DATABASE_NAME = "Planner.db";




    public PlannerDBHelper(Context context, Planner input){
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
        planner = input;
        classesRead =false;
        readInClasses();

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
        db.execSQL("CREATE TABLE class_names" +"("
        + PlannerEntry.COLUMN_NAME_CLASS
        + PlannerEntry._ID + "INTEGER PRIMARY KEY)");

        for(int i = 0; i < classes.size(); i++){
            db.execSQL("CREATE TABLE " + classes.get(i) + "("
            + PlannerEntry.COLUMN_NAME_ASSIGNMENTS + " TEXT,"
            + PlannerEntry.COLUMN_NAME_DUE_DATE + " TEXT,"
            + PlannerEntry.COLUMN_NAME_TYPE + " TEXT,"
            + PlannerEntry.COLUMN_NAME_PTS + " INTEGER,"
            + PlannerEntry._ID + " INTEGER PRIMARY KEY)");
        }
    }

    private void deleteAllTables(SQLiteDatabase db){
        ArrayList deleteCommands = new ArrayList();

        db.execSQL("DROP TABLE IF EXISTS class_names");

        for (int i = 0; i < classes.size(); i++){
            db.execSQL("DROP TABLE IF EXISTS " + classes.get(i));
        }
    }

    public void saveAssignment(String classname, Assignment assignment){
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(PlannerEntry.COLUMN_NAME_ASSIGNMENTS,assignment.getAssignmentName());
        values.put(PlannerEntry.COLUMN_NAME_DUE_DATE,assignment.getDueDateS());
        values.put(PlannerEntry.COLUMN_NAME_TYPE,assignment.getType());
        values.put(PlannerEntry.COLUMN_NAME_PTS,assignment.getPtsWorth());

        database.insert(classname,null,values);
        database.close();
    }

    //// TODO: 1/12/2017 FIX TO MAKE IT UPDATE INSTEAD OF ADD EVERYTHING.
    public void updateDatabase(){
        //Gets the data repository in write mode
        SQLiteDatabase database = this.getWritableDatabase();

        // create a new map of values, where column names are the keys
        ContentValues values = new ContentValues();

        for(int i =0; i < classes.size(); i++){
            course currentClass = planner.getCourse(i);
            ArrayList<Assignment> assignments = currentClass.getAssignments();
            for (int j = 0; j <assignments.size(); j++) {
                values.put(PlannerEntry.COLUMN_NAME_ASSIGNMENTS, assignments.get(i).getAssignmentName());
                values.put(PlannerEntry.COLUMN_NAME_DUE_DATE, assignments.get(i).getDueDateS());
                values.put(PlannerEntry.COLUMN_NAME_TYPE, assignments.get(i).getType());
                values.put(PlannerEntry.COLUMN_NAME_PTS, assignments.get(i).getPtsWorth());
            }
            database.insert(classes.get(i),null,values);
            values.clear();
        }
        database.close();
    }

    public Planner getDatabasePlanner(){
        SQLiteDatabase database = this.getReadableDatabase();
        Planner retPlanner = new Planner();
        Cursor cursor = database.rawQuery("SELECT * FROM class_names",null);
        ArrayList<String> classes = new ArrayList<>();
        while(cursor.moveToNext()){
            classes.add(cursor.getString(Integer.valueOf(PlannerEntry._ID)));
        }
        cursor.moveToFirst();


        for(int i = 0; i < classes.size(); i++){
            course tmpClass = new course(classes.get(i));
            cursor = database.rawQuery("SELECT * FROM " + classes.get(i),null);
            while(cursor.moveToNext()){
            }
        }


        return retPlanner;

    }



}
