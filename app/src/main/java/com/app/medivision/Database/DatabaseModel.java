package com.app.medivision.Database;

/**
 * Created by digital on 12/12/2016.
 */
public class DatabaseModel {
    String name;
    String gender;
    String age;
    String smoker;
    String systoru;
    String sysvalue;


    public String getDiovalue() {
        return diovalue;
    }

    public void setDiovalue(String diovalue) {
        this.diovalue = diovalue;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getSmoker() {
        return smoker;
    }

    public void setSmoker(String smoker) {
        this.smoker = smoker;
    }

    public String getSystoru() {
        return systoru;
    }

    public void setSystoru(String systoru) {
        this.systoru = systoru;
    }

    public String getSysvalue() {
        return sysvalue;
    }

    public void setSysvalue(String sysvalue) {
        this.sysvalue = sysvalue;
    }



    public String getHdlch() {
        return hdlch;
    }

    public void setHdlch(String hdlch) {
        this.hdlch = hdlch;
    }

    public String getTotalch() {
        return totalch;
    }

    public void setTotalch(String totalch) {
        this.totalch = totalch;
    }

    public String getDiabetes() {
        return diabetes;
    }

    public void setDiabetes(String diabetes) {
        this.diabetes = diabetes;
    }



    public String getFamilyhistory() {
        return familyhistory;
    }

    public void setFamilyhistory(String familyhistory) {
        this.familyhistory = familyhistory;
    }

    public String getFinalrisk() {
        return finalrisk;
    }

    public void setFinalrisk(String finalrisk) {
        this.finalrisk = finalrisk;
    }

    String diovalue;
    String totalch;
    String hdlch;
    String diabetes;

    public void setBmivalue(double bmivalue) {
        this.bmivalue = bmivalue;
    }

    public double getBmivalue() {
        return bmivalue;
    }

    double bmivalue;
    String familyhistory;
    String finalrisk;

    public String getHba1c() {
        return hba1c;
    }

    public void setHba1c(String hba1c) {
        this.hba1c = hba1c;
    }

    String hba1c;
    int id;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    String date;
    String email;
    String phone;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }



    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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








}
