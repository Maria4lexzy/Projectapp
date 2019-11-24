package com.example.projectapp.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projectapp.Adapter.DrinksAdapter;
import com.example.projectapp.R;
import com.example.projectapp.database.Drinks;
import com.example.projectapp.database.viewmodel.DrinkViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import static android.app.Activity.RESULT_OK;


public class DrinksFragment extends Fragment {
   /* public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstance){
        View rootView=inflater.inflate(R.layout.menu_fragment,container,false);
        return rootView;
    }

}*/
private DrinkViewModel drinksViewModel;
    private FloatingActionButton addDrinkButton;


    private RecyclerView recyclerView;
    private List<Drinks> drinks=new ArrayList<>();
    private DrinksAdapter adapter;
public static final int EDIT_DRINK=1;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstance){
        View rootView=inflater.inflate(R.layout.drink_fragment,container,false);

       addDrinkButton =rootView.findViewById(R.id.add_drink_btn);
        addDrinkButton.setOnClickListener(new View.OnClickListener() {
           @Override
            public void onClick(View v) {

              getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new AddDrinkFragment()).addToBackStack(null).commit();

            }
        });
        recyclerView=rootView.findViewById(R.id.drink_rv);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.hasFixedSize();

      drinksViewModel= ViewModelProviders.of(this).get(DrinkViewModel.class);
        drinksViewModel.getDrinkList().observe(this, new Observer<List<Drinks>>(){


            @Override
            public void onChanged(@Nullable List<Drinks> drinklist){
                //update the view
                drinks= new ArrayList<>();
                for (int i = 0; i < drinklist.size(); i++) {
                    drinks.add(drinklist.get(i));
                }

                adapter=new DrinksAdapter(drinks,getActivity());
                recyclerView.setAdapter(adapter);

            }
        });


new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT|ItemTouchHelper.RIGHT) {
    @Override
    public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
        return false;
    }

    @Override
    public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
drinksViewModel.delete(adapter.getDrinkAt(viewHolder.getAdapterPosition()));
        Toast.makeText(getActivity(), "Drink Deleted", Toast.LENGTH_SHORT).show();

    }
}).attachToRecyclerView(recyclerView);


        return rootView;

    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

         if (requestCode == EDIT_DRINK && resultCode == RESULT_OK) {
            int id = data.getIntExtra(AddDrinkFragment.EXTRA_ID, -1);

            if (id == -1) {
                Toast.makeText(getActivity(), "Unable to update entry", Toast.LENGTH_SHORT).show();
                return;
            }

            String name = data.getStringExtra(AddDrinkFragment.EXTRA_NAME);
            String description = data.getStringExtra(AddDrinkFragment.EXTRA_DESC);
            double price = data.getIntExtra(AddDrinkFragment.EXTRA_PRICE, 1);
             double capacity = data.getIntExtra(AddDrinkFragment.EXTRA_CAPACITY, 1);


             Drinks drink = new Drinks(name, description, price, capacity);
            drink.setId(id);
            drinksViewModel.update(drink);

            Toast.makeText(getActivity(), "Drink item updated", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(getActivity(), "Drink item was not saved", Toast.LENGTH_SHORT).show();
        }
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);}

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater=getActivity().getMenuInflater();
        inflater.inflate(R.menu.drinks_menu, menu);
      //  super.onCreateOptionsMenu(menu, inflater);
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.delete_all_drinks:
                drinksViewModel.deleteAll();

                Toast.makeText(getActivity(), "All Drink items have been deleted", Toast.LENGTH_SHORT).show();


                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

        }

