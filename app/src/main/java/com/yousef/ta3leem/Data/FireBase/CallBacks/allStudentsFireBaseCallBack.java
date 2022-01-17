package com.yousef.ta3leem.Data.FireBase.CallBacks;

import com.yousef.ta3leem.Data.FireBase.FireBaseHelper.Student;

import java.util.List;

public interface allStudentsFireBaseCallBack {
    void onCallBack(List<Student> allStudents);
}
