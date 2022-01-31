package com.example.task2;




import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatEditText;

import com.example.task2.Base.BaseActivity;
import com.example.task2.Database.DbHelper;

public class Signup_activity extends BaseActivity {
    AppCompatEditText name;
    AppCompatEditText number;
    AppCompatEditText email;
    AppCompatEditText password;
    AppCompatButton signup;
    DbHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        initUi();




    }

    @Override
    protected void initUi() {
        super.initUi();

         name = findViewById(R.id.nameView);
         number = findViewById(R.id.number);
         email = findViewById(R.id.signupEmail);
         password = findViewById(R.id.signupPassword);
         signup = findViewById(R.id.signupButton);
         dbHelper = new DbHelper(this);

         signup.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 String userName = name.getText().toString();
                  String userNumber = number.getText().toString();
                  String userEmail = email.getText().toString();
                  String userPassword = password.getText().toString();

                  if(userName.equals("") || userNumber.equals("") || userEmail.equals("") || userPassword.equals(""))
                  {
                      Toast.makeText(getApplicationContext(), "Enter the field", Toast.LENGTH_SHORT).show();
                  }
                  else
                  {
                      boolean val =   dbHelper.insertData(userName,userNumber,userEmail,userPassword);

                         if(val == true)
                         {
                             Toast.makeText(getApplicationContext(),"Registration Successful",Toast.LENGTH_SHORT).show();

                             Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                             startActivity(intent);
                         }
                  }

             }
         });


    }

}