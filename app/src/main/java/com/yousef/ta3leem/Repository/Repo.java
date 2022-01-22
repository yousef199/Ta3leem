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

    public void addClassAndSubjectsFireBase(String id , List<ClassesSubjects> classesSubjects){
        fireBaseAdd.addClassesToTeacher(id , classesSubjects);
    }

    public void addTeacherFireBase(Teacher teacher){
        fireBaseAdd.addTeacher(teacher);
    }

    public void addClass(ClassesSubjects classesSubjects){
        fireBaseAdd.addNewClass(classesSubjects);
    }

    public void addStudentFireBase(Student student){
        fireBaseAdd.addStudent(student);
    }
}

