package com.example.jacob.chef.Worker;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.jacob.chef.DbHelper;
import com.example.jacob.chef.R;

public class EditWorker extends AppCompatActivity {

    private Button btndel , btnchange , btncancel ;
    private EditText name , phone , pdj , paied;
    private RadioGroup dej ;
    private RadioButton dejbtn ;
    private DbHelper mDatabaseHelper ;
    private String cin ;
    private Integer ndj ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editworker);

        //buttons
        btnchange = (Button) findViewById(R.id.savebtn2);
        btncancel = (Button) findViewById(R.id.cancelbtn);
        btndel = (Button) findViewById(R.id.deletebtn);
        //edits
        name = (EditText) findViewById(R.id.nameedt);
        phone = (EditText) findViewById(R.id.phoneedt);
        pdj = (EditText) findViewById(R.id.pdjedt);
        paied = (EditText) findViewById(R.id.payeedt);
        dej = (RadioGroup) findViewById(R.id.dejgrp);


        //database
        mDatabaseHelper = new DbHelper(this);

        // Setting worker info
        Intent receivedIntent = getIntent();
        cin = receivedIntent.getStringExtra("cin");

        Cursor data = mDatabaseHelper.selectWorker(cin);

        while(data.moveToNext())
        {
            name.setText(data.getString(1));
            phone.setText(data.getString(2));
            ndj = data.getInt(3);
            pdj.setText(data.getInt(4)+"");
            paied.setText(data.getInt(6)+"");
        }

        btncancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentreturn = new Intent(EditWorker.this, ListWorker.class);
                intentreturn.putExtra("page",1);
                startActivity(intentreturn);
            }
        });

        btnchange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String sname = name.getText().toString();
                String sphone = phone.getText().toString();
                String spdj = pdj.getText().toString();
                String spaied = paied.getText().toString();
                Integer pdji = Integer.parseInt(spdj);
                Integer paiedi = Integer.parseInt(spaied);

                int dejid = dej.getCheckedRadioButtonId();
                dejbtn = (RadioButton) findViewById(dejid);
                Integer total = 0 ;
                if(dejbtn.getText().equals("Non")) {
                    total = pdji * ndj;
                }
                else
                {
                    total = (pdji + 5) * ndj ;
                }

                if(!(sname.isEmpty() && sphone.isEmpty() && spdj.isEmpty() && spaied.isEmpty()))
                {

                    boolean changerData = mDatabaseHelper.changeWorker(cin,sname,sphone,pdji,paiedi,total);
                    if (changerData)
                    {
                        Toast.makeText(EditWorker.this, "Les paramétres ont étés bien changés !! ", Toast.LENGTH_SHORT).show();
                        Intent intentreturn = new Intent(EditWorker.this, ListWorker.class);
                        startActivity(intentreturn);
                    }
                }
                else
                { Toast.makeText(EditWorker.this, "Remplir les cases svp !!", Toast.LENGTH_SHORT).show();}

            }
        });

        btndel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean deleteWorker = mDatabaseHelper.deleteWorker(cin);
                if (deleteWorker)
                {
                    Toast.makeText(EditWorker.this, "Un ouvriers a eté supprimer !! ", Toast.LENGTH_SHORT).show();
                    Intent intentreturn = new Intent(EditWorker.this, ListWorker.class);
                    startActivity(intentreturn);
                }
                else
                {
                Toast.makeText(EditWorker.this, "Erreur !!", Toast.LENGTH_SHORT).show();}
                }
        });

    }
}
