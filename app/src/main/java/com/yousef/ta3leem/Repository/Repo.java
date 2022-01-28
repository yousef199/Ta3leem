package com.yousef.ta3leem.Repository;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.yousef.ta3leem.Constants;
import com.yousef.ta3leem.Data.FireBase.CallBacks.allTeachersFirebaseCallBack;
import com.yousef.ta3leem.Data.FireBase.FireBaseAdd;
import com.yousef.ta3leem.Data.FireBase.FireBaseGet;
import com.yousef.ta3leem.Data.FireBase.FireBaseHelper.ClassesSubjects;
import com.yousef.ta3leem.Data.FireBase.FireBaseHelper.Student;
import com.yousef.ta3leem.Data.FireBase.FireBaseHelper.Teacher;
import com.yousef.ta3leem.Data.Room.DAO.AdminDAO;
import com.yousef.ta3leem.Data.Room.DataBase.SchoolDataBase;
import com.yousef.ta3leem.Data.Room.Enitities.Admin;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CountDownLatch;

public class Repo {
    AdminDAO adminDAO;
    LiveData<List<Admin>> adminList;
    FireBaseAdd fireBaseAdd = new FireBaseAdd();
    FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    DatabaseReference dataBase;

    public Repo(Application application){
        SchoolDataBase dataBase = SchoolDataBase.getInstance(application);
        adminDAO = dataBase.adminDAO();
        adminList = adminDAO.getAdmins();
    }

    public Repo() {
    }

    public LiveData<List<Admin>> getAllAdmins(){
        return adminList;
    }

    public void addClassAndSubjectsFireBase(String id , ClassesSubjects classesSubjects){
        fireBaseAdd.addClassesToTeacher(id , classesSubjects);
    }

    public void addTeacherFireBase(Teacher teacher){
        fireBaseAdd.addTeacher(teacher);
    }

    public void addClass(String classname, String Name , List<String> Subjects){
        fireBaseAdd.addNewClass(classname , Name, Subjects);
    }

    public void addStudentFireBase(Student student){
        fireBaseAdd.addStudent(student);
    }

    public MutableLiveData<List<Teacher>> getTeachersFireBase(){
        dataBase = firebaseDatabase.getReference().child(Constants.TEACHER_FIREBASE_NAME);
        MutableLiveData<List<Teacher>> mutableLiveData = new MutableLiveData<>();
        dataBase.addValueEventListener(new ValueEventListener() {
            List<Teacher> allTeachers = new ArrayList<>();
            Set<Teacher> checkTeachers = new HashSet<>();
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()) {
                    Iterable<DataSnapshot> children = snapshot.getChildren();
                    for (DataSnapshot dataSnapshot : children) {
                        if(!(checkTeachers.contains(dataSnapshot.getValue()))){
                            checkTeachers.add(dataSnapshot.getValue(Teacher.class));
                            allTeachers.add(dataSnapshot.getValue(Teacher.class));
                        }
                    }
                }
                mutableLiveData.setValue(allTeachers);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        return mutableLiveData;
    }

    public MutableLiveData<List<Student>> getStudentsFireBase(){
        dataBase = firebaseDatabase.getReference().child(Constants.STUDENT_FIREBASE_NAME);
        MutableLiveData<List<Student>> mutableLiveData = new MutableLiveData<>();
        dataBase.addValueEventListener(new ValueEventListener() {
            List<Student> allStudents = new ArrayList<>();
            Set<Student> checkStudents = new HashSet<>();
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()) {
                    Iterable<DataSnapshot> children = snapshot.getChildren();
                    for (DataSnapshot dataSnapshot : children) {
                        if(!(checkStudents.contains(dataSnapshot.getValue()))){
                            checkStudents.add(dataSnapshot.getValue(Student.class));
                            allStudents.add(dataSnapshot.getValue(Student.class));
                        }
                    }
                }
                mutableLiveData.setValue(allStudents);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        return mutableLiveData;
    }
}

