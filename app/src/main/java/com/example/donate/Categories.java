package com.example.donate;

import static com.example.donate.DBHelper.TABLENAME;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import java.util.ArrayList;

public class Categories extends AppCompatActivity {
    DBHelper DB;
    SQLiteDatabase sqLiteDatabase;
    RecyclerView recyclerView;
    Adapter Adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categories);
        DB = new DBHelper(this);

        recyclerView=findViewById(R.id.rv);
        displayData();
        recyclerView.setLayoutManager(new LinearLayoutManager(this,RecyclerView.VERTICAL,false));
    }
    public void displayData() {
        String str=getIntent().getStringExtra("extra1");
        sqLiteDatabase = DB.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("select * from "+TABLENAME+" where category = ?", new String[]{str});
        ArrayList<Model> models = new ArrayList<>();
        while (cursor.moveToNext()) {
            int id=cursor.getInt(0);
            String username=cursor.getString(1);
            String itemname = cursor.getString(3);
            String itemquantity = cursor.getString(4);
            String itemdetail = cursor.getString(5);
            byte[] avatar = cursor.getBlob(6);
            models.add(new Model(id, username,itemname,itemquantity,itemdetail, avatar));
        }
        cursor.close();
        Adapter=new Adapter(this,R.layout.singledata1,models,sqLiteDatabase);
        recyclerView.setAdapter(Adapter);
    }
}
