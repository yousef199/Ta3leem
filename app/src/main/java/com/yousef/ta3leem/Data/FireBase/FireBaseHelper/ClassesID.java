package com.yousef.ta3leem.Data.FireBase.FireBaseHelper;

import java.util.List;

public class ClassesID {
    String id;
    List<String> Classes;

    public ClassesID() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<String> getClasses() {
        return Classes;
    }

    public void setClasses(List<String> classes) {
        Classes = classes;
    }

    public ClassesID(String id, List<String> classes) {
        this.id = id;
        Classes = classes;
    }
}
