package com.example.projectapp.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.projectapp.R;
import com.google.android.material.navigation.NavigationView;
import com.example.projectapp.fragments.*;


public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    Toolbar myToolbar;
    View mHeaderView;
    private TextView htv_name;
    private TextView htv_email;
    private ImageView profilePic;
    private static final int CAMERA_REQUEST = 1888;
    private static final int MY_PERMISSIONS_REQUEST = 100;
    UserInfo userInfo = UserInfo.getInstance();

    private DrawerLayout drawerLayout;
    NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//welcome user
        //Tool bar
        myToolbar = findViewById(R.id.my_toolbar);
        // myToolbar.setTitle("Bone\'s");
        setSupportActionBar(myToolbar);

        navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        drawerLayout = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, myToolbar, R.string.nav_drawer_open, R.string.nav_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new FoodFragment()).commit();
            navigationView.setCheckedItem(R.id.nav_menu);
        }

        //Header
        navigationView = findViewById(R.id.nav_view);
        mHeaderView = navigationView.getHeaderView(0);

        //Header content
        htv_email = mHeaderView.findViewById(R.id.tv_user_email_nav);
        htv_name = mHeaderView.findViewById(R.id.tv_username_nav);
        profilePic = findViewById(R.id.profile_img);
        //set username and password
      htv_email.setText(userInfo.getFirebaseUser().getEmail());
      htv_name.setText(userInfo.getFirebaseUser().getDisplayName());


    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);
        Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.fragment_container);
        fragment.onActivityResult(requestCode, resultCode, data);
        ImageStorage imageToISModule = new ImageStorage();
        if (requestCode == CAMERA_REQUEST && resultCode == Activity.RESULT_OK) {
            Bitmap photo = (Bitmap) data.getExtras().get("data");
            profilePic.setImageBitmap(photo);
            imageToISModule.saveToInternalStorage(photo, this, "img");

        }


    }

    public void onClickImg(View v) {

        requestStoragePermission();
        Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(cameraIntent, CAMERA_REQUEST);


    }

    private void requestStoragePermission() {
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                    MY_PERMISSIONS_REQUEST);
        }

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.nav_menu:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new FoodFragment()).commit();
                break;
            case R.id.nav_drinks:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new DrinksFragment()).commit();
                break;
            case R.id.nav_family_club:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new FamilyClub()).commit();
                break;
            case R.id.nav_fav:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new InspirationFragment()).commit();
                break;
            case R.id.nav_account:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new ProfileFragment()).commit();
                break;

        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else super.onBackPressed();
    }
}
