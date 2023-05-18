package com.example.doctor_pad;

public class Patient {
    String p_Name;
    String p_Age;
    String p_Phone_no;
    String p_Address;
    String p_Bloodgroup;

    public Patient() {
    }

    public Patient(String p_Name, String p_Age, String p_Phone_no, String p_Address, String p_Bloodgroup) {
        this.p_Name = p_Name;
        this.p_Age = p_Age;
        this.p_Phone_no = p_Phone_no;
        this.p_Address = p_Address;
        this.p_Bloodgroup = p_Bloodgroup;
    }

    public String getP_Name() {
        return p_Name;
    }

    public void setP_Name(String p_Name) {
        this.p_Name = p_Name;
    }

    public String getP_Age() {
        return p_Age;
    }

    public void setP_Age(String p_Age) {
        this.p_Age = p_Age;
    }

    public String getP_Phone_no() {
        return p_Phone_no;
    }

    public void setP_Phone_no(String p_Phone_no) {
        this.p_Phone_no = p_Phone_no;
    }

    public String getP_Address() {
        return p_Address;
    }

    public void setP_Address(String p_Address) {
        this.p_Address = p_Address;
    }

    public String getP_Bloodgroup() {
        return p_Bloodgroup;
    }

    public void setP_Bloodgroup(String p_Bloodgroup) {
        this.p_Bloodgroup = p_Bloodgroup;
    }

}


