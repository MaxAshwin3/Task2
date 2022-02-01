package com.example.task2.Fragments;

import static android.content.Context.MODE_PRIVATE;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.task2.Database.DbHelper;
import com.example.task2.MainActivity;
import com.example.task2.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link userFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class userFragment extends Fragment {

    TextView usrFname , usrLname , usrEmail , usrGender , usrNumber , welcome , message ;
    TextView accFname , accLname, accEmail , accGender , accNumber;
    Button logout;

    private static final String KEY_EMAIL = "e_mail";





    public userFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View  v = inflater.inflate(R.layout.fragment_user, container, false);

         accFname = v.findViewById(R.id.accFname);
         usrFname = v.findViewById(R.id.usrFname);
        accLname = v.findViewById(R.id.accLname);
        usrLname = v.findViewById(R.id.usrLname);
        accNumber = v.findViewById(R.id.accNumber);
        usrNumber = v.findViewById(R.id.usrNumber);
        accEmail = v.findViewById(R.id.accEmail);
        usrEmail = v.findViewById(R.id.usrEmail);
        accGender = v.findViewById(R.id.accGender);
        usrGender = v.findViewById(R.id.usrGender);
        welcome = v.findViewById(R.id.welcome);
        message = v.findViewById(R.id.welcomeEmail);
        logout = v.findViewById(R.id.logout);

        SharedPreferences  sp = getContext().getSharedPreferences("LoginData", MODE_PRIVATE);

        String email = sp.getString(KEY_EMAIL, null);

        if(email != null){
            message.setText(email);
        }


        DbHelper dbHelper = new DbHelper(getContext());
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        Cursor userData = db.rawQuery("select * from users where email LIKE '" + email + "'", null);
        userData.moveToFirst();


        do{
            usrFname.setText(userData.getString(1));
            usrLname.setText(userData.getString(2));
            usrGender.setText(userData.getString(6));
            usrEmail.setText(userData.getString(4));
            usrNumber.setText(userData.getString(3));
        }
        while(userData.moveToNext());

        db.close();

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences.Editor editor = sp.edit();
                editor.clear();
                editor.commit();
                startActivity(new Intent(getContext(), MainActivity.class));
                getActivity().finish();
            }
        });




        return v ;
    }
}