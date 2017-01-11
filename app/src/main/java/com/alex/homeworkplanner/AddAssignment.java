package com.alex.homeworkplanner;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.icu.util.Calendar;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;

public class AddAssignment extends AppCompatActivity {
    public int[] date;
    public EditText dateText;
    public Planner organizer;
    public final String Tag = "ADD ASSIGNMENT - ";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_assignment);
        date = new int[3];
        organizer = new Planner();
        initUIElements();

    }

    public void initUIElements(){
        dateText = (EditText) this.findViewById(R.id.dueDateField);
        dateText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if(b){
                    showDatePickerDialog(view);
                }
            }
        });


        final EditText pts = (EditText) findViewById(R.id.ptsField);
        pts.setOnKeyListener(new View.OnKeyListener() {
            boolean retValue = false;
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if((event.getAction() == KeyEvent.ACTION_DOWN)
                        && (keyCode == KeyEvent.KEYCODE_ENTER)){
                    InputMethodManager manager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    manager.hideSoftInputFromWindow(pts.getWindowToken(),0);
                    retValue = true;
                }
                return retValue;
            }
        });


    }

    public void addToHomepageNoSave(View view){
        Intent intent = new Intent(this,MainPage.class);
        startActivity(intent);
    }

    public void addToHomepageSave(View view){
        Intent intent = new Intent(this,MainPage.class);
        saveAssignment();
        intent.putExtra("thePlanner",organizer);
        startActivity(intent);
    }

    public void saveAssignment(){

        String assignmentName = "none";
        String className = "none";
        String dueDate = "none";
        String assignmentType = "none";
        String ptsWorth = "none";
        Boolean isTest = false;

        EditText nameField = (EditText)findViewById(R.id.nameField);
        EditText dueDateField = (EditText)findViewById(R.id.dueDateField);
        EditText typeField = (EditText)findViewById(R.id.typeField);
        EditText classNameField = (EditText)findViewById(R.id.classNameField);
        EditText ptsField = (EditText)findViewById(R.id.ptsField);
        CheckBox testField = (CheckBox)findViewById(R.id.test_checkbox);

        assignmentName = nameField.getText().toString();
        dueDate = dueDateField.getText().toString();
        assignmentType = typeField.getText().toString();
        className = classNameField.getText().toString();
        ptsWorth = ptsField.getText().toString();

        Log.d(Tag,"class name: " + className + "\n");

        if(testField.isChecked()){
            isTest = true;
        }

        course newCourse;


        if(!(organizer.getCourse(className).getClassName().equals("none"))){
            newCourse = new course(className);
            Log.d(Tag,"giving new course a name new name");
        }else{
            newCourse = organizer.getCourse(className);
        }

        if(assignmentName != "") {
            newCourse.addAssignment(new Assignment(assignmentName, dueDate, assignmentType, Integer.valueOf(ptsWorth), isTest));
            organizer.addNewCourse(newCourse);
        }

    }

    public void showDatePickerDialog(View view){
        DialogFragment fragment = new DatePickerFragment();

        fragment.show(getSupportFragmentManager(), "datePicker");
    }


    public static class DatePickerFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener{

        @RequiresApi(api = Build.VERSION_CODES.N)
        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState){
            //Use the current time as the default values for the picker
            final Calendar c = Calendar.getInstance();
            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH);
            int day = c.get(Calendar.DAY_OF_MONTH);

            if(month < 1){
                month = 1;
            }

            //Create a new Instance of TimePickerDialog and return it
            return new DatePickerDialog(getActivity(),this,year,month,day);
        }

        public void onDateSet(DatePicker view, int year, int month, int day){
            //do something
            EditText dateInput = (EditText) this.getActivity().findViewById(R.id.dueDateField);
            dateInput.setText(month + "/" + day + "/" + year);

            EditText typeText = (EditText) this.getActivity().findViewById(R.id.typeField);
            typeText.requestFocus();
        }
    }
}
