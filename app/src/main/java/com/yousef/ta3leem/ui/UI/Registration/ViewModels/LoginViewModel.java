package com.yousef.ta3leem.ui.UI.Registration.ViewModels;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.yousef.ta3leem.Constants;
import com.yousef.ta3leem.Data.FireBase.CallBacks.allTeachersFirebaseCallBack;
import com.yousef.ta3leem.Data.FireBase.CallBacks.studentFireBaseCallBack;
import com.yousef.ta3leem.Data.FireBase.CallBacks.teacherFireBaseCallBack;
import com.yousef.ta3leem.Data.FireBase.FireBaseGet;
import com.yousef.ta3leem.Data.FireBase.FireBaseHelper.Student;
import com.yousef.ta3leem.Data.FireBase.FireBaseHelper.Teacher;
import com.yousef.ta3leem.Data.Room.Enitities.Admin;
import com.yousef.ta3leem.R;
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
        LoginFragment.navigation nav = new LoginFragment.navigation();
        get.getTeacher(id, new teacherFireBaseCallBack() {
            String image;
            @Override
            public void teacherOnCallBack(Teacher teacher) {
                if(get.isExits()){
                    if(teacher.getPassword() == "not set"){
                        Toast.makeText(getApplication(), Constants.AUTHENTICATE_USER, Toast.LENGTH_SHORT).show();
                    }
                    else if (password.equals(teacher.getPassword())){
                        if (teacher.getImage() ==null){
                            image = Integer.toString(R.drawable.user_icon);
                        }
                        else
                            image = teacher.getImage();
                        nav.navigateToTeacher(view , teacher.getName(), teacher.getId() , image);
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
        LoginFragment.navigation nav = new LoginFragment.navigation();

        get.getStudent(id, new studentFireBaseCallBack() {
            String image;
            @Override
            public void onCallback(Student student) {
                if(get.isExits()){
                    if(student.getPassword() == "not set"){
                        Toast.makeText(getApplication(), Constants.AUTHENTICATE_USER, Toast.LENGTH_SHORT).show();
                    }
                    else if (password.equals(student.getPassword())){
                        // Check if there is an image if not pass the default user image
                        if (student.getImage() == null){
                            image = Integer.toString(R.drawable.user_icon);
                        }
                        else
                            image = student.getImage();
                        nav.navigateToStudent(view , student.getName(), student.getId() , image , student.getClassName());
                    }
                    else
                        Toast.makeText(getApplication(), Constants.WRONG_USERNAME_PASSWORD, Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(getApplication(), Constants.WRONG_USERNAME_PASSWORD, Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void clearSharedPrefs(String destination){
        SharedPreferences sharedPreferences;
        SharedPreferences.Editor editor;
        switch (destination){
            case "admin":
                sharedPreferences = getApplication().getSharedPreferences(Constants.ADMIN_NAME_PREF , Context.MODE_PRIVATE);
                editor = sharedPreferences.edit();
                editor.clear();
                editor.apply();
                break;
            case "teacher":
                sharedPreferences = getApplication().getSharedPreferences(Constants.TEACHER_SHARED_PREF , Context.MODE_PRIVATE);
                editor = sharedPreferences.edit();
                editor.clear();
                editor.apply();
                break;
            case "student":
                sharedPreferences = getApplication().getSharedPreferences(Constants.STUDENT_SHARED_PREF , Context.MODE_PRIVATE);
                editor = sharedPreferences.edit();
                editor.clear();
                editor.apply();
                break;
        }
        sharedPreferences = getApplication().getSharedPreferences(Constants.IMAGE_SHARED_PREF , Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();
    }

    public void getAllTeachers (allTeachersFirebaseCallBack callBack){
        repo.getTeachersOneTime(callBack);
    }
}
