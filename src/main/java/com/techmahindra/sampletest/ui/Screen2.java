package com.techmahindra.sampletest.ui;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.techmahindra.sampletest.R;
import com.techmahindra.sampletest.model.AlbumData;
import com.techmahindra.sampletest.viewmodel.AlbumDataViewModel;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class Screen2 extends AppCompatActivity {

    private RecyclerView recyclerView;// declaring recyclerview
    private AlbumDataAdapter albumDataAdapter;// decalring adapter
    private ProgressBar progressBar;// decalaring  progressbar
    private TextView txtoolbar; // declaring the textview of toolbar
    private AlbumDataViewModel albumDataViewModel; // screen 2 view model class declaration
    private RecyclerView.LayoutManager layoutManager; // recyclerview layout manager declaration
    private ArrayList<AlbumData> albumDataArrayList; // album data list variable
    ViewModelProvider.Factory factory = new ViewModelProvider.NewInstanceFactory();


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.screen2);

        recyclerView=(RecyclerView) findViewById(R.id.screen2_recyclerview);
        albumDataArrayList=new ArrayList<>();
        progressBar= (ProgressBar)  findViewById(R.id.screen2_progressbar);

        txtoolbar=(TextView) findViewById(R.id.txtoolbar) ;
        albumDataViewModel= new ViewModelProvider(this,factory).get(AlbumDataViewModel.class);
        // getting the incoming intent data
        getIncomingIntent();


        albumDataViewModel.getAlbumData().observe(this, new Observer<List<AlbumData>>() {
            @Override
            public void onChanged(List<AlbumData> albumData) {
                Log.i("Screen2 ",String.valueOf(albumData.size()));
                if(albumData.size()>0)
                {
                    progressBar.setVisibility(View.GONE);
                    try {
                        for (int i = 0; i < albumData.size(); i++) {

                            // adding the data in the list
                            albumDataArrayList.add(new AlbumData(albumData.get(i).getAlbumId(),albumData.get(i).getId(),albumData.get(i).getTitle(),albumData.get(i).getUrl(),albumData.get(i).getThumbnailUrl()));
                        }

                    }catch (NullPointerException e)
                    {

                        Log.i("Error screen 2",e.toString());
                    }
                    albumDataAdapter.notifyDataSetChanged(); // notifying the adapter for data changes
                }
            }
        });
        initAblumDataAdapter(albumDataArrayList);


    }


    /* retrieving the intents data from screen 1*/
    public  void getIncomingIntent() {

        if(getIntent().hasExtra("ID"))
        {
            int selectedID=getIntent().getIntExtra("ID",0);
            String s="Album ID: "+selectedID;
            txtoolbar.setText(s);
            albumDataViewModel.getIntentData(selectedID);// calling screen 2 viewmodel method

            // int id=getIntent().getIntExtra("ID",0);


        }

    }

    private void initAblumDataAdapter(ArrayList<AlbumData> albumDataArrayList) {


        albumDataAdapter=new AlbumDataAdapter(this,albumDataArrayList);
        layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        //Log.i("list data",screen1ViewModel.getUserInfo().getValue().toString());
        recyclerView.setAdapter(albumDataAdapter);
    }

}
