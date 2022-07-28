package com.example.jacob.chef.Worker;

import android.app.Dialog;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.example.jacob.chef.DbHelper;
import com.example.jacob.chef.R;

import java.util.ArrayList;

public class WorkerDaysList extends AppCompatActivity {

    private ListView mlistView ;
    private DbHelper mDatabaseHelper ;
    public CustomAdapterDays adapter ;
    private Button clearBtn , returnBtn ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workerdayslist);

        Intent intent = getIntent();
        String cin = intent.getStringExtra("cin");

        clearBtn = (Button) findViewById(R.id.clearBtn);
        returnBtn = (Button) findViewById(R.id.returnBtn);
        mlistView = (ListView) findViewById(R.id.listView3);
        mDatabaseHelper = new DbHelper(this);
        populateListView(cin);

        clearBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mDatabaseHelper.clearDate();
                Intent intent = new Intent(WorkerDaysList.this,ListWorker.class);
                startActivity(intent);
            }
        });

        returnBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(WorkerDaysList.this,ListWorker.class);
                startActivity(intent);
            }
        });
    }


    private void populateListView(String cin) {

        //get the data append to the list
        Cursor data = mDatabaseHelper.getDays(cin);
        final ArrayList<Day> listData = new ArrayList<>();
        while(data.moveToNext())
        {
            Day model = new Day();
            model.setDate(data.getString(1));
            model.setCin(data.getString(2));
            model.setNdj(data.getInt(3));
            model.setPdj(data.getInt(4));
            model.setHd(data.getInt(5));
            model.setHf(data.getInt(6));
            model.setCmtr(data.getString(7));
            listData.add(model);
        }

        adapter = new CustomAdapterDays(this, R.layout.custom_layoutdays, listData);
        mlistView.setAdapter(adapter);

    }

}
