package com.example.eventzone;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;


public class AccountFragment extends Fragment implements View.OnClickListener {

    MainActivity mainActivity;

    Button EditBtn,saveBtn,InviteFrndBtn;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
      View view= inflater.inflate(R.layout.fragment_account, container, false);

        mainActivity = (MainActivity) getActivity();
        return view;
    }

    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.share)
        {


        }


    }
}