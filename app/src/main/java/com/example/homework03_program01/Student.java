package com.example.homework03_program01;

import java.io.Serializable;

public class Student implements Serializable
{
    private String uName;
    private String fName;
    private String lName;
    private String email;
    private String major;

    private int age;

    private float gpa;

    public Student()
    {

    }

    public Student(String u, String f, String l, String e, String m, int a, float g)
    {
        fName = f;
        lName = l;
        uName = u;
        email = e;
        major = m;
        age   = a;
        gpa   = g;

    }


    //=================================================================================
    //                             GETTERS
    //=================================================================================


    public String getfName() {
        return fName;
    }

    public String getlName() {
        return lName;
    }

    public String getuName() {
        return uName;
    }

    public String getEmail() {
        return email;
    }

    public String getMajor() {
        return major;
    }

    public int getAge() {
        return age;
    }

    public float getGpa() {
        return gpa;
    }



    //=================================================================================
    //                             SETTERS
    //=================================================================================


    public void setfName(String fName) {
        this.fName = fName;
    }

    public void setlName(String lName) {
        this.lName = lName;
    }

    public void setuName(String uName) {
        this.uName = uName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setGpa(float gpa) {
        this.gpa = gpa;
    }
}
