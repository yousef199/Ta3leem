package com.yousef.ta3leem.Data.FireBase.FireBaseHelper;

public class Student {
    String  name , id , age , password = "not set";

    public Student() {
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

    public Student( String name, String id, String age) {
        this.name = name;
        this.id = id;
        this.age = age;
    }
}