package com.yousef.ta3leem.ui.UI.Student.ViewModels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.yousef.ta3leem.Data.FireBase.CallBacks.teacherSubjectCallBack;
import com.yousef.ta3leem.Repository.Repo;

import java.util.List;
import java.util.Map;

public class StudentViewModel extends AndroidViewModel {
    Repo repo;

    public StudentViewModel(@NonNull Application application) {
        super(application);
        repo = new Repo(application);
    }

    public void getSudentClassTeachers(String className, teacherSubjectCallBack callback){
        repo.getStudentClassTeachers(className,callback);
    }

    public MutableLiveData<Map<String , List<String>>> observeStudentClassTeachers(String className){
        return repo.observeStudentClassTeacher(className);
    }
}
