package com.example.jacob.chef.Bills;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;
import com.example.jacob.chef.DbHelper;
import com.example.jacob.chef.R;

import java.util.ArrayList;

public class Bills extends AppCompatActivity {

    private ListView lista ;
    private EditText qtt , des , pu , pt ;
    private Button add , cancel , clear ;
    private Dialog facts ;
    private DbHelper dbHelper ;
    private CustomAdapterBills adapter ;

    @SuppressLint("RestrictedApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bills);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        lista = (ListView) findViewById(R.id.ContentList);
        facts = new Dialog(this);
        dbHelper = new DbHelper(this);
        clear = (Button) findViewById(R.id.clearBtn);

        populateListView();

        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean clear = dbHelper.clearFact();
                if(clear)
                {
                    Toast.makeText(Bills.this, "Liste vidée !! ", Toast.LENGTH_SHORT).show();
                    adapter.clear();
                }
                else
                    Toast.makeText(Bills.this, "Erreur ou la liste est deja vide !!", Toast.LENGTH_SHORT).show();

                finish();
                startActivity(getIntent());
            }
        });

        final FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setVisibility(View.INVISIBLE);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //dbHelper.upgrade();
                showDialog();
            }
        });

        fab.startAnimation(AnimationUtils.loadAnimation(this,R.anim.splash_in));

    }

    private void showDialog() {
        facts.setContentView(R.layout.dialog_addbill);

        qtt = (EditText) facts.findViewById(R.id.qttedt);
        des = (EditText) facts.findViewById(R.id.desedt);
        pu = (EditText) facts.findViewById(R.id.puedt);
        pt = (EditText) facts.findViewById(R.id.ptedt);

        add = (Button) facts.findViewById(R.id.addBtn);
        cancel = (Button) facts.findViewById(R.id.cancelBtn);

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                facts.dismiss();
            }
        });

        pt.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {

                if(!( qtt.getText().toString().isEmpty() || pu.getText().toString().isEmpty())){
                    String sqtt = qtt.getText().toString();
                    String spu = pu.getText().toString();
                    int qtti = Integer.parseInt(sqtt);
                    int pui = Integer.parseInt(spu);
                    pt.setText(qtti*pui+"");
                }
            }
        });


        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String sdes = des.getText().toString() ;
                String sqtt = qtt.getText().toString();
                String spu = pu.getText().toString();
                String spt = pt.getText().toString();
                if(!(sdes.isEmpty() || sqtt.isEmpty() || spu.isEmpty() || spt.isEmpty()))
                {
                    int qtti = Integer.parseInt(sqtt);
                    int pui = Integer.parseInt(spu);
                    int pti = Integer.parseInt(spt);
                    addFact(sdes,qtti,pui,pti);
                }


                facts.dismiss();
                finish();
                startActivity(getIntent());
            }
        });

        facts.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        facts.show();
    }

    private void addFact(String des,int qtt,int pu,int pt) {

        boolean insertData = dbHelper.addFact(des,qtt,pu,pt);

        if (insertData) {
            Toast.makeText(this, "Facture a été ajoutée!", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Erreur !!", Toast.LENGTH_SHORT).show();
        }

    }

    private void populateListView() {
        Cursor data = dbHelper.getFact();
        final ArrayList<Fact> listWork = new ArrayList<>();
        while(data.moveToNext())
        {
            //get the value from the database in column 1
            //then add it to the ArrayList
            Fact model = new Fact();
            model.setId(data.getInt(0));
            model.setDes(data.getString(1));
            model.setQt(data.getInt(2));
            model.setPu(data.getInt(3));
            model.setPt(data.getInt(4));
            listWork.add(model);
        }

        //create the list adapter and set the adapter
        adapter = new CustomAdapterBills(this, R.layout.custom_adapterbills, listWork);
        lista.setAdapter(adapter);
    }

}
