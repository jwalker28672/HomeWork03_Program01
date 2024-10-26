package com.example.homework03_program01;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class EditStudent extends AppCompatActivity {


    EditText et_j_StudentEdit_fName;
    EditText et_j_StudentEdit_lName;
    EditText et_j_StudentEdit_uName;
    EditText et_j_StudentEdit_email;
    EditText et_j_StudentEdit_age;
    EditText et_j_StudentEdit_gpa;

    Spinner sp_j_StudentEdit_majorDropDown;

    Button btn_j_EditStudent_cancel;
    Button btn_j_EditStudent_update;

    Intent intent_j_StudentDetails;

    DatabaseHelper dbHelper;

    String name;
    String majorName;

    ArrayAdapter<String> adapter;

    Student student;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_edit_student);

        et_j_StudentEdit_fName         = findViewById(R.id.et_v_StudentEdit_fName);
        et_j_StudentEdit_lName         = findViewById(R.id.et_v_StudentEdit_lName);
        et_j_StudentEdit_uName         = findViewById(R.id.et_v_StudentEdit_uName);
        et_j_StudentEdit_email         = findViewById(R.id.et_v_StudentEdit_email);
        et_j_StudentEdit_age           = findViewById(R.id.et_v_StudentEdit_age);
        et_j_StudentEdit_gpa           = findViewById(R.id.et_v_StudentEdit_gpa);

        sp_j_StudentEdit_majorDropDown = findViewById(R.id.sp_v_StudentEdit_majorsDropDown);

        btn_j_EditStudent_cancel       = findViewById(R.id.btn_v_EditStudent_cancel);
        btn_j_EditStudent_update       = findViewById(R.id.btn_v_EditStudent_update);

        sp_j_StudentEdit_majorDropDown = findViewById(R.id.sp_v_StudentEdit_majorsDropDown);

        dbHelper = new DatabaseHelper(this);

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, dbHelper.getAllMajorNames());
        sp_j_StudentEdit_majorDropDown.setAdapter(adapter);

        intent_j_StudentDetails = new Intent(EditStudent.this, StudentDetails.class);

        name = SessionData.getCurrentSelectedStudent();


        student = dbHelper.getStudentDataGivenUname(name);
        Log.d("StudentEdit", student.getfName());

        if(student.getMajor() != null)
        {
            int index = adapter.getPosition(student.getMajor());
            sp_j_StudentEdit_majorDropDown.setSelection(index);
        }

        fillDetails();
        cancelButtonListener();
        updateButtonListener();
        spinnerEventListener();
    }

    private void cancelButtonListener()
    {
        btn_j_EditStudent_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                startActivity(intent_j_StudentDetails);
            }
        });
    }

    private void updateButtonListener()
    {
        btn_j_EditStudent_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Student s = updateStudentDetails();

                dbHelper.updateStudentGivenUsername(SessionData.getCurrentSelectedStudent(), s);
                startActivity(intent_j_StudentDetails);
            }
        });
    }

    private void spinnerEventListener()
    {
        sp_j_StudentEdit_majorDropDown.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l)
            {

                majorName = adapterView.getItemAtPosition(i).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }


    private void fillDetails()
    {
        et_j_StudentEdit_fName.setText(student.getfName());
        et_j_StudentEdit_lName .setText(student.getlName());
        et_j_StudentEdit_uName.setText(student.getuName());
        et_j_StudentEdit_email.setText(student.getEmail());
        et_j_StudentEdit_age .setText(Integer.toString(student.getAge()));
        et_j_StudentEdit_gpa .setText(Float.toString(student.getGpa()));

    }

    private Student updateStudentDetails()
    {
        Student s = new Student();

        s.setfName(et_j_StudentEdit_fName.getText().toString());
        s.setlName(et_j_StudentEdit_lName.getText().toString());
        s.setEmail(et_j_StudentEdit_email.getText().toString());
        s.setAge(Integer.parseInt(et_j_StudentEdit_age.getText().toString()));
        s.setGpa(Float.parseFloat(et_j_StudentEdit_gpa.getText().toString()));
        s.setMajor(majorName);

        return s;
    }
}