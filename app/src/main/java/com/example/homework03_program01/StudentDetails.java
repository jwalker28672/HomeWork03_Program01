package com.example.homework03_program01;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class StudentDetails extends AppCompatActivity {

    TextView tv_j_StudentDetails_fName;
    TextView tv_j_StudentDetails_lName;
    TextView tv_j_StudentDetails_uName;
    TextView tv_j_StudentDetails_email;
    TextView tv_j_StudentDetails_age;
    TextView tv_j_StudentDetails_major;
    TextView tv_j_StudentDetails_gpa;

    Button btn_j_StudentDetails_mainMenu;
    Button btn_j_StudentDetails_edit;

    Intent intent_j_MainActivity;
    Intent intent_j_EditStudent;

    Student student;

    String name;

    DatabaseHelper dbHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_student_details);

        tv_j_StudentDetails_fName = findViewById(R.id.tv_v_StudentDetails_fName);
        tv_j_StudentDetails_lName = findViewById(R.id.tv_v_StudentDetails_lName);
        tv_j_StudentDetails_uName = findViewById(R.id.tv_v_StudentDetails_uName);
        tv_j_StudentDetails_email = findViewById(R.id.tv_v_StudentDetails_email);
        tv_j_StudentDetails_age   = findViewById(R.id.tv_v_StudentDetails_age);
        tv_j_StudentDetails_major = findViewById(R.id.tv_v_StudentDetails_major);
        tv_j_StudentDetails_gpa   = findViewById(R.id.tv_v_StudentDetails_gpa);

        btn_j_StudentDetails_mainMenu = findViewById(R.id.btn_v_StudentDetails_mainMenu);
        btn_j_StudentDetails_edit     = findViewById(R.id.btn_v_StudentDetails_edit);

        intent_j_MainActivity = new Intent(StudentDetails.this, MainActivity.class);
        intent_j_EditStudent = new Intent(StudentDetails.this,EditStudent.class);

        dbHelper = new DatabaseHelper(this);


        name = SessionData.getCurrentSelectedStudent();

        student = dbHelper.getStudentDataGivenUname(SessionData.getCurrentSelectedStudent());

        Log.d("StudentDetails",student.getfName());

        mainMenuButtonListener();
        editButtonListener();
        fillDetails();

    }

    private void mainMenuButtonListener()
    {
        btn_j_StudentDetails_mainMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(intent_j_MainActivity);
            }
        });
    }

    private void editButtonListener()
    {
        btn_j_StudentDetails_edit.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                intent_j_EditStudent.putExtra("StudentInfo", name);
                startActivity(intent_j_EditStudent);
            }
        });
    }

    private void fillDetails()
    {
        tv_j_StudentDetails_fName.setText(student.getfName());
        tv_j_StudentDetails_lName.setText(student.getlName());
        tv_j_StudentDetails_uName.setText(student.getuName());
        tv_j_StudentDetails_email.setText(student.getEmail());
        tv_j_StudentDetails_age.setText(Integer.toString(student.getAge()));
        tv_j_StudentDetails_major.setText(student.getMajor());
        tv_j_StudentDetails_gpa.setText(Float.toString(student.getGpa()));

    }
}