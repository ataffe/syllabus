package com.alex.homeworkplanner;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

/**
 * Created by Alex Taffe on 12/11/2016.
 */

public class Planner implements Parcelable {
    private ArrayList<course> classes;
    private double gpa;


    public Planner(){
        classes = new ArrayList<>();
        gpa = 0.0;
    }

    private Planner(Parcel in){
        classes = new ArrayList<course>();
        in.readTypedList(classes, course.CREATOR);
        gpa = in.readDouble();
    }

    @Override
    public void writeToParcel(Parcel out , int flags){
        out.writeTypedList(classes);
        out.writeDouble(gpa);
    }

    @Override
    public int describeContents(){
        return 0;
    }

    public static final Parcelable.Creator<Planner> CREATOR = new Parcelable.Creator<Planner>(){

        @Override
        public Planner createFromParcel(Parcel in){
            return new Planner(in);
        }

        @Override
        public Planner[] newArray(int size){
            return new Planner[size];
        }
    };



    public void addNewCourse(course newCourse){
        classes.add(newCourse);
    }

    public void setGpa(double gpa){
        this.gpa = gpa;
    }

    public course getCourse(int index){
        return classes.get(index);
    }

    public course getCourse(String name){
        course retValue = new course("none");

        for(int iterator = 0; iterator < classes.size(); iterator++){
            if(name.toLowerCase().equals(classes.get(iterator).getClassName().toLowerCase())){
                retValue = classes.get(iterator);
            }
        }

        return retValue;
    }

    public int getNumCourses(){
        return classes.size();
    }

}
