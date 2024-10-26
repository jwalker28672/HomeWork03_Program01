package com.example.homework03_program01;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class SearchListAdapater extends BaseAdapter
{
    Context context;
    ArrayList<Student> listOfStudents;

    public SearchListAdapater(Context c, ArrayList<Student> ls)
    {
        context = c;
        listOfStudents = ls;
    }

    @Override
    public int getCount() {
        return listOfStudents.size();
    }

    @Override
    public Object getItem(int i) {
        return listOfStudents.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        if (view == null)
        {
            LayoutInflater mInflater = (LayoutInflater) context.getSystemService(MainActivity.LAYOUT_INFLATER_SERVICE);
            view = mInflater.inflate(R.layout.searchlist_cell, null);
        }

        TextView uName = view.findViewById(R.id.tv_v_sc_uName);
        TextView fName = view.findViewById(R.id.tv_v_sc_fName);
        TextView lName = view.findViewById(R.id.tv_v_sc_lName);
        TextView email = view.findViewById(R.id.tv_v_sc_email);
        TextView major = view.findViewById(R.id.tv_v_sc_major);
        TextView age = view.findViewById(R.id.tv_v_sc_age);
        TextView gpa = view.findViewById(R.id.tv_v_sc_gpa);

        Student student = listOfStudents.get(i);

        uName.setText(student.getuName());
        fName.setText(student.getfName());
        lName.setText(student.getlName());
        email.setText(student.getEmail());
        major.setText(student.getMajor());
        age.setText(Integer.toString(student.getAge()));
        gpa.setText(Float.toString(student.getGpa()));

        return view;
    }
}
