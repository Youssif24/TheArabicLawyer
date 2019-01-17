package com.saad.youssif.arabiclawyer.Helpers;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.saad.youssif.arabiclawyer.Model.ClientDB;
import com.saad.youssif.arabiclawyer.Model.DelegationDB;
import com.saad.youssif.arabiclawyer.Model.IssueDB;
import com.saad.youssif.arabiclawyer.Model.SittingDB;

import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {
    public static final String DBname="data.db";
    public DBHelper(Context context) {
        super(context, DBname, null, 2);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("Create table Client ( id  INTEGER PRIMARY KEY,name TEXT,type TEXT, phone TEXT)");
        db.execSQL("Create table Issue (issue_num INTEGER PRIMARY KEY,issue_type TEXT,issue_token TEXT,issue_client_name TEXT,issue_opponent_name TEXT,court_name TEXT,issue_details TEXT)");
        db.execSQL("Create table Sitting (id INTEGER PRIMARY KEY AUTOINCREMENT ,sitting_issue_num TEXT,client_name TEXT,opponent_name TEXT,brol_num TEXT ,delay_date TEXT,judgment TEXT)");
        db.execSQL("Create table Delegation(del_num INTEGER PRIMARY KEY,del_client_name TEXT,del_org TEXT,del_date TEXT,del_photo TEXT)");


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS Client");
        db.execSQL("DROP TABLE IF EXISTS Issue");
        db.execSQL("DROP TABLE IF EXISTS Sitting");
        db.execSQL("DROP TABLE IF EXISTS Delegation");

        onCreate(db);

    }
    //function to insert data (to get writale)
    public boolean insertClientData(String id,String name,String type,String phone){
        SQLiteDatabase db =this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put("id",id);
        contentValues.put("name",name);
        contentValues.put("type",type);
        contentValues.put("phone",phone);


        long result=db.insert("Client",null,contentValues);
        if(result==-1)
            return false;
        else
            return true;
    }
    //function to reterieve data(to get readable)
    public ArrayList getAllrecord(){
        ArrayList<ClientDB> arrayList=new ArrayList();
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor=db.rawQuery("Select * from Client" ,null);
        cursor.moveToFirst();//move to the first field
        while (cursor.isAfterLast()==false)//last field
        {
            ClientDB clientDB=new ClientDB();
            clientDB.setId(cursor.getString(0));
            clientDB.setName(cursor.getString(1));
            clientDB.setType(cursor.getString(2));
            clientDB.setPhone(cursor.getString(3));
           /* String t1=cursor.getString(0);
            String t2=cursor.getString(1);
            String t3=cursor.getString(2);
            String t4=cursor.getString(3);*/
            arrayList.add(clientDB);
            cursor.moveToNext();

        }
        return arrayList;

    }
    public ArrayList getAllClients(){
        ArrayList<String> arrayList=new ArrayList();
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor=db.rawQuery("Select * from Client" ,null);
        cursor.moveToFirst();//move to the first field
        while (cursor.isAfterLast()==false)//last field
        {
            arrayList.add(cursor.getString(1));
            cursor.moveToNext();

        }
        return arrayList;

    }
    //to get update on databae its look like addData function .
    public boolean updateData(String id,String name,String type,String phone){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put("name",name);
        contentValues.put("type",type);
        contentValues.put("phone",phone);
        db.update("Client",contentValues,"id= ?",new String[]{String.valueOf(id)});

        return true;
    }
    public Integer Delete(String id){
        SQLiteDatabase db=this.getWritableDatabase();
        return db.delete("Client","id= ?",new String[]{id});


    }


    //add new issue method
    public boolean insertIssueData(String issue_num,String issue_type,String issue_token,String issue_client_name
            ,String issue_opponent_name,String court_name,String issue_details){
        SQLiteDatabase db =this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put("issue_num",issue_num);
        contentValues.put("issue_type",issue_type);
        contentValues.put("issue_token",issue_token);
        contentValues.put("issue_client_name",issue_client_name);
        contentValues.put("issue_opponent_name",issue_opponent_name);
        contentValues.put("court_name",court_name);
        contentValues.put("issue_details",issue_details);



        long result=db.insert("Issue",null,contentValues);
        if(result==-1)
            return false;
        else
            return true;
    }

    //get all issues
    public ArrayList getAllIssues(){
        ArrayList<IssueDB> arrayList=new ArrayList();
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor=db.rawQuery("Select * from Issue" ,null);
        cursor.moveToFirst();//move to the first field
        while (cursor.isAfterLast()==false)//last field
        {
            IssueDB issueDB=new IssueDB();
            issueDB.setNum(cursor.getString(0));
            issueDB.setType(cursor.getString(1));
            issueDB.setToken(cursor.getString(2));
            issueDB.setClient_name(cursor.getString(3));
            issueDB.setOpponent_name(cursor.getString(4));
            issueDB.setCourt_name(cursor.getString(5));
            issueDB.setDetails(cursor.getString(6));

            arrayList.add(issueDB);
            cursor.moveToNext();

        }
        return arrayList;

    }

    public ArrayList getAllIssuesNum(){
        ArrayList<String> arrayList=new ArrayList();
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor=db.rawQuery("Select * from Issue" ,null);
        cursor.moveToFirst();//move to the first field
        while (cursor.isAfterLast()==false)//last field
        {
            arrayList.add(cursor.getString(0));
            cursor.moveToNext();

        }
        return arrayList;

    }

    public IssueDB getClient_Opponent_name(int num)
    {
        IssueDB issueDB = null;
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor=db.rawQuery("Select * from Issue where issue_num="+num ,null);
        cursor.moveToFirst();//move to the first field
        while (cursor.isAfterLast()==false)//last field
        {
            issueDB=new IssueDB();
            issueDB.setClient_name(cursor.getString(3));
            issueDB.setOpponent_name(cursor.getString(4));
            cursor.moveToNext();

        }
        return issueDB;

    }

    //update an issue
    public boolean updateIssue(String issue_num,String issue_type,String issue_token,String issue_client_name
            ,String issue_opponent_name,String court_name,String issue_details){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put("issue_type",issue_type);
        contentValues.put("issue_token",issue_token);
        contentValues.put("issue_client_name",issue_client_name);
        contentValues.put("issue_opponent_name",issue_opponent_name);
        contentValues.put("court_name",court_name);
        contentValues.put("issue_details",issue_details);


        db.update("Issue",contentValues,"issue_num= ?",new String[]{String.valueOf(issue_num)});

        return true;
    }

    //delete an issue
    public Integer deleteIssue(String issue_num){
        SQLiteDatabase db=this.getWritableDatabase();
        return db.delete("Issue","issue_num= ?",new String[]{issue_num});


    }


    // add new sitting
    public boolean insertSittingData(String sitting_issue_num,String client_name,String opponent_name,String brol_num
            ,String delay_date,String judgment){
        SQLiteDatabase db =this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put("sitting_issue_num",sitting_issue_num);
        contentValues.put("client_name",client_name);
        contentValues.put("opponent_name",opponent_name);
        contentValues.put("brol_num",brol_num);
        contentValues.put("delay_date",delay_date);
        contentValues.put("judgment",judgment);

        long result=db.insert("Sitting",null,contentValues);
        if(result==-1)
            return false;
        else
            return true;
    }

    // get all sittings

    public ArrayList getAllSitting(){
        ArrayList<SittingDB> arrayList=new ArrayList();
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor=db.rawQuery("Select * from Sitting" ,null);
        cursor.moveToFirst();//move to the first field
        while (cursor.isAfterLast()==false)//last field
        {
            SittingDB sittingDB=new SittingDB();
            sittingDB.setId(cursor.getInt(0));
            sittingDB.setSitting_issue_num(cursor.getString(1));
            sittingDB.setClient_name(cursor.getString(2));
            sittingDB.setOpponent_name(cursor.getString(3));
            sittingDB.setBrol_num(cursor.getString(4));
            sittingDB.setDelay_date(cursor.getString(5));
            sittingDB.setJudgment(cursor.getString(6));

            arrayList.add(sittingDB);
            cursor.moveToNext();

        }
        return arrayList;

    }

    // update a sitting
    public boolean updateSitting(String id,String issue_num,String client_name,String opponent_name,String brol_num
            ,String delay_date,String judgment){
        SQLiteDatabase db =this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put("id",id);
        contentValues.put("sitting_issue_num",issue_num);
        contentValues.put("client_name",client_name);
        contentValues.put("opponent_name",opponent_name);
        contentValues.put("brol_num",brol_num);
        contentValues.put("delay_date",delay_date);
        contentValues.put("judgment",judgment);

        db.update("Sitting",contentValues,"id= ?",new String[]{String.valueOf(id)});
        return true;
    }
    //delete an Sitting
    public Integer deleteSitting(String id){
        SQLiteDatabase db=this.getWritableDatabase();
        return db.delete("Sitting","id= ?",new String[]{id});


    }




    // add new delegation
    public boolean insertDelegationData(String del_num,String del_client_name,String del_org,String del_date)
    {
        SQLiteDatabase db =this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put("del_num",del_num);
        contentValues.put("del_client_name",del_client_name);
        contentValues.put("del_org",del_org);
        contentValues.put("del_date",del_date);

        long result=db.insert("Delegation",null,contentValues);
        if(result==-1)
            return false;
        else
            return true;
    }


    //get all delegations
    public ArrayList getAllDelegations(){
        ArrayList<DelegationDB> arrayList=new ArrayList();
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor=db.rawQuery("Select * from Delegation" ,null);
        cursor.moveToFirst();//move to the first field
        while (cursor.isAfterLast()==false)//last field
        {
            DelegationDB delegationDB=new DelegationDB();
            delegationDB.setDel_num(cursor.getString(0));
            delegationDB.setDel_client_name(cursor.getString(1));
            delegationDB.setDel_org(cursor.getString(2));
            delegationDB.setDel_date(cursor.getString(3));

            arrayList.add(delegationDB);
            cursor.moveToNext();

        }
        return arrayList;

    }

    // update a Delegation
    public boolean updateDelegation(String del_num,String del_client_name,String del_org,String del_date)
    {
        SQLiteDatabase db =this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put("del_num",del_num);
        contentValues.put("del_client_name",del_client_name);
        contentValues.put("del_org",del_org);
        contentValues.put("del_date",del_date);


        db.update("Delegation",contentValues,"del_num= ?",new String[]{String.valueOf(del_num)});
        return true;
    }

    //delete an Delegation
    public Integer deleteDelegation(String del_num){
        SQLiteDatabase db=this.getWritableDatabase();
        return db.delete("Delegation","del_num= ?",new String[]{del_num});


    }






}

