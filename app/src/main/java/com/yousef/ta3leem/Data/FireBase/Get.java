package com.yousef.ta3leem.Data.FireBase;

import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.yousef.ta3leem.Constants;
import com.yousef.ta3leem.Data.FireBase.CallBacks.allTeachersFirebaseCallBack;
import com.yousef.ta3leem.Data.FireBase.CallBacks.teacherFireBaseCallBack;
import com.yousef.ta3leem.Data.FireBase.FireBaseHelper.Student;
import com.yousef.ta3leem.Data.FireBase.FireBaseHelper.Teacher;
import com.yousef.ta3leem.Data.FireBase.CallBacks.*;

import java.util.ArrayList;
import java.util.List;

public class Get {
    private boolean exits;
    FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    DatabaseReference dataBase;

    public void getTeachers(allTeachersFirebaseCallBack callBack){
        dataBase = firebaseDatabase.getReference().child(Constants.TEACHER_FIREBASE_NAME);
        List<Teacher> allTeachers = new ArrayList<>();
        dataBase.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()) {
                    exits = true;
                    Iterable<DataSnapshot> children = snapshot.getChildren();
                    for (DataSnapshot dataSnapshot : children) {
                        allTeachers.add(dataSnapshot.getValue(Teacher.class));
                    }
                }
                else exits = false;

                callBack.allTeachersOnCallBack(allTeachers);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public void getTeacher(String id , teacherFireBaseCallBack callBack){
        dataBase = firebaseDatabase.getReference().child(Constants.TEACHER_FIREBASE_NAME);
        Query getTeacher = dataBase.orderByChild("id").equalTo(id);

        getTeacher.addListenerForSingleValueEvent(new ValueEventListener() {
            Teacher teacher = new Teacher();
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()) {
                    teacher = snapshot.child(id).getValue(Teacher.class);
                    exits = true;
                }
                else
                    exits = false;

                callBack.teacherOnCallBack(teacher);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public void getStudents(allStudentsFireBaseCallBack callBack){
        dataBase = firebaseDatabase.getReference().child(Constants.STUDENT_FIREBASE_NAME);
        List<Student> allStudents = new ArrayList<>();
        dataBase.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()) {
                    exits = true;
                    Iterable<DataSnapshot> children = snapshot.getChildren();
                    for (DataSnapshot s : children) {
                        allStudents.add(s.getValue(Student.class));
                    }
                }
                else
                    exits = false;

                callBack.onCallBack(allStudents);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public void getStudent(String id , studentFireBaseCallBack callBack){
        dataBase = FirebaseDatabase.getInstance().getReference(Constants.STUDENT_FIREBASE_NAME);
        Query getStudent = dataBase.orderByChild("id").equalTo(id);
        getStudent.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Student student = new Student();
                if(snapshot.exists()){
                    exits = true;
                    student = snapshot.child(id).getValue(Student.class);
                }

                else
                    exits = false;
                callBack.onCallback(student);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    public boolean isExits() {
        return exits;
    }
}
