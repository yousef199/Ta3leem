package com.yousef.ta3leem.Data.FireBase.FireBaseHelper;

import java.util.List;
import java.util.Map;

public class TeacherSubject {
    Map<String , List<String>> teacherSubjects;

    public Map<String, List<String>> getTeacherSubjects() {
        return teacherSubjects;
    }

    public void setTeacherSubjects(Map<String, List<String>> teacherSubjects) {
        this.teacherSubjects = teacherSubjects;
    }

    public TeacherSubject() {
    }

    public TeacherSubject(Map<String, List<String>> teacherSubjects) {
        this.teacherSubjects = teacherSubjects;
    }
}
