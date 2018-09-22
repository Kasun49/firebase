package com.example.acer.firebase;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.example.acer.firebase.common.common;
import com.example.acer.firebase.model.cat;
import com.example.acer.firebase.model.questions;
import com.example.acer.firebase.viewHolder.categoryViewHolder;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Collections;

public class Choose extends AppCompatActivity {

    Button past, fav, sch;
    FirebaseDatabase database;
    DatabaseReference questionss;
    //int Pno;

    RecyclerView listcat;
    RecyclerView.LayoutManager layoutManager;
    FirebaseRecyclerAdapter<cat, categoryViewHolder> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose);

        database = FirebaseDatabase.getInstance();
        questionss = database.getReference("Questions");


        past = (Button) findViewById(R.id.pp);
        fav = (Button) findViewById(R.id.fp);

      /*  int Pno = 02;
        final String ppno = Integer.toString(Pno);
        LoadQuestions(ppno);

*/



        past.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                    Intent intent = new Intent(Choose.this, homepappers.class);
                    startActivity(intent);


                    int kk = 01;
                    common.PPno = kk;


                    finish();

            }
        });

        fav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Choose.this, homepappers.class);
                startActivity(intent);


                int kkk = 2;
                common.PPno = kkk;



                finish();
            }
        });



    }

    public void LoadQuestions(String pp, String kk){

        if (common.questionlist.size() > 0)
            common.questionlist.clear();


        questionss.orderByChild("Questions")
                .startAt("pno")
                .endAt("pno").equalTo(pp)
                .orderByChild("Questions")
                .startAt("CategoryId")
                .endAt("CategoryId").equalTo(kk)
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        for(DataSnapshot postsnapshot: dataSnapshot.getChildren()){

                            questions qus= postsnapshot.getValue(questions.class);
                            common.questionlist.add(qus);



                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });

    }
}

