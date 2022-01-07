package com.yousef.ta3leem.ui.ui.Registration.ViewModels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.yousef.ta3leem.Data.Room.Enitities.Admin;
import com.yousef.ta3leem.Repository.Repo;

import java.util.List;

public class LoginViewModel extends AndroidViewModel {
    Repo repo;
    LiveData<List<Admin>> AdminList;

    public LoginViewModel(@NonNull Application application) {
        super(application);
        repo = new Repo(application);
        AdminList = repo.getAllAdmins();
    }


    public LiveData<List<Admin>> getAllAdmins(){
        return AdminList;
    }
}
