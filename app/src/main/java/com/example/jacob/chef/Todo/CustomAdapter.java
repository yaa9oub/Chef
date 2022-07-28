package com.example.jacob.chef.Todo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.jacob.chef.R;
import com.example.jacob.chef.Todo.Task;

import java.util.ArrayList;


public class CustomAdapter extends ArrayAdapter {

    private TextView label , date , time , detail ;
    private Context contexte ;
    private ArrayList<Task> lista ;


    public CustomAdapter(Context context, int resource, ArrayList<Task> objects) {
        super(context, resource, objects);
        contexte = context ;
        lista = objects ;
    }

    @Override
    public View getView(int i, View convertView, ViewGroup parent) {
        convertView = LayoutInflater.from(contexte).inflate(R.layout.custom_adapter, null);

        label = (TextView) convertView.findViewById(R.id.labeltxt);
        date = (TextView) convertView.findViewById(R.id.datetxt);
        time = (TextView) convertView.findViewById(R.id.timetxt);
        detail = (TextView) convertView.findViewById(R.id.detailtxt);

        label.setText(lista.get(i).getLabel());
        date.setText(lista.get(i).getDate());
        time.setText(lista.get(i).getTime());
        detail.setText(lista.get(i).getDetails());



        return convertView ;
    }
}
