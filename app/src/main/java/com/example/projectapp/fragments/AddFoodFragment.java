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
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.example.projectapp.R;

import com.example.projectapp.database.Food;
import com.example.projectapp.database.viewmodel.FoodViewModel;


public class AddFoodFragment extends Fragment {
   /* public class MenuFragment extends Fragment {
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstance){
            View rootView=inflater.inflate(R.layout.add_food_fragment,container,false);
            return rootView;
        }

    }*/
   private FoodViewModel foodViewModel;
    private EditText editTextName;
    private  EditText editTextDesc;
    private  EditText editTextPrice;
    private  EditText editTextWeight;


    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstance){

        View rootView=inflater.inflate(R.layout.add_food_fragment,container,false);


        editTextName=rootView.findViewById(R.id.edit_food_name);
        editTextDesc=rootView.findViewById(R.id.edit_food_desc);
editTextWeight=rootView.findViewById(R.id.edit_food_weight);
        editTextPrice=rootView.findViewById(R.id.edit_food_price);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_close);
foodViewModel= ViewModelProviders.of(this).get(FoodViewModel.class);




        return rootView;
    }
public void  saveFood(){
     String name=editTextName.getText().toString();
     String desc=editTextDesc.getText().toString();
     String priceString=editTextPrice.getText().toString();
     double price=Double.parseDouble(priceString);
    String weightString=editTextWeight.getText().toString();
     String imageUrl="image";
    double weight=Double.parseDouble(weightString);

    Food food=new Food(name, desc,price, weight);
     if(name.trim().isEmpty() || desc.trim().isEmpty()|| priceString.trim().isEmpty()|| weightString.trim().isEmpty()) {
         Toast.makeText(getActivity(), "Fill out all fields", Toast.LENGTH_SHORT).show();



     }  else {
         foodViewModel.insert(food);

     }

}

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    setHasOptionsMenu(true);}

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
      inflater=getActivity().getMenuInflater();
        inflater.inflate(R.menu.add_food_menu, menu);
        Toast.makeText(getActivity(), "Add something new to the Menu :)", Toast.LENGTH_SHORT).show();
          super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.save_food:saveFood();

            Toast.makeText(getActivity(), "Food added to Menu", Toast.LENGTH_SHORT).show();
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new MenuFragment()).commit();

                return  true;
            default:

     return super.onOptionsItemSelected(item);}
    }
}

