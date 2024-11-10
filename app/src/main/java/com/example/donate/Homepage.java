package com.example.donate;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Homepage extends AppCompatActivity {
    Button btn1,btn2,btn3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);
        btn1 = (Button) findViewById(R.id.btn1);
        btn2 = (Button) findViewById(R.id.btn2);
        btn3 = (Button) findViewById(R.id.btn3);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String str = btn1.getText().toString();
                Intent intent = new Intent(getApplicationContext(), Categories.class);
                intent.putExtra("extra1", str);
                startActivity(intent);
            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String str = btn2.getText().toString();
                Intent intent = new Intent(getApplicationContext(), Categories.class);
                intent.putExtra("extra1", str);
                startActivity(intent);
            }
        });
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String str = btn3.getText().toString();
                Intent intent = new Intent(getApplicationContext(), Categories.class);
                intent.putExtra("extra1", str);
                startActivity(intent);
            }
        });
    }
}