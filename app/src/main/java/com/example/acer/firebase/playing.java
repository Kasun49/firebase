package com.example.acer.firebase;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.sip.SipSession;
import android.os.CountDownTimer;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.acer.firebase.common.common;
import com.github.chrisbanes.photoview.PhotoView;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

public class playing extends AppCompatActivity implements OnClickListener {

    final static  long INTERVEL = 1000;
    final static  long TIMEOUT  = 7000;
    int ProgressValue =0;

    public String answerA;
    String kk = answerA;


    private FloatingActionButton fab_widget,fab1_sad;
    private PopupWindow window;
    String TAG = "MainActivity";

    CountDownTimer mCountDown;
    int index=0,score=0,thisQuestion=0,totalQustion,correctAnswer;

//firebase
    FirebaseDatabase database;
    DatabaseReference questions;

    ProgressBar pbar;
   // ImageView imgv;
    PhotoView imgv;
    Button ba,bb,bc,bd,be,bk;
  //  Button btnncall,btnsound;
    TextView tscore,tqno,tqes;

    //popup window
   // public ChildEventListener objChild;

    Button on;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_playing);
        //firebase
        database = FirebaseDatabase.getInstance();
        questions = database.getReference("Questions");
        imgv = (PhotoView) findViewById(R.id.photo_view);

      //  objChild = new ShowPopupWindoww();

     //   Picasso.with(getBaseContext())
    //            .load(common.questionlist.get(index).getQuestion())
    //            .into(imgv);


        on =(Button)findViewById(R.id.best);
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

        //popUp
      //  btncall = (Button) findViewById(R.id.btncall);
     // btncall=(Button)findViewById(R.id.btncall);

      // Button btnncall=(Button)findViewById(R.id.btncall);
       // btncall.setOnClickListener(this);


       // btncall.setOnClickListener(this);

       // bk.setOnClickListener(this);
        ba.setOnClickListener(this);
        bb.setOnClickListener(this);
        bc.setOnClickListener(this);
        bd.setOnClickListener(this);
        be.setOnClickListener(this);

        //answerA

        //popup window

        fab_widget = (FloatingActionButton)findViewById(R.id.fab);
       // fab1_sad = (FloatingActionButton)findViewById(R.id.fab_sad);

        fab_widget.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e(TAG, " open popup window touch");
                ShowPopupWindoww();
               // on.setText(common.questionlist.get(index).getAnswerA());


            }

        });





    }


    public void ShowPopupWindoww() {

        try{

            //Button on,kl;

            LayoutInflater inflater = (LayoutInflater) playing.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View layout = inflater.inflate(R.layout.pppppop, null);
            window = new PopupWindow(layout, 310, 450, true);

            window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            window.setOutsideTouchable(true);
            window.showAtLocation(layout, Gravity.CENTER, 40, 60);

           // on = findViewById(R.id.best).setText();

            on.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {

                    Toast.makeText(playing.this,"hellooooo",Toast.LENGTH_SHORT).show();
                 //  on.setText("answerA");
                   // on.setText(kk);

                }

            });


            // kl = findViewById(R.id.onk);
          //  on.setText(common.questionlist.get(index).getAnswerA());
            Toast.makeText(playing.this,"done until here",Toast.LENGTH_SHORT).show();




           // public static OnClickListener on = new OnClickListener()









    }catch (Exception e){

    }
}







    @Override
    public void onClick(View v) {


        //on.setText("answerA");
        Log.e(TAG, " button call press ");
        window.dismiss();


        mCountDown.cancel();
        if (index < totalQustion)
        {

            Button clickedButton = (Button) v;
            Toast.makeText(playing.this,"1load",Toast.LENGTH_SHORT).show();




            if (clickedButton.getText().equals(common.questionlist.get(index).getCorrectAnswer())){
                //choose correct answer

                score += 1;
                correctAnswer++;
                ShowQuest(++index);//next queelse
                Toast.makeText(playing.this,"2load",Toast.LENGTH_SHORT).show();

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
                Toast.makeText(playing.this,"3load",Toast.LENGTH_SHORT).show();
            }

        }
    }

    private void ShowQuest(int index) {

        if (index < totalQustion){

            thisQuestion++;
            tqno.setText(String.format("%d/%s",thisQuestion,totalQustion));
            pbar.setProgress(0);
            ProgressValue=0;
           // Toast.makeText(playing.this,"4load",Toast.LENGTH_SHORT).show();
          //  String tt,kk;
           //  char kk = answerA;



            if (common.questionlist.get(index).getIsimageQuestion().equals("true")){

                //if fs a image
                Picasso.with(getBaseContext())
                        .load(common.questionlist.get(index).getQuestion())
                        .into(imgv);

                imgv.setVisibility(View.VISIBLE);
                tqes.setVisibility(View.INVISIBLE);
                Toast.makeText(playing.this,"5load",Toast.LENGTH_SHORT).show();

            }else{

              //  tqes.setText(common.questionlist.get(index).getQuestion());

               // imgv.setVisibility(View.INVISIBLE);
               // tqes.setVisibility(View.VISIBLE);

                Picasso.with(getBaseContext())
                        .load(common.questionlist.get(index).getQuestion())
                        .into(imgv);

                imgv.setVisibility(View.INVISIBLE);
                tqes.setVisibility(View.VISIBLE);

             //   Toast.makeText(playing.this,"6load",Toast.LENGTH_SHORT).show();

            }

            ba.setText(common.questionlist.get(index).getAnswerA());
            bb.setText(common.questionlist.get(index).getAnswerB());
            bc.setText(common.questionlist.get(index).getAnswerC());
            bd.setText(common.questionlist.get(index).getAnswerD());
            be.setText(common.questionlist.get(index).getAnswerE());
           // on.setText(common.questionlist.get(index).getAnswerE());

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
            Toast.makeText(playing.this,"7load",Toast.LENGTH_SHORT).show();

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


        // ihave edit this on 6/10  ++index to index
        ShowQuest(++index);

    }





}

