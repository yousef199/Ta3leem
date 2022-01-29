package com.yousef.ta3leem.ui.UI.Student.ViewModels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.yousef.ta3leem.Data.FireBase.CallBacks.teacherSubjectCallBack;
import com.yousef.ta3leem.Repository.Repo;

public class StudentViewModel extends AndroidViewModel {
    Repo repo;

    public StudentViewModel(@NonNull Application application) {
        super(application);
        repo = new Repo(application);
    }

    public void getSudentClassTeachers(String className, teacherSubjectCallBack callback){
        repo.getStudentClassTeachers(className,callback);
    }
}
