package com.example.acer.firebase;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.acer.firebase.Interface.ItemClickListner;
import com.example.acer.firebase.common.common;
import com.example.acer.firebase.model.cat;
import com.example.acer.firebase.viewHolder.categoryViewHolder;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;


public class pappers extends Fragment {

    View myfrag;
    RecyclerView listcat;
    RecyclerView.LayoutManager layoutManager;
    FirebaseRecyclerAdapter<cat, categoryViewHolder> adapter;

    FirebaseDatabase database;
    DatabaseReference categories,catt,on;



    public static pappers newInstance() {

        pappers Cat = new pappers();

        return Cat;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        database = FirebaseDatabase.getInstance();

        categories = database.getReference("category");
        catt = database.getReference("paper");






        //String ppno = Integer.toString(month);
/*
        switch (month)
        {
            case 1:

                categories = database.getReference("category");


                break;

            case 2:

                categories = database.getReference("paper");

                break;


        }

*/

    }




    @Nullable
    @Override


    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {



        myfrag = inflater.inflate(R.layout.fragment_pappers, container, false);

        listcat = (RecyclerView) myfrag.findViewById(R.id.listcatt);
        listcat.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(container.getContext());
        listcat.setLayoutManager(layoutManager);





            int i=common.PPno;

            if(i==1){

                loadcategoriess();

            }else if(i == 2){

                loadcategories();
            }else {


            }







        return myfrag;

        //edit


    }
    public void vet(){
        cat vet=new cat();
        vet.getImage();


    }



    private void loadcategories() {





        adapter = new FirebaseRecyclerAdapter<cat, categoryViewHolder>(
                cat.class,


                R.layout.category_layout,
                categoryViewHolder.class,
                catt

        ) {
            @Override
            protected void populateViewHolder(categoryViewHolder viewHolder, final cat model, int position) {
                viewHolder.category_name.setText(model.getName());
                Picasso.with(getActivity())
                        .load(model.getImage())
                        .into(viewHolder.category_image); // .load(model.getImage())


                viewHolder.setItemClickListner(new ItemClickListner() {
                    @Override
                    public void onClick(View view, int position, boolean isLongClick) {
                        //   Toast.makeText(getActivity(), String.format("%s|%s",adapter.getRef(position).getKey(),model.getName()), Toast.LENGTH_SHORT).show();

                        Intent startgame = new Intent(getActivity(),start.class);
                        common.papperno = adapter.getRef(position).getKey();
                        startActivity(startgame);


                    }
                });
            }
        };

        adapter.notifyDataSetChanged();
        listcat.setAdapter(adapter);

    }

    private void loadcategoriess() {





        adapter = new FirebaseRecyclerAdapter<cat, categoryViewHolder>(
                cat.class,


                R.layout.category_layout,
                categoryViewHolder.class,
                categories

        ) {
            @Override
            protected void populateViewHolder(categoryViewHolder viewHolder, final cat model, int position) {
                viewHolder.category_name.setText(model.getName());
                Picasso.with(getActivity())
                        .load(model.getImage())
                        .into(viewHolder.category_image); // .load(model.getImage())


                viewHolder.setItemClickListner(new ItemClickListner() {
                    @Override
                    public void onClick(View view, int position, boolean isLongClick) {
                        //   Toast.makeText(getActivity(), String.format("%s|%s",adapter.getRef(position).getKey(),model.getName()), Toast.LENGTH_SHORT).show();

                        Intent startgame = new Intent(getActivity(),start.class);
                        common.papperno = adapter.getRef(position).getKey();
                        startActivity(startgame);


                    }
                });
            }
        };

        adapter.notifyDataSetChanged();
        listcat.setAdapter(adapter);

    }
}


