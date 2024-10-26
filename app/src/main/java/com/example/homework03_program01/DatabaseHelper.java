package com.example.homework03_program01;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

public class DatabaseHelper extends SQLiteOpenHelper
{
    private static final String database_name = "Student.db";
    private static final String students_table_name = "students";
    private static final String majors_table_name = "majors";


    public DatabaseHelper(Context c)
    {
        super(c,database_name,null,2);
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        db.execSQL("CREATE TABLE " + students_table_name + " (uName varchar(50) primary key, fname varchar(50), lName varchar(50), email varchar(50), age integer, major varchar(50), gpa float);");
        db.execSQL("CREATE TABLE " + majors_table_name + " (majorId integer primary key autoincrement not null, majorName varchar(50), majorPrefix varchar(5), foreign key (majorName) references " + students_table_name + " (major));");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1)
    {
        db.execSQL("DROP TABLE IF EXISTS " + students_table_name + ";");
        db.execSQL("DROP TABLE IF EXISTS " + majors_table_name + ";");

        onCreate(db);
    }

    public String getStudentdb()
    {
        return  students_table_name;
    }

    public String getMajordb()
    {
        return majors_table_name;
    }

    public String getFirstMajorName()
    {
        String name = "";

        SQLiteDatabase db =this.getReadableDatabase();

        String selectStatement = "SELECT majorName FROM " + majors_table_name;

        Cursor cursor = db.rawQuery(selectStatement, null);

        if(cursor != null)
        {
            cursor.moveToFirst();
            name = cursor.getString(0);
        }

        db.close();

        return name;
    }

    public void initTables()
    {
        initStudents();
        initMajors();

    }

    //Add initial data for student table
    private void initStudents()
    {
        //if no records exist in the table then fill it with initial data
        if (countRecordsFromTable(students_table_name) == 0)
        {
            //create a writeable copy of database
            SQLiteDatabase db = this.getWritableDatabase();

            //inserts initial table of students
            db.execSQL("INSERT INTO " + students_table_name + "(uName, fName, lName, email, age, major, gpa) VALUES ('LLindsay', 'Lucia', 'Lindsay', 'LLindsay@gmail.com', 26, 'Mechanical Engineering', 3.6);");
            db.execSQL("INSERT INTO " + students_table_name + "(uName, fName, lName, email, age, major, gpa) VALUES ('AShah', 'Anas', 'Shah', 'AShah@aol.com', 37, 'Psychology', 2.9);");
            db.execSQL("INSERT INTO " + students_table_name + "(uName, fName, lName, email, age, major, gpa) VALUES ('SBuchanan', 'Simeon', 'Buchanan', 'SBuchanan@gamail.com', 18, 'Early Childhood Education', 3.0);");
            db.execSQL("INSERT INTO " + students_table_name + "(uName, fName, lName, email, age, major, gpa) VALUES ('TMathis', 'Theodore', 'Mathis', 'TMathis@gmail.com', 19, 'Game Development', 3.3);");
            db.execSQL("INSERT INTO " + students_table_name + "(uName, fName, lName, email, age, major, gpa) VALUES ('VSuarez', 'Victoria', 'Suarez', 'VSuarez@att.net', 23, 'Electrical Engineering', 3.9);");
            db.execSQL("INSERT INTO " + students_table_name + "(uName, fName, lName, email, age, major, gpa) VALUES ('EDonovan', 'Edgar', 'Donovan', 'EDonovan@yahoo.com', 32, 'Business Management', 3.7);");

            db.close();
        }
    }

