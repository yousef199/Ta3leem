package com.yousef.ta3leem.ui.UI.Registration.ViewModels;

import android.app.Application;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.yousef.ta3leem.Constants;
import com.yousef.ta3leem.Data.FireBase.CallBacks.studentFireBaseCallBack;
import com.yousef.ta3leem.Data.FireBase.CallBacks.teacherFireBaseCallBack;
import com.yousef.ta3leem.Data.FireBase.FireBaseGet;
import com.yousef.ta3leem.Data.FireBase.FireBaseHelper.Student;
import com.yousef.ta3leem.Data.FireBase.FireBaseHelper.Teacher;
import com.yousef.ta3leem.Data.Room.Enitities.Admin;
import com.yousef.ta3leem.Helper.navigation;
import com.yousef.ta3leem.Repository.Repo;
import com.yousef.ta3leem.ui.UI.Registration.Fragments.LoginFragment;

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

    public void checkTeacher (String id , String password , View view){
        FireBaseGet get = new FireBaseGet();
        navigation nav = new navigation();
        get.getTeacher(id, new teacherFireBaseCallBack() {
            @Override
            public void teacherOnCallBack(Teacher teacher) {
                if(get.isExits()){
                    if(teacher.getPassword() == "not set"){
                        Toast.makeText(getApplication(), Constants.AUTHENTICATE_USER, Toast.LENGTH_SHORT).show();
                    }
                    else if (password.equals(teacher.getPassword())){
                        nav.navigateToStudent(view);
                    }

                    else
                        Toast.makeText(getApplication(), Constants.WRONG_USERNAME_PASSWORD, Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(getApplication(), Constants.WRONG_USERNAME_PASSWORD, Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
    public void checkStudent (String id , String password , View view){
        FireBaseGet get = new FireBaseGet();
        navigation nav = new navigation();

        get.getStudent(id, new studentFireBaseCallBack() {
            @Override
            public void onCallback(Student student) {
                if(get.isExits()){
                    if(student.getPassword() == "not set"){
                        Toast.makeText(getApplication(), Constants.AUTHENTICATE_USER, Toast.LENGTH_SHORT).show();
                    }
                    else if (password.equals(student.getPassword())){
                        nav.navigateToStudent(view);
                    }
                    else
                        Toast.makeText(getApplication(), Constants.WRONG_USERNAME_PASSWORD, Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(getApplication(), Constants.WRONG_USERNAME_PASSWORD, Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

}
