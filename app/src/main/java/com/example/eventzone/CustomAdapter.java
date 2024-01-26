package com.example.eventzone;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class CustomAdapter extends ArrayAdapter<Event> {
    private Activity context;
    private List<Event> eventList;

    public CustomAdapter(Activity context, List<Event> eventList) {
        super(context,R.layout.eventtem, eventList);
        this.context = context;
        this.eventList = eventList;
    }

    @NonNull
    @Override
    public View getView(int position,  View convertView,  ViewGroup parent) {

        LayoutInflater layoutInflater=context.getLayoutInflater();
        View view=layoutInflater.inflate(R.layout.eventtem,null,true);
        Event event=eventList.get(position);
        TextView tit=view.findViewById(R.id.tittleItem);
        TextView cat=view.findViewById(R.id.categoryItem);
        TextView des=view.findViewById(R.id.descriptionItem);
        TextView loc=view.findViewById(R.id.locationItem);
        TextView dat=view.findViewById(R.id.dateItem);
        TextView  pri=view.findViewById(R.id.priceItem);
        TextView phn=view.findViewById(R.id.phoneItem);
        tit.setText("Tittle : "+event.getTittles());
        cat.setText("Category : "+event.getCategories());
        des.setText("Description : "+event.getDescriptions());
        loc.setText("Location : "+event.getLocations());
        dat.setText("Date: "+event.getDates());
        pri.setText("Price: "+event.getPrices());
        phn.setText("Phone No : "+event.getPhones());


        return view;
    }
}
