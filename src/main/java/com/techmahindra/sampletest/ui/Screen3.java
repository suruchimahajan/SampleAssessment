package com.techmahindra.sampletest.ui;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.techmahindra.sampletest.R;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class Screen3 extends AppCompatActivity {

    private TextView txphotoId,txalbumid,tximagetxt;// decalaration of the text views
    private ImageView image;//decalaration of the image views for url setup

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.screen3);

        // instantiation of the views
        txphotoId=(TextView) findViewById(R.id.screen3_txphotoid);
        txalbumid=(TextView) findViewById(R.id.screen3_txalbumid);
        tximagetxt=(TextView) findViewById(R.id.screen3_tximagtxt);
        image=(ImageView) findViewById(R.id.screen3_image);

        // peforming intent data retrieval from other activity by cally this function
        getIntentdata();

    }
    private void getIntentdata() {

        if(getIntent().hasExtra("AlbumId")||getIntent().hasExtra("PhotoId")|| getIntent().hasExtra("URL")||getIntent().hasExtra("ImageText"))
        {

            String photoID="Photo ID: "+String.valueOf(getIntent().getIntExtra("PhotoId",0));
            String AlbumID="Album ID: "+String.valueOf(getIntent().getIntExtra("AlbumId",0));
            txphotoId.setText(photoID);
            txalbumid.setText(AlbumID);
            tximagetxt.setText(String.valueOf(getIntent().getStringExtra("ImageText")));

            // setting the url to image source through picasso library
            Picasso.with(this).load(getIntent().getStringExtra("URL")).placeholder(R.drawable.ic_launcher_background).error(R.drawable.ic_launcher_background).into(image);
        }
    }




}
