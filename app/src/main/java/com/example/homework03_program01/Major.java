package com.example.homework03_program01;

import android.content.Context;

import java.util.ArrayList;
import java.util.Arrays;

public class Major
{
    int id;

    String name;
    String prefix;

    ArrayList<Major> listOfMajors = new ArrayList<>();


    public Major()
    {

    }
    public Major(int i, String mn, String mp)
    {
        id = i;
        name = mn;
        prefix = mp;
    }

    //=================================================================================
    //                             GETTERS
    //=================================================================================


    public int getMajorId()
    {
        return id;
    }

    public String getMajorName()
    {
        return name;
    }

    public String getMajorPrefix()
    {
        return prefix;
    }


    //=================================================================================
    //                             SETTERS
    //=================================================================================


    public void setMajorId(int majorId)
    {
        this.id = majorId;
    }

    public void setMajorName(String majorName)
    {
        this.name = majorName;
    }

    public void setMajorPrefix(String majorPrefix)
    {
        this.prefix = majorPrefix;
    }

}
