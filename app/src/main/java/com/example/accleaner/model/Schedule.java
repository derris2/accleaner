package com.example.accleaner.model;

public class Schedule {
    private String id;
    private String name;
    private String default_yn;
    private String monday_yn;
    private String tuesday_yn;
    private String wednesday_yn;
    private String thursday_yn;
    private String friday_yn;
    private String saturday_yn;
    private String sunday_yn;
    private String start_hour;
    private String repeated_interval;
    private String repeated_unit;
    private String schedule_status;

    public Schedule() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDefault_yn() {
        return default_yn;
    }

    public void setDefault_yn(String default_yn) {
        this.default_yn = default_yn;
    }

    public String getMonday_yn() {
        return monday_yn;
    }

    public void setMonday_yn(String monday_yn) {
        this.monday_yn = monday_yn;
    }


    public String getTuesday_yn() {
        return tuesday_yn;
    }

    public void setTuesday_yn(String tuesday_yn) {
        this.tuesday_yn = tuesday_yn;
    }

    public String getWednesday_yn() {
        return wednesday_yn;
    }

    public void setWednesday_yn(String wednesday_yn) {
        this.wednesday_yn = wednesday_yn;
    }

    public String getThursday_yn() {
        return thursday_yn;
    }

    public void setThursday_yn(String thursday_yn) {
        this.thursday_yn = thursday_yn;
    }

    public String getFriday_yn() {
        return friday_yn;
    }

    public void setFriday_yn(String friday_yn) {
        this.friday_yn = friday_yn;
    }

    public String getSaturday_yn() {
        return saturday_yn;
    }

    public void setSaturday_yn(String saturday_yn) {
        this.saturday_yn = saturday_yn;
    }

    public String getSunday_yn() {
        return sunday_yn;
    }

    public void setSunday_yn(String sunday_yn) {
        this.sunday_yn = sunday_yn;
    }

    public String getStart_hour() {
        return start_hour;
    }

    public void setStart_hour(String start_hour) {
        this.start_hour = start_hour;
    }

    public String getRepeated_interval() {
        return repeated_interval;
    }

    public void setRepeated_interval(String repeated_interval) {
        this.repeated_interval = repeated_interval;
    }

    public String getRepeated_unit() {
        return repeated_unit;
    }

    public void setRepeated_unit(String repeated_unit) {
        this.repeated_unit = repeated_unit;
    }

    public String getSchedule_status() {
        return schedule_status;
    }

    public void setSchedule_status(String schedule_status) {
        this.schedule_status = schedule_status;
    }
}
