package com.yousef.ta3leem.Data.FireBase.FireBaseHelper;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Teacher implements Cloneable{
    String name , id , age, image , password = "not set";
    HashMap<String, ClassesSubjects> classes;

    public Teacher() {
    }

    public Teacher(Teacher teacher){
        this.name = teacher.name;
        this.age = teacher.age;
        this.id = teacher.id;
        this.password = teacher.password;
        this.classes = teacher.classes;
    }


    public HashMap<String, ClassesSubjects> getClasses() {
        return classes;
    }

    public void setClasses(HashMap<String, ClassesSubjects> classes) {
        this.classes = classes;
    }

    public Teacher(String name, String id, String age , String image,List<ClassesSubjects> classesSubjectsList) {
        this.name = name;
        this.id = id;
        this.age = age;
        this.image = image;
    }

    public Teacher(String name, String image) {
        this.name = name;
        this.image = image;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
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
