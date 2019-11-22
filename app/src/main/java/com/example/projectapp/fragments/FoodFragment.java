package com.example.projectapp.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projectapp.Adapter.FoodAdapter;
import com.example.projectapp.R;

import com.example.projectapp.database.Food;
import com.example.projectapp.database.viewmodel.FoodViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;


public class FoodFragment extends Fragment {


    private FloatingActionButton addFoodbtn;

    private FoodAdapter adapter;
    private RecyclerView recyclerView;
    private List<Food> foods;
private FoodViewModel foodViewModel;
public static final int EDIT_FOOD_ITEM_REQ=1;
    public View onCreateView(final LayoutInflater inflater, ViewGroup container, Bundle savedInstance){
        View rootView=inflater.inflate(R.layout.menu_fragment,container,false);

        addFoodbtn =rootView.findViewById(R.id.add_btn);

        recyclerView=rootView.findViewById(R.id.menu_rv);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.hasFixedSize();

        foodViewModel= ViewModelProviders.of(this).get(FoodViewModel.class);
        foodViewModel.getFoodList().observe(this, new Observer<List<Food>>(){
            @Override
            public void onChanged(@Nullable List<Food> foodList){
                //update the view
                foods= new ArrayList<>();
                for (int i = 0; i < foodList.size(); i++) {
                    foods.add(foodList.get(i));
                }

                adapter=new FoodAdapter(foods,getActivity());
                recyclerView.setAdapter(adapter);
            }
        });
        addFoodbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new AddFoodFragment()).addToBackStack(null).commit();

            }
        });
        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT|ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                foodViewModel.delete(adapter.getFoodAt(viewHolder.getAdapterPosition()));
Toast.makeText(getActivity(), "Food  item deleted", Toast.LENGTH_SHORT).show();
            }
        }).attachToRecyclerView(recyclerView);

        return rootView;

    }
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);}

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater=getActivity().getMenuInflater();
        inflater.inflate(R.menu.menu_menu, menu);
        //  super.onCreateOptionsMenu(menu, inflater);
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.delete_all_food:
                foodViewModel.deleteAll();
                Toast.makeText(getActivity(), "All food  items have been deleted", Toast.LENGTH_SHORT).show();

              //  Toast.makeText(getActivity(), "All Drink items have been deleted", Toast.LENGTH_SHORT).show();


                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}

