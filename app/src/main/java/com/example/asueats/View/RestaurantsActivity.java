package com.example.asueats.View;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.asueats.R;

public class RestaurantsActivity extends AppCompatActivity {

    Button rv_cart_btn, rv_account_btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.restaurants_view);
        rv_cart_btn = findViewById(R.id.rv_cart_btn);
        rv_account_btn = findViewById(R.id.rv_account_btn);
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
                Intent i = new Intent(getApplicationContext(), ProfileActivity.class);
                startActivity(i);
            }
        });
    }
}