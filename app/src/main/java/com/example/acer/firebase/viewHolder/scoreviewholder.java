package com.example.acer.firebase.viewHolder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.acer.firebase.R;

/**
 * Created by acer on 6/28/2018.
 */

public class scoreviewholder extends RecyclerView.ViewHolder {

    public TextView txt_name, txt_score;


    public scoreviewholder(View itemView) {
        super(itemView);

        txt_name = (TextView)itemView.findViewById(R.id.txtname);
        txt_score = (TextView)itemView.findViewById(R.id.txtscore);
    }
}
