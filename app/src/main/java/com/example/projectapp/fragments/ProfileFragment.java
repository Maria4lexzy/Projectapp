package com.example.projectapp.fragments;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.media.Image;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.example.projectapp.Activities.ImageStorage;
import com.example.projectapp.R;
import com.example.projectapp.Activities.LoginActivity;
import com.example.projectapp.Activities.UserInfo;
import com.google.firebase.auth.FirebaseAuth;


public class ProfileFragment extends Fragment {
    private TextView tv_name;
    private TextView tv_email;

    private  Button btn_log_out;



private  View header;
    private static final int CAMERA_REQUEST = 1888;
    private static final int MY_PERMISSIONS_REQUEST = 100;

 UserInfo   userInfo=UserInfo.getInstance();
    Toolbar myToolbar;
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstance){
        View rootView=inflater.inflate(R.layout.profile_fragment,container,false);

        //View
      //  header= new

        tv_email=rootView.findViewById(R.id.tv_profile_email);
      tv_name=rootView.findViewById(R.id.tv_profile_name);
      btn_log_out=rootView.findViewById(R.id.profile_sign_out);

        tv_name.setText(  userInfo.getFirebaseUser().getDisplayName());
        tv_email.setText(userInfo.getFirebaseUser().getEmail());

    btn_log_out.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            FirebaseAuth.getInstance().signOut();
            startActivity(new Intent(getActivity(), LoginActivity.class));
        }
    });

        return rootView;
    }
}
