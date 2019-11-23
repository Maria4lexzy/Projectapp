package com.example.projectapp.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.projectapp.R;
import com.example.projectapp.database.Food;

import java.util.ArrayList;
import java.util.List;

public class FoodAdapter extends RecyclerView.Adapter<FoodAdapter.FoodHolder> {
private List<Food>foodList;
 private Context context;
private OnListIemClickListener listListener;
public  FoodAdapter(List<Food> foodList, Context context){
    this.foodList=foodList;
    this.context=context;

}
    @Override
    public FoodHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
      View itemView= LayoutInflater.from(parent.getContext()).inflate(R.layout.food_menu_items, parent, false);
        return new FoodHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull FoodHolder holder, int position) {
Food currentFood=foodList.get(position);
holder.tv_food_price.setText(String.valueOf(currentFood.getPrice()));
holder.tv_food_name.setText(currentFood.getFoodName());
holder.tv_food_dec.setText(currentFood.getFoodDescription());
holder.tv_food_weight.setText(String.valueOf(currentFood.getWeight()));
    }

    @Override
    public int getItemCount() {
        return foodList.size();
    }


/*public void setFoodList(List<Food> foodList){
        this.foodList=foodList;
        notifyDataSetChanged();
}*/
public Food getFoodAt(int position) {
    return foodList.get(position);
}
   public class FoodHolder extends  RecyclerView.ViewHolder{
          TextView tv_food_name;
          TextView tv_food_price;
          TextView tv_food_dec;
          TextView tv_food_weight;
public FoodHolder(View itemView){
    super(itemView);
    tv_food_dec=itemView.findViewById(R.id.tv_food_desc);
    tv_food_name=itemView.findViewById(R.id.tv_food_name);
    tv_food_price=itemView.findViewById(R.id.tv_price);
    tv_food_weight=itemView.findViewById(R.id.tv_food_weight);
itemView.setOnClickListener(new View.OnClickListener(){
    @Override
    public void onClick(View v) {
        int position=getAdapterPosition();
        if(listListener!=null && position!=RecyclerView.NO_POSITION){
        listListener.OnListItemClick(foodList.get(position));
    }}
});
}
    }
    public  interface  OnListIemClickListener{
    void OnListItemClick(Food food);
    }public void  setOnListItemCLickListener(OnListIemClickListener listListener){
    this.listListener=listListener;
    }

}
