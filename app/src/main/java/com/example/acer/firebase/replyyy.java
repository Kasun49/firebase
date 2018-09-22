package com.example.acer.firebase;

import android.content.Intent;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.acer.firebase.common.common;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

public class replyyy extends AppCompatActivity implements View.OnClickListener{

    final static  long INTERVEL = 1000;
    final static  long TIMEOUT  = 7000;
    int ProgressValue =0;

    CountDownTimer mCountDown;
    int index=0,score=0,thisQuestion=0,totalQustion,correctAnswer;

    //firebase
    FirebaseDatabase database;
    DatabaseReference questions;

    ProgressBar pbar;
    ImageView imgv;
    Button ba,bb,bc,bd,be;
    TextView tscore,tqno,tqes;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_replyyy);


        database = FirebaseDatabase.getInstance();
        questions = database.getReference("Questions");
        imgv = (ImageView)findViewById(R.id.imagess);

        //   Picasso.with(getBaseContext())
        //            .load(common.questionlist.get(index).getQuestion())
        //            .into(imgv);

   // ShowQuest(++index);

        //view
        tscore = (TextView)findViewById(R.id.tscore);
        tqno=(TextView)findViewById(R.id.numqest);
        tqes=(TextView)findViewById(R.id.questions);

        pbar=(ProgressBar) findViewById(R.id.prog);

        ba=(Button)findViewById(R.id.buttonA);
        bb=(Button)findViewById(R.id.buttonB);
        bc=(Button)findViewById(R.id.buttonC);
        bd=(Button)findViewById(R.id.buttonD);
        be=(Button)findViewById(R.id.buttonF);

        ba.setOnClickListener(this);
        bb.setOnClickListener(this);
        bc.setOnClickListener(this);
        bd.setOnClickListener(this);
        be.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        if (index < totalQustion)
        {

            Button clickedButton = (Button) v;


            if (clickedButton.getText().equals(common.questionlist.get(index).getCorrectAnswer())){
                //choose correct answer

                score += 1;
                correctAnswer++;
                ShowQuest(++index);//next queelse

            }
    }
}

    private void ShowQuest(int i) {

        if (index < totalQustion){

            thisQuestion++;
            tqno.setText(String.format("%d/%s",thisQuestion,totalQustion));
            pbar.setProgress(0);
            ProgressValue=0;

            if (common.questionlist.get(index).getIsimageQuestion().equals("true")){

                //if fs a image
                Picasso.with(getBaseContext())
                        .load(common.questionlist.get(index).getQuestion())
                        .into(imgv);

                imgv.setVisibility(View.VISIBLE);
                tqes.setVisibility(View.INVISIBLE);

            }else{

                tqes.setText(common.questionlist.get(index).getQuestion());

                imgv.setVisibility(View.INVISIBLE);
                tqes.setVisibility(View.VISIBLE);

            }

            ba.setText(common.questionlist.get(index).getAnswerA());
            bb.setText(common.questionlist.get(index).getAnswerB());
            bc.setText(common.questionlist.get(index).getAnswerC());
            bd.setText(common.questionlist.get(index).getAnswerD());
            be.setText(common.questionlist.get(index).getAnswerE());
            mCountDown.start(); //start timer



        }
        else {

            //if it is the final question
            Intent intent = new Intent(this,done.class);
            Bundle dataSend = new Bundle();
            dataSend.putInt("SCORE",score );
            dataSend.putInt("TOTAL",totalQustion );
            dataSend.putInt("COREECT",correctAnswer );
            intent.putExtras(dataSend);
            startActivity(intent);
            finish();

        }



    }
    }
