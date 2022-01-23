package com.yousef.ta3leem.Data.FireBase;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.yousef.ta3leem.Data.FireBase.FireBaseHelper.ClassesSubjects;
import com.yousef.ta3leem.Data.FireBase.FireBaseHelper.Student;
import com.yousef.ta3leem.Data.FireBase.FireBaseHelper.Teacher;
import com.yousef.ta3leem.R;
import com.yousef.ta3leem.Constants;

import java.util.List;

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


    public void addClassesToTeacher(String id, List<ClassesSubjects> classes) {
        for (ClassesSubjects s : classes){
            dataBase = FirebaseDatabase.getInstance().getReference(Constants.TEACHER_FIREBASE_NAME).child(id).child("classes").child(s.getClassName());
            dataBase.setValue(s);
        }
    }

    public void addNewClass(ClassesSubjects classes) {
        dataBase = firebaseDatabase.getInstance().getReference(Constants.CLASSES_FIREBASE_NAME).child(classes.getClassName());
        dataBase.setValue(classes.getSubjects());
    }
}
