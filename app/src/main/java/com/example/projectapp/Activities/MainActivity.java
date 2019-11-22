package com.example.projectapp.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.projectapp.R;
import com.google.android.material.navigation.NavigationView;
import com.example.projectapp.fragments.*;


public class MainActivity extends AppCompatActivity implements  NavigationView.OnNavigationItemSelectedListener{
Toolbar myToolbar;
View mHeaderView;
    private  TextView htv_name;
    private  TextView   htv_email;
    private ImageView imageView;
    UserInfo   userInfo=UserInfo.getInstance();

private DrawerLayout drawerLayout;
NavigationView navigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//welcome user
        //Tool bar
        myToolbar=findViewById(R.id.my_toolbar);
       // myToolbar.setTitle("Bone\'s");
        setSupportActionBar(myToolbar);

        navigationView=findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        drawerLayout=findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle=new ActionBarDrawerToggle(this, drawerLayout, myToolbar, R.string.nav_drawer_open,R.string.nav_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        if(savedInstanceState==null){
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new FoodFragment()).commit();
        navigationView.setCheckedItem(R.id.nav_menu);}

        //Header
        navigationView=findViewById(R.id.nav_view);
        mHeaderView =navigationView.getHeaderView(0);

        //Header content
        htv_email=mHeaderView.findViewById(R.id.tv_user_email_nav);
        htv_name=mHeaderView.findViewById(R.id.tv_username_nav);

        //set username and password
        htv_email.setText(userInfo.getFirebaseUser().getEmail());
        htv_name.setText(userInfo.getFirebaseUser().getDisplayName());


    }

   

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.fragment_container);
        fragment.onActivityResult(requestCode, resultCode, data);
    }



    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()){
            case R.id.nav_menu:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new FoodFragment()).commit();
                break;
            case R.id.nav_drinks:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new DrinksFragment()).commit();
                break;
            case R.id.nav_family_club:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new FamilyClub()).commit();
                break;
            case R.id.nav_fav:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new InspirationFragment()).commit();
                break;
            case R.id.nav_account:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new ProfileFragment()).commit();
break;

        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onBackPressed(){
        if(drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        }
        else      super.onBackPressed();
    }
}
