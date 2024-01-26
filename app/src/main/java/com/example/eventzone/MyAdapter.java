package com.example.eventzone;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.core.Context;
import com.squareup.picasso.Picasso;

import java.util.List;


public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    private PostList context;
    private List<Upload> uploadList;

    public MyAdapter(PostList context, List<Upload> uploadList) {
        this.context = context;
        this.uploadList = uploadList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.postitem, parent, false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Upload upload = uploadList.get(position);
        holder.textView.setText(upload.getImageName());
        Picasso.get().load(upload.getImgUri()).placeholder(R.mipmap.ic_launcher).fit().centerCrop().into(holder.imageView);

    }

    @Override
    public int getItemCount() {
        return uploadList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView textView;
        ImageView imageView;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.cardTextId);
            imageView = itemView.findViewById(R.id.cardImageViewId);
        }
    }
}
