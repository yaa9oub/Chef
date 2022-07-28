package com.example.jacob.chef.Work;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.jacob.chef.DbHelper;
import com.example.jacob.chef.R;
import com.example.jacob.chef.Worker.EditWorker;
import com.example.jacob.chef.Worker.ListWorker;

public class EditWork extends AppCompatActivity {

    private Button btndel , btnchange , btncancel ;
    private EditText place , contact , workers , duration , mdo , divers ;
    private DbHelper mDatabaseHelper ;
    private int id ;
    private String sid ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editwork);

        //buttons
        btnchange = (Button) findViewById(R.id.savebtn2);
        btncancel = (Button) findViewById(R.id.cancelbtn);
        btndel = (Button) findViewById(R.id.deletebtn);
        //edits
        place = (EditText) findViewById(R.id.localedt);
        contact = (EditText) findViewById(R.id.contactedt);
        workers = (EditText) findViewById(R.id.workeredt);
        duration = (EditText) findViewById(R.id.dureedt);
        mdo = (EditText) findViewById(R.id.mdoedt);
        divers = (EditText) findViewById(R.id.diversedt);


        //database
        mDatabaseHelper = new DbHelper(this);

        // Setting worker info
        Intent receivedIntent = getIntent();
        id = receivedIntent.getIntExtra("id",0);

        Cursor data = mDatabaseHelper.selectWork(id);

        while(data.moveToNext())
        {
            place.setText(data.getString(2));
            contact.setText(data.getString(3));
            workers.setText(data.getInt(5)+"");
            duration.setText(data.getInt(4)+"");
            mdo.setText(data.getInt(6)+"");
            divers.setText(data.getInt(7)+"");
        }

        btncancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentreturn = new Intent(EditWork.this, ListWork.class);
                intentreturn.putExtra("page",1);
                startActivity(intentreturn);
            }
        });

        btnchange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String splace = place.getText().toString();
                String scontact = contact.getText().toString();
                String sworkers = workers.getText().toString();
                String sduration = duration.getText().toString();
                String smdo = mdo.getText().toString();
                String sdiver = divers.getText().toString();
                Integer iworkers = Integer.parseInt(sworkers);
                Integer iduration = Integer.parseInt(sduration);
                Integer imdo = Integer.parseInt(smdo);
                Integer idivers = Integer.parseInt(sdiver);

                if(!(splace.isEmpty() && scontact.isEmpty() && sworkers.isEmpty() && sduration.isEmpty() && smdo.isEmpty() && sdiver.isEmpty() ))
                {

                    boolean changerData = mDatabaseHelper.changeWork(id,splace,scontact,iworkers,iduration,imdo,idivers);
                    if (changerData)
                    {
                        Toast.makeText(EditWork.this, "Les paramétres ont étés bien changés !! ", Toast.LENGTH_SHORT).show();
                        Intent intentreturn = new Intent(EditWork.this, ListWork.class);
                        startActivity(intentreturn);
                    }
                }
                else
                { Toast.makeText(EditWork.this, "Remplir les cases svp !!", Toast.LENGTH_SHORT).show();}

            }
        });


    }
}
