package com.example.eventzone;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class StageAdapter extends RecyclerView.Adapter<StageAdapter.MyViewHolder> implements Filterable {
    private Context context;
    private List<Category> categoryList;

    int row_index = -1;

    public StageAdapter(Context context, List<Category> categoryList) {
        this.context = context;
        this.categoryList = categoryList;
    }
    public void setFilteredList(List<Category> filteredList)
    {
        this.categoryList =filteredList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View views = layoutInflater.inflate(R.layout.eventtem, parent, false);
        return new MyViewHolder(views);
    }

    @Override
    public void onBindViewHolder(@NonNull StageAdapter.MyViewHolder holder, int position) {
        Category category = categoryList.get(position);
        holder.tittleText.setText("Tittle:  " + category.getTITTLE());
        holder.categoryText.setText("Category:  " + category.getCATEGORY());
        holder.desText.setText("Description:  " + category.getDESCRIPTION());
        holder.locationText.setText("Location:  " + category.getLOCATION());
        holder.dateText.setText("Date:   " + category.getDATE());
        holder.priceText.setText("Price:  " + category.getPRICE());
        holder.phoneText.setText("Phone No:  " + category.getPHONE());
        Picasso.get().load(category.getImageUri()).placeholder(R.mipmap.ic_launcher).fit().centerCrop().into(holder.imagePic);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), EventDetails.class);
                intent.putExtra("tittle", categoryList.get(position).getTITTLE());
                intent.putExtra("category", categoryList.get(position).getCATEGORY());
                intent.putExtra("description", categoryList.get(position).getDESCRIPTION());
                intent.putExtra("location", categoryList.get(position).getLOCATION());
                intent.putExtra("date", categoryList.get(position).getDATE());
                intent.putExtra("price", categoryList.get(position).getPRICE());
                intent.putExtra("phone", categoryList.get(position).getPHONE());
                intent.putExtra("image", categoryList.get(position).getImageUri());
                v.getContext().startActivity(intent);
            }
        });

    }


    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tittleText, categoryText, desText, locationText, dateText, priceText, phoneText;
        ImageView imagePic;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tittleText = itemView.findViewById(R.id.tittleItem);
            categoryText = itemView.findViewById(R.id.categoryItem);
            desText = itemView.findViewById(R.id.descriptionItem);
            locationText = itemView.findViewById(R.id.locationItem);
            dateText = itemView.findViewById(R.id.dateItem);
            priceText = itemView.findViewById(R.id.priceItem);
            phoneText = itemView.findViewById(R.id.phoneItem);
            imagePic = itemView.findViewById(R.id.imgItem);
        }
    }

    @Override
    public int getItemCount() {
        return categoryList.size();
    }

    @Override
    public Filter getFilter() {
        return null;
    }
}
