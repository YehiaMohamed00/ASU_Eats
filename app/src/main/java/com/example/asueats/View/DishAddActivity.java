package com.example.asueats.View;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

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
        dishImg.setImageResource(dish.getDishImg());
        dishName.setText(dish.getDishName());
        dishDescription.setText(dish.getDishDescription());
        String strPrice = ((Double)dish.getDishPrice()).toString();
        dishPrice.setText(strPrice);
        String strAvailability = "Availability: " + ((Integer)dish.getDishAvailability()).toString();
        dishAvailability.setText(strAvailability);

        plusBtn.setOnClickListener(view -> {
            int newAvailability = dish.getDishAvailability()-1;
            if(newAvailability >= 0){
                dish.setDishAvailability(newAvailability);
                String strAvailability1 = "Availability: " + newAvailability;
                dishAvailability.setText(strAvailability1);
                amount.setText(String.valueOf(Integer.parseInt(amount.getText().toString())+1));
            }
        });

        minusBtn.setOnClickListener(view -> {

            int newAvailability = dish.getDishAvailability()+1;
            if(Integer.parseInt(amount.getText().toString()) > 1 ){
                dish.setDishAvailability(newAvailability);
                String strAvailability1 = "Availability: " + newAvailability;
                dishAvailability.setText(strAvailability1);
                amount.setText(String.valueOf(Integer.parseInt(amount.getText().toString())-1));
            }
        });

        toCartBtn.setOnClickListener(view -> {
//            Intent i = new Intent(getApplicationContext(), CartActivity.class);
//            startActivity(i);
            // TODO : Add functionality of adding the dish to the order in the cart
            finish();
        });



    }
}