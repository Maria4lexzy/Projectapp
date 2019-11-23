package com.example.projectapp.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projectapp.R;
import com.example.projectapp.database.Drinks;


import java.util.List;

public class DrinksAdapter extends RecyclerView.Adapter<DrinksAdapter.DrinkHolder> {
private List<Drinks> drinks;
 private Context context;

public DrinksAdapter(List<Drinks> drinks, Context context){
    this.drinks = drinks;
    this.context=context;

}
    @Override
    public DrinkHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
      View itemView= LayoutInflater.from(parent.getContext()).inflate(R.layout.drink_menu_items, parent, false);
        return new DrinkHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull DrinkHolder holder, int position) {
Drinks currentDrink= drinks.get(position);
holder.tv_drink_price.setText(String.valueOf(currentDrink.getPrice()));
holder.tv_drink_name.setText(currentDrink.getDrinkName());
holder.tv_drink_desc.setText(currentDrink.getDrinkDescription());
holder.tv_drink_capacity.setText(String.valueOf(currentDrink.getCapacity()));
    }

    @Override
    public int getItemCount() {
        return drinks.size();
    }


/*public void setFoodList(List<Food> drinks){
        this.drinks=drinks;
        notifyDataSetChanged();
}*/
public Drinks getDrinkAt(int position) {
    return drinks.get(position);
}
   public class DrinkHolder extends  RecyclerView.ViewHolder{
          TextView tv_drink_name;
          TextView tv_drink_price;
          TextView tv_drink_desc;
          TextView tv_drink_capacity;
public DrinkHolder(View itemView){
    super(itemView);
    tv_drink_desc =itemView.findViewById(R.id.tv_drink_desc);
    tv_drink_name =itemView.findViewById(R.id.tv_drink_name);
    tv_drink_price =itemView.findViewById(R.id.tv_drink_price);
    tv_drink_capacity=itemView.findViewById(R.id.tv_drink_cap);
}
    }

}
