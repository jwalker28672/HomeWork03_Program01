package com.example.homework03_program01;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class AddMajor extends AppCompatActivity {

    EditText et_j_AddMajor_majorName;
    EditText et_j_AddMajor_majorPrefix;

    TextView tv_j_AddMajor_nameExists;
    TextView tv_j_AddMajor_blankFields;

    Button btn_j_AddMajor_back;
    Button btn_j_AddMajor_addMajor;

    Intent intent_j_AddStudent;

    DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_add_major);

        et_j_AddMajor_majorName   = findViewById(R.id.et_v_AddMajor_majorName);
        et_j_AddMajor_majorPrefix = findViewById(R.id.et_v_AddMajor_majorPrefix);

        tv_j_AddMajor_nameExists  = findViewById(R.id.tv_v_AddMajor_nameExists);
        tv_j_AddMajor_blankFields = findViewById(R.id.tv_v_AddMajor_blankFields);

        btn_j_AddMajor_back       = findViewById(R.id.btn_v_AddMajor_back);
        btn_j_AddMajor_addMajor   = findViewById(R.id.btn_v_AddMajor_addMajor);

        intent_j_AddStudent = new Intent(AddMajor.this, AddStudent.class);

        dbHelper = new DatabaseHelper(this);

        backButtonListener();
        addMajorButtonListener();

    }

    private void backButtonListener()
    {
        btn_j_AddMajor_back.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                startActivity(intent_j_AddStudent);
            }
        });
    }

    private void addMajorButtonListener()
    {
        btn_j_AddMajor_addMajor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                if(addMajor())
                {
                    Log.d("Add major Button: ", "Function returned true");
                    startActivity(intent_j_AddStudent);
                }
            }
        });
    }

    private boolean addMajor()
    {
        String majorName   = et_j_AddMajor_majorName.getText().toString();
        String majorPrefix = et_j_AddMajor_majorPrefix.getText().toString();


        //make sure we are not passing empty data
        if (!majorName.isEmpty() && !majorPrefix.isEmpty())
        {
            tv_j_AddMajor_blankFields.setVisibility(View.INVISIBLE);

            Major m = new Major();

            m.setMajorName(majorName);
            m.setMajorPrefix(majorPrefix);

            if (!dbHelper.checkMajorExists(m.getMajorName()))
            {
                tv_j_AddMajor_nameExists.setVisibility(View.INVISIBLE);
                dbHelper.addMajorToDatabase(m);
                return true;
            }
            else
            {
                tv_j_AddMajor_nameExists.setVisibility(View.VISIBLE);
                return false;
            }

        }
        else
        {
            //empty field error
            tv_j_AddMajor_blankFields.setVisibility(View.VISIBLE);
        }

        return false;
    }
}