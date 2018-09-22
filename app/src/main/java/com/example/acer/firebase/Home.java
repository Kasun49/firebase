package com.example.acer.firebase;


import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.design.internal.BottomNavigationItemView;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

import java.io.File;

public class Home extends AppCompatActivity {


BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hell);



        bottomNavigationView =(BottomNavigationView) findViewById(R.id.navigation);


        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment selectedFragment =  null;
               switch (item.getItemId())
                {
                    case R.id.action_cat:
                     selectedFragment = category.newInstance();
                    break;

                    case R.id.action_rankin:
                        selectedFragment = ranking.newInstance();
                        break;
                }
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.kas,selectedFragment);
                transaction.commit();


                return true;
            }


        }


        );

       setDefaultFragments();
    }

    private void setDefaultFragments() {

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.kas,category.newInstance());
        transaction.commit();

    }
}
