package com.example.asueats.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.asueats.Model.CartAdapter;
import com.example.asueats.Model.Dish;
import com.example.asueats.Model.MenuAdapter;
import com.example.asueats.R;

import java.util.List;

public class CartActivity extends AppCompatActivity implements CartAdapter.OnDishListener {

    Button cv_topayment_btn;
    RecyclerView recyclerView;
    LinearLayoutManager layoutManager;
    List<Dish> dishList;
    CartAdapter cartAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cart_view);
        cv_topayment_btn = findViewById(R.id.cv_topayment_btn);

        // initRecyclerView
        recyclerView = findViewById(R.id.cv_recyclerview);
        layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        cartAdapter = new CartAdapter(RestaurantsActivity.cartList, this);
        recyclerView.setAdapter(cartAdapter);
        cartAdapter.notifyDataSetChanged();
    }

    @Override
    protected void onResume() {
        super.onResume();
        cv_topayment_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), PaymentActivity.class);
                startActivity(i);
            }
        });
    }

    @Override
    public void onDishClick(int position) {
    }
}