package com.example.donate;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.time.temporal.TemporalQueries;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Registration extends AppCompatActivity {
    EditText username, password, repassword,name,mobile,email,altno,address;
    Button btn_reg, signin;
    Spinner category;
    DBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);
        repassword = (EditText) findViewById(R.id.repassword);
        name= (EditText) findViewById(R.id.name);
        mobile= (EditText) findViewById(R.id.mobile);
        email= (EditText) findViewById(R.id.email);
        altno= (EditText) findViewById(R.id.altno);
        address=(EditText)findViewById(R.id.address);
        category=(Spinner)findViewById(R.id.category);
        btn_reg = (Button) findViewById(R.id.btnsignup);
        signin = (Button) findViewById(R.id.btnsignin);
        DB = new DBHelper(this);

        btn_reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user = username.getText().toString();
                String pass = password.getText().toString();
                String repass = repassword.getText().toString();
                String name1=name.getText().toString();
                String mobile1=mobile.getText().toString();
                String email1=email.getText().toString();
                String alt=altno.getText().toString();
                String add=address.getText().toString();
                String cat=category.getSelectedItem().toString();
                if(user.equals("")||pass.equals("")||repass.equals("")||name1.equals("")||mobile1.equals("")||email1.equals("")||alt.equals("")||add.equals("")||cat.equals("")){
                    Toast.makeText(Registration.this, "Please Enter All the Fields", Toast.LENGTH_SHORT).show();
                }
                else if(!isValidName(name.getText().toString()) ){
                    Toast.makeText(Registration.this, "Invalid Donor Name", Toast.LENGTH_SHORT).show();
                }
                else if(!isValidEmail(email.getText().toString()) ){
                    Toast.makeText(Registration.this, "Invalid Email", Toast.LENGTH_SHORT).show();
                }
                else if(!isValidNumber(mobile.getText().toString()) ){
                    Toast.makeText(Registration.this, "Invalid  Mobile number", Toast.LENGTH_SHORT).show();
                }
                else if(!isValidNumber(altno.getText().toString()) ){
                    Toast.makeText(Registration.this, "Invalid Alternate Mobile number", Toast.LENGTH_SHORT).show();
                }
                else if(!isValidPassword(pass)){
                    Toast.makeText(Registration.this, "Invalid password", Toast.LENGTH_SHORT).show();
                }

                else{
                    if(pass.equals(repass)){
                        Boolean checkuser = DB.checkusername(user);
                        if(checkuser==false){
                            Boolean insert = DB.insertData(user,pass,name1,mobile1,email1,alt,add,cat);
                            if(insert==true){
                                Toast.makeText(Registration.this, "Registered successfully", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getApplicationContext(),LoginActivity.class);
                                startActivity(intent);
                            }else{
                                Toast.makeText(Registration.this, "Registration failed", Toast.LENGTH_SHORT).show();
                            }
                        }
                        else{
                            Toast.makeText(Registration.this, "User already exists! please sign in", Toast.LENGTH_SHORT).show();
                        }
                    }else{
                        Toast.makeText(Registration.this, "Passwords not matching", Toast.LENGTH_SHORT).show();
                    }
                } }
        });
        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);
            }
        });
    }

    public static  boolean isValidName(String name) {
        Pattern pattern1;
        Matcher matcher1;
        final String NAME_PATTERN = "^[A-Za-z ]+$";
        pattern1 = Pattern.compile(NAME_PATTERN);
        matcher1 = pattern1.matcher(name);
        return matcher1.matches();
    }

    public static  boolean isValidEmail(String email) {
        Pattern pattern2;
        Matcher matcher2;
        final String EMAIL_PATTERN = "[A-Za-z0-9._-]+@[a-z]+\\.+[a-z]+";
        pattern2 = Pattern.compile(EMAIL_PATTERN);
        matcher2 = pattern2.matcher(email);
        return matcher2.matches();
    }

    public static  boolean isValidPassword(String password) {
        Pattern pattern;
        Matcher matcher;
        final String PASSWORD_PATTERN = "[0-9A-Za-z]{6}";
        pattern = Pattern.compile(PASSWORD_PATTERN);
        matcher = pattern.matcher(password);
        return matcher.matches();
    }

    public static boolean isValidNumber(String number){
        Pattern p= Pattern.compile("[6-9]+[0-9]+");
        Matcher m=p.matcher(number);
        return (m.find() && m.group().equals(number));
    }
}


