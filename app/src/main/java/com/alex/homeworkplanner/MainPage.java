package com.alex.homeworkplanner;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CalendarView;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;

import java.util.ArrayList;

public class MainPage extends Activity {
    public CalendarView homeCalendar;
    public ExpandableListView assignments;
    public ExpandableListAdapter adapter;
    public ArrayList<String> titles;
    public ArrayList<String> content;
    public Planner organizer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);
        organizer = getIntent().getParcelableExtra("thePlanner");
        init();
    }


    public void mainToAdd(View view){
        Intent intent = new Intent(this,AddAssignment.class);
        startActivity(intent);
    }

    public void init(){
        homeCalendar = (CalendarView) findViewById(R.id.main_calendar);
        assignments = (ExpandableListView) findViewById(R.id.assignments_listview);
        titles = new ArrayList<>();
        content = new ArrayList<>();


        try {
            course class1 = organizer.getCourse(0);
            String classname = class1.getClassName();
            String assignmentName = class1.getAssignments().get(0).getAssignmentName();
            String dueDate = class1.getAssignments().get(0).getDueDateS();
            int pts = class1.getAssignments().get(0).getPtsWorth();
            titles.add(assignmentName + " \t Class: " + classname + " \t Due: " + dueDate);
            content.add("Pts: " + Integer.toString(pts) + " \t " + "Class Grade: B+");
        }catch(NullPointerException e){
            titles.add("Swipe right to enter new Assignments");
            content.add("no assignments yet");
            e.printStackTrace();
        }catch (IndexOutOfBoundsException e){
            titles.add("Swipe right to enter new Assignments");
            content.add("no assignments yet");
            e.printStackTrace();
        }

        adapter = new CustomExpandableListAdapter(this,titles,content);
        assignments.setAdapter(adapter);

    }

}
