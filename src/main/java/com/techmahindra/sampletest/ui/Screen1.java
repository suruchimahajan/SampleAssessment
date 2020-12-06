package com.techmahindra.sampletest.ui;

import androidx.appcompat.app.AppCompatActivity;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import com.techmahindra.sampletest.R;
import com.techmahindra.sampletest.model.UserInfo;
import com.techmahindra.sampletest.viewmodel.UserInfoViewModel;

import java.util.ArrayList;
import java.util.List;

public class Screen1 extends AppCompatActivity {

    private RecyclerView recyclerView;// decalraing recyclerview
    private UserInfoAdapter userInfoAdapter;// decalraing adapter
    private ProgressBar progressBar;// decalraing progressbar
    private UserInfoViewModel userInfoViewModel; // decalraing scren1 viewmodel
    private RecyclerView.LayoutManager layoutManager; // declaring recyclerview layout manager
    private ArrayList<UserInfo> userList; // variable user info list
    ViewModelProvider.Factory factory = new ViewModelProvider.NewInstanceFactory();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.screen1);

        recyclerView=(RecyclerView) findViewById(R.id.screen1_recyclerview);
        progressBar= (ProgressBar)  findViewById(R.id.screen1_progressbar);
        //instantiating the list
        userList=new ArrayList<>();

        userInfoViewModel= new ViewModelProvider(this,factory).get(UserInfoViewModel.class);
        userInfoViewModel.getUserInfo().observe(this, new Observer<List<UserInfo>>() {
            @Override
            public void onChanged(List<UserInfo> userInfos) {
                if(userInfos.size()>0)
                {
                    progressBar.setVisibility(View.GONE);
                    try {
                        for (int i = 0; i < userInfos.size(); i++) {

                            userList.add(new UserInfo(userInfos.get(i).getId(),userInfos.get(i).getName(),userInfos.get(i).getEmail(),userInfos.get(i).getPhone()));
                        }

                    }catch (NullPointerException e)
                    {

                        Log.i("Error screen 1",e.toString());
                    }
                    userInfoAdapter.notifyDataSetChanged(); // notifying the adapter
                }

            }
        });
        // setting the adapter with the user list data
        intiRecyclerAdapter(userList);

    }

    private void intiRecyclerAdapter(ArrayList<UserInfo> list) {

        userInfoAdapter=new UserInfoAdapter(this,list);
        layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(userInfoAdapter);
    }

}