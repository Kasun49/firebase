package com.example.acer.firebase;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.acer.firebase.common.common;
import com.example.acer.firebase.model.questionScore;
import com.google.android.gms.common.api.CommonStatusCodes;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class done extends AppCompatActivity {

    Button tryagain;
    TextView txtresultscore,txtresques;
    ProgressBar progressBar;
    FirebaseDatabase database;
    DatabaseReference qscore;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_done);

        database = FirebaseDatabase.getInstance();
        qscore = database.getReference("Question_Score");

        txtresultscore =(TextView)findViewById(R.id.txttotalquestion);
        txtresques = (TextView)findViewById(R.id.totalscorer);
        tryagain = (Button)findViewById(R.id.trybtn);
        progressBar =(ProgressBar)findViewById(R.id.donepbar);

        tryagain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(done.this,Home.class);
                startActivity(intent);
                finish();


            }
        });

    // get data from bundell and sent them to View

        Bundle extra = getIntent().getExtras();
        if(extra != null)
        {
           int score = extra.getInt("SCORE");
            int TotalQ = extra.getInt("TOTAL");
            int Cans = extra.getInt("CORRECT");


         //   dataSend.putInt("SCORE",score );
         //   dataSend.putInt("TOTAL",totalQustion );
         //   dataSend.putInt("COREECT",correctAnswer );



            txtresultscore.setText(String.format("SCORE : %d",score));
            txtresques.setText(String.format("PAASED : %d / %d",Cans,TotalQ));
            progressBar.setMax(TotalQ);
            progressBar.setProgress(Cans);


            int Pno = common.PPno;
            int k0 = 01;
            String ppno = Integer.toString(Pno);
            String twoDigit = String.format("%02d", Pno);

            qscore.child(String.format("%s_%s_%s_%s", common.currrenttUser.getUsername(),
                                                common.catogeryId,twoDigit,common.PPno))
                        .setValue(new questionScore(String.format("%s_%s_%s", common.currrenttUser.getUsername(),
                                                    common.catogeryId,twoDigit),
                                                    common.currrenttUser.getUsername(),
                                                    String.valueOf(score),
                                                    common.catogeryId,
                                                    common.categoryname,
                                                    twoDigit

                        ));



        }

    }




}
