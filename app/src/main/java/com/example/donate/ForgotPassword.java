package com.example.donate;

import static com.example.donate.Registration.isValidPassword;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ForgotPassword extends AppCompatActivity {
    EditText uname, pass1, newpass1;
    Button forgot;
    DBHelper DB;
    SQLiteDatabase sqLiteDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);
        uname = (EditText) findViewById(R.id.uname);
        pass1 = (EditText) findViewById(R.id.pass1);
        newpass1 = (EditText) findViewById(R.id.newpass1);
        forgot = (Button) findViewById(R.id.forgot);
        DB = new DBHelper(this);
        String str = getIntent().getStringExtra("extra");
        uname.setText(str);

        forgot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user = uname.getText().toString();
                String pass = pass1.getText().toString();
                String newpass = newpass1.getText().toString();
                if (!isValidPassword(pass1.getText().toString())) {
                    Toast.makeText(ForgotPassword.this, "Invalid Password", Toast.LENGTH_SHORT).show();
                } else {
                    if (pass.equals(newpass)) {
                        Boolean checkuser = DB.checkusername(user);
                        if (checkuser == false) {
                            Toast.makeText(ForgotPassword.this, "User Doesn't Exist", Toast.LENGTH_SHORT).show();
                        } else {
                            Boolean pwd = DB.updatedpwd(user, pass);
                            if (pwd) {
                                Toast.makeText(ForgotPassword.this, "Password Updated Successfully", Toast.LENGTH_SHORT).show();
                                Intent intent=new Intent(getApplicationContext(),LoginActivity.class);
                                startActivity(intent);
                            } else {
                                Toast.makeText(ForgotPassword.this, "Password not Updated", Toast.LENGTH_SHORT).show();
                            }
                        }
                    } else {
                        Toast.makeText(ForgotPassword.this, "Passwords Not Matching", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}