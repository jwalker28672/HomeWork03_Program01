package com.example.homework03_program01;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Arrays;

public class Search extends AppCompatActivity {

    EditText et_j_search_fName;
    EditText et_j_search_lName;
    EditText et_j_search_uName;
    EditText et_j_search_gpa;

    Button btn_j_Search_search;
    Button btn_j_Search_back;

    Spinner sp_j_Search_operatorDropDown;
    Spinner sp_j_Search_majorDropDown;

    ListView lv_j_search_filterResults;

    SearchListAdapater adapter;

    DatabaseHelper dbHelper;

    ArrayAdapter<String> dropDownAdapter;
    ArrayAdapter<String> majorAdapter;

    ArrayList<Student> listOfStudents;

    static ArrayList<String> majorNames;
    static ArrayList<String> operator;

    Intent intent_j_MainActivity;

    int op;

    String major;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_search);

        et_j_search_fName            = findViewById(R.id.et_v_search_fName);
        et_j_search_lName            = findViewById(R.id.et_v_search_lName);
        et_j_search_uName            = findViewById(R.id.et_v_search_uName);
        et_j_search_gpa              = findViewById(R.id.et_v_search_gpa);

        btn_j_Search_search          = findViewById(R.id.btn_v_Search_search);
        btn_j_Search_back            = findViewById(R.id.btn_v_Search_back);

        sp_j_Search_operatorDropDown = findViewById(R.id.sp_v_Search_operatorDropDown);
        sp_j_Search_majorDropDown    = findViewById(R.id.sp_v_Search_majorDropDown);

        lv_j_search_filterResults  = findViewById(R.id.lv_v_search_filterResults);

        listOfStudents = new ArrayList<>();

        operator = new ArrayList<>(Arrays.asList("<", ">"));

        dbHelper = new DatabaseHelper(this);

        dropDownAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, operator);
        sp_j_Search_operatorDropDown.setAdapter(dropDownAdapter);

        majorNames = new ArrayList<>(Arrays.asList(""));
        majorNames.addAll(dbHelper.getAllMajorNames());
        major = majorNames.get(0);


        majorAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item,majorNames);
        sp_j_Search_majorDropDown.setAdapter(majorAdapter);

        intent_j_MainActivity = new Intent(Search.this, MainActivity.class);


        searchButtonListener();
        backButtonListener();
        spinnerEventListener();
        majorSpinnerEventListener();
    }

    private void backButtonListener()
    {
        btn_j_Search_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(intent_j_MainActivity);
            }
        });
    }

    private void searchButtonListener()
    {
        btn_j_Search_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                String uName = "";
                String fName = "";
                String lName = "";
                String gpa = "";

                uName = et_j_search_uName.getText().toString();
                fName = et_j_search_fName.getText().toString();
                lName = et_j_search_lName.getText().toString();
                gpa   = et_j_search_gpa.getText().toString();

                Log.d("Student major:", major);
                listOfStudents = dbHelper.searchDatabase(uName ,fName, lName, major, gpa, op);

                fillSearchListView(listOfStudents);

            }
        });
    }

    private void spinnerEventListener()
    {
        sp_j_Search_operatorDropDown.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l)
            {
                op = i;
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    private void majorSpinnerEventListener()
    {
        sp_j_Search_majorDropDown.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l)
            {
                major = adapterView.getItemAtPosition(i).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    private void fillSearchListView(ArrayList<Student> studentList)
    {

        adapter = new SearchListAdapater(this, studentList);
        lv_j_search_filterResults.setAdapter(adapter);
    }
}