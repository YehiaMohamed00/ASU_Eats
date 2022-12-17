package com.example.asueats.Model;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.asueats.R;

import java.util.List;

public class RestaurantAdapter extends RecyclerView.Adapter<RestaurantAdapter.RestViewHolder> {

    List<Restaurant> restaurantList;

    public RestaurantAdapter(List<Restaurant> restaurantList) {
        this.restaurantList = restaurantList;
    }

    @NonNull
    @Override
    public RestViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rstrnt_recycler_item, parent, false);
        return new RestViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RestViewHolder holder, int position) {
        int restImg= restaurantList.get(position).getRestImg();
        String restName = restaurantList.get(position).getRestName();
        String restCousine = restaurantList.get(position).getRestCousine();
        String restPriceRange =  restaurantList.get(position).getRestPriceRange();


        holder.RVHrestImg.setImageResource(restImg);
        holder.RVHrestName.setText(restName);
        String concatenated = restCousine + ", " + restPriceRange;
        holder.RVHrestCousineNrestPriceRange.setText(concatenated);
    }

    @Override
    public int getItemCount() {
        return restaurantList.size();
    }

    public class RestViewHolder extends RecyclerView.ViewHolder{

        public ImageView RVHrestImg;
        TextView RVHrestName, RVHrestCousineNrestPriceRange;

        public RestViewHolder(@NonNull View itemView) {
            super(itemView);

            RVHrestImg = itemView.findViewById(R.id.rri_rest_img);
            RVHrestName = itemView.findViewById(R.id.rri_rstrntname_tv);
            RVHrestCousineNrestPriceRange = itemView.findViewById(R.id.rri_pricerange_tv);
        }

    }
}
