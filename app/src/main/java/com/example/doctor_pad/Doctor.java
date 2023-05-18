package com.example.doctor_pad;

public class Doctor {
    String d_Name;
    String d_Age;
    String d_Phone_no;
    String d_filename;

    public Doctor() {
    }

    public String getD_filename() {
        return d_filename;
    }

    public void setD_filename(String d_filename) {
        this.d_filename = d_filename;
    }

    public Doctor(String d_Name, String d_Age, String d_Phone_no,String d_filename) {
        this.d_Name = d_Name;
        this.d_Age = d_Age;
        this.d_Phone_no = d_Phone_no;
        this.d_filename=d_filename;
    }

    public String getD_Name() {
        return d_Name;
    }

    public void setD_Name(String d_Name) {
        this.d_Name = d_Name;
    }

    public String getD_Age() {
        return d_Age;
    }

    public void setD_Age(String d_Age) {
        this.d_Age = d_Age;
    }

    public String getD_Phone_no() {
        return d_Phone_no;
    }

    public void setD_Phone_no(String d_Phone_no) {
        this.d_Phone_no = d_Phone_no;
    }


}
