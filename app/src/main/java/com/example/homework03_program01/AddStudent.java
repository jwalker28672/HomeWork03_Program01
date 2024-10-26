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
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class AddStudent extends AppCompatActivity {

    EditText et_j_addStudent_fName;
    EditText et_j_addStudent_lName;
    EditText et_j_addStudent_uName;
    EditText et_j_addStudent_email;
    EditText et_j_addStudent_age;
    EditText et_j_addStudent_gpa;

    TextView tv_j_addStudent_addMajor;
    TextView tv_j_addStudent_emptyFieldsError;
    TextView tv_j_addStudent_unameExistsError;

    Spinner sp_j_addStudent_majorsDropDown;

    Button btn_j_addStudent_mainMenu;
    Button btn_j_addStudent_addStudent;

    Intent intent_j_MainActivity;
    Intent intent_j_AddNewMajor;

    ArrayAdapter<String> adapter;

    DatabaseHelper dbHelper;

    String majorName = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_add_student);

        et_j_addStudent_fName            = findViewById(R.id.et_v_addStudent_fName);
        et_j_addStudent_lName            = findViewById(R.id.et_v_addStudent_lName);
        et_j_addStudent_uName            = findViewById(R.id.et_v_addStudent_uName);
        et_j_addStudent_email            = findViewById(R.id.et_v_addStudent_email);
        et_j_addStudent_age              = findViewById(R.id.et_v_addStudent_age);
        et_j_addStudent_gpa              = findViewById(R.id.et_v_addStudent_gpa);

        tv_j_addStudent_addMajor         = findViewById(R.id.tv_v_addStudent_addMajor);
        tv_j_addStudent_emptyFieldsError = findViewById(R.id.tv_v_AddStudent_emptyFieldsErrorMsg);
        tv_j_addStudent_unameExistsError = findViewById(R.id.tv_v_AddStudent_unameExistsError);

        btn_j_addStudent_mainMenu        = findViewById(R.id.btn_v_addStudent_mainMenu);
        btn_j_addStudent_addStudent      = findViewById(R.id.btn_v_addStudent_addStudent);

        sp_j_addStudent_majorsDropDown   = findViewById(R.id.sp_v_addStudent_majorsDropDown);

        intent_j_MainActivity = new Intent(AddStudent.this, MainActivity.class);
        intent_j_AddNewMajor  = new Intent(AddStudent.this,AddMajor.class);

        dbHelper = new DatabaseHelper(this);

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, dbHelper.getAllMajorNames());
        sp_j_addStudent_majorsDropDown.setAdapter(adapter);

        mainMenuButtonListener();
        clearEditText();
        spinnerEventListener();
        addNewMajorEventListener();
        addStudentButtonListener();

    }



    private void spinnerEventListener()
    {
        sp_j_addStudent_majorsDropDown.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l)
            {
                majorName = adapterView.getItemAtPosition(i).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView)
            {

            }
        });
    }

    private void mainMenuButtonListener()
    {
        btn_j_addStudent_mainMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                startActivity(intent_j_MainActivity);
            }
        });
    }

    private void addNewMajorEventListener()
    {
        tv_j_addStudent_addMajor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                   startActivity(intent_j_AddNewMajor);
            }
        });
    }

    private void addStudentButtonListener()
    {
        btn_j_addStudent_addStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                String fName = et_j_addStudent_fName.getText().toString();
                String lName = et_j_addStudent_lName.getText().toString();
                String uName = et_j_addStudent_uName.getText().toString();
                String email = et_j_addStudent_email.getText().toString();
                String major = majorName;
                String age   = et_j_addStudent_age.getText().toString();
                String gpa   = et_j_addStudent_gpa.getText().toString();

                Log.d("Major name: ", majorName);
                //check is all text boxes are filled out
                if(!fName.isEmpty() && !lName.isEmpty() && !uName.isEmpty() && !email.isEmpty() && !major.isEmpty() && !age.isEmpty() && !gpa.isEmpty())
                {
                    Student s = new Student();

                    s.setuName(uName);
                    s.setfName(fName);
                    s.setlName(lName);
                    s.setEmail(email);
                    s.setMajor(major);
                    s.setAge(Integer.parseInt(age));
                    s.setGpa(Float.parseFloat(gpa));

                    //nest if statement to check if username already exists

                    if(!dbHelper.checkUsernameExistGivenUsername(s.getuName()))
                    {
                        Log.d("Message: ", "No user name found");
                        dbHelper.addStudentToDatabase(s);
                        clearEditText();
                        tv_j_addStudent_unameExistsError.setVisibility(View.INVISIBLE);
                    }
                    else
                    {
                        Log.d("Error: ", "Username Exists");
                        tv_j_addStudent_unameExistsError.setVisibility(View.VISIBLE);
                    }


                    tv_j_addStudent_emptyFieldsError.setVisibility(View.INVISIBLE);

                }
                else
                {
                    //throw error one of the boxes is not filled out
                    Log.d("Error: ", "Text fields empty");
                    tv_j_addStudent_emptyFieldsError.setVisibility(View.VISIBLE);
                }

            }
        });
    }

    public void clearEditText()
    {
        et_j_addStudent_fName.setText("");
        et_j_addStudent_lName.setText("");
        et_j_addStudent_uName.setText("");
        et_j_addStudent_email.setText("");
        et_j_addStudent_age.setText("");
        et_j_addStudent_gpa.setText("");
    }

}