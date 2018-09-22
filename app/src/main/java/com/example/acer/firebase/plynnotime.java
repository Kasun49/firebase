package com.example.acer.firebase;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.CountDownTimer;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.PopupWindow;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.acer.firebase.common.common;
import com.github.chrisbanes.photoview.PhotoView;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

public class plynnotime extends AppCompatActivity implements View.OnClickListener{

    final static  long INTERVEL = 1000;
    final static  long TIMEOUT  = 700000;
    int ProgressValue =0;

    CountDownTimer mCountDown;
    int index=0,score=0,thisQuestion=0,totalQustion,correctAnswer;

    //firebase
    FirebaseDatabase database;
    DatabaseReference questions;

    ProgressBar pbar;
    // ImageView imgv;
    PhotoView imgv;
    Button ba,bb,bc,bd,be,on;
    TextView tscore,tqno,tqes;






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_playing);
        //firebase
        database = FirebaseDatabase.getInstance();
        questions = database.getReference("Questions");
        imgv = (PhotoView) findViewById(R.id.photo_view);

        //   Picasso.with(getBaseContext())
        //            .load(common.questionlist.get(index).getQuestion())
        //            .into(imgv);



        //view
        tscore = (TextView)findViewById(R.id.tscore);
        tqno=(TextView)findViewById(R.id.numqest);
        tqes=(TextView)findViewById(R.id.questions);

        pbar=(ProgressBar) findViewById(R.id.prog);

        ba=(Button)findViewById(R.id.buttonAA);
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

        mCountDown.cancel();
        if (index < totalQustion)
        {

            Button clickedButton = (Button) v;
            Toast.makeText(plynnotime.this,"1load",Toast.LENGTH_SHORT).show();


            if (clickedButton.getText().equals(common.questionlist.get(index).getCorrectAnswer())){
                //choose correct answer

                score += 1;
                correctAnswer++;
                ShowQuest(++index);//next queelse
                Toast.makeText(plynnotime.this,"2load",Toast.LENGTH_SHORT).show();

            }
            else {

                //wrong
                Intent intent = new Intent(this,done.class);
                Bundle dataSend = new Bundle();
                dataSend.putInt("SCORE",score );
                dataSend.putInt("TOTAL",totalQustion );
                dataSend.putInt("CORRECT",correctAnswer );
                intent.putExtras(dataSend);
                startActivity(intent);
                finish();
                Toast.makeText(plynnotime.this,"3load",Toast.LENGTH_SHORT).show();
            }

        }
    }

    private void ShowQuest(int index) {

        if (index < totalQustion){

            thisQuestion++;
            tqno.setText(String.format("%d/%s",thisQuestion,totalQustion));
            pbar.setProgress(0);
            ProgressValue=0;
            Toast.makeText(plynnotime.this,"4load",Toast.LENGTH_SHORT).show();


            if (common.questionlist.get(index).getIsimageQuestion().equals("true")){

                //if fs a image
                Picasso.with(getBaseContext())
                        .load(common.questionlist.get(index).getQuestion())
                        .into(imgv);

                imgv.setVisibility(View.VISIBLE);
                tqes.setVisibility(View.INVISIBLE);
                Toast.makeText(plynnotime.this,"5load",Toast.LENGTH_SHORT).show();

            }else{

                //  tqes.setText(common.questionlist.get(index).getQuestion());

                // imgv.setVisibility(View.INVISIBLE);
                // tqes.setVisibility(View.VISIBLE);

                Picasso.with(getBaseContext())
                        .load(common.questionlist.get(index).getQuestion())
                        .into(imgv);

                imgv.setVisibility(View.INVISIBLE);
                tqes.setVisibility(View.VISIBLE);

                Toast.makeText(plynnotime.this,"6load",Toast.LENGTH_SHORT).show();

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
            Toast.makeText(plynnotime.this,"7load",Toast.LENGTH_SHORT).show();

        }





    }

    @Override
    protected void onResume() {
        super.onResume();

        totalQustion =  common.questionlist.size();
        mCountDown = new CountDownTimer(TIMEOUT,INTERVEL){
            @Override
            public void onTick(long minisec) {
                pbar.setProgress(ProgressValue);
                ProgressValue++;


            }

            @Override
            public void onFinish() {
                mCountDown.cancel();
                ShowQuest(++index);


            }
        };
        ShowQuest(index);

    }

}
