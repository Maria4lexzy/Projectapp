package com.example.projectapp.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.example.projectapp.R;


public class FamilyClub extends Fragment {
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstance){
        View rootView=inflater.inflate(R.layout.family_club_fragment,container,false);
        return rootView;
    }
}