    //does same as initStudents() except for the majors
    private void initMajors()
    {

        if(countRecordsFromTable(majors_table_name) == 0)
        {
            SQLiteDatabase db = this.getWritableDatabase();

            db.execSQL("INSERT INTO " + majors_table_name + "(majorName, majorPrefix) VALUES ('Mechanical Engineering', 'ENG');");
            db.execSQL("INSERT INTO " + majors_table_name + "(majorName, majorPrefix) VALUES ('Psychology', 'PSY');");
            db.execSQL("INSERT INTO " + majors_table_name + "(majorName, majorPrefix) VALUES ('Early Childhood Education', 'EDU');");
            db.execSQL("INSERT INTO " + majors_table_name + "(majorName, majorPrefix) VALUES ('Game Development', 'CIS');");
            db.execSQL("INSERT INTO " + majors_table_name + "(majorName, majorPrefix) VALUES ('Electrical Engineering', 'ENG');");
            db.execSQL("INSERT INTO " + majors_table_name + "(majorName, majorPrefix) VALUES ('Business Management', 'BUS');");

            db.close();
        }
    }


    public int countRecordsFromTable(String tableName)
    {
        SQLiteDatabase db = this.getReadableDatabase();

        int numRows = (int) DatabaseUtils.queryNumEntries(db,tableName);

        db.close();

        return numRows;
    }

    public ArrayList<Student> getAllStudentData()
    {
        ArrayList<Student> listOfStudents = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();

        String selectStatement = "SELECT * FROM " + students_table_name;

        Cursor cursor = db.rawQuery(selectStatement, null);

        while(cursor.moveToNext())
        {
            Student student = new Student();

            student.setuName(cursor.getString(0));
            student.setfName(cursor.getString(1));
            student.setlName(cursor.getString(2));
            student.setEmail(cursor.getString(3));
            student.setAge(cursor.getInt(4));
            student.setMajor(cursor.getString(5));
            student.setGpa(cursor.getFloat(6));

            listOfStudents.add(student);

        }

        db.close();

        return listOfStudents;
    }

    public ArrayList<Major> getAllMajorData()
    {
        ArrayList<Major> listOfMajors = new ArrayList<>();

        SQLiteDatabase db =this.getReadableDatabase();

        String selectStatement = "SELECT * FROM " + majors_table_name;

        Cursor cursor = db.rawQuery(selectStatement, null);

        while (cursor.moveToNext())
        {
            Major major = new Major();

            major.setMajorId(cursor.getInt(0));
            major.setMajorName(cursor.getString(1));
            major.setMajorPrefix(cursor.getString(2));

            listOfMajors.add(major);
        }

        db.close();

        return listOfMajors;
    }

    public ArrayList<String> getAllMajorNames()
    {
        ArrayList<String> listOfMajorNames = new ArrayList<>();

        SQLiteDatabase db =this.getReadableDatabase();

        String selectStatement = "SELECT majorName FROM " + majors_table_name;

        Cursor cursor = db.rawQuery(selectStatement, null);

        while (cursor.moveToNext())
        {
            String name;

            name = cursor.getString(0);

            listOfMajorNames.add(name);
        }

        return listOfMajorNames;
    }

    public void addStudentToDatabase(Student s)
    {
        SQLiteDatabase db = this.getWritableDatabase();

        db.execSQL("INSERT INTO " + students_table_name
                 + " VALUES  ('" + s.getuName() + "','" + s.getfName() + "','" + s.getlName() + "','" + s.getEmail() + "','" + s.getAge() + "','" + s.getMajor() + "','" + s.getGpa() + "');");

        db.close();
    }

    public boolean checkUsernameExistGivenUsername(String u)
    {
        SQLiteDatabase db = this.getReadableDatabase();

        String selectStatement = "SELECT uName FROM " + students_table_name;

        Cursor cursor = db.rawQuery(selectStatement, null);

        while (cursor.moveToNext())
        {
            String name = cursor.getString(0);
            Log.d("Database: ", name);
            if(u.equals(name))
            {
                Log.d("Database: ", "Name exist");
                return true;
            }
        }

        return false;
    }

    public Student getStudentDataGivenUname(String n)
    {
        SQLiteDatabase db = this.getReadableDatabase();

        String selectStatement = "SELECT * FROM " + students_table_name + " WHERE uName ='" + n + "';";

        Cursor cursor = db.rawQuery(selectStatement, null);

        Student s = new Student();

        if(cursor != null)
        {
            cursor.moveToFirst();

            s.setuName(cursor.getString(0));
            s.setfName(cursor.getString(1));
            s.setlName(cursor.getString(2));
            s.setEmail(cursor.getString(3));
            s.setAge(cursor.getInt(4));
            s.setMajor(cursor.getString(5));
            s.setGpa(cursor.getFloat(6));

        }

        return s;
    }

