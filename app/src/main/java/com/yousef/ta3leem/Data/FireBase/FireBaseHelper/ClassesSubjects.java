package com.yousef.ta3leem.Data.FireBase.FireBaseHelper;

import java.util.List;

public class ClassesSubjects {
    String className;
    String teacherID;
    List<String> subjects;

    public String getTeacherID() {
        return teacherID;
    }

    public void setTeacherID(String teacherID) {
        this.teacherID = teacherID;
    }

    public ClassesSubjects() {
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public List<String> getSubjects() {
        return subjects;
    }

    public void setSubjects(List<String> subjects) {
        this.subjects = subjects;
    }
}
