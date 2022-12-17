package com.example.asueats.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.Button;

import com.example.asueats.Model.Restaurant;
import com.example.asueats.Model.RestaurantAdapter;
import com.example.asueats.R;

import java.util.ArrayList;
import java.util.List;

public class RestaurantsActivity extends AppCompatActivity {

    Button rv_cart_btn, rv_account_btn;
    RecyclerView recyclerView;
    LinearLayoutManager layoutManager;
    List<Restaurant>restaurantList;
    RestaurantAdapter restaurantAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.restaurants_view);
        rv_cart_btn = findViewById(R.id.rv_cart_btn);
        rv_account_btn = findViewById(R.id.rv_account_btn);


        // initData
        restaurantList = new ArrayList<>();

        restaurantList.add(new Restaurant(R.drawable.foodcorner, "Food Corner",
                "Pasta", "$$"));
        restaurantList.add(new Restaurant(R.drawable.momen, "Momen", "Sandwich", "$$$"));

        restaurantList.add(new Restaurant(R.drawable.foodcorner, "City Crepe",
                "Crepe", "$"));
        restaurantList.add(new Restaurant(R.drawable.momen, "Pizza King", "Pizza", "$$"));


        // initRecyclerView
        recyclerView = findViewById(R.id.rv_recyclerview);
        layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        restaurantAdapter = new RestaurantAdapter(restaurantList);
        recyclerView.setAdapter(restaurantAdapter);
        restaurantAdapter.notifyDataSetChanged();

    }

    @Override
    protected void onResume() {
        super.onResume();

        rv_cart_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), CartActivity.class);

                startActivity(i);
            }
        });

        rv_account_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent tmp = getIntent();
                Intent i = new Intent(getApplicationContext(), ProfileActivity.class);
                i.putExtra("email", tmp.getStringExtra("email"));
                i.putExtra("password", tmp.getStringExtra("password"));
                Log.d("rest", i.getStringExtra("email") + "  " + i.getStringExtra("password"));
                startActivity(i);
            }
        });
    }
}