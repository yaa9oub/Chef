package com.example.jacob.chef.Bills;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.jacob.chef.R;
import com.example.jacob.chef.Todo.Task;

import java.util.ArrayList;


public class CustomAdapterBills extends ArrayAdapter {

    private TextView des, pt , pu , qtt ;
    private Context contexte ;
    private ArrayList<Fact> lista ;


    public CustomAdapterBills(Context context, int resource, ArrayList<Fact> objects) {
        super(context, resource, objects);
        contexte = context ;
        lista = objects ;
    }

    @Override
    public View getView(int i, View convertView, ViewGroup parent) {
        convertView = LayoutInflater.from(contexte).inflate(R.layout.custom_adapterbills, null);

        des = (TextView) convertView.findViewById(R.id.destxt);
        qtt = (TextView) convertView.findViewById(R.id.qtttxt);
        pu = (TextView) convertView.findViewById(R.id.putxt);
        pt = (TextView) convertView.findViewById(R.id.pttxt);

        des.setText(lista.get(i).getDes());
        qtt.setText(lista.get(i).getQt()+"");
        pu.setText(lista.get(i).getPu()+"");
        pt.setText(lista.get(i).getPt()+"");


        return convertView ;
    }
}
