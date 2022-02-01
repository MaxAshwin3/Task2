package com.example.task2;




import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatEditText;

import com.example.task2.Base.BaseActivity;
import com.example.task2.Database.DbHelper;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Signup_activity extends BaseActivity {
    AppCompatEditText fname,lname;
    AppCompatEditText number;
    AppCompatEditText email;
    AppCompatEditText password;
    AppCompatButton signup;
    DbHelper dbHelper;
    RadioGroup genderGrp;
    RadioButton g_male, g_female;
    String gender = "";

    String userFirstName,userLastName,userNumber,userEmail,userPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        initUi();





    }

    @Override
    protected void initUi() {
        super.initUi();

         fname = findViewById(R.id.FirstnameView);
         lname = findViewById(R.id.LastnameView);
         number = findViewById(R.id.number);
         genderGrp = findViewById(R.id.radioGrp);
         g_male = findViewById(R.id.maleRadio);
         g_female = findViewById(R.id.femaleRadio);
         email = findViewById(R.id.signupEmail);
         password = findViewById(R.id.signupPassword);
         signup = findViewById(R.id.signupButton);
         dbHelper = new DbHelper(this);

         signup.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                  userFirstName = fname.getText().toString();
                  userLastName = lname.getText().toString();
                   userNumber = number.getText().toString();
                   userEmail = email.getText().toString();
                   userPassword = password.getText().toString();

                   // For gender



                   // This code here is for validation

                 Pattern textPattern = Pattern.compile("^[a-zA-Z]+$");
                 Pattern phonePattern = Pattern.compile("^[0-9]{10}+$");
                 Pattern emailPattern = Pattern.compile("^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9]+.+[a-zA-Z]+$");
//Minimum eight characters, at least one uppercase letter, one lowercase letter, one number and one special character:
                 Pattern passwordPattern = Pattern.compile("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$");


                 Matcher fNameMatcher = textPattern.matcher(userFirstName);
                 boolean fNameMatches = fNameMatcher.matches();

                 Matcher lNameMatcher = textPattern.matcher(userLastName);
                 boolean lNameMatches = lNameMatcher.matches();

                 Matcher phoneMatcher = phonePattern.matcher(userNumber);
                 boolean phoneMatches = phoneMatcher.matches();

                 Matcher emailMatcher = emailPattern.matcher(userEmail);
                 boolean emailMatches = emailMatcher.matches();
                 if (!fNameMatches || !lNameMatches || !phoneMatches || !emailMatches) {
                     if (!fNameMatches) {
                         Toast.makeText(getApplicationContext(), "Please Enter First Name properly", Toast.LENGTH_LONG).show();
                     }  if (!lNameMatches) {
                         Toast.makeText(getApplicationContext(), "Please Enter Last Name properly", Toast.LENGTH_LONG).show();
                     }  if (!phoneMatches) {
                         Toast.makeText(getApplicationContext(), "Please Enter Phone No properly", Toast.LENGTH_LONG).show();
                     } else {
                         Toast.makeText(getApplicationContext(), "Please Enter Email properly", Toast.LENGTH_LONG).show();
                     }

                 }
                 // if fields are empty

                  if(userFirstName.equals("") || userLastName.equals("") || userNumber.equals("") || userEmail.equals("") || userPassword.equals(""))
                  {
                      Toast.makeText(getApplicationContext(), "Nothing Entered", Toast.LENGTH_SHORT).show();
                  }

                  else
                  {
                      boolean val =   dbHelper.insertData(userFirstName,userLastName,userNumber,userEmail,userPassword,gender);

                         if(val == true)
                         {
                             Toast.makeText(getApplicationContext(),"Registration Successful",Toast.LENGTH_SHORT).show();

                             Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                             startActivity(intent);
                         }
                  }




             }
         });

        genderGrp.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if(i==R.id.maleRadio){
                    gender="Male";
                }
                else if(i==R.id.femaleRadio){
                    gender="Female";
                }
            }
        });
    }

}