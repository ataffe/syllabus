package com.alex.homeworkplanner;

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
    public static final int     DATABASE_VERSION  = 1;
    public static final String  DATABASE_NAME = "Planner.db";
    public static final String  COLUMN_NAME_CLASS = "class";
    public static final String  COLUMN_NAME_ASSIGNMENTS = "assignments";


    public PlannerDBHelper(Context context, Planner input){
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
        planner = input;
        classesRead =false;
        readInClasses();
    }

    private void readInClasses(){
        if(null != planner) {
            for (int i = 0; i < planner.getNumCourses(); i++) {
                classes.add(planner.getCourse(i).getClassName());
            }
            classesRead =true;
        }
    }

    public void onCreate(SQLiteDatabase db){
        if(classesRead) {
            for (int i = 0; i < classes.size(); i++) {
                //// TODO: 1/12/2017 Add column for due date, assignment type, pts!
                db.execSQL("create table " + classes.get(i) + " ( " + COLUMN_NAME_ASSIGNMENTS + ");");
            }
        }
    }


}
