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
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;

import com.google.android.material.textfield.TextInputLayout;
import com.yousef.ta3leem.Adapters.ClassesSpinnerAdapter;
import com.yousef.ta3leem.Adapters.SubjectsSpinnerAdapter;
import com.yousef.ta3leem.Constants;
import com.yousef.ta3leem.Data.FireBase.FireBaseHelper.ClassesSubjects;
import com.yousef.ta3leem.Data.FireBase.FireBaseHelper.Teacher;
import com.yousef.ta3leem.Helper.StateVO;
import com.yousef.ta3leem.R;
import com.yousef.ta3leem.Repository.Repo;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class AddTeacherDialog extends AppCompatDialogFragment {
    TextInputLayout idInput, nameInput, ageInput;
    Button addClassAndSubjects;
    AutoCompleteTextView classesAutoCompleteTextView ,subjectsAutoCompleteTextView ;
    Teacher teacher;
    String id , name , age;
    Map <String , ?> chosenClasses , chosenSubjects;
    List<String> subjectsList = new ArrayList<>();
    List<String> classesList = new ArrayList<>();
    List<ClassesSubjects> classesSubjectsList = new ArrayList<>();
    ClassesSubjects classesSubjects = new ClassesSubjects();
    Repo repo = new Repo();

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder= new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.addteacherdialog , null);

        //Hooks
        idInput = view.findViewById(R.id.addTeacherDialog_ID);
        nameInput = view.findViewById(R.id.addTeacherDialog_Name);
        ageInput = view.findViewById(R.id.addTeacherDialog_Age);
        addClassAndSubjects = view.findViewById(R.id.addClassAndSubjects);
        classesAutoCompleteTextView = view.findViewById(R.id.classesAutoCompleteText);
        subjectsAutoCompleteTextView = view.findViewById(R.id.subjectsAutoCompleteText);


        //Initializations
        initializeSubjectsDropDownList();
        initializeClassesDropDownList();


        //This makes the dialog have the custom view we created
        builder.setView(view).
                setTitle("اضافة مدرس جديد").
                setNegativeButton("الغاء", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        clearClassesSharedPref();
                        clearSubjectsSharedPref();
                    }
                }).
                setPositiveButton("اضافة", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });

        clicks();


        return builder.create();
    }

    @Override
    public void onResume() {
        super.onResume();
        final AlertDialog alertDialog = (AlertDialog) getDialog();

        if (alertDialog != null){
            Button positiveButton = (Button) alertDialog.getButton(Dialog.BUTTON_POSITIVE);
            positiveButton.setOnClickListener(new View.OnClickListener() {

                //Todo: Call the add to add to the firebase
                @Override
                public void onClick(View view) {
                    Initialization();
                    setError();
                    if (checkFields()){
                        addAllToFireBaseThread();
                        alertDialog.dismiss();
                    }
                }

            });
        }
    }

    //All Methods

    // Assign the options from the list and assign the adapter
    private void initializeSubjectsDropDownList(){
        ArrayList<StateVO> listVOs = new ArrayList<>();
        for (int i = 0; i < Constants.SUBJECTS.length; i++) {
            StateVO stateVO = new StateVO();
            stateVO.setTitle(Constants.SUBJECTS[i]);
            stateVO.setSelected(false);
            listVOs.add(stateVO);
        }
        SubjectsSpinnerAdapter subjectsSpinnerAdapter = new SubjectsSpinnerAdapter(getActivity(), 0,
                listVOs);
        subjectsAutoCompleteTextView.setAdapter(subjectsSpinnerAdapter);
    }

    // Assign the options from the list and assign the adapter
    private void initializeClassesDropDownList(){
        ArrayAdapter arrayAdapter = new ArrayAdapter<>(requireContext(), R.layout.classesdropdownlistdesign, Constants.CLASSES);
        classesAutoCompleteTextView.setAdapter(arrayAdapter);
    }

    // Get the checked items from the shared preferences and return them as a map
    public Map<String , ?> classesSharedPreferencesValue(){
        SharedPreferences classesSharedPreferences = getActivity().getSharedPreferences(Constants.CLASSES_PREF,Context.MODE_PRIVATE);
        Map<String , ?> chosenClasses = classesSharedPreferences.getAll();
        return chosenClasses;
    }

    // Get the checked items from the shared preferences and return them as a map
    public Map<String , ?> subjectsSharedPreferencesValue(){
        SharedPreferences subjectsSharedPreferences = getActivity().getSharedPreferences(Constants.SUBJECTS_PREF,Context.MODE_PRIVATE);
        Map<String , ?> chosenSubjects = subjectsSharedPreferences.getAll();
        return chosenSubjects;
    }

    //Clear the shared preferences file
    public void clearClassesSharedPref(){
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences(Constants.CLASSES_PREF , Context.MODE_PRIVATE);
        SharedPreferences.Editor editor= sharedPreferences.edit();
        editor.clear();
        editor.apply();
    }

    //Clear the shared preferences file
    public void clearSubjectsSharedPref(){
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences(Constants.SUBJECTS_PREF , Context.MODE_PRIVATE);
        SharedPreferences.Editor editor= sharedPreferences.edit();
        editor.clear();
        editor.apply();
    }

    //clear the spinner to clear the check boxes
    public void clearSubjectsDropDownList(){
        SubjectsSpinnerAdapter subjectsSpinnerAdapter = (SubjectsSpinnerAdapter) subjectsAutoCompleteTextView.getAdapter();
        subjectsSpinnerAdapter.clear();
        subjectsSpinnerAdapter.notifyDataSetChanged();
    }

    //clear the spinner to clear the check boxes
    public void clearClassesDropDownList(){
//        ArrayAdapter arrayAdapter = (ArrayAdapter) classesAutoCompleteTextView.getAdapter();
//        arrayAdapter.clear();
//        arrayAdapter.notifyDataSetChanged();
    }

    //this method calls all the clear functions (used after adding a class and respective subjects)
    public void clearAll(){
        clearClassesSharedPref();
        clearSubjectsSharedPref();
        clearSubjectsDropDownList();
//        clearClassesDropDownList();
    }

    // set the entered items into a teacher object
    public Teacher setTeacher(){
        Teacher teacher = new Teacher();
        teacher.setName(name);
        teacher.setId(id);
        teacher.setAge(age);
        return teacher;
    }

    // check if any fields are null
    public boolean checkFields(){
        boolean state;

        if (id.equals("") || name.equals("") || age.equals("")  || classesSubjectsList.size() == 0)
            state = false;
        else state = true;

        return state;
    }

    //Todo: Display an error message on the spinners
    //sets errors on the null fields and removes them if the fields have values
    public void setError(){
        if(id.equals("")){
            idInput.setError(Constants.EMPTY_FIELD_ERROR_MESSAGE);
            idInput.setErrorEnabled(true);
        }
        else {
            idInput.setError(null);
            idInput.setErrorEnabled(false);
        }

        if(name.equals("")){
            nameInput.setError(Constants.EMPTY_FIELD_ERROR_MESSAGE);
            nameInput.setErrorEnabled(true);
        }
        else {
            nameInput.setError(null);
            nameInput.setErrorEnabled(false);
        }

        if(age.equals("")){
            ageInput.setError(Constants.EMPTY_FIELD_ERROR_MESSAGE);
            ageInput.setErrorEnabled(true);
        }
        else {
            ageInput.setError(null);
            ageInput.setErrorEnabled(false);
        }

        if (classesSubjectsList.size() == 0){
            Toast.makeText(getActivity(), "Please Enter Classes And Subjects", Toast.LENGTH_SHORT).show();
        }
    }

    // gets the values from the fields
    public void Initialization(){
        id = idInput.getEditText().getText().toString().trim();
        name = nameInput.getEditText().getText().toString().trim();
        age = ageInput.getEditText().getText().toString().trim();
    }

    // initializing  the selected classes list by getting the checked items from the sharedPreferences file and looping the map
    public void initializeClassesList(){
        classesList.clear();
        chosenClasses = classesSharedPreferencesValue();
        if(chosenClasses.size() !=0) {
            for (Map.Entry<String, ?> i : chosenClasses.entrySet()) {
                classesList.add((String) i.getValue());
            }
        }
    }

    // initializing  the selected subjects list by getting the checked items from the sharedPreferences file and looping the map
    public void initializeSubjectsList(){
        subjectsList.clear();
        chosenSubjects = subjectsSharedPreferencesValue();
        if(chosenSubjects.size() !=0) {
            for (Map.Entry<String, ?> i : chosenSubjects.entrySet()) {
                subjectsList.add((String) i.getValue());
            }
        }
    }

    //todo: get the value of the class and clear the class selection
    public void addClassAndSubjectsButtonClick(){
        initializeSubjectsList();
        initializeClassesList();
        if(subjectsList.size() !=0) {
            classesSubjects.setClassName(classesAutoCompleteTextView.getText().toString());
            classesSubjects.setSubjects(subjectsList);
            classesSubjectsList.add(classesSubjects);
            Toast.makeText(getActivity(), "تمت الاضافه بنجاح", Toast.LENGTH_SHORT).show();
            System.out.println(classesAutoCompleteTextView.getText().toString());
            System.out.println(classesSubjectsList.size());
            System.out.println("Class Name : "+classesSubjects.getClassName() + "Subject Name: "+classesSubjects.getSubjects().get(0));
        }
        clearAll();
        initializeClassesDropDownList();
        initializeSubjectsDropDownList();
    }

    //todo:fix the firebase only adding the final element in the classesSubjectsList
    public void addAllToFireBaseThread(){
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                teacher = setTeacher();
                repo.addTeacherFireBase(teacher);
                repo.addClassAndSubjectsFireBase(id , classesSubjectsList);
            }
        };
        Thread thread = new Thread(runnable);
        thread.start();
    }

    // all button clicks
    private void clicks(){

        /* On the user add click we assign the values of the classesSubjects objects and add
        them to the list to later pass them to the firebase and after adding we clear the
        spinners for more additions
         */
        addClassAndSubjects.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addClassAndSubjectsButtonClick();
            }
        });
    }
}
