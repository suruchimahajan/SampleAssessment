package com.techmahindra.sampletest.api;

import com.techmahindra.sampletest.model.AlbumData;
import com.techmahindra.sampletest.model.UserInfo;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiUrl {


    String BASE_URL = "https://jsonplaceholder.typicode.com/";

    @GET("users")
    Call<ArrayList<UserInfo>> getUserInfo();

    @GET("photos")
    Call<ArrayList<AlbumData>> getAlbumData();
}
