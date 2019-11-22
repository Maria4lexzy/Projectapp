package com.example.projectapp.fragments;

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


public class DrinksFragment extends Fragment {
   /* public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstance){
        View rootView=inflater.inflate(R.layout.menu_fragment,container,false);
        return rootView;
    }

}*/
private DrinkViewModel drinksViewModel;
    private FloatingActionButton addDrinkButton;

    private DrinksAdapter adapter;
    private RecyclerView recyclerView;
    private List<Drinks> drinks;


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

