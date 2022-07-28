package com.example.jacob.chef.Work;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.jacob.chef.DbHelper;
import com.example.jacob.chef.MainActivity;
import com.example.jacob.chef.R;

public class AddWork extends AppCompatActivity {

    private EditText  label ,  place , contact , duration , workers ;
    private Button btnAdd , btnViewData , btnCancel ;
    private DbHelper mDbHelper ;
    private  int duri , workeri ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addwork);

        /* FORMULAIRE */
        label = (EditText) findViewById(R.id.labeledt);
        place = (EditText) findViewById(R.id.placeedt);
        contact = (EditText) findViewById(R.id.contactedt);
        duration = (EditText) findViewById(R.id.durationedt);
        workers = (EditText) findViewById(R.id.workersedt);


        btnAdd = (Button) findViewById(R.id.savebtn);
        btnViewData = (Button) findViewById(R.id.databtn);
        btnCancel = (Button) findViewById(R.id.cancelbtn);

        mDbHelper = new DbHelper(this);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String slabel = label.getText().toString();
                String splace = place.getText().toString();
                String scontact = contact.getText().toString();
                String sduration = duration.getText().toString();
                String sworkers = workers.getText().toString();


                if (slabel.length() != 0 && splace.length() != 0 && scontact.length() != 0 && sduration.length() != 0 && sworkers.length() != 0) {

                    duri = Integer.parseInt(duration.getText().toString());
                    workeri = Integer.parseInt(workers.getText().toString());
                    addWork(slabel,splace,scontact,duri,workeri);
                    label.setText("");
                    contact.setText("");
                    place.setText("");
                    duration.setText("");
                    workers.setText("");

                } else {
                    Toast.makeText(AddWork.this, "Il y 'a des champs à remplir !", Toast.LENGTH_SHORT).show();
                }

            }
        });

        btnViewData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentreturn = new Intent(AddWork.this, ListWork.class);
                startActivity(intentreturn);
            }
        });


        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentreturn = new Intent(AddWork.this, MainActivity.class);
                intentreturn.putExtra("page",1);
                startActivity(intentreturn);
            }
        });

    }

    public void addWork(String label ,String place,String contact , Integer duration, Integer workers) {
        //boolean insertData = mDatabaseHelper.addWorker(cin,name,phone,ndji,pdji,total,0);
        boolean insertData = mDbHelper.addWork(label,place,contact,duration,workers);


        if (insertData) {
            Toast.makeText(this, "Un nouveau travail a été ajouté!", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Erreur !!", Toast.LENGTH_SHORT).show();
        }

    }

}
