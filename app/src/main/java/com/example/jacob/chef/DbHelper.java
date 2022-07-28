package com.example.jacob.chef;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DbHelper extends SQLiteOpenHelper {

    private static final String dbname ="chef";
    private static final int dbversion = 1;

    public DbHelper(Context context)
    {
        super(context,dbname,null,dbversion);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE WORKER (CIN VARCHAR(8) PRIMARY KEY, NAME VARCHAR(10),PHONE VARCHAR(8), NDJ INTEGER , PDJ INTEGER , TOTAL INTEGER , PAIED INTEGR );";
        String query2 = "CREATE TABLE WORKS (ID INTEGER PRIMARY KEY AUTOINCREMENT, LABEL VARCHAR(10) , PLACE VARCHAR(10),CONTACT VARCHAR(8), DURATION INTEGER , WORKERS INTEGER , MDO INTEGER , DIVERS INTEGR );";
        String query3 = "CREATE TABLE DAYS (ID INTEGER PRIMARY KEY AUTOINCREMENT , DATE VARCHAR(10) , CIN VARCHAR(8)  , NDJ INTEGER ,PDJ INTEGER , HD INTEGER , HF INTEGER , CMTR VARCHAR(20), DEJ INTEGER );";
        String query4 = "CREATE TABLE TASK (ID INTEGER PRIMARY KEY AUTOINCREMENT, LABEL VARCHAR(10) , DATE VARCHAR(10),TIME varchar(5) , DETAILS VARCHAR(20), REV INTEGR );";
        String query5 = "CREATE TABLE FACT (ID INTEGER PRIMARY KEY AUTOINCREMENT, DES VARCHAR(20) , QTT INTEGER,PU INTEGER , PT INTEGER);";
        db.execSQL(query);
        db.execSQL(query2);
        db.execSQL(query3);
        db.execSQL(query4);
        db.execSQL(query5);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        String query = "DROP TABLE IF EXISTS WORKER";
        String query2 = "DROP TABLE IF EXISTS WORKS";
        String query3 = "DROP TABLE IF EXISTS DAYS";
        String query4 = "DROP TABLE IF EXISTS TASK";
        String query5 = "DROP TABLE IF EXISTS FACT";
        db.execSQL(query);
        db.execSQL(query2);
        db.execSQL(query3);
        db.execSQL(query4);
        db.execSQL(query5);

        onCreate(db);
    }


    /* Worker */

    public boolean addWorker(String cin, String name , String phone , Integer ndj,Integer pdj,Integer total , Integer paied) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor result = db.rawQuery("INSERT INTO WORKER VALUES ('"+cin+"','"+name+"','"+phone+"',"+ndj+","+pdj+","+total+","+paied+")",null);

        if (result.getCount() == -1) {
            return false;
        } else {
            return true;
        }
    }

    public Cursor getWorker(){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM WORKER" ;
        Cursor data = db.rawQuery(query,null);
        return  data ;
    }

    public Cursor selectWorker(String cin){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM WORKER WHERE CIN = '" + cin + "'" ;
        Cursor data = db.rawQuery(query,null);
        return  data ;
    }

    public boolean clearWorker(){
        SQLiteDatabase db = this.getWritableDatabase();

        String query = "SELECT  *  FROM WORKER ";
        Cursor data = db.rawQuery(query, null);
        data.moveToFirst();
        int icount = data.getCount();
        if(icount > 0)
        {
            String query2 = "DELETE FROM WORKER";
            Cursor data2 = db.rawQuery(query2, null);
            db.execSQL("DROP TABLE IF EXISTS WORKER");
            createWorker();
            if (data2.getCount() == -1) {
                return false;
            } else {
                return true;
            }
        }
        else
        {
            return false ;
        }

    }

    public boolean clearDate(){
        SQLiteDatabase db = this.getWritableDatabase();

        String query = "SELECT  *  FROM DAYS ";
        Cursor data = db.rawQuery(query, null);
        data.moveToFirst();
        int icount = data.getCount();
        if(icount > 0)
        {
            String query2 = "DELETE FROM DAYS";
            Cursor data2 = db.rawQuery(query2, null);
            db.execSQL("DROP TABLE IF EXISTS DAYS");
            createDays();
            if (data2.getCount() == -1) {
                return false;
            } else {
                return true;
            }
        }
        else
        {
            return false ;
        }

    }

    public Cursor getWorker(String cin ){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT  NAME  FROM WORKER WHERE CIN = '" + cin + "'";
        Cursor data = db.rawQuery(query, null);
        return data;
    }

    public boolean deleteWorker(String cin){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "DELETE FROM WORKER WHERE CIN = '"+cin+"' ";
        Cursor data = db.rawQuery(query, null);
        if (data.getCount() == -1) {
            return false;
        } else {
            return true;
        }
    }

    public boolean changeWorker(String cin,String name,String phone,int pdj , int paied , int total){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "UPDATE WORKER SET NAME = '"+name+"' , PHONE = '"+phone+"' , PDJ ="+pdj+" , PAIED = "+paied+" , TOTAL = "+total+" WHERE CIN = '"+cin+"' ";
        Cursor data = db.rawQuery(query, null);
        if (data.getCount() == -1) {
            return false;
        } else {
            return true;
        }
    }

    public boolean addDay(String cin,int ndj , int total){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "UPDATE WORKER SET NDJ ="+ndj+" , TOTAL = TOTAL + "+total+" WHERE CIN = '"+cin+"' ";
        Cursor data = db.rawQuery(query, null);
        if (data.getCount() == -1) {
            return false;
        } else {
            return true;
        }
    }



    /* WORK */
    public boolean addWork(String label, String place , String contact , Integer duration ,Integer workers) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor result = db.rawQuery("INSERT INTO WORKS VALUES (NULL ,'"+label+"','"+place+"','"+contact+"',"+duration+","+workers+","+0+","+0+")",null);

        if (result.getCount() == -1) {
            return false;
        } else {
            return true;
        }
    }

    public Cursor getWork(){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT  *  FROM WORKS ";
        Cursor data = db.rawQuery(query, null);
        return data;
    }

    public boolean clearWork(){
        SQLiteDatabase db = this.getWritableDatabase();

        String query = "SELECT  *  FROM WORKS ";
        Cursor data = db.rawQuery(query, null);
        data.moveToFirst();
        int icount = data.getCount();
        if(icount>0) {

        String query2 = "DELETE FROM WORKS";
            Cursor data2 = db.rawQuery(query2, null);
            db.execSQL("DROP TABLE IF EXISTS WORKS");
            createWork();
            if (data2.getCount() == -1) {
                return false;
            } else {
                return true;
            }
        }
        else
        {
            return false ;
        }

        
    }


    public Cursor selectWork(Integer id){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM WORK WHERE ID = " + id + "" ;
        Cursor data = db.rawQuery(query,null);
        return  data ;
    }

    public boolean changeWork(Integer id ,String place,String contact ,int worker , int duration , int mdo , int divers ){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "UPDATE WORK SET PLACE = '"+place+"' , CONTACT = '"+contact+"' , WORKERS ="+worker+" , DURATION = "+duration+" , MDO = "+mdo+" , DIVERS = "+divers+" WHERE ID = '"+id+"' ";
        Cursor data = db.rawQuery(query, null);
        if (data.getCount() == -1) {
            return false;
        } else {
            return true;
        }
    }


    /* DAY */

    public boolean Daily(String date,String cin, Integer ndj , Integer pdj  , Integer hd ,Integer hf , String cmtr , Integer dej)
    {   SQLiteDatabase db = this.getWritableDatabase();

        Cursor result = db.rawQuery("INSERT INTO DAYS VALUES (NULL ,'"+date+"','"+cin+"',"+ndj+","+pdj+","+hd+","+hf+",'"+cmtr+"',"+dej+")",null);
        if (result.getCount() == -1) {
            return false;
        } else {
            return true;
        }


    }

    public Cursor getDays (String cin)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM DAYS WHERE CIN = '" + cin + "'" ;
        Cursor data = db.rawQuery(query,null);
        return  data ;
    }


    /*GENERAL */

    public void createWork()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        String query2 = "CREATE TABLE WORKS (ID INTEGER PRIMARY KEY AUTOINCREMENT, LABEL VARCHAR(10) , PLACE VARCHAR(10),CONTACT VARCHAR(8), DURATION INTEGER , WORKERS INTEGER , MDO INTEGER , DIVERS INTEGR );";
        db.execSQL(query2);
    }

    public void createDays()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        String query3 = "CREATE TABLE DAYS (ID INTEGER PRIMARY KEY AUTOINCREMENT , DATE VARCHAR(10) , CIN VARCHAR(8)  , NDJ INTEGER ,PDJ INTEGER , HD INTEGER , HF INTEGER , CMTR VARCHAR(20), DEJ INTEGER );";
        db.execSQL(query3);
    }

    public void createWorker()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "CREATE TABLE WORKER (CIN VARCHAR(8) PRIMARY KEY, NAME VARCHAR(10),PHONE VARCHAR(8), NDJ INTEGER , PDJ INTEGER , TOTAL INTEGER , PAIED INTEGR );";
        db.execSQL(query);
    }

    public void createTask()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "CREATE TABLE TASK (ID INTEGER PRIMARY KEY AUTOINCREMENT, LABEL VARCHAR(10) , DATE VARCHAR(10),TIME varchar(5) , DETAILS VARCHAR(20), REV INTEGR );";
        db.execSQL(query);
    }

    public void createFact()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "CREATE TABLE FACT (ID INTEGER PRIMARY KEY AUTOINCREMENT, DES VARCHAR(20) , QTT INTEGER,PU INTEGER , PT INTEGER);";
        db.execSQL(query);
    }

    /*TOdOo*/
    public boolean addTask(String label, String date, String time, String detail, int rev) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor result = db.rawQuery("INSERT INTO TASK VALUES (Null , '"+label+"','"+date+"','"+time+"','"+detail+"',"+rev+")",null);

        if (result.getCount() == -1) {
            return false;
        } else {
            return true;
        }
    }

    public Cursor getTask() {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM TASK" ;
        Cursor data = db.rawQuery(query,null);
        return  data ;
    }

    public boolean clearTask(){
        SQLiteDatabase db = this.getWritableDatabase();

        String query = "SELECT  *  FROM TASK";
        Cursor data = db.rawQuery(query, null);
        data.moveToFirst();
        int icount = data.getCount();
        if(icount>0) {
            String query2 = "DELETE FROM TASK";
            Cursor data2 = db.rawQuery(query2, null);
            db.execSQL("DROP TABLE IF EXISTS TASK");
            createTask();
            if (data2.getCount() == -1) {
                return false;
            } else {
                return true;
            }
        }
        else
        {
            return false ;
        }


    }

    /*BILLS*/

    public boolean addFact(String des,int qtt,int pu,int pt) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor result = db.rawQuery("INSERT INTO FACT VALUES (Null , '"+des+"',"+qtt+","+pu+","+pt+")",null);

        if (result.getCount() == -1) {
            return false;
        } else {
            return true;
        }
    }

    public Cursor getFact() {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM FACT" ;
        Cursor data = db.rawQuery(query,null);
        return  data ;
    }

    public boolean clearFact(){
        SQLiteDatabase db = this.getWritableDatabase();

        String query = "SELECT  *  FROM FACT";
        Cursor data = db.rawQuery(query, null);
        data.moveToFirst();
        int icount = data.getCount();
        if(icount>0) {
            String query2 = "DELETE FROM FACT";
            Cursor data2 = db.rawQuery(query2, null);
            db.execSQL("DROP TABLE IF EXISTS FACT");
            createFact();
            if (data2.getCount() == -1) {
                return false;
            } else {
                return true;
            }
        }
        else
        {
            return false ;
        }


    }


    public void upgrade()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        onUpgrade(db,0,6);
    }

    public void onInit() {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "CREATE TABLE if not exists WORKER (CIN VARCHAR(8) PRIMARY KEY, NAME VARCHAR(10),PHONE VARCHAR(8), NDJ INTEGER , PDJ INTEGER , TOTAL INTEGER , PAIED INTEGR );";
        String query2 = "CREATE TABLE if not exists WORKS (ID INTEGER PRIMARY KEY AUTOINCREMENT, LABEL VARCHAR(10) , PLACE VARCHAR(10),CONTACT VARCHAR(8), DURATION INTEGER , WORKERS INTEGER , MDO INTEGER , DIVERS INTEGR );";
        String query3 = "CREATE TABLE if not exists DAYS (ID INTEGER PRIMARY KEY AUTOINCREMENT , DATE VARCHAR(10) , CIN VARCHAR(8)  , NDJ INTEGER ,PDJ INTEGER , HD INTEGER , HF INTEGER , CMTR VARCHAR(20), DEJ INTEGER );";
        String query4 = "CREATE TABLE if not exists TASK (ID INTEGER PRIMARY KEY AUTOINCREMENT, LABEL VARCHAR(10) , DATE VARCHAR(10),TIME varchar(5) , DETAILS VARCHAR(20), REV INTEGR );";
        String query5 = "CREATE TABLE if not exists FACT (ID INTEGER PRIMARY KEY AUTOINCREMENT, DES VARCHAR(20) , QTT INTEGER,PU INTEGER , PT INTEGER);";
        db.execSQL(query);
        db.execSQL(query2);
        db.execSQL(query3);
        db.execSQL(query4);
        db.execSQL(query5);
    }
}
