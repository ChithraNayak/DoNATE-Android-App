package com.example.donate;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {
    EditText username,password;
    TextView newregister,forgotpass;
    Button btn_login;
    DBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        username=(EditText) findViewById(R.id.user);
        password=(EditText) findViewById(R.id.pass);
        newregister=(TextView) findViewById(R.id.newregister);
        forgotpass=(TextView) findViewById(R.id.forgotpass);
        btn_login=(Button) findViewById(R.id.btnlogin);
        forgotpass.setPaintFlags(forgotpass.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        newregister.setPaintFlags(newregister.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        DB = new DBHelper(this);
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String user = username.getText().toString();
                String pass = password.getText().toString();

                if(user.equals("")||pass.equals(""))
                    Toast.makeText(LoginActivity.this, "Please Enter All the Fields", Toast.LENGTH_SHORT).show();
                else{
                    Boolean checkuserpass = DB.checkusernamepassword(user, pass);
                    if(checkuserpass==true){
                        Toast.makeText(LoginActivity.this, "Login  successful", Toast.LENGTH_SHORT).show();
                        Intent intent  = new Intent(getApplicationContext(), Profile.class);
                        String str= username.getText().toString();
                        intent.putExtra("extra1",str);
                        startActivity(intent);
                        username.setText("");
                        password.setText("");
                    }else{
                        Toast.makeText(LoginActivity.this, "Invalid Username or Password", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        newregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Registration.class);
                startActivity(intent);
            }
        });

        forgotpass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user = username.getText().toString();
                Intent intent = new Intent(getApplicationContext(), ForgotPassword.class);
                intent.putExtra("extra",user);
                startActivity(intent);
                username.setText("");
                password.setText("");
            }
        });
    }
}