package com.example.projectapp.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.example.projectapp.R;


public class FamilyClub extends Fragment {
  //  private ImageView imageView;
    private Button button;
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstance){
        View rootView=inflater.inflate(R.layout.family_club_fragment,container,false);
   //     imageView =rootView.findViewById(R.id.glide_image);
       // button=rootView.findViewById(R.id.image_loader);


       return rootView;
    }
    private void loadImageByUrl(){
        String url="http://i.imgur.com/DvpvklR.png";
       // Glide.with(getContext()).load(url).into(imageView);
    }
}

