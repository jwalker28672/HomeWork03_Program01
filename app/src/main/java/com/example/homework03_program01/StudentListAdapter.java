package com.example.homework03_program01;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class StudentListAdapter extends BaseAdapter
{
    Context context;
    ArrayList<Student> listOfStudents;

    public StudentListAdapter(Context c, ArrayList<Student> ls)
    {
        context = c;
        listOfStudents = ls;
    }

    @Override
    public int getCount()
    {
        return listOfStudents.size();
    }

    @Override
    public Object getItem(int i)
    {
        return listOfStudents.get(i);
    }

    @Override
    public long getItemId(int i)
    {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup)
    {
        if (view == null)
        {
            LayoutInflater mInflater = (LayoutInflater) context.getSystemService(MainActivity.LAYOUT_INFLATER_SERVICE);
            view = mInflater.inflate(R.layout.studentlist_cell, null);
        }

        TextView fName = view.findViewById(R.id.tv_v_slc_fName);
        TextView lName = view.findViewById(R.id.tv_v_slc_lName);
        TextView uName = view.findViewById(R.id.tv_v_slc_uName);

        Student student = listOfStudents.get(i);

        fName.setText(student.getfName());
        lName.setText(student.getlName());
        uName.setText(student.getuName());

        return view;
    }
}
