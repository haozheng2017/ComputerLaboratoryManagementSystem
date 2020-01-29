package com.example.zh.clms.database.Teacher;

public class Teacher {
    private String userName;
    private String password;
    private String realName;
    private String phoneNumber;
    private String roomNum;


    public Teacher() {

    }

    public Teacher(String userName, String password, String realName, String phoneNumber, String
            roomNum) {
        super();
        this.userName = userName;
        this.password = password;
        this.realName = realName;
        this.phoneNumber = phoneNumber;
        this.roomNum = roomNum;
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

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }


    public String getRoomNum() {
        return roomNum;
    }

    public void setRoomNum(String roomNum) {
        this.roomNum = roomNum;
    }

}
