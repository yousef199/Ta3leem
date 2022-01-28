package com.yousef.ta3leem.ui.UI.Admin.ViewModels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.yousef.ta3leem.Data.FireBase.FireBaseHelper.Teacher;
import com.yousef.ta3leem.Repository.Repo;

import java.util.List;

public class AdminTeacherViewModel extends AndroidViewModel {
    Repo repo ;

    public AdminTeacherViewModel(@NonNull Application application) {
        super(application);
        repo = new Repo(application);
    }

    public MutableLiveData<List<Teacher>> getTeachersList(){
        return repo.getTeachersFireBase();
    }
}
