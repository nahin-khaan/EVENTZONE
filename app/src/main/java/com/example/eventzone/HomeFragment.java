package com.example.eventzone;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import androidx.fragment.app.Fragment;

public class HomeFragment extends Fragment implements View.OnClickListener {
    MainActivity mainActivity;
    ImageButton createEventBt, createpostbtn, postlistbtn,stageImgBtn,foodImgBtn,videoImgBtn,venueImgBtn,carImgBtn,inviteImgBtn,makeupImgBtn,soundImgBtn;


    public HomeFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        createEventBt = view.findViewById(R.id.EventBtnId);
        createEventBt.setOnClickListener(this);

        createpostbtn = view.findViewById(R.id.createPostBtnId);
        createpostbtn.setOnClickListener(this);

        postlistbtn = view.findViewById(R.id.postlistBtnId);
        postlistbtn.setOnClickListener(this);

        stageImgBtn=view.findViewById(R.id.stageImgBtnId);
        stageImgBtn.setOnClickListener(this);

        foodImgBtn=view.findViewById(R.id.foodImgBtnId);
        foodImgBtn.setOnClickListener(this);

        videoImgBtn=view.findViewById(R.id.videoImgBtnId);
        videoImgBtn.setOnClickListener(this);

        venueImgBtn=view.findViewById(R.id.venueImgBtnId);
        venueImgBtn.setOnClickListener(this);

        carImgBtn=view.findViewById(R.id.carrenterImgBtnId);
        carImgBtn.setOnClickListener(this);

        inviteImgBtn=view.findViewById(R.id.inviteImgBtnId);
        inviteImgBtn.setOnClickListener(this);


        makeupImgBtn=view.findViewById(R.id.makeupImgBtnId);
        makeupImgBtn.setOnClickListener(this);

        soundImgBtn=view.findViewById(R.id.lightsoundImgBtnId);
        soundImgBtn.setOnClickListener(this);


        mainActivity = (MainActivity) getActivity();
        return view;


    }


    @Override
    public void onClick(View v) {

     if (v.getId()==R.id.EventBtnId)
     {
         Intent intent=new Intent(mainActivity, CategorySelect.class);
         startActivity(intent);
     }

        if (v.getId()==R.id.createPostBtnId) {
            Intent intent = new Intent(mainActivity, CreatePost.class);
            startActivity(intent);
        }
        if (v.getId() == R.id.postlistBtnId)
        {
            Intent intent = new Intent(getActivity(), PostList.class);
            startActivity(intent);
        }

        if (v.getId() == R.id.stageImgBtnId)
        {
            Intent intent = new Intent(getActivity(), StageDecorator.class);
            startActivity(intent);
        }
        if (v.getId() == R.id.foodImgBtnId)
        {
            Intent intent = new Intent(getActivity(), FoodDecorator.class);
            startActivity(intent);
        }

        if (v.getId() == R.id.videoImgBtnId)
        {
            Intent intent = new Intent(getActivity(), VideoDecorator.class);
            startActivity(intent);
        }

        if (v.getId() == R.id.venueImgBtnId)
        {
            Intent intent = new Intent(getActivity(), VenueDecorator.class);
            startActivity(intent);
        }

        if (v.getId() == R.id.carrenterImgBtnId)
        {
            Intent intent = new Intent(getActivity(), CarDecorator.class);
            startActivity(intent);
        }

        if (v.getId() == R.id.inviteImgBtnId)
        {
            Intent intent = new Intent(getActivity(), InviteDecorator.class);
            startActivity(intent);
        }

        if (v.getId() == R.id.makeupImgBtnId)
        {
            Intent intent = new Intent(getActivity(), MakeupDecorator.class);
            startActivity(intent);
        }

        if (v.getId() == R.id.lightsoundImgBtnId)
        {
            Intent intent = new Intent(getActivity(), SoundDecorator.class);
            startActivity(intent);
        }
    }


}


