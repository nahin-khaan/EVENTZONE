package com.example.eventzone;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class EventDetails extends AppCompatActivity {
    ImageView imgDetails;
    TextView tittleDetails,categoryDetails,descriptionDetails,locationDetails,dateDetails,priceDetails,phoneDetails;
    Button payevent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_details);
        imgDetails=findViewById(R.id.imageDetailsId);
        tittleDetails=findViewById(R.id.tittleDetailsId);
        categoryDetails=findViewById(R.id.categoryDetailsId);
        descriptionDetails=findViewById(R.id.descriptionDetailsId);
        locationDetails=findViewById(R.id.locationDetailsId);
        dateDetails=findViewById(R.id.dateDetailsId);
        priceDetails=findViewById(R.id.priceDetailsId);
        phoneDetails=findViewById(R.id.phoneDetailsId);
        payevent=findViewById(R.id.payEvntId);
        payevent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(EventDetails.this,Payement.class);
                startActivity(intent);
            }
        });


        Intent data=getIntent();
        String tit=data.getStringExtra("tittle");
        String cat=data.getStringExtra("category");
        String des=data.getStringExtra("description");
        String loc=data.getStringExtra("location");
        String dat=data.getStringExtra("date");
        String pri=data.getStringExtra("price");
        String phn=data.getStringExtra("phone");
        String img=data.getStringExtra("image");

        Picasso.get().load(img).placeholder(R.mipmap.ic_launcher).fit().centerCrop().into(imgDetails);
        tittleDetails.setText(tit);
        categoryDetails.setText(cat);
        descriptionDetails.setText(des);
        locationDetails.setText(loc);
        dateDetails.setText(dat);
        priceDetails.setText(pri);
        phoneDetails.setText(phn);

    }
}