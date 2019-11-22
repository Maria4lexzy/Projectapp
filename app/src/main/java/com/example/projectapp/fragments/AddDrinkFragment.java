package com.example.projectapp.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.example.projectapp.R;
import com.example.projectapp.database.Drinks;
import com.example.projectapp.database.viewmodel.DrinkViewModel;


public class AddDrinkFragment extends Fragment {
   /* public class FoodFragment extends Fragment {
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstance){
            View rootView=inflater.inflate(R.layout.add_food_fragment,container,false);
            return rootView;
        }

    }*/
   private DrinkViewModel drinksViewModel;
    private EditText editTextName;
    private  EditText editTextDesc;
    private  EditText editTextPrice;
    private  EditText editTextWeight;

/*
    public static final String EXTRA_NAME ="com.example.projectapp.fragments.EXTRA_NAME";
    public static final String EXTRA_DESC ="com.example.projectapp.fragments.EXTRA_DESC";
    public static final String EXTRA_PRICE="com.example.projectapp.fragments.EXTRA_PRICE";
*/
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstance){


        View rootView=inflater.inflate(R.layout.add_drinks_fragment,container,false);
        setHasOptionsMenu(true);

        editTextName=rootView.findViewById(R.id.edit_drink_name);
        editTextDesc=rootView.findViewById(R.id.edit_drink_desc);
editTextWeight=rootView.findViewById(R.id.edit_drink_capacity);
        editTextPrice=rootView.findViewById(R.id.edit_drink_price);


drinksViewModel = ViewModelProviders.of(this).get(DrinkViewModel.class);




        return rootView;
    }
public void saveDrink(){
     String name=editTextName.getText().toString();
     String desc=editTextDesc.getText().toString();
     String priceString=editTextPrice.getText().toString();
     double price=Double.parseDouble(priceString);
    String capacityString=editTextWeight.getText().toString();
     String imageUrl="image";
    double capacity=Double.parseDouble(capacityString);

    Drinks drink=new Drinks(name, desc,price, capacity);
     if(name.trim().isEmpty() || desc.trim().isEmpty()|| priceString.trim().isEmpty()|| capacityString.trim().isEmpty()) {
         Toast.makeText(getActivity(), "Please fill out all fields", Toast.LENGTH_SHORT).show();



     }  else {
         drinksViewModel.insert(drink);

     }

}

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    setHasOptionsMenu(true);}

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
      inflater=getActivity().getMenuInflater();
        inflater.inflate(R.menu.add_drink_menu, menu);
        Toast.makeText(getActivity(), "Add something new to the  drinks Menu :)", Toast.LENGTH_SHORT).show();
           super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.save_drink:
                saveDrink();
                Toast.makeText(getActivity(), "Drink  item saved", Toast.LENGTH_SHORT).show();

                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new DrinksFragment()).commit();
                           return  true;
            default:

     return super.onOptionsItemSelected(item);}
    }

}

