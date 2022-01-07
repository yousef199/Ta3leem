package com.yousef.ta3leem.Data.Room.Enitities;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;


@Entity(tableName = "Admin")
public class Admin implements Cloneable {

    @PrimaryKey
    @NonNull
    public String id;
    public String name , password;


    public Admin(String id, String name , String password) {
        this.id = id;
        this.name = name;
        this.password = password;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public Object clone() throws CloneNotSupportedException{
    return super.clone();
    }
}
