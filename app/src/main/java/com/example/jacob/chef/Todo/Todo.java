package com.example.jacob.chef.Todo;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.icu.text.SimpleDateFormat;
import android.os.Build;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.Toast;
import com.example.jacob.chef.DbHelper;
import com.example.jacob.chef.R;
import com.example.jacob.chef.Work.CustomAdapterWork;

import java.util.ArrayList;
import java.util.Date;

public class Todo extends AppCompatActivity implements TimePickerDialog.OnTimeSetListener {

    private ListView lista ;
    private Dialog tasks , calendrier ;
    private ImageView mic , calendar , time  ;
    private EditText label , dateedt , timeedt , detail ;
    private int rev ;
    private RadioButton nonBtn ;
    private CalendarView calendarView ;
    private Button date , add , cancel , clear ;
    private static final  int REQUEST_CODE_SPEECH_INPUT  = 1000 ;
    private DbHelper dbHelper ;
    private CustomAdapter adapter ;


    @SuppressLint("RestrictedApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todo);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        lista = (ListView) findViewById(R.id.ContentList);
        clear = (Button) findViewById(R.id.clearBtn);


        tasks = new Dialog(this);
        calendrier = new Dialog(this);
        dbHelper = new DbHelper(this);

        populateListView();

        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean clear = dbHelper.clearTask();
                if(clear)
                {
                    Toast.makeText(Todo.this, "Liste vidée !! ", Toast.LENGTH_SHORT).show();
                    adapter.clear();
                }
                else
                    Toast.makeText(Todo.this, "Erreur ou la liste est deja vide !!", Toast.LENGTH_SHORT).show();

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
        tasks.setContentView(R.layout.dialog_addtask);

        /*form*/

        label = (EditText) tasks.findViewById(R.id.labeledt);
        dateedt = (EditText) tasks.findViewById(R.id.dateedt);
        timeedt = (EditText) tasks.findViewById(R.id.timeedt);
        detail = (EditText) tasks.findViewById(R.id.detailsedt);



        /*Button*/

        mic = (ImageView) tasks.findViewById(R.id.micbtn);
        calendar = (ImageView) tasks.findViewById(R.id.cal_btn);
        time = (ImageView) tasks.findViewById(R.id.timebtn);
        add = (Button) tasks.findViewById(R.id.addBtn);
        cancel = (Button) tasks.findViewById(R.id.cancelBtn);
        nonBtn = (RadioButton) tasks.findViewById(R.id.Nonbtn);

        mic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                speak();
            }
        });

        calendar.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View view) {
                mark();
            }
        });

        time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                temps();
            }
        });

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String slabel = label.getText().toString() ;
                String sdate = dateedt.getText().toString();
                String stime = timeedt.getText().toString();
                String sdetail = detail.getText().toString();

                if(!(slabel.isEmpty() && sdate.isEmpty() && stime.isEmpty() && sdetail.isEmpty()))
                {
                    if(nonBtn.isChecked())
                    {
                        rev = 0 ;
                    }
                    else
                    {
                        rev = 1 ;
                    }
                    addTask(slabel,sdate,stime,sdetail,rev);
                }


                tasks.dismiss();
                finish();
                startActivity(getIntent());
            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tasks.dismiss();
            }
        });


        tasks.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        tasks.show();
    }

    private void temps() {
        DialogFragment timepicker = new TimePicker();
        timepicker.show(getSupportFragmentManager(),"");
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void mark() {
        calendrier.setContentView(R.layout.dialog_calendar);

        calendarView = (CalendarView) calendrier.findViewById(R.id.calendarView);
        date = (Button) calendrier.findViewById(R.id.saveBtn);

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        String selectedDate = sdf.format(new Date(calendarView.getDate()));

        final String[] sdate = {""+selectedDate};

        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView calendarView, int i, int i1, int i2) {
                sdate[0] = (i1+1)+"/"+i2+"/"+i;
                Toast.makeText(Todo.this, sdate[0]+"", Toast.LENGTH_SHORT).show();
            }
        });

        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dateedt.setText(sdate[0]);
                calendrier.dismiss();
            }
        });



        calendrier.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        calendrier.show();
    }

    private void speak() {

        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, "ar-TN");
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, "fr-FR");
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT,"Dire quelque chose !!");

        try {
            startActivityForResult(intent,  REQUEST_CODE_SPEECH_INPUT);
        }
        catch (Exception e)
        {
            Toast.makeText(this, e.getMessage()+"", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode){
            case REQUEST_CODE_SPEECH_INPUT : {
                if (resultCode == RESULT_OK && null != data){
                    ArrayList<String> result = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    label.setText(result.get(0));
                }
                break;
            }
        }
    }


    @Override
    public void onTimeSet(android.widget.TimePicker timePicker, int i, int i1) {
        if(i>9 && i1>9)
        {
            timeedt.setText(i+":"+i1);
        }
        else if (i<9 && i1<9)
        {
            timeedt.setText("0"+i+":"+i1+"0");
        }
        else if (i<9)
        {
            timeedt.setText("0"+i+":"+i1);
        }
        else if (i1<9)
        {
            timeedt.setText(i+":"+i1+"0");
        }
    }

    private void addTask(String label, String date, String time, String detail, int rev) {

        boolean insertData = dbHelper.addTask(label,date,time,detail,rev);

        if (insertData) {
            Toast.makeText(this, "Tache a été ajoutée!", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Erreur !!", Toast.LENGTH_SHORT).show();
        }

    }

    private void populateListView() {
        Cursor data = dbHelper.getTask();
        final ArrayList<Task> listWork = new ArrayList<>();
        while(data.moveToNext())
        {
            //get the value from the database in column 1
            //then add it to the ArrayList
            Task model = new Task();
            model.setRang(data.getInt(0));
            model.setLabel(data.getString(1));
            model.setDate(data.getString(2));
            model.setTime(data.getString(3));
            model.setDetails(data.getString(4));
            listWork.add(model);
        }

        //create the list adapter and set the adapter
        adapter = new CustomAdapter(this, R.layout.custom_adapter, listWork);
        lista.setAdapter(adapter);
    }


}
