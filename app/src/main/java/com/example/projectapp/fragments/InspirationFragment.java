package com.example.projectapp.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.projectapp.R;
import com.example.projectapp.networking.Meal;
import com.example.projectapp.networking.MealViewModel;


public class InspirationFragment extends Fragment {
   // TextView tv_name;

    MealViewModel viewModel;
 TextView tv_name;
    TextView tv_category;
    TextView tv_instructions;
    TextView tv_instructions_title;
    TextView   tv_link;


   // String myBaseUrl=" https://www.themealdb.com/api/json/v1/1/random.php/meals/";

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstance){
        View rootView=inflater.inflate(R.layout.inspiration_fragment,container,false);

        //View
        tv_category=rootView.findViewById(R.id.itv_category);
     tv_name=rootView.findViewById(R.id.itv_nam);
        tv_instructions_title=rootView.findViewById(R.id.itv_instruction_title);
        tv_instructions=rootView.findViewById(R.id.itv_instructions);
        tv_instructions.setVisibility(View.GONE);
tv_link=rootView.findViewById(R.id.itv_link);
        viewModel= ViewModelProviders.of(this).get(MealViewModel.class);
        viewModel.getMeal().observe(this, new Observer<Meal>() {
            @Override
            public void onChanged(Meal meal) {




tv_link.setText(meal.getStrYoutube());

             tv_name.setText(meal.getStrMeal());
             tv_category.setText(meal.getStrCategory());
             tv_instructions.setText(meal.getStrInstructions());


            }
        });


tv_instructions_title.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
    toggle_contents(v);
    }
});


        return rootView;
    }



    public  void toggle_contents(View view){
       tv_instructions.setVisibility(tv_instructions.isShown()? view.GONE: view.VISIBLE);
    }
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);}

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater=getActivity().getMenuInflater();
        inflater.inflate(R.menu.generate_inspiration_menu, menu);
        //  Toast.makeText(getActivity(), "Add something new to the Menu :)", Toast.LENGTH_SHORT).show();
        //   super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.share:
                shareFood();

                //   Toast.makeText(getActivity(), "Food added to Menu", Toast.LENGTH_SHORT).show();
               // getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new FoodFragment()).commit();
                break;
            case R.id.generate_food:
                viewModel.updateMeal();

            default:
                return super.onOptionsItemSelected(item);
        }return true;


    }
    public  void shareFood(){
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_EMAIL, "");
        intent.putExtra(Intent.EXTRA_SUBJECT, "Recipe for: "+tv_name.getText().toString());
        String str="";
        str+="Category: "+tv_category.getText().toString()+"\n";
        str+="\nVideo tutorial: "+tv_link.getText().toString()+"\n";
        str+="\nInstructions: "+tv_instructions.getText().toString();
        intent.putExtra(Intent.EXTRA_TEXT, str);
        startActivity(intent);

    }



}

