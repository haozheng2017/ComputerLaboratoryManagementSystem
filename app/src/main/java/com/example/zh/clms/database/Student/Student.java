package com.example.zh.clms.database.Student;

public class Student {
    private String userName;
    private String password;
    private String realName;
    private String gradeClass;


    public Student() {

    }

    public Student(String userName, String password, String realName, String gradeClass) {
        super();
        this.userName = userName;
        this.password = password;
        this.realName = realName;
        this.gradeClass = gradeClass;
    }

    public String getUserName() {

        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getGradeClass() {
        return gradeClass;
    }

    public void setGradeClass(String gradeClass) {
        this.gradeClass = gradeClass;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }
}
