package com.example.acer.firebase;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.acer.firebase.common.common;
import com.example.acer.firebase.model.questions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.Collection;
import java.util.Collections;

public class start extends AppCompatActivity {

     Button btnplay;
     Button btnnorush;
     TextView co;

     FirebaseDatabase database;
     DatabaseReference questionss,ppers;
     ImageView imageView;




    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);  // me page eke thyenna oni layout eka
        //  play = (Button) findViewById(R.id.btnply);
        //   play.setOnClickListener(new View.OnClickListener() {

        //   @Override
        //  public void onClick(View v) {
        //      showup();

        //  }

        // private void showup() {
        //   startplay();

        //}
        // });
        // }
        // private void startplay() {
        //  Intent intent=new Intent(this,playing.class);
        //  startActivity(intent);
        // }
      //  imageView = (ImageView)findViewById(R.id.logo);


       // int index=0;
      //  Picasso.with(getBaseContext())
      //          .load(common.questionlist.get(index).getQuestion())
       //         .into(imageView);

        database = FirebaseDatabase.getInstance();
        questionss = database.getReference("Questions");
        ppers = database.getReference("paper");

       // ppers.orderByChild("")
//new
        int Pno = common.PPno;
        int k0 = 01;
        String ppno = Integer.toString(Pno);
        String twoDigit = String.format("%02d", Pno);
        // String kko = Integer.toString(k0);
       // LoadQuestionss(ppno);



       LoadQuestions(common.catogeryId, twoDigit);
        co = (TextView)findViewById(R.id.coom);
        btnplay = (Button)findViewById(R.id.btnply);
        btnnorush = (Button)findViewById(R.id.btnply2);

        co.setText(common.catogeryId+"_"+twoDigit+"_"+common.papperno);

        btnplay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(start.this,playing.class);
                startActivity(intent);
                finish();
            }
        });

        btnnorush.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(start.this,plynnotime.class);
               // int at = common.attepmt;
                startActivity(intent);
                finish();
            }
        });


    }




    private void LoadQuestions(String catogeryId,String kk) {

        //first clear list if have old questions
        if (common.questionlist.size() > 0)
            common.questionlist.clear();


        questionss.orderByChild("CategoryId_pno").equalTo(catogeryId+"_"+kk)
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







        // random list
        Collections.shuffle(common.questionlist);

    }



/*
    public void LoadQuestionss(String pp){

        if (common.questionlist.size() > 0)
            common.questionlist.clear();


        questionss.orderByChild("pno").equalTo(pp)
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

    }  */
}

