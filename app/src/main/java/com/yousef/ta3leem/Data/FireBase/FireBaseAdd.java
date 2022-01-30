package com.yousef.ta3leem.Data.FireBase;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.yousef.ta3leem.Data.FireBase.FireBaseHelper.ClassesSubjects;
import com.yousef.ta3leem.Data.FireBase.FireBaseHelper.Student;
import com.yousef.ta3leem.Data.FireBase.FireBaseHelper.Teacher;
import com.yousef.ta3leem.R;
import com.yousef.ta3leem.Constants;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FireBaseAdd {
    FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    DatabaseReference dataBase;

    public void addTeacher(Teacher teacher) {
        dataBase = firebaseDatabase.getInstance().getReference(Constants.TEACHER_FIREBASE_NAME).child(teacher.getId());
        dataBase.setValue(teacher);
    }

    public void addStudent(Student student) {
        dataBase = firebaseDatabase.getInstance().getReference(Constants.STUDENT_FIREBASE_NAME).child(student.getId());
        dataBase.setValue(student);
    }


    public void addClassesToTeacher(String id, ClassesSubjects classes) {
            dataBase = FirebaseDatabase.getInstance().getReference(Constants.TEACHER_FIREBASE_NAME).child(id).child("classes").child(classes.getClassName());
            dataBase.setValue(classes.getSubjects());
    }

    public void addNewClass(String className, String Name , List<String> Subjects) {
        HashMap<String, String> classNameObject = new HashMap<>();
        classNameObject.put("Class Name" , Name);
        dataBase = firebaseDatabase.getInstance().getReference(Constants.CLASSES_FIREBASE_NAME).child(className).child(Name);
        dataBase.setValue(classNameObject);
        dataBase.setValue(Subjects);
    }


    public void setTeacherPassword(String id , String Password){
        dataBase = FirebaseDatabase.getInstance().getReference(Constants.TEACHER_FIREBASE_NAME).child(id).child("password");
        dataBase.setValue(Password);
    }

    public void setStudentPassword(String id , String Password){
        dataBase = FirebaseDatabase.getInstance().getReference(Constants.STUDENT_FIREBASE_NAME).child(id).child("password");
        dataBase.setValue(Password);
    }
}
