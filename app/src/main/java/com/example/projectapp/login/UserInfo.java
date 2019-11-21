package com.example.projectapp.login;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class UserInfo {
    FirebaseAuth firebaseAuth;
    FirebaseUser firebaseUser;
    private static UserInfo instance;
    public  UserInfo(){
        firebaseAuth=FirebaseAuth.getInstance();
                firebaseUser= firebaseAuth.getCurrentUser();
    }
    public static UserInfo getInstance(){

        if(instance==null){
            instance=new UserInfo();
        }
        return instance;
    }
    public  FirebaseUser getFirebaseUser(){
        return firebaseUser;
    }
}
