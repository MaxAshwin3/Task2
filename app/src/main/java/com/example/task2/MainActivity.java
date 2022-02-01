package com.example.task2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.AppCompatTextView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.task2.Base.BaseActivity;
import com.example.task2.Database.DbHelper;

public class MainActivity extends BaseActivity {
    AppCompatEditText emaill;
    AppCompatEditText password;
    AppCompatButton button;
    AppCompatTextView signupText;
    DbHelper dbHelper;
    SharedPreferences sp;

    private static final String KEY_EMAIL = "e_mail";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initUi();

        signupText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(MainActivity.this, Signup_activity.class);
                startActivity(intent);
            }
        });


    }

    @Override
    public void initUi() {
        super.initUi();
        emaill = findViewById(R.id.emailView);
        password = findViewById(R.id.password);
        button = findViewById(R.id.loginButton);
        signupText = findViewById(R.id.signupText);
        dbHelper = new DbHelper(this);
        sp = getSharedPreferences("LoginData", MODE_PRIVATE);
        String email = sp.getString(KEY_EMAIL, null);
        if(email != null){
            Intent intent = new Intent(MainActivity.this, welcomeActivity.class);
            startActivity(intent);
        }


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String usremail = emaill.getText().toString();
                String usrpass = password.getText().toString();





                if(usremail.equals("") || usrpass.equals(""))
                {
                    Toast.makeText(getApplicationContext(),"Enter Details",Toast.LENGTH_SHORT).show();
                }
                else
                {
                   Boolean log= dbHelper.CheckEmailPassword(usremail , usrpass);

                   if(log == true)
                   {
                       Toast.makeText(getApplicationContext(), "Login Successful", Toast.LENGTH_SHORT).show();
                       SharedPreferences.Editor editor = sp.edit();
                       editor.putString(KEY_EMAIL,usremail);

                       editor.apply();
                       Intent intent = new Intent(getApplicationContext(),welcomeActivity.class);
                       startActivity(intent);
                   }
                   else
                   {
                       Toast.makeText(getApplicationContext(), "No Records Found,Register", Toast.LENGTH_SHORT).show();
                   }
                }
            }
        });
    }

}