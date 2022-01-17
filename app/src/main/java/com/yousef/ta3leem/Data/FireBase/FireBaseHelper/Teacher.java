package com.yousef.ta3leem.Data.FireBase.FireBaseHelper;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

public class Teacher implements Cloneable{
    String name , id , age , password = "not set";
    List <String> subjects= new ArrayList<>();
    List <String> classes = new ArrayList<>();

    public Teacher() {
    }

    public Teacher(Teacher teacher){
        this.name = teacher.name;
        this.age = teacher.age;
        this.id = teacher.id;
        this.subjects = teacher.subjects;
        this.classes = teacher.classes;
        this.password = teacher.password;
    }

    public Teacher(String name, String id, String age , List<String> subjects , List<String> classes ) {
        this.name = name;
        this.id = id;
        this.age = age;
        this.subjects = subjects;
        this.classes = classes;
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

    public List<String> getSubjects() {
        return subjects;
    }

    public void setSubjects(List<String> subjects) {
        this.subjects = subjects;
    }

    public List<String> getClasses() {
        return classes;
    }

    public void setClasses(List<String> classes) {
        this.classes = classes;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
