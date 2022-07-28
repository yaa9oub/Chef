package com.example.jacob.chef;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.jacob.chef.Bills.Bills;
import com.example.jacob.chef.Todo.Todo;
import com.example.jacob.chef.Work.AddWork;
import com.example.jacob.chef.Work.ListWork;
import com.example.jacob.chef.Worker.AddWorker;
import com.example.jacob.chef.Worker.ListWorker;

public class MainActivity extends AppCompatActivity {

    LinearLayout worker , work , listworker , listwork , todo , bill ;
    private final int SPLASH_DISPLAY_LENGTH = 1500;
    private int page = 0 ;
    DbHelper mdbHelper ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        worker = (LinearLayout) findViewById(R.id.worker_btn);
        work = (LinearLayout) findViewById(R.id.work_btn);
        listworker = (LinearLayout) findViewById(R.id.workerlist_btn);
        listwork = (LinearLayout) findViewById(R.id.worklistbtn);
        todo = (LinearLayout) findViewById(R.id.Commandbtn);
        bill = (LinearLayout) findViewById(R.id.bill_btn);

        mdbHelper = new DbHelper(this);
        mdbHelper.onInit();

        final Animation animation = AnimationUtils.loadAnimation(this,R.anim.splash_in);

        /*Splash Screen */
        Intent intent = getIntent();
        page = intent.getIntExtra("page",0);
        if (page==0) {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {


                    Toast.makeText(MainActivity.this, "This app belongs to Jacob", Toast.LENGTH_SHORT).show();

                    worker.setAnimation(animation);
                    worker.setVisibility(View.VISIBLE);

                    work.setAnimation(animation);
                    work.setVisibility(View.VISIBLE);

                    listwork.setAnimation(animation);
                    listwork.setVisibility(View.VISIBLE);

                    listworker.setAnimation(animation);
                    listworker.setVisibility(View.VISIBLE);

                    bill.setAnimation(animation);
                    bill.setVisibility(View.VISIBLE);

                    todo.setAnimation(animation);
                    todo.setVisibility(View.VISIBLE);



                }
            }, SPLASH_DISPLAY_LENGTH);
        }
        else
        {
            worker.setVisibility(View.VISIBLE);
            work.setVisibility(View.VISIBLE);
            listwork.setVisibility(View.VISIBLE);
            listworker.setVisibility(View.VISIBLE);
            bill.setVisibility(View.VISIBLE);
            todo.setVisibility(View.VISIBLE);
        }

        worker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent goingto = new Intent(MainActivity.this , AddWorker.class );
                startActivity(goingto);
            }
        });

        listworker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent goingto = new Intent(MainActivity.this , ListWorker.class );
                startActivity(goingto);
            }
        });

        work.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent goingto = new Intent(MainActivity.this , AddWork.class );
                startActivity(goingto);
            }
        });

        listwork.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent goingto = new Intent(MainActivity.this , ListWork.class );
                startActivity(goingto);
            }
        });

        todo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent goingto = new Intent(MainActivity.this , Todo.class );
                startActivity(goingto);
            }
        });

        bill.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent goingto = new Intent(MainActivity.this , Bills.class );
                startActivity(goingto);
            }
        });

    }

    public void animation(){
        final Animation animation2 = AnimationUtils.loadAnimation(this,R.anim.splash_out);
        worker.setAnimation(animation2);
        work.setAnimation(animation2);
        listwork.setAnimation(animation2);
        listworker.setAnimation(animation2);
        bill.setAnimation(animation2);
        todo.setAnimation(animation2);
    }
}
