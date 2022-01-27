package com.yousef.ta3leem.Data.FireBase.FireBaseHelper;

import androidx.annotation.NonNull;

public class Student {
    String  name, className, imageURL , id , age , password = "not set";

    public Student() {
    }

    public String getImage() {
        return imageURL;
    }

    public void setImage(String imageURL) {
        this.imageURL = imageURL;
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

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public Student(String name, String id, String age) {
        this.name = name;
        this.id = id;
        this.age = age;
    }
}
