package com.techmahindra.sampletest.ui;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.techmahindra.sampletest.R;
import com.techmahindra.sampletest.model.AlbumData;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

public class AlbumDataAdapter extends RecyclerView.Adapter<AlbumDataAdapter.ViewHolder> {
    Context context; // decalaration of the activity instance
    List<AlbumData> albumDataList; // decalaration of model class Albumdata in a list

    public AlbumDataAdapter(Context context, List<AlbumData> albumDataList) {
        this.context = context;
        this.albumDataList = albumDataList;
    }


    @NonNull
    @Override
    public AlbumDataAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.screen2_rowlayout,null);
        AlbumDataAdapter.ViewHolder holder = new AlbumDataAdapter.ViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull AlbumDataAdapter.ViewHolder viewholder, int position) {
        Log.e("uRl",albumDataList.get(position).getThumbnailUrl());
        Picasso.with(context).load(albumDataList.get(position).getThumbnailUrl()).placeholder(R.drawable.ic_launcher_background).error(R.drawable.ic_launcher_background).into(viewholder.image);
        viewholder.imgtxt.setText(albumDataList.get(position).getTitle());

        // getting the clicked data from the recyclerview list by setting up the onclicklistener
        viewholder.ctlayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent screen3= new Intent(context, Screen3.class);
                screen3.putExtra("AlbumId",albumDataList.get(position).getAlbumId());
                screen3.putExtra("PhotoId",albumDataList.get(position).getId());
                screen3.putExtra("URL",albumDataList.get(position).getUrl());
                screen3.putExtra("ImageText",albumDataList.get(position).getTitle());
                context.startActivity(screen3); // calling teh new activity

            }
        });

    }

    @Override
    public int getItemCount() {
        return albumDataList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView image;
        TextView imgtxt;
        ConstraintLayout ctlayout;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            image=(ImageView) itemView.findViewById(R.id.screen2_imageView);
            imgtxt=(TextView) itemView.findViewById(R.id.tx_imgtext);
            ctlayout=(ConstraintLayout) itemView.findViewById(R.id.screen2_ctlayout);
        }
    }
}
