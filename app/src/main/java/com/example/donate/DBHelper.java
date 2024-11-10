package com.example.donate;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {
    public static final String DBNAME = "DonateApp.db";
    public static final String TABLENAME = "items";
    public static final String TABLENAME2= "donors";
    public static final String TABLENAME3= "login";
    public DBHelper(Context context) {
        super(context, DBNAME, null, 1);
}

    @Override
    public void onCreate(SQLiteDatabase MyDB) {
        MyDB.execSQL("create Table "+TABLENAME3+" (username varchar(10) primary key,password varchar(6) not null)");
        MyDB.execSQL("create Table "+TABLENAME2+"(username varchar(10) primary key, password varchar(6) not null,name varchar(30) not null,mobile number(10) not null,email varchar(40) not null,altno number(10) not null,address varchar(150) not null,category varchar(20) not null)");
        MyDB.execSQL("create table "+TABLENAME+"(id integer primary key AUTOINCREMENT,username varchar(10) not null ,category varchar(20) not null,itemname varchar(25) not null,itemquantity number(20) not null,itemdetails varchar(100) not null,image blob not null ,foreign key(username) references "+TABLENAME2+"(username) )");
    }

    @Override
    public void onUpgrade(SQLiteDatabase MyDB, int i, int i1) {
        MyDB.execSQL("drop Table if exists "+TABLENAME2);
        MyDB.execSQL("drop Table if exists "+TABLENAME);
        MyDB.execSQL("drop Table if exists "+TABLENAME3);
    }

    public boolean insertData(String username, String password,String name,String mobile,String email,String altno,String address,String category){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues contentValues= new ContentValues();
        ContentValues cv=new ContentValues();
        contentValues.put("username", username);
        contentValues.put("password", password);
        contentValues.put("name", name);
        contentValues.put("mobile", mobile);
        contentValues.put("email", email);
        contentValues.put("altno", altno);
        contentValues.put("address", address);
        contentValues.put("category",category);
        cv.put("username",username);
        cv.put("password",password);
        long result1 = MyDB.insert(TABLENAME2, null, contentValues);
        long result2=MyDB.insert("login",null,cv);
        if(result1==-1 && result2==-1) return false;
        else
            return true;
    }

    public boolean insertdata1(String username1,String category1,String itemname1,String itemquantity1,String itemdetails1,byte[] img1){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues cv=new ContentValues();
        cv.put("username",username1);
        cv.put("category",category1);
        cv.put("itemname",itemname1);
        cv.put("itemquantity",itemquantity1);
        cv.put("itemdetails",itemdetails1);
        cv.put("image",img1);
        long result=db.insert(TABLENAME,null,cv);
        if(result==-1)
            return false;
        else
            return true;
    }

    public Boolean checkusername(String username) {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("Select * from "+TABLENAME2+" where username = ?", new String[]{username});
        if (cursor.getCount() > 0)
            return true;
        else
            return false;
    }

    public Boolean checkusernamepassword(String username, String password){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("Select * from "+TABLENAME2+" where username = ? and password = ?", new String[] {username,password});
        if(cursor.getCount()>0)
            return true;
        else
            return false;
    }

    public Cursor getdata(String uname){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("Select * from "+TABLENAME2+" where username=?", new String[]{uname});
        return cursor;
    }

    Boolean updatedpwd(String uname,String password){
        SQLiteDatabase MyDB=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put("password",password);
        long res1=MyDB.update(TABLENAME2,contentValues,"username=?",new String[]{uname});
        long res2=MyDB.update(TABLENAME3,contentValues,"username=?",new String[]{uname});
        MyDB.close();
        if(res1==-1 && res2==-1)
            return false;
        else
            return true;
    }

}

