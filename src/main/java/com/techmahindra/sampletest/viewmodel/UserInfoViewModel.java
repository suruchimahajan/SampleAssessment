package com.techmahindra.sampletest.viewmodel;

import com.techmahindra.sampletest.api.ApiUrl;
import com.techmahindra.sampletest.model.UserInfo;

import java.util.ArrayList;
import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class UserInfoViewModel extends ViewModel {

    private MutableLiveData<List<UserInfo>> userInfo; // decalring the mutable data

    /* getting the instance and setting the data*/
    public LiveData<List<UserInfo>> getUserInfo(){


        //if the list is null
        if (userInfo == null) {
            userInfo = new MutableLiveData<List<UserInfo>>();
            //we will load it asynchronously from server in this method
            userData();
        }
        return userInfo;

    }


    private void userData() {

        // building and calling the network connection with retrofit library
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ApiUrl.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();


        ApiUrl api = retrofit.create(ApiUrl.class);
        Call<ArrayList<UserInfo>> call = api.getUserInfo();

        // opening the network connection
       call.enqueue(new Callback<ArrayList<UserInfo>>() {
           @Override
           public void onResponse(Call<ArrayList<UserInfo>> call, Response<ArrayList<UserInfo>> response) {
               if(response.isSuccessful()){
                   List<UserInfo> list=response.body();
                   userInfo.setValue(list); // updating the data to userInfo model class


               }
           }

           @Override
           public void onFailure(Call<ArrayList<UserInfo>> call, Throwable t) {
               //Log.e("Error", "response 34: "+ t.tostring()) );
           }
       });
    }

}
