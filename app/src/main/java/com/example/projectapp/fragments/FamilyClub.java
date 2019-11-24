package com.example.projectapp.fragments;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.example.projectapp.R;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;


public class FamilyClub extends Fragment {
    private static final int CAMERA_REQUEST = 1888;
    private static final int MY_CAMERA_PERMISSION_CODE = 100;
    private static final int PICK_FROM_GALLERY = 1;
    private static final int MY_PERMISSIONS_REQUEST = 100;

    private Button btn_camera;
    private ImageView camera_image;

    private Button btn_gallery;
    private EditText img_name;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstance) {
        View rootView = inflater.inflate(R.layout.family_club_fragment, container, false);
        camera_image = rootView.findViewById(R.id.camera_image);
        btn_camera = rootView.findViewById(R.id.btn_camera);


        btn_gallery = rootView.findViewById(R.id.btn_gallery);


        // btn_camera=rootView.findViewById(R.id.image_loader);
//loadImageByUrl();
        btn_gallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        btn_camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                requestStoragePermission();
                Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(cameraIntent, CAMERA_REQUEST);


            }
        });
        /*






         */

        return rootView;
    }


    private void requestStoragePermission() {
        if (ContextCompat.checkSelfPermission(getActivity(),
                Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(getActivity(),
                    new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                    MY_PERMISSIONS_REQUEST);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        ImageToISModule imageToISModule = new ImageToISModule();
        if (requestCode == CAMERA_REQUEST && resultCode == Activity.RESULT_OK) {
            Bitmap photo = (Bitmap) data.getExtras().get("data");
            camera_image.setImageBitmap(photo);
            imageToISModule.saveToInternalStorage(photo, getActivity(), "img");
        } else if (requestCode == PICK_FROM_GALLERY && resultCode == Activity.RESULT_OK) {
            Bitmap photo = (Bitmap) data.getExtras().get("data");
            camera_image.setImageBitmap(photo);
        }


        super.onActivityResult(requestCode, resultCode, data);
    }

    public class ImageToISModule {

        public String saveToInternalStorage(Bitmap bitmapImage, Context context, String imageName) {
            ContextWrapper cw = new ContextWrapper(context);

            // path to /data/data/yourapp/app_data/DesignerNote
            File directory = cw.getDir("Projectapp", Context.MODE_PRIVATE);
            File mypath = new File(directory, imageName + ".jpg");
            FileOutputStream fos = null;
            try {
                fos = new FileOutputStream(mypath);
                bitmapImage.compress(Bitmap.CompressFormat.PNG, 1, fos);
                return "Image successfully saved";
            } catch (Exception e) {
                e.printStackTrace();
                return "Problem with saving image";
            } finally {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        public Bitmap loadImageFromStorage(String path, Context context) {

            try {
                ContextWrapper cw = new ContextWrapper(context);
                File directory = cw.getDir("Projectapp", Context.MODE_PRIVATE);
                File mypath = new File(directory, path);
                Bitmap b = BitmapFactory.decodeStream(new FileInputStream(mypath));
                return b;
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                return null;
            }

        }
    }

}

