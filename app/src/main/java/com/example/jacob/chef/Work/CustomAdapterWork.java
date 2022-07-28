package com.example.jacob.chef.Work;

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
import com.example.jacob.chef.Worker.Worker;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class CustomAdapterWork extends ArrayAdapter {
    private Context contexte ;
    private ArrayList<Work> lista ;
    private DbHelper mDatabaseHelper ;
    private TextView label , local , contact , mdo , divers ;

    public CustomAdapterWork(@NonNull Context context, int resource, @NonNull ArrayList<Work> objects) {
        super(context, resource, objects);
        contexte = context ;
        lista = objects ;
    }

    @NonNull
    @Override
    public View getView(final int i , @Nullable View convertView, @NonNull ViewGroup parent) {
        convertView = LayoutInflater.from(contexte).inflate(R.layout.custom_layoutwork, null);

        label = (TextView) convertView.findViewById(R.id.labeltxt);
        local = (TextView) convertView.findViewById(R.id.placetxt);
        contact = (TextView) convertView.findViewById(R.id.contacttxt);
        mdo = (TextView) convertView.findViewById(R.id.mdotxt);
        divers = (TextView) convertView.findViewById(R.id.diverstxt);

        label.setText(lista.get(i).getLabel());
        local.setText(lista.get(i).getPlace());
        contact.setText(lista.get(i).getContact());
        mdo.setText(lista.get(i).getMdo()+"");
        divers.setText(lista.get(i).getDivers()+"");

        //database
        mDatabaseHelper = new DbHelper(contexte);

        return convertView ;
    }
}
