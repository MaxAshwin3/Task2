package com.example.task2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;

import com.example.task2.Adapters.FragmentAdapters;
import com.google.android.material.tabs.TabLayout;

public class welcomeActivity extends AppCompatActivity {
   TabLayout tabLayout;
   ViewPager2 viewPager2;
   FragmentAdapters fragmentAdapters;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

         tabLayout = findViewById(R.id.tabLayout);
         viewPager2 = findViewById(R.id.viewPager);

        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentAdapters = new FragmentAdapters(fragmentManager,getLifecycle());
        viewPager2.setAdapter(fragmentAdapters);
        tabLayout.addTab(tabLayout.newTab().setText("Male"));
        tabLayout.addTab(tabLayout.newTab().setText("Female"));
        tabLayout.addTab(tabLayout.newTab().setText("Users"));

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager2.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

       viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
           /**
            * This method will be invoked when a new page becomes selected. Animation is not
            * necessarily complete.
            *
            * @param position Position index of the new selected page.
            */
           @Override
           public void onPageSelected(int position) {
               super.onPageSelected(position);
               tabLayout.selectTab(tabLayout.getTabAt(position));
           }
       });

        }
    }
