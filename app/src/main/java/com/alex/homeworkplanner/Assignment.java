package com.alex.homeworkplanner;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Alex Taffe on 12/27/2016.
 */

public class Assignment implements Parcelable {
    private String name;
    private String dueDateS;
    private int[] dueDateI;
    private int ptsWorth;
    private String grade;
    private float ptsRecieved;
    private int priority;
    private String type;
    private boolean isTest;


    public Assignment(String name, String dueDate, String type, int ptsWorth, Boolean isTest){
        this.name = name;
        this.dueDateS = dueDate;
        this.dueDateI = convertDate(dueDateS); //year month day
        this.ptsWorth = ptsWorth;
        this.grade = "none";
        this.ptsRecieved = 0;
        this.priority = 0;
        this.type = type;
        this.isTest = false;


    }

    private Assignment(Parcel in){
       name = in.readString();
       dueDateS =  in.readString();
       dueDateI = in.createIntArray();
       ptsWorth = in.readInt();
       grade =  in.readString();
       ptsRecieved = in.readFloat();
       priority = in.readInt();
       type = in.readString();
       isTest = Boolean.valueOf(in.readString());
    }

    @Override
    public void writeToParcel(Parcel out, int flags){
        out.writeString(name);
        out.writeString(dueDateS);
        out.writeIntArray(dueDateI);
        out.writeInt(ptsWorth);
        out.writeString(grade);
        out.writeFloat(ptsRecieved);
        out.writeInt(priority);
        out.writeString(type);
        out.writeString(Boolean.toString(isTest));
    }

    @Override
    public int describeContents(){
        return 0;
    }

    public static final Parcelable.Creator<Assignment> CREATOR = new Parcelable.Creator<Assignment>(){

        @Override
        public Assignment createFromParcel(Parcel in){
            return new Assignment(in);
        }

        @Override
        public Assignment[] newArray(int size){
            return new Assignment[size];
        }
    };





    public int[] convertDate(String date){
        int[] returnData = new int[3];
        String[] splitDate = date.split("/");
        returnData[0] = Integer.valueOf(splitDate[0]);
        returnData[1] = Integer.valueOf(splitDate[1]);
        returnData[2] = Integer.valueOf(splitDate[2]);
        return returnData;
    }

    public void setAssignmentName(String name){
        this.name = name;
    }

    public void setDueDateS(String dueDateS){
        this.dueDateS = dueDateS;
    }

    public void setPtsWorth(int pts){
        this.ptsWorth = pts;
    }

    public void setGrade(String grade){
        this.grade = grade;
    }

    public void setPriority(int newPriority){
        priority = newPriority;
    }

    public void setType(String type){
        this.type = type;
    }

    public void setTest(boolean value){
        this.isTest = value;
    }

    public void setPtsRecieved(float pts){
        ptsRecieved = pts;
    }

    public String getAssignmentName(){
        return name;
    }

    public String getDueDateS(){
        return dueDateS;
    }

    public int[] getDueDateI(){
        return dueDateI;
    }

    public int getPtsWorth(){
        return ptsWorth;
    }

    public String getGrade(){
        return grade;
    }

    public int getPriority(){
        return priority;
    }

    public String getType(){
        return type;
    }

    public boolean getTest(){
        return isTest;
    }


}
