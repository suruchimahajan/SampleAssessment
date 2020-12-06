package com.techmahindra.sampletest.ui;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.techmahindra.sampletest.R;
import com.techmahindra.sampletest.model.UserInfo;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

public class UserInfoAdapter extends RecyclerView.Adapter<UserInfoAdapter.ViewHolder> {

    Context context; // declaring the instance of the activity
    List<UserInfo> list;// declaring the model class User info into a list
    private View.OnClickListener onItemClickListener;

    // constructor
    public UserInfoAdapter(Context context, List<UserInfo> list) {
        this.context = context;
        this.list = list;
    }


    @NonNull
    @Override
    public UserInfoAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.screen1_rowlayout,null);
        ViewHolder holder = new ViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull UserInfoAdapter.ViewHolder viewHolder, int position) {
        viewHolder.id.setText(String.valueOf(list.get(position).getId()));
        viewHolder.username.setText(list.get(position).getName());
        viewHolder.email.setText(list.get(position).getEmail());
        viewHolder.phone.setText(list.get(position).getPhone());
        viewHolder.ctlayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // calling the new activity screen 2
                Intent screen2data = new Intent(context, Screen2.class);
                screen2data.putExtra("ID", list.get(position).getId());// passig the data through intents
                context.startActivity(screen2data);

            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView username, email, id, phone;
        ConstraintLayout ctlayout;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            id=(TextView) itemView.findViewById(R.id.tx_userId);
            username=(TextView) itemView.findViewById(R.id.tx_userName);
            email=(TextView) itemView.findViewById(R.id.tx_userEmail);
            phone=(TextView) itemView.findViewById(R.id.tx_userPhone);
            ctlayout=(ConstraintLayout) itemView.findViewById(R.id.ct_userinfo);
        }
    }
}
