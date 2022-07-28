package com.example.jacob.chef.Worker;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;
import java.util.Calendar;

import com.example.jacob.chef.DbHelper;
import com.example.jacob.chef.R;

import java.util.ArrayList;
import java.util.Date;

public class CustomAdapterWorker extends ArrayAdapter {
    private ArrayList<Worker> lista ;
    private Context contexte ;
    private TextView name , ndj , pdj , total , plus , paied ;
    private Button add , cancel ;
    private Dialog addDay ;
    private EditText endj , epdj , ehd , ehf , cemtr;
    private RadioButton dejbtn ;
    private DbHelper mDatabaseHelper ;
    private Integer hd , hf ;

    public CustomAdapterWorker(@NonNull Context context, int resource, @NonNull ArrayList<Worker> objects) {
        super(context, resource, objects);
        contexte = context ;
        lista = objects ;
    }

    @NonNull
    @Override
    public View getView(final int i , @Nullable View convertView, @NonNull ViewGroup parent) {
        convertView = LayoutInflater.from(contexte).inflate(R.layout.custom_layoutworker, null);

        name = (TextView) convertView.findViewById(R.id.nametxt);
        ndj = (TextView) convertView.findViewById(R.id.ndjtxt);
        pdj = (TextView) convertView.findViewById(R.id.pdjtxt);
        total = (TextView) convertView.findViewById(R.id.totaltxt);
        paied = (TextView) convertView.findViewById(R.id.payetxt);
        plus = (TextView) convertView.findViewById(R.id.plustxt);
        addDay = new Dialog(contexte);

        name.setText(lista.get(i).getName());
        ndj.setText(lista.get(i).getNdj().toString());
        pdj.setText(lista.get(i).getPdj().toString());
        paied.setText(lista.get(i).getPaied().toString());
        total.setText(lista.get(i).getTotal().toString());

        //database
        mDatabaseHelper = new DbHelper(contexte);

        plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog(lista.get(i).getCin().toString(),lista.get(i).getNdj().toString(),lista.get(i).getPdj().toString());
            }
        });


        return convertView ;
    }

    private void showDialog(final String cin , String ndj , final String pdj) {
        addDay.setContentView(R.layout.dialog_dayadd);

        ehd = (EditText) addDay.findViewById(R.id.hdedt);
        ehf = (EditText) addDay.findViewById(R.id.hfedt);
        cemtr = (EditText) addDay.findViewById(R.id.detailsedt);
        endj = (EditText) addDay.findViewById(R.id.ndjedt);
        epdj = (EditText) addDay.findViewById(R.id.pdjedt);
        dejbtn = (RadioButton) addDay.findViewById(R.id.Nonbtn);

        epdj.setText(pdj);
        final int ondj = Integer.parseInt(ndj);

        add = (Button) addDay.findViewById(R.id.addBtn);
        cancel = (Button) addDay.findViewById(R.id.cancelBtn);


        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                int salairy ;
                int ndji = Integer.parseInt(endj.getText().toString());
                int pdji = Integer.parseInt(epdj.getText().toString());
                int dej ;
                final String cmtr = cemtr.getText().toString();
                String date ="" ;

                String shd = ehd.getText().toString();
                String shf = ehf.getText().toString();
                if(shd.isEmpty() || shf.isEmpty())
                {
                    hd = hf = 0 ;
                }
                else
                {
                    hd = Integer.parseInt(shd);
                    hf = Integer.parseInt(shf);
                }

                if(dejbtn.isChecked()) {
                    salairy = pdji * ndji ;
                    dej = 0 ;
                }
                else
                {
                    salairy = (pdji + 5) * ndji ;
                    dej = 0 ;
                }


                Date currentTime = Calendar.getInstance().getTime();
                date = currentTime.toString().substring(0,10);

                boolean changerData = mDatabaseHelper.addDay(cin,ndji+ondj,salairy);
                boolean addDays = mDatabaseHelper.Daily(date,cin,ndji ,pdji,hd,hf,cmtr,dej);

                if (changerData && addDays)
                {
                    Toast.makeText(contexte, "Une journée a été ajoutée !", Toast.LENGTH_SHORT).show();
                    Intent intentreturn = new Intent(contexte, ListWorker.class);
                    contexte.startActivity(intentreturn);
                }
                addDay.dismiss();
            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addDay.dismiss();
            }
        });

        addDay.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        addDay.show();
    }
}
