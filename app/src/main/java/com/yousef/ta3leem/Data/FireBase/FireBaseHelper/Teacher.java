package com.yousef.ta3leem.Data.FireBase.FireBaseHelper;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

public class Teacher implements Cloneable{
    String name , id , age , password = "not set";

    public Teacher() {
    }

    public Teacher(Teacher teacher){
        this.name = teacher.name;
        this.age = teacher.age;
        this.id = teacher.id;
        this.password = teacher.password;
    }

    public Teacher(String name, String id, String age , List<ClassesSubjects> classesSubjectsList) {
        this.name = name;
        this.id = id;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
