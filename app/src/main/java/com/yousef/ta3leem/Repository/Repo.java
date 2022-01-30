package com.yousef.ta3leem.Repository;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.yousef.ta3leem.Constants;
import com.yousef.ta3leem.Data.FireBase.CallBacks.allTeachersFirebaseCallBack;
import com.yousef.ta3leem.Data.FireBase.FireBaseAdd;
import com.yousef.ta3leem.Data.FireBase.FireBaseHelper.ClassesSubjects;
import com.yousef.ta3leem.Data.FireBase.FireBaseHelper.Student;
import com.yousef.ta3leem.Data.FireBase.FireBaseHelper.Teacher;
import com.yousef.ta3leem.Data.FireBase.FireBaseHelper.TeacherSubject;
import com.yousef.ta3leem.Data.Room.DAO.AdminDAO;
import com.yousef.ta3leem.Data.Room.DataBase.SchoolDataBase;
import com.yousef.ta3leem.Data.Room.Enitities.Admin;
import com.yousef.ta3leem.Data.FireBase.CallBacks.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

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

    public void getTeachersOneTime(allTeachersFirebaseCallBack callBack){
        dataBase = firebaseDatabase.getReference().child(Constants.TEACHER_FIREBASE_NAME);
        List<Teacher> teacherList = new ArrayList<>();

        dataBase.get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if(task.isSuccessful()){
                    task.getResult();
                    for (DataSnapshot snapshot : task.getResult().getChildren()){
                        teacherList.add(snapshot.getValue(Teacher.class));
                    }
                }
                callBack.allTeachersOnCallBack(teacherList);
            }
        });
    }

    public void getStudentsOneTime(allStudentsFireBaseCallBack callBack){
        dataBase = firebaseDatabase.getReference().child(Constants.STUDENT_FIREBASE_NAME);
        List<Student> studentsList = new ArrayList<>();
        dataBase.get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if(task.isSuccessful()){
                    task.getResult();
                    for (DataSnapshot snapshot : task.getResult().getChildren()){
                        studentsList.add(snapshot.getValue(Student.class));
                    }
                }
             callBack.onCallBack(studentsList);
            }
        });
    }

    public MutableLiveData<Map<String , List<String>>> observeStudentSubjectPageInfo(String className){
        dataBase = firebaseDatabase.getReference().child(Constants.CLASSES_FIREBASE_NAME);
        Query query = dataBase.orderByKey().equalTo(className);
        Map<String , List<String>> teachers = new HashMap<>();
        MutableLiveData<Map<String , List<String>>> m = new MutableLiveData<>();
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()){
                    for(DataSnapshot s : snapshot.getChildren() ){
                        for(DataSnapshot snapshot1 : s.getChildren()){
                            List<String> subjects = new ArrayList<>();
                            for(int i = 0 ; i< snapshot1.getChildrenCount() ; i++){
                                subjects.add(snapshot1.child(String.valueOf(i)).getValue(String.class));
                            }
                            teachers.put(snapshot1.getKey() , subjects);
                        }
                    }
                }
                m.setValue(teachers);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        return m;
    }

    public void getStudentSubjectPageInfo(String className , teacherSubjectCallBack callBack){
        dataBase = firebaseDatabase.getReference().child(Constants.CLASSES_FIREBASE_NAME);
        Query query = dataBase.orderByKey().equalTo(className);
        query.get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            TeacherSubject teacherSubject = new TeacherSubject();
            Map<String , List<String>> teachers = new HashMap<>();
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if (task.isSuccessful()) {
                    for(DataSnapshot snapshot : task.getResult().getChildren() ){
                        for(DataSnapshot snapshot1 : snapshot.getChildren()){
                            List<String> subjects = new ArrayList<>();
                            for(int i = 0 ; i< snapshot1.getChildrenCount() ; i++){
                              subjects.add(snapshot1.child(String.valueOf(i)).getValue(String.class));
                            }
                            teachers.put(snapshot1.getKey() , subjects);
                        }
                    }
                    teacherSubject.setTeacherSubjects(teachers);
                }
                callBack.onCallBack(teacherSubject);
            }
        });

    }

    public MutableLiveData<Map<String, List<String>>> getTeacherClassesInfo(String id){
        dataBase = firebaseDatabase.getReference().child(Constants.TEACHER_FIREBASE_NAME).child(id).child("classes");
        MutableLiveData<Map<String, List<String>>> liveData = new MutableLiveData<>();
        Map<String , List<String>> classSubjectMap = new HashMap<>();

        dataBase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    for(DataSnapshot s1 : snapshot.getChildren()){
                        List<String> subjectList = new ArrayList<>();
                            for(int i = 0 ; i< s1.getChildrenCount() ; i++) {
                                subjectList.add(s1.child(String.valueOf(i)).getValue(String.class));
                            }
                            classSubjectMap.put(s1.getKey() , subjectList);
                            System.out.println("Class Name: " + s1.getKey() + "Subject: " + subjectList.get(0));
                    }
                }
                liveData.setValue(classSubjectMap);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        return liveData;
    }
}

