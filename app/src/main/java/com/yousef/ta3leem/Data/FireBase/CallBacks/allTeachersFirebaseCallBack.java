package com.yousef.ta3leem.Data.FireBase.CallBacks;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.yousef.ta3leem.Data.FireBase.FireBaseHelper.Teacher;

import java.lang.invoke.MutableCallSite;
import java.util.List;

public interface allTeachersFirebaseCallBack {
    void allTeachersOnCallBack(List<Teacher> allTeachers);
}
