package com.example.jacob.chef.Worker;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.jacob.chef.DbHelper;
import com.example.jacob.chef.R;

import java.util.ArrayList;

public class CustomAdapterDays  extends ArrayAdapter {
    private ArrayList<Day> lista ;
    private Context contexte ;
    private TextView date, ndj , pdj , hd , hf ,cmtr ;
    private DbHelper mDatabaseHelper ;



    public CustomAdapterDays(@NonNull Context context, int resource, @NonNull ArrayList<Day> objects) {
        super(context, resource, objects);
        contexte = context ;
        lista = objects ;
    }

    @NonNull
    @Override
    public View getView(final int i , @Nullable View convertView, @NonNull ViewGroup parent) {
        convertView = LayoutInflater.from(contexte).inflate(R.layout.custom_layoutdays, null);

        date = (TextView) convertView.findViewById(R.id.datetxt);
        ndj = (TextView) convertView.findViewById(R.id.ndjtxt);
        pdj = (TextView) convertView.findViewById(R.id.pdjtxt);
        hd = (TextView) convertView.findViewById(R.id.hdtxt);
        hf = (TextView) convertView.findViewById(R.id.hftxt);
        cmtr = (TextView) convertView.findViewById(R.id.cmtrtxt);

        date.setText(lista.get(i).getDate());
        ndj.setText(lista.get(i).getNdj().toString());
        pdj.setText(lista.get(i).getPdj().toString());
        hd.setText(lista.get(i).getHd().toString()+" AM");
        hf.setText(lista.get(i).getHf().toString()+" PM");
        cmtr.setText(lista.get(i).getCmtr());

        //database
        mDatabaseHelper = new DbHelper(contexte);

        return convertView ;
    }


}
