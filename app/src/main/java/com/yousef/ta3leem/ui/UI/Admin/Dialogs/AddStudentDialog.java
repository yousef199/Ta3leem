package com.yousef.ta3leem.ui.UI.Admin.Dialogs;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;

import com.google.android.material.textfield.TextInputLayout;
import com.yousef.ta3leem.Constants;
import com.yousef.ta3leem.Data.FireBase.FireBaseHelper.Student;
import com.yousef.ta3leem.R;
import com.yousef.ta3leem.Repository.Repo;

import java.util.Map;

public class AddStudentDialog extends AppCompatDialogFragment {
    private TextInputLayout idInput, nameInput , ageInput , classInput , imageInput;
    AutoCompleteTextView classesAutoCompleteTextView;
    String id , name , age , className , imageUrl;
    Student student;
    Repo repo = new Repo();

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder= new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.studentadddialog , null);

        //Hooks
        idInput = view.findViewById(R.id.addStudentDialog_ID);
        nameInput = view.findViewById(R.id.addStudentDialog_Name);
        ageInput = view.findViewById(R.id.addStudentDialog_Age);
        imageInput = view.findViewById(R.id.addStudentDialog_Image);
        classesAutoCompleteTextView =view.findViewById(R.id.classesAutoCompleteText);
        classInput =view.findViewById(R.id.studentClassesTextInput);

        //Initialization
        initializeClassesDropDownList();

        //This makes the dialog have the custom view we created
        builder.setView(view).
                setTitle("اضافة طالب جديد").
                setNegativeButton("الغاء", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                }).
                setPositiveButton("اضافة", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });


        return builder.create();
    }

    @Override
    public void onResume() {
        super.onResume();
        final AlertDialog alertDialog = (AlertDialog) getDialog();

        if (alertDialog != null){
            Button positiveButton = (Button) alertDialog.getButton(Dialog.BUTTON_POSITIVE);
            positiveButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Initialization();
                    setError();
                    if (checkFields()){
                        addAllToFireBaseThread();
                        Toast.makeText(getActivity(), "تم اضافة الطالب بنجاح"  , Toast.LENGTH_SHORT).show();
                        alertDialog.dismiss();
                    }
                }

            });
        }
    }

    //All Methods

    // Assign the options from the list and assign the adapter
    private void initializeClassesDropDownList(){
        ArrayAdapter arrayAdapter = new ArrayAdapter<>(requireContext(), R.layout.classesdropdownlistdesign, Constants.CLASSES);
        classesAutoCompleteTextView.setAdapter(arrayAdapter);
    }

    // Get the checked items from the shared preferences and return them as a map
    public Map<String , ?> classesSharedPreferencesValue(){
        SharedPreferences classesSharedPreferences = getActivity().getSharedPreferences(Constants.CLASSES_PREF, Context.MODE_PRIVATE);
        Map<String , ?> chosenClasses = classesSharedPreferences.getAll();
        return chosenClasses;
    }

    //Clear the shared preferences file
    public void clearClassesSharedPref(){
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences(Constants.CLASSES_PREF , Context.MODE_PRIVATE);
        SharedPreferences.Editor editor= sharedPreferences.edit();
        editor.clear();
        editor.apply();
    }

    // set the entered items into a student object
    public Student setStudent(){
        Student student = new Student();
        student.setName(name);
        student.setId(id);
        student.setAge(age);
        student.setClassName(className);
        if(imageUrl.equals("")){
            imageUrl = "null";
            student.setImage(imageUrl);
        }
        else
            student.setImage(imageUrl);
        return student;
    }

    // check if any fields are null
    public boolean checkFields(){
        boolean state;

        if (id.equals("") || name.equals("") || age.equals("") || className.equals("") || className.equals("الصف"))
            state = false;
        else state = true;

        return state;
    }

    //sets errors on the null fields and removes them if the fields have values
    public void setError() {
        if (id.equals("")) {
            idInput.setError(Constants.EMPTY_FIELD_ERROR_MESSAGE);
            idInput.setErrorEnabled(true);
        } else {
            idInput.setError(null);
            idInput.setErrorEnabled(false);
        }

        if (name.equals("")) {
            nameInput.setError(Constants.EMPTY_FIELD_ERROR_MESSAGE);
            nameInput.setErrorEnabled(true);
        } else {
            nameInput.setError(null);
            nameInput.setErrorEnabled(false);
        }

        if (age.equals("")) {
            ageInput.setError(Constants.EMPTY_FIELD_ERROR_MESSAGE);
            ageInput.setErrorEnabled(true);
        } else {
            ageInput.setError(null);
            ageInput.setErrorEnabled(false);
        }

        if (className.equals("الصف") || className.equals("")) {
            classInput.setError(Constants.CHOOSE_ONE_ERROR_MESSAGE);
            classInput.setErrorEnabled(true);
        } else {
            classInput.setError(null);
            classInput.setErrorEnabled(false);
        }
    }

    // gets the values from the fields
    public void Initialization(){
        id = idInput.getEditText().getText().toString().trim();
        name = nameInput.getEditText().getText().toString().trim();
        age = ageInput.getEditText().getText().toString().trim();
        className = classesAutoCompleteTextView.getText().toString().trim();
        imageUrl = imageInput.getEditText().getText().toString().trim();
    }


    public void addAllToFireBaseThread(){
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                student = setStudent();
                repo.addStudentFireBase(student);
            }
        };
        Thread thread = new Thread(runnable);
        thread.start();
    }

 }

