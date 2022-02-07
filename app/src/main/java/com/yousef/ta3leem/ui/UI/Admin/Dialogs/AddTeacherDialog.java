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
import com.yousef.ta3leem.Adapters.SubjectsDropDownListAdapter;
import com.yousef.ta3leem.Constants;
import com.yousef.ta3leem.Data.FireBase.FireBaseHelper.ClassesSubjects;
import com.yousef.ta3leem.Data.FireBase.FireBaseHelper.Teacher;
import com.yousef.ta3leem.Helper.DropDownListObject;
import com.yousef.ta3leem.R;
import com.yousef.ta3leem.Repository.Repo;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class AddTeacherDialog extends AppCompatDialogFragment {
    TextInputLayout idInput, nameInput, ageInput , classInput , subjectsInput , imageTextInput;
    Button addClassAndSubjects;
    AutoCompleteTextView classesAutoCompleteTextView ,subjectsAutoCompleteTextView ;
    Teacher teacher;
    String id , name , age , className ,imageURL, subjects;
    Map <String , ?> chosenClasses , chosenSubjects;
    List<String> subjectsList;
    List<String> classesList = new ArrayList<>();
    List<ClassesSubjects> classesSubjectsList = new ArrayList<>();
    ClassesSubjects classesSubjects;
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
        classInput = view.findViewById(R.id.teacherClassesTextInput);
        subjectsInput = view.findViewById(R.id.teacherSubjectTextInput);
        addClassAndSubjects = view.findViewById(R.id.addClassAndSubjects);
        classesAutoCompleteTextView = view.findViewById(R.id.classesAutoCompleteText);
        subjectsAutoCompleteTextView = view.findViewById(R.id.subjectsAutoCompleteText);
        imageTextInput = view.findViewById(R.id.addTeacherDialog_ImgUrl);


        //Initializations
        populateSubjectsDropDownList();
        populateClassesDropDownList();


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

                @Override
                public void onClick(View view) {
                    Initialization();
                    initializeSubjectsList();
                    setError();
                    if (checkFields()){
                        addAllToFireBaseThread();
                        Toast.makeText(getActivity(), "تم اضافة المدرس بنجاح"  , Toast.LENGTH_SHORT).show();
                        alertDialog.dismiss();
                    }
                }

            });
        }
    }

    //All Methods

    // Assign the options from the list and assign the adapter
    private void populateSubjectsDropDownList(){
        ArrayList<DropDownListObject> listVOs = new ArrayList<>();
        for (int i = 0; i < Constants.SUBJECTS.length; i++) {
            DropDownListObject dropDownListObject = new DropDownListObject();
            dropDownListObject.setTitle(Constants.SUBJECTS[i]);
            dropDownListObject.setSelected(false);
            listVOs.add(dropDownListObject);
        }
        SubjectsDropDownListAdapter subjectsDropDownListAdapter = new SubjectsDropDownListAdapter(getActivity(), 0,
                listVOs);
        subjectsAutoCompleteTextView.setAdapter(subjectsDropDownListAdapter);
    }

    // Assign the options from the list and assign the adapter
    private void populateClassesDropDownList(){
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
        SubjectsDropDownListAdapter subjectsDropDownListAdapter = (SubjectsDropDownListAdapter) subjectsAutoCompleteTextView.getAdapter();
        subjectsDropDownListAdapter.clear();
        subjectsDropDownListAdapter.notifyDataSetChanged();
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
        teacher.setImage(imageURL);
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

        if(className.equals("الصف") || className.equals("") ||classesSubjectsList.size() == 0 ){
            classInput.setError(Constants.CHOOSE_ONE_ERROR_MESSAGE);
            classInput.setErrorEnabled(true);
        }
        else {
            classInput.setError(null);
            classInput.setErrorEnabled(false);
        }

        if(subjectsList.size() == 0 || classesSubjectsList.size() == 0){
            subjectsInput.setError(Constants.CHOOSE_ONE_ERROR_MESSAGE);
            subjectsInput.setErrorEnabled(true);
        }
        else {
            subjectsInput.setError(null);
            subjectsInput.setErrorEnabled(false);
        }


    }

    // gets the values from the fields
    public void Initialization(){
        id = idInput.getEditText().getText().toString().trim();
        name = nameInput.getEditText().getText().toString().trim();
        age = ageInput.getEditText().getText().toString().trim();
        className = classesAutoCompleteTextView.getText().toString().trim();
        imageURL = imageTextInput.getEditText().getText().toString();
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
        subjectsList = new ArrayList<>();
        chosenSubjects = subjectsSharedPreferencesValue();
        if(chosenSubjects.size() !=0) {
            for (Map.Entry<String, ?> i : chosenSubjects.entrySet()) {
                subjectsList.add((String) i.getValue());
            }
        }
    }

    public void addClassAndSubjectsButtonClick(){
        classesSubjects = new ClassesSubjects();
        initializeSubjectsList();
        if(subjectsList.size() !=0) {
            classesSubjects.setClassName(classesAutoCompleteTextView.getText().toString());
            classesSubjects.setSubjects(subjectsList);
            classesSubjectsList.add(classesSubjects);
            Toast.makeText(getActivity(), "تمت الاضافه بنجاح", Toast.LENGTH_SHORT).show();
        }
        clearAll();
        populateClassesDropDownList();
        populateSubjectsDropDownList();
    }

    public void addAllToFireBaseThread(){
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                teacher = setTeacher();
                repo.addTeacherFireBase(teacher);
                for (ClassesSubjects s : classesSubjectsList){
                    repo.addClassAndSubjectsFireBase(id , s);
                    repo.addClass(s.getClassName() , id,name , s.getSubjects());
                }
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
