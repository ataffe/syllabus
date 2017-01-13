package com.alex.homeworkplanner;

import android.provider.BaseColumns;

/**
 * Created by Alexxx on 1/12/2017.
 */

public class PlannerContract {
    // To prevent someone from accidentally instantiating the contract class,
    // make the constructor private.
    private PlannerContract(){}

    //Inner class that defines the table contents
    public static class PlannerEntry implements BaseColumns{

        public static final String  COLUMN_NAME_ASSIGNMENTS = "assignments";
        public static final String  COLUMN_NAME_DUE_DATE = "due_date";
        public static final String  COLUMN_NAME_TYPE = "type";
        public static final String  COLUMN_NAME_PTS = "pts";

        public static final String  COLUMN_NAME_CLASS = "class";
    }
}
