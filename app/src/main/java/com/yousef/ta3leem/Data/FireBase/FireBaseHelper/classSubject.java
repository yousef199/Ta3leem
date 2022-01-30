package com.yousef.ta3leem.Data.FireBase.FireBaseHelper;

import java.util.List;
import java.util.Map;

public class classSubject {
    Map<String , List<String>> classSubjects;

    public classSubject() {
    }

    public Map<String, List<String>> getClassSubjects() {
        return classSubjects;
    }

    public void setClassSubjects(Map<String, List<String>> classSubjects) {
        this.classSubjects = classSubjects;
    }

    public classSubject(Map<String, List<String>> classSubjects) {
        this.classSubjects = classSubjects;
    }
}
