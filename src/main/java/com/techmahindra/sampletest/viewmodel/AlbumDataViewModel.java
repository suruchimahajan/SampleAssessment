package com.techmahindra.sampletest.viewmodel;

import android.util.Log;

import com.techmahindra.sampletest.api.ApiUrl;
import com.techmahindra.sampletest.model.AlbumData;

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

public class AlbumDataViewModel extends ViewModel {

    private MutableLiveData<List<AlbumData>> albumdata;
    private List<AlbumData> dataset;
    private int selectedId;
    public LiveData<List<AlbumData>> getAlbumData(){


        //if the list is null
        if (albumdata == null) {
            albumdata = new MutableLiveData<List<AlbumData>>();
            //we will load it asynchronously from server in this method

            loadAlbumData();

        }
        return albumdata;

    }
    /* getting the  albumid of the selected list from the screen 2 class recyclerview*/
    public void getIntentData(int id) {
        selectedId=id;
        Log.i("selected",String.valueOf(selectedId));

    }

    private void loadAlbumData() {
        /*opening and setting
         * network request
         * */
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ApiUrl.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();


        ApiUrl api = retrofit.create(ApiUrl.class);
        Call<ArrayList<AlbumData>> call = api.getAlbumData();

        call.enqueue(new Callback<ArrayList<AlbumData>>() {
            @Override
            public void onResponse(Call<ArrayList<AlbumData>> call, Response<ArrayList<AlbumData>> response) {
                if(response.isSuccessful()){
                    List<AlbumData> list=response.body();

                    dataset= new ArrayList<>(); // empty arraylist

                    /* saving the selected albumid data only to a new list  and
                     *  upadting the data in albumdata model class
                     * */
                    for(int j=0;j<list.size();j++)
                    {
                        if(String.valueOf(list.get(j).getAlbumId()).equals(String.valueOf(selectedId)))
                        {
                            //adding the data to the empty list data set
                            dataset.add(new AlbumData(list.get(j).getAlbumId(),list.get(j).getId(),list.get(j).getTitle(),list.get(j).getUrl(),list.get(j).getThumbnailUrl()));
                        }


                    }
                    albumdata.setValue(dataset); // updating data of album data model class


                }
            }

            @Override
            public void onFailure(Call<ArrayList<AlbumData>> call, Throwable t) {
                Log.i("Error>>> ",call.toString());
            }
        });

           }

}