    public void updateStudentGivenUsername(String n, Student s)
    {
        SQLiteDatabase db = this.getWritableDatabase();

        Log.d("dbHelper", s.getfName());

        db.execSQL("UPDATE " + students_table_name + " SET fName = '" + s.getfName() + "' , lName = '" + s.getlName() + "' , email = '" + s.getEmail() + "' , age = '" + s.getAge() + "' , major = '" + s.getMajor() + "' , gpa = '" + s.getGpa() + "'" + " WHERE uName = '" +  n  + "'");

        db.close();
    }

    public void deleteSelectedStudent(String n)
    {
        Log.d("Delete this student: ", n);

        SQLiteDatabase db = this.getWritableDatabase();

        db.execSQL("DELETE FROM " + students_table_name + " WHERE uName = '" + n + "'");
    }

    public boolean checkMajorExists(String n)
    {
        SQLiteDatabase db = this.getWritableDatabase();

        String selectStatement = "SELECT majorName FROM " + majors_table_name;

        Cursor cursor = db.rawQuery(selectStatement, null);

        while(cursor.moveToNext())
        {
            String name = cursor.getString(0);

            //major name exists
            if(name.equals(n))
            {
                db.close();
                return true;
            }
        }

        db.close();
        // if cycled through loop and never came back true then major name does not exist
        return false;
    }

    public void addMajorToDatabase(Major m)
    {
        SQLiteDatabase db = this.getWritableDatabase();

        db.execSQL("INSERT INTO " + majors_table_name + " (majorName, majorPrefix) VALUES ( '" + m.getMajorName() + "' , '" + m.getMajorPrefix() + "');");

        db.close();
    }

    @SuppressLint("Range")
    public ArrayList<Student> searchDatabase (String u, String f, String l, String m, String g, int op)
    {

        ArrayList<Student> listOfStudents = new ArrayList<Student>();

        String selectStatement = "Select * from " + students_table_name + " Where ";

        if(u.isEmpty())
        {
            selectStatement += "uName is not null";
        }
        else
        {
            selectStatement += "uName = '" + u + "' ";
        }
        selectStatement += " AND ";
        if(f.isEmpty())
        {
            selectStatement += "fName is not null ";
        }
        else
        {
            selectStatement += "fName = '" + f + "' ";
        }
        selectStatement += " AND ";
        if(l.isEmpty())
        {
            selectStatement += "lName is not null ";
        }
        else
        {
            selectStatement += "lName = '" + l + "' ";
        }
        selectStatement += " AND ";
        if(m.isEmpty())
        {
            selectStatement += "major is not null ";
        }
        else
        {
            selectStatement += "major = '" + m + "' ";
        }
        selectStatement += " AND ";
        if (g.isEmpty())
        {
            selectStatement += "gpa is not null ";
        }
        else if(op == 0)
        {
            selectStatement += "gpa < '" + g + "' ";
        }
        else if (op == 1)
        {
            selectStatement += "gpa > '" + g + "' ";
        }


        selectStatement += ";";

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(selectStatement, null);

        while(cursor.moveToNext())
        {
            Student s = new Student();

            s.setuName(cursor.getString(cursor.getColumnIndex("uName")));
            s.setfName(cursor.getString(cursor.getColumnIndex("fname")));
            s.setlName(cursor.getString(cursor.getColumnIndex("lName")));
            s.setEmail(cursor.getString(cursor.getColumnIndex("email")));
            s.setMajor(cursor.getString(cursor.getColumnIndex("major")));
            s.setAge(cursor.getInt(cursor.getColumnIndex("age")));
            s.setGpa(cursor.getFloat(cursor.getColumnIndex("gpa")));

            listOfStudents.add(s);

        }


        db.close();
        return listOfStudents;
    }

}