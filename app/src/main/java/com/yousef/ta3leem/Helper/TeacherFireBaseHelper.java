package com.yousef.ta3leem.Helper;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TeacherFireBaseHelper {
    String id , name , subjectsTaught , password;

    public TeacherFireBaseHelper(String id, String name, String subjectsTaught, String password) {
        this.id = id;
        this.name = name;
        this.subjectsTaught = subjectsTaught;
        this.password = password;
    }

    public TeacherFireBaseHelper(String id, String password) {
        this.id = id;
        this.password = password;
    }

    public TeacherFireBaseHelper() {
    }
}
