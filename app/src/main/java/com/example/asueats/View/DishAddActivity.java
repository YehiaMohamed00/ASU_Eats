package com.example.asueats.View;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.asueats.Model.Dish;
import com.example.asueats.R;

public class DishAddActivity extends AppCompatActivity {

    Dish dish;
    Button plusBtn, minusBtn, toCartBtn;
    EditText amount;
    ImageView dishImg;
    TextView dishName, dishDescription, dishPrice, dishAvailability;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dish_add_view);

        dishImg = findViewById(R.id.dav_dish_img);
        dishName = findViewById(R.id.dav_dishname_tv);
        dishDescription = findViewById(R.id.dav_description_tv);
        dishPrice = findViewById(R.id.dav_price_tv);
        dishAvailability = findViewById(R.id.dav_availability_tv);
        plusBtn = findViewById(R.id.dav_amountplus_btn);
        minusBtn = findViewById(R.id.dav_amountminus_btn);
        toCartBtn = findViewById(R.id.dav_addtocart_btn);
        amount = findViewById(R.id.dav_amount_et);


        Bundle bundle = getIntent().getBundleExtra("dish");
        dish = (Dish)bundle.getSerializable("dishSelected");
        Glide.with(dishImg.getContext()).load(dish.getDishImg()).into(dishImg);
//        dishImg.setImageResource(dish.getDishImg());
        dishName.setText(dish.getDishName());
        dishDescription.setText(dish.getDishDescription());
        String strPrice = ((Double)dish.getDishPrice()) + " EGP";
        dishPrice.setText(strPrice);
        String strAvailability = "Availability: " + dish.getDishAvailability().toString();
        dishAvailability.setText(strAvailability);

        plusBtn.setOnClickListener(view -> {
            if(dish.getDishAvailability().equals("Available")){
//                dish.setDishAvailability(newAvailability);
//                String strAvailability1 = "Availability: " + newAvailability;
//                dishAvailability.setText(strAvailability1);
                // TODO : Add new item to cartlist
                RestaurantsActivity.cartList.add(new Dish(dish));
                Log.d("yehiaaDebug = cartlist","added ->" + RestaurantsActivity.cartList.toString());
                amount.setText(String.valueOf(Integer.parseInt(amount.getText().toString())+1));
            }
        });

        minusBtn.setOnClickListener(view -> {
            // TODO: remove from cartlist
            for (Dish dishh: RestaurantsActivity.cartList){
                int i = 0;
                if(dishh.getDishName().equals(dish.getDishName())){
                    RestaurantsActivity.cartList.remove(i);
                    Log.d("yehiaaDebug = cartlist","removed ->" + RestaurantsActivity.cartList.toString());
                    break;
                }
                i++;
            }
//            RestaurantsActivity.cartList.remove();
//            amount.setText(String.valueOf(Integer.parseInt(amount.getText().toString())-1));
        });

        toCartBtn.setOnClickListener(view -> {
            Toast.makeText(this, "Added to Cart", Toast.LENGTH_SHORT).show();
            finish();
        });
    }
}