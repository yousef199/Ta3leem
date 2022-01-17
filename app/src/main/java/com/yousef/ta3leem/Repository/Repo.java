package com.yousef.ta3leem.Repository;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.yousef.ta3leem.Data.FireBase.CallBacks.allTeachersFirebaseCallBack;
import com.yousef.ta3leem.Data.FireBase.FireBaseHelper.Teacher;
import com.yousef.ta3leem.Data.FireBase.Get;
import com.yousef.ta3leem.Data.Room.DAO.AdminDAO;
import com.yousef.ta3leem.Data.Room.DataBase.SchoolDataBase;
import com.yousef.ta3leem.Data.Room.Enitities.Admin;

import java.util.List;

public class Repo {
    AdminDAO adminDAO;
    LiveData<List<Admin>> adminList;
    LiveData<List<Teacher>> allTeachers;

    public Repo(Application application){
        SchoolDataBase dataBase = SchoolDataBase.getInstance(application);
        adminDAO = dataBase.adminDAO();
        adminList = adminDAO.getAdmins();
    }

    public LiveData<List<Admin>> getAllAdmins(){
        return adminList;
    }

}

