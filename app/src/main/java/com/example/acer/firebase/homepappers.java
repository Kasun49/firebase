package com.example.acer.firebase;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class homepappers extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepappers);

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        // selectedFragment = category.newInstance();
        transaction.replace(R.id.kas,pappers.newInstance());
        transaction.commit();


    }


}
