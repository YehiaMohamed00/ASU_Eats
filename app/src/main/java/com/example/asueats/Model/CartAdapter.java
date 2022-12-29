package com.example.asueats.Model;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.asueats.R;
import java.util.List;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.CartViewHolder> {

    List<Dish> dishList;
    OnDishListener mOnDishListener;

    public CartAdapter(List<Dish> dishList, OnDishListener onDishListener) {
        this.dishList = dishList;
        this.mOnDishListener = onDishListener;
    }

    @NonNull
    @Override
    public CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cart_recycler_item, parent, false);
        return new CartViewHolder(view, mOnDishListener);
    }

    @Override
    public void onBindViewHolder(@NonNull CartViewHolder holder, int position) {
        String dishImg= dishList.get(position).getDishImg();
        String dishName = dishList.get(position).getDishName();
        String dishDescription = dishList.get(position).getDishDescription();
        Double dishPrice =  dishList.get(position).getDishPrice();
//        String dishAvailability = dishList.get(position).getDishAvailability();

        Glide.with(holder.MVHdishImg.getContext()).load(dishImg).into(holder.MVHdishImg);
//        holder.MVHdishImg.setImageResource(dishImg);
        holder.MVHdishName.setText(dishName);
//        holder.MVHdishDescription.setText(dishDescription);
        String strPrice = dishPrice+  " EGP";
        holder.MVHdishPrice.setText(strPrice);
//        String strAvailability = dishAvailability.toString();
//        holder.MVHdishAvailability.setText(strAvailability);
    }

    @Override
    public int getItemCount() {
        return dishList.size();
    }

    public class CartViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        ImageView MVHdishImg;
        TextView MVHdishName, MVHdishDescription, MVHdishPrice, MVHdishAvailability;
        OnDishListener onDishListener;

        public CartViewHolder(@NonNull View itemView, OnDishListener onDishListener) {
            super(itemView);

            MVHdishImg = itemView.findViewById(R.id.cri_dish_img);
            MVHdishName = itemView.findViewById(R.id.cri_dishname_tv);
//            MVHdishDescription = itemView.findViewById(R.id.cri_description_tv);
            MVHdishPrice = itemView.findViewById(R.id.cri_price_tv);
//            MVHdishAvailability = itemView.findViewById(R.id.mri_availability_tv);
            this.onDishListener = onDishListener;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            onDishListener.onDishClick(getAdapterPosition());
        }
    }

    public interface OnDishListener{
        void onDishClick(int position);
    }
}
