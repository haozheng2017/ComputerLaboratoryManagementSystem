package com.example.zh.clms.database.LitePal_Student_Apply;

import org.litepal.crud.LitePalSupport;

public class Student_Apply extends LitePalSupport {
    private int id;
    private String name;
    private String phone;
    private String roomNumber;
    private String startTime;
    private String endTime;
    private String user;
    private int tagg;


    public Student_Apply() {

    }

    public Student_Apply(int id, String name, String phone, String roomNumber, String startTime,
                         String endTime, String user, int tagg) {
        super();
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.roomNumber = roomNumber;
        this.startTime = startTime;
        this.endTime = endTime;
        this.user = user;
        this.tagg = tagg;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(String roomNumber) {
        this.roomNumber = roomNumber;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public int getTagg() {
        return tagg;
    }

    public void setTagg(int tagg) {
        this.tagg = tagg;
    }

}
