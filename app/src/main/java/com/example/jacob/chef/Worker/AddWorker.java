package com.example.jacob.chef.Worker;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.jacob.chef.DbHelper;
import com.example.jacob.chef.MainActivity;
import com.example.jacob.chef.R;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class AddWorker extends AppCompatActivity {

    private Button btnAdd , btnViewData , btnCancel ;
    private EditText cin , name , phone , ndj , pdj , hd , hf , cmtr  ;
    private RadioGroup dej ;
    private RadioButton dejbtn ;
    //com.example.jacob.chef.DatabaseHelper mDatabaseHelper ;
    DbHelper mDbHelper ;
    private ArrayList<Worker> object = new ArrayList<>();
    private int ndji = 0 , pdji = 0 , total = 0 ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addworker);

        /* FORMULAIRE */
        cin = (EditText) findViewById(R.id.cinedt);
        name = (EditText) findViewById(R.id.nameedt);
        phone = (EditText) findViewById(R.id.phoneedt);
        ndj = (EditText) findViewById(R.id.ndjedt);
        pdj = (EditText) findViewById(R.id.pdjedt);
        dej = (RadioGroup) findViewById(R.id.dejgrp);
        hd = (EditText) findViewById(R.id.hdedt);
        hf = (EditText) findViewById(R.id.hfedt);
        cmtr = (EditText) findViewById(R.id.detailsedt);

        btnAdd = (Button) findViewById(R.id.savebtn);
        btnViewData = (Button) findViewById(R.id.databtn);
        btnCancel = (Button) findViewById(R.id.cancelbtn);
        /* END. */

        mDbHelper = new DbHelper(this);
        //mDatabaseHelper = new com.example.jacob.chef.DatabaseHelper(this);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String scin = cin.getText().toString();
                String sname = name.getText().toString();
                String sphone = phone.getText().toString();
                String scmtr = cmtr.getText().toString();
                String shd = hd.getText().toString();
                String shf = hf.getText().toString();
                int dejid = dej.getCheckedRadioButtonId();
                dejbtn = (RadioButton) findViewById(dejid);

                if (sname.length() != 0 && scin.length() != 0 && sphone.length() != 0 && ndj.getText().toString().length() != 0
                    && pdj.getText().toString().length() != 0 && !scmtr.isEmpty() && !shd.isEmpty() && !shf.isEmpty() ) {

                    int hdi = Integer.parseInt(shd);
                    int hfi = Integer.parseInt(shf);

                    int deji ;
                    ndji = Integer.parseInt(ndj.getText().toString());
                    pdji = Integer.parseInt(pdj.getText().toString());
                    if(dejbtn.getText().equals("Non")) {
                        total = pdji * ndji;
                        deji = 0 ;
                    }
                    else
                    {
                        total = (pdji + 5) * ndji ;
                        deji = 1 ;
                    }
                    AddWorker(scin,sname,sphone,ndji,pdji,total,deji,scmtr,hdi,hfi);
                    cin.setText("");
                    phone.setText("");
                    name.setText("");
                    ndj.setText("1");
                    pdj.setText("");
                    hf.setText("");
                    hd.setText("");
                    cmtr.setText("");

                } else {
                    printf("Il y 'a des champs à remplir !");
                }

            }
        });

        btnViewData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AddWorker.this, ListWorker.class);
                intent.putParcelableArrayListExtra("list",object);
                startActivity(intent);
            }
        });


        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentreturn = new Intent(AddWorker.this, MainActivity.class);
                intentreturn.putExtra("page",1);
                startActivity(intentreturn);
            }
        });

    }

    public void AddWorker(String cin ,String name,String phone , Integer ndji, Integer pdji, Integer total,Integer dej,String cmtr,Integer hdi,Integer hfi) {
        //boolean insertData = mDatabaseHelper.addWorker(cin,name,phone,ndji,pdji,total,0);
        Date currentTime = Calendar.getInstance().getTime();
        String date = currentTime.toString().substring(0,10);

        boolean insertData = mDbHelper.addWorker(cin,name,phone,ndji,pdji,total,0);
        boolean addDays = mDbHelper.Daily(date,cin,ndji ,pdji,hdi,hfi,cmtr,dej);


        if (insertData && addDays) {
            printf("Un nouveau ouvrier a été ajouté!");
        } else {
            printf("Erreur !!");
        }

    }


    private void printf(String message)
    {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
