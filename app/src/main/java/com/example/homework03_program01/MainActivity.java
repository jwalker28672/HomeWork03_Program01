package com.example.homework03_program01;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    DatabaseHelper dbHelper;

    Button btn_j_main_addStudent;
    Button btn_j_main_search;

    ListView lv_j_main_listOfStudents;

    Intent intent_j_AddStudent;
    Intent intent_j_StudentDetails;
    Intent intent_j_search;

    StudentListAdapter adapter;

    ArrayList<Student> listOfStudents = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        //set up guid connection to java
        btn_j_main_addStudent    = findViewById(R.id.btn_v_main_addStudent);
        btn_j_main_search        = findViewById(R.id.btn_v_main_search);

        lv_j_main_listOfStudents = findViewById(R.id.lv_v_main_listOfStudents);

        dbHelper = new DatabaseHelper(this);

        dbHelper.initTables();

        intent_j_AddStudent     = new Intent(MainActivity.this, AddStudent.class);
        intent_j_StudentDetails = new Intent(MainActivity.this, StudentDetails.class);
        intent_j_search         = new Intent(MainActivity.this,Search.class);

        addStudentButtonListener();
        //searchButtonListener();
        listViewClickerListener();
        listViewLongClickListener();
        fillStudentListView();

    }

    private void listViewClickerListener()
    {
        lv_j_main_listOfStudents.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l)
            {
                String name;

                SessionData.setCurrentSelectedStudent(listOfStudents.get(i).getuName());

                startActivity(intent_j_StudentDetails);
            }
        });
    }

    private void listViewLongClickListener()
    {
        lv_j_main_listOfStudents.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {

                dbHelper.deleteSelectedStudent(listOfStudents.get(i).getuName());

                recreate();
                return true;
            }
        });
    }

    private void addStudentButtonListener()
    {
        btn_j_main_addStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                startActivity(intent_j_AddStudent);
            }
        });
    }

    private void searchButtonListener()
    {
        btn_j_main_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(intent_j_search);
            }
        });
    }


    private void fillStudentListView()
    {

        listOfStudents = dbHelper.getAllStudentData();
        adapter = new StudentListAdapter(this,listOfStudents);

        lv_j_main_listOfStudents.setAdapter(adapter);

    }

}