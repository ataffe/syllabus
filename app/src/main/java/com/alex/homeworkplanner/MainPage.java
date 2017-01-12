package com.alex.homeworkplanner;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.util.Log;
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
    public final String Tag = "Main Page - ";

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
                for(int i = 0; i < organizer.getNumCourses(); i++) {
                    course class1 = organizer.getCourse(i);
                    String classname = class1.getClassName();
                    classname = classname.toUpperCase();
                    String assignmentName = class1.getAssignments().get(i).getAssignmentName();
                    assignmentName = CapitalizeFirstLetter(assignmentName);
                    String dueDate = class1.getAssignments().get(i).getDueDateS();

                    int pts = class1.getAssignments().get(i).getPtsWorth();
                    titles.add(assignmentName + " \t\t\t\t\t Class: " + classname + " \t\t\t\t\t\t Due: " + dueDate);
                    content.add("Pts: " + Integer.toString(pts) + " \t\t\t\t\t\t\t " + "Class Grade: B+");
                }
            } catch (NullPointerException e) {
                titles.add("Swipe right to enter new Assignments");
                content.add("no assignments yet");
                e.printStackTrace();
            } catch (IndexOutOfBoundsException e) {
                titles.add("Swipe right to enter new Assignments");
                content.add("no assignments yet");
                e.printStackTrace();
            }


        adapter = new CustomExpandableListAdapter(this,titles,content);
        assignments.setAdapter(adapter);

    }

    private String CapitalizeFirstLetter(String input){
        input.replaceAll("\\s+","");
        String[] ret = input.split("");
        ret[1] = ret[1].toUpperCase();

        StringBuilder builder = new StringBuilder();
        builder.append(ret[1]);
        for (int i = 2; i < ret.length ; i++) {
            ret[i] = ret[i].toLowerCase();
            builder.append(ret[i]);
        }

        return builder.toString();
    }

}
