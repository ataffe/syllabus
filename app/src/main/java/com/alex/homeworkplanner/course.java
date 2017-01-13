package com.alex.homeworkplanner;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

/**
 * Created by Alex Taffe on 12/30/2016.

*/

public class course implements Parcelable{
    private String className;
    private ArrayList<Assignment> assignments;
    private String grade;
    private double rawGrade;
    private String syllabus;
    public int numAssignments;



    public course(String name){
        assignments = new ArrayList<>();
        grade = "NO GRADE";
        rawGrade = 0.0;
        syllabus = "NO SYLLABUS";
        className = name;
    }

    private course(Parcel in){
        className = in.readString();
        assignments = new ArrayList<>();
        in.readTypedList(assignments,Assignment.CREATOR);
        grade = in.readString();
        rawGrade = in.readDouble();
        syllabus = in.readString();
    }

    @Override
    public void writeToParcel(Parcel out, int flags){
        out.writeString(className);
        out.writeTypedList(assignments);
        out.writeString(grade);
        out.writeDouble(rawGrade);
        out.writeString(syllabus);
    }

    @Override
    public int describeContents(){
        return 0;
    }

    public static final Parcelable.Creator<course> CREATOR = new Parcelable.Creator<course>(){

        @Override
        public course createFromParcel(Parcel in){
            return new course(in);
        }

        @Override
        public course[] newArray(int size){
            return new course[size];
        }
    };





    public void addAssignment(Assignment assignment){
        assignments.add(assignment);
    }

    public void setCourseName(String newName){
        className = newName;
    }

    public void setGrade(String newGrade){
        grade = newGrade;
    }

    public void setRawGrade(double raw){
        rawGrade = raw;
    }

    public void setSyllabus(String s){
        syllabus = s;
    }

    public String getGrade(){
        return grade;
    }

    public double getRawGrade(){
        return rawGrade;
    }

    public String getSyllabus(){
        return syllabus;
    }

    public String getClassName(){
        return className;
    }

    public ArrayList<Assignment> getAssignments(){
        return assignments;
    }

    public int getNumAssignments(){
        return assignments.size();
    }




}
