package com.example.eventzone;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

public class CategorySelect extends AppCompatActivity implements  View.OnClickListener{
    Button continueBtn;
    ImageButton stageslctImgBtn,foodslctImgBtn,videoslctImgBtn,venueslctImgBtn,carrentslctImgBtn,soundslctImgBtn,makeupslctImgBtn,inviteslctImgBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_select);
//hide tittle bar
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

//hide action bar
        getSupportActionBar().hide();

        stageslctImgBtn=findViewById(R.id.stageEventCreateId);
        stageslctImgBtn.setOnClickListener(this);


        foodslctImgBtn=findViewById(R.id.foodEventCreateId);
        foodslctImgBtn.setOnClickListener(this);


        videoslctImgBtn=findViewById(R.id.videoEventCreateId);
        videoslctImgBtn.setOnClickListener(this);


        venueslctImgBtn=findViewById(R.id.venueEventCreateId);
        venueslctImgBtn.setOnClickListener(this);


        carrentslctImgBtn=findViewById(R.id.careEventCreateId);
        carrentslctImgBtn.setOnClickListener(this);


        soundslctImgBtn=findViewById(R.id.soundEvntCreateId);
        soundslctImgBtn.setOnClickListener(this);


        makeupslctImgBtn=findViewById(R.id.makeupEventCreateId);
        makeupslctImgBtn.setOnClickListener(this);

        inviteslctImgBtn=findViewById(R.id.inviteEventCreateId);
        inviteslctImgBtn.setOnClickListener(this);


    }

    public void onClick(View v)

    {
        if(v.getId()==R.id.stageEventCreateId){
            Intent intent=new Intent(CategorySelect.this,StageCategoryCreate.class);
            startActivity(intent);
        }


        if(v.getId()==R.id.foodEventCreateId){
            Intent intent=new Intent(CategorySelect.this,FoodCategoryCreate.class);
            startActivity(intent);
        }

        if(v.getId()==R.id.videoEventCreateId){
            Intent intent=new Intent(CategorySelect.this,VideoCategoryCreate.class);
            startActivity(intent);
        }

        if(v.getId()==R.id.venueEventCreateId){
            Intent intent=new Intent(CategorySelect.this,VenueCategoryCreate.class);
            startActivity(intent);
        }

        if(v.getId()==R.id.careEventCreateId){
            Intent intent=new Intent(CategorySelect.this,CarrentCategoryCreate.class);
            startActivity(intent);
        }

        if(v.getId()==R.id.soundEvntCreateId){
            Intent intent=new Intent(CategorySelect.this,SoundCategoryCreate.class);
            startActivity(intent);
        }

        if(v.getId()==R.id.makeupEventCreateId){
            Intent intent=new Intent(CategorySelect.this,MakeupCategoryCreate.class);
            startActivity(intent);
        }

        if(v.getId()==R.id.inviteEventCreateId){
            Intent intent=new Intent(CategorySelect.this,InviteCategoryCreate.class);
            startActivity(intent);
        }

    }
}