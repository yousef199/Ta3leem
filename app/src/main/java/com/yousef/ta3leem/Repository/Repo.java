package com.yousef.ta3leem.Repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.yousef.ta3leem.Data.FireBase.FireBaseAdd;
import com.yousef.ta3leem.Data.FireBase.FireBaseHelper.ClassesSubjects;
import com.yousef.ta3leem.Data.FireBase.FireBaseHelper.Student;
import com.yousef.ta3leem.Data.FireBase.FireBaseHelper.Teacher;
import com.yousef.ta3leem.Data.Room.DAO.AdminDAO;
import com.yousef.ta3leem.Data.Room.DataBase.SchoolDataBase;
import com.yousef.ta3leem.Data.Room.Enitities.Admin;

import java.util.List;
import java.util.Map;

public class Repo {
    AdminDAO adminDAO;
    LiveData<List<Admin>> adminList;
    FireBaseAdd fireBaseAdd = new FireBaseAdd();
    LiveData<List<Teacher>> allTeachers;

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
}

