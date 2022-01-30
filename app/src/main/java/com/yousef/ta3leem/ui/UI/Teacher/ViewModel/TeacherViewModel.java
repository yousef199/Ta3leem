package com.yousef.ta3leem.ui.UI.Teacher.ViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.yousef.ta3leem.Repository.Repo;

import java.util.List;
import java.util.Map;

public class TeacherViewModel extends AndroidViewModel {
    Repo repo;

    public TeacherViewModel(@NonNull Application application) {
        super(application);
        repo = new Repo(application);
    }

    public MutableLiveData<Map<String , List<String>>> observeTeacherClassesInfo(String id){
        return repo.getTeacherClassesInfo(id);
    }
}
