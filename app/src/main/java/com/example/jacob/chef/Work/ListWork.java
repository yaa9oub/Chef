package com.example.jacob.chef.Work;

import android.app.Dialog;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.jacob.chef.DbHelper;
import com.example.jacob.chef.MainActivity;
import com.example.jacob.chef.R;
import com.example.jacob.chef.Worker.AddWorker;
import com.example.jacob.chef.Worker.CustomAdapterWorker;
import com.example.jacob.chef.Worker.EditWorker;
import com.example.jacob.chef.Worker.ListWorker;
import com.example.jacob.chef.Worker.Worker;

import java.util.ArrayList;

public class ListWork extends AppCompatActivity {

    private ListView mlistView ;
    private static final String TAG = "ListWork" ;
    private DbHelper mDatabaseHelper ;
    private Button clearbtn , returnbtn , addBtn ;
    public CustomAdapterWork adapter ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listwork);

        clearbtn = (Button) findViewById(R.id.clearBtn);
        returnbtn = (Button) findViewById(R.id.returnBtn);
        addBtn = (Button) findViewById(R.id.addbtn);

        mlistView = (ListView) findViewById(R.id.listView2);
        mDatabaseHelper = new DbHelper(this);
        populateListView();

        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent goingto = new Intent(ListWork.this , AddWork.class );
                startActivity(goingto);
            }
        });

        clearbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean clear = mDatabaseHelper.clearWork();
                if(clear)
                {
                    Toast.makeText(ListWork.this, "Liste vidée !! ", Toast.LENGTH_SHORT).show();
                    adapter.clear();
                }
                else
                    Toast.makeText(ListWork.this, "Erreur ou la liste est deja vide !!", Toast.LENGTH_SHORT).show();
            }
        });

        returnbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentreturn = new Intent(ListWork.this, MainActivity.class);
                intentreturn.putExtra("page",1);
                startActivity(intentreturn);
            }
        });


    }

    private void populateListView() {
        Cursor data = mDatabaseHelper.getWork();
        final ArrayList<Work> listWork = new ArrayList<>();
        while(data.moveToNext())
        {
            //get the value from the database in column 1
            //then add it to the ArrayList
            Work model = new Work();
            model.setId(data.getInt(0));
            model.setLabel(data.getString(1));
            model.setPlace(data.getString(2));
            model.setContact(data.getString(3));
            model.setDuration(data.getInt(4));
            model.setWorker(data.getInt(5));
            model.setMdo(data.getInt(6));
            model.setDivers(data.getInt(7));
            listWork.add(model);
        }

        //create the list adapter and set the adapter
        adapter = new CustomAdapterWork(this, R.layout.custom_layoutwork, listWork);
        mlistView.setAdapter(adapter);

        mlistView.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> adapter, View v, int position,long arg3)
            {
                int id = listWork.get(position).getId();
                Intent editScreenIntent = new Intent(ListWork.this, EditWork.class);
                editScreenIntent.putExtra("id",id);
                startActivity(editScreenIntent);
            }
        });

    }
}
