package com.example.task2;



import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.task2.Fragments.FirstFragment;
import com.example.task2.Fragments.SecondFragment;
import com.example.task2.Fragments.userFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class BottomNav extends AppCompatActivity {

    BottomNavigationView btm_nav;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bottom_nav);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportFragmentManager().beginTransaction().add(R.id.frame, new FirstFragment()).commit();

        btm_nav = findViewById(R.id.btm_nav);

        btm_nav.setSelectedItemId(R.id.btm_nav_maleusers);
        btm_nav.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {

            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                Fragment selectedFragment = null;

                switch (item.getItemId()){

                    case R.id.btm_nav_maleusers:
                        selectedFragment = new FirstFragment();
                        break;

                    case R.id.btm_nav_femaleusers:
                        selectedFragment = new SecondFragment();
                        break;

                    case R.id.btm_nav_useracc:
                        selectedFragment = new userFragment();
                        break;
                }

                getSupportFragmentManager().beginTransaction().replace(R.id.frame, selectedFragment).commit();

                return true;
            }
        });
    }
}