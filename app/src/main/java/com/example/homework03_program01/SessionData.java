package com.example.homework03_program01;

public class SessionData
{
    private static String curStudent;

    public static String getCurrentSelectedStudent()
    {
        return curStudent;
    }

    public static void setCurrentSelectedStudent(String n)
    {
        curStudent = n;
    }
}
