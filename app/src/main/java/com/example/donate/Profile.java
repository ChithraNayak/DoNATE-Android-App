package com.example.donate;

import static com.example.donate.DBHelper.TABLENAME;
import static com.example.donate.DBHelper.TABLENAME2;
import static com.example.donate.DBHelper.TABLENAME3;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class Profile extends AppCompatActivity {
    TextView name,mobile,email,altno,address,category,username,password,txtdelete;
    Button btn_add,btn_disp;
    ImageButton delete;
    DBHelper DB;
    SQLiteDatabase sqLiteDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        name=(TextView) findViewById(R.id.name);
        mobile=(TextView) findViewById(R.id.mobile);
        name=(TextView) findViewById(R.id.name);
        email=(TextView) findViewById(R.id.email);
        altno=(TextView) findViewById(R.id.altno);
        category=(TextView) findViewById(R.id.category);
        address=(TextView) findViewById(R.id.address);
        username=(TextView) findViewById(R.id.username);
        password=(TextView) findViewById(R.id.password);
        btn_add=(Button) findViewById(R.id.btn_add);
        btn_disp=(Button) findViewById(R.id.btn_disp);
        delete=(ImageButton)findViewById(R.id.delete);
        txtdelete=(TextView)findViewById(R.id.txtdelete) ;
        txtdelete.setPaintFlags(txtdelete.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        DB=new DBHelper(this);

        String str=getIntent().getStringExtra("extra1");
        Cursor res=DB.getdata(str);
        while (res.moveToNext()) {
            username.setText(res.getString(0));
            password.setText(res.getString(1));
            name.setText(res.getString(2));
            mobile.setText(res.getString(3));
            email.setText(res.getString(4));
            altno.setText(res.getString(5));
            address.setText(res.getString(6));
            category.setText(res.getString(7));

            btn_add.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String str1=username.getText().toString();
                    String str2=category.getText().toString();
                    Intent intent  = new Intent(getApplicationContext(), AddItem.class);
                    intent.putExtra("extra1",str1);
                    intent.putExtra("extra2",str2);
                    startActivity(intent);
                }
            });
            btn_disp.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String str1=username.getText().toString();
                    Intent intent  = new Intent(getApplicationContext(), DisplayData.class);
                    intent.putExtra("extra1",str1);
                    startActivity(intent);
                }
            });
        }
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder dm=new AlertDialog.Builder(Profile.this);
                dm.setMessage("Want to delete profile?");
                dm.setNegativeButton("No",null);
                dm.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        sqLiteDatabase = DB.getWritableDatabase();
                        long res1 = sqLiteDatabase.delete(TABLENAME2, "username=?", new String[]{str});
                        long res2 = sqLiteDatabase.delete(TABLENAME, "username=?", new String[]{str});
                        long res3 = sqLiteDatabase.delete(TABLENAME3, "username=?", new String[]{str});
                        sqLiteDatabase.close();
                        if (res1 == -1 && res2 == -1 && res3 == -1) {
                            Toast.makeText(Profile.this, "Profile Not Deleted", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(Profile.this, "Profile Deleted Successfully", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                            startActivity(intent);}
                    }
                });
                AlertDialog alt=dm.create();
                alt.show();
            }
        });
    }
}




