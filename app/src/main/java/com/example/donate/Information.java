package com.example.donate;

import static com.example.donate.DBHelper.TABLENAME;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Paint;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class Information extends AppCompatActivity {
    ImageView image;
    TextView txt1, txt2, txt3, txt4, txt5, txt6, txt7, txt8;
    SQLiteDatabase sqLiteDatabase;
    DBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information);
        DB = new DBHelper(this);
        image = (ImageView) findViewById(R.id.image);
        txt1 = (TextView) findViewById(R.id.txt1);
        txt2 = (TextView) findViewById(R.id.txt2);
        txt3 = (TextView) findViewById(R.id.txt3);
        txt4 = (TextView) findViewById(R.id.txt4);
        txt5 = (TextView) findViewById(R.id.txt5);
        txt6 = (TextView) findViewById(R.id.txt6);
        txt7 = (TextView) findViewById(R.id.txt7);
        txt8 = (TextView) findViewById(R.id.txt8);
        txt5.setPaintFlags(txt5.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        txt7.setPaintFlags(txt7.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        String str1 = getIntent().getStringExtra("extra1");
        String str2 = getIntent().getStringExtra("extra3");
        String str3 = getIntent().getStringExtra("extra4");
        String str4 = getIntent().getStringExtra("extra5");

        txt1.setText(str4);
        txt2.setText(str2);
        txt3.setText(str3);
        Cursor res = DB.getdata(str1);
        while (res.moveToNext()) {
            txt4.setText(res.getString(2));
            txt5.setText(res.getString(3));
            txt6.setText(res.getString(4));
            txt7.setText(res.getString(5));
            txt8.setText(res.getString(6));
        }
        String number = txt5.getText().toString();
        txt5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:" + number));
                startActivity(intent);
            }
        });
        String number1 = txt7.getText().toString();
        txt7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:" + number1));
                startActivity(intent);
            }
        });
        sqLiteDatabase = DB.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("select * from "+TABLENAME+" where username = ?", new String[]{str1});
        if (cursor.moveToNext()) {
            byte[] avatar = cursor.getBlob(6);
            Bitmap bitmap = BitmapFactory.decodeByteArray(avatar, 0, avatar.length);
            image.setImageBitmap(bitmap);
        }
    }
}
