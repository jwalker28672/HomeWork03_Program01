package com.example.homework03_program01;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class Search extends AppCompatActivity {

    EditText et_j_search_fName;
    EditText et_j_search_lName;
    EditText et_j_search_uName;
    EditText et_j_search_gpaLessThan;
    EditText et_j_search_gpaGreaterThan;

    Button btn_j_Search_search;


    String fName = "";
    String lName = "";
    String uName = "";
    String gpaLess = "";
    String gpaGreater = "";

    DatabaseHelper dbHelper;

    ArrayList<String> listOfStudents;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_search);

        et_j_search_fName          = findViewById(R.id.et_v_search_fName);
        et_j_search_lName          = findViewById(R.id.et_v_search_lName);
        et_j_search_uName          = findViewById(R.id.et_v_search_uName);
        et_j_search_gpaLessThan    = findViewById(R.id.et_v_search_gpaLessThan);
        et_j_search_gpaGreaterThan = findViewById(R.id.et_v_search_gpaGreaterThan);

        btn_j_Search_search        = findViewById(R.id.btn_v_Search_search);


        dbHelper = new DatabaseHelper(this);

        listOfStudents = new ArrayList<>();

        //searchButtonListener();
    }


    private void searchButtonListener()
    {
        btn_j_Search_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                fName = et_j_search_fName.getText().toString();
                lName = et_j_search_lName.getText().toString();

                listOfStudents = dbHelper.searchDatabaseGivenFName(fName,lName);

                for(int i = 0; i < listOfStudents.size(); i++)
                {
                    Log.d("Students", listOfStudents.get(i));
                }
            }
        });
    }
}