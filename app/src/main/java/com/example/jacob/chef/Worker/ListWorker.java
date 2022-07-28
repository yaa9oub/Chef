package com.example.jacob.chef.Worker;

import android.app.Dialog;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jacob.chef.DbHelper;
import com.example.jacob.chef.MainActivity;
import com.example.jacob.chef.R;

import java.util.ArrayList;
public class ListWorker extends AppCompatActivity {

    private ListView mlistView ;
    private static final String TAG = "ListWorker" ;
    private Dialog profile ;
    private DbHelper mDatabaseHelper ;
    public  CustomAdapterWorker adapter ;
    private Button clearbtn , returnbtn , addBtn , days ;
    private TextView cin , name , phone , ndj , pdj , total , paied  ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listworker);

        clearbtn = (Button) findViewById(R.id.clearBtn);
        returnbtn = (Button) findViewById(R.id.returnBtn);
        addBtn = (Button) findViewById(R.id.addbtn);

        mlistView = (ListView) findViewById(R.id.listView);
        mDatabaseHelper = new DbHelper(this);
        profile = new Dialog(this);
        populateListView();

        adapter.notifyDataSetChanged();

        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent goingto = new Intent(ListWorker.this , AddWorker.class );
                startActivity(goingto);
            }
        });

        clearbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                boolean clear = mDatabaseHelper.clearWorker();
                mDatabaseHelper.clearDate();
                if(clear)
                {
                    Toast.makeText(ListWorker.this, "Liste vidée !! ", Toast.LENGTH_SHORT).show();
                    adapter.clear();
                }
                else
                    Toast.makeText(ListWorker.this, "Erreur ou la liste est deja vide !!", Toast.LENGTH_SHORT).show();
            }
        });

        returnbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentreturn = new Intent(ListWorker.this, MainActivity.class);
                intentreturn.putExtra("page",1);
                startActivity(intentreturn);
            }
        });
    }

    private void populateListView() {
        Log.d(TAG, "populateListView: Displaying data in the ListView.");

        //get the data append to the list
        Cursor data = mDatabaseHelper.getWorker();
        final ArrayList<Worker> listData = new ArrayList<>();
        while(data.moveToNext())
        {
            //get the value from the database in column 1
            //then add it to the ArrayList
            Worker model = new Worker();
            model.setCin(data.getInt(0)+"");
            model.setName(data.getString(1));
            model.setPhone(data.getString(2));
            model.setNdj(data.getInt(3));
            model.setPdj(data.getInt(4));
            model.setTotal(data.getInt(5));
            model.setPaied(data.getInt(6));
            listData.add(model);
        }

        //create the list adapter and set the adapter
        adapter = new CustomAdapterWorker(this, R.layout.custom_layoutworker, listData);
        mlistView.setAdapter(adapter);


        //ITEM ON CLICK
        mlistView.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> adapter, View v, int position,long arg3)
            {
                String cin = listData.get(position).getCin();
                Intent editScreenIntent = new Intent(ListWorker.this, EditWorker.class);
                editScreenIntent.putExtra("cin",cin);
                startActivity(editScreenIntent);
            }
        });

        mlistView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                String scin = listData.get(i).getCin();
                Cursor prof = mDatabaseHelper.getWorker(scin);

                showDialog();

                cin = (TextView) profile.findViewById(R.id.cintxt);
                name = (TextView) profile.findViewById(R.id.nametxt);
                phone = (TextView) profile.findViewById(R.id.phonetxt);
                ndj = (TextView) profile.findViewById(R.id.ndjtxt);
                pdj = (TextView) profile.findViewById(R.id.pdjtxt);
                paied = (TextView) profile.findViewById(R.id.paiedtxt);
                total = (TextView) profile.findViewById(R.id.totaltxt);

                cin.setText(listData.get(i).getCin());
                name.setText(listData.get(i).getName());
                phone.setText(listData.get(i).getPhone());
                paied.setText(listData.get(i).getPaied().toString());
                pdj.setText(listData.get(i).getPdj().toString());
                ndj.setText(listData.get(i).getNdj().toString());
                total.setText(listData.get(i).getTotal().toString());

                return true ;
            }
        });

    }

    private void showDialog() {
        profile.setContentView(R.layout.dialog_profile);

        cin = (TextView) profile.findViewById(R.id.cintxt);
        name = (TextView) profile.findViewById(R.id.nametxt);
        phone = (TextView) profile.findViewById(R.id.phonetxt);
        ndj = (TextView) profile.findViewById(R.id.ndjtxt);
        pdj = (TextView) profile.findViewById(R.id.pdjtxt);
        paied = (TextView) profile.findViewById(R.id.paiedtxt);
        total = (TextView) profile.findViewById(R.id.totaltxt);
        days = (Button) profile.findViewById(R.id.daysBtn);

        days.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(ListWorker.this,WorkerDaysList.class );
                intent.putExtra("cin", cin.getText().toString());
                startActivity(intent);
            }
        });

        profile.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        profile.show();
    }
}
