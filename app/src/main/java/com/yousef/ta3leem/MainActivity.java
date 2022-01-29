package com.yousef.ta3leem;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.yousef.ta3leem.Data.FireBase.CallBacks.allStudentsFireBaseCallBack;
import com.yousef.ta3leem.Data.FireBase.CallBacks.allTeachersFirebaseCallBack;
import com.yousef.ta3leem.Data.FireBase.FireBaseHelper.Student;
import com.yousef.ta3leem.Data.FireBase.FireBaseHelper.Teacher;
import com.yousef.ta3leem.Repository.Repo;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    Repo repo = new Repo();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_app);
        getAllStudents();
        getAllTeachers();
    }

    /**
     * Cache the firebase on the client app for faster data upload
     */
    public void getAllStudents(){
        repo.getStudentsOneTime(new allStudentsFireBaseCallBack() {
            @Override
            public void onCallBack(List<Student> allStudents) {

            }
        });
    }

    public void getAllTeachers(){
        repo.getTeachersOneTime(new allTeachersFirebaseCallBack() {
            @Override
            public void allTeachersOnCallBack(List<Teacher> allTeachers) {

            }
        });
    }
}