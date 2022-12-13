package com.example.asueats.View;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.asueats.R;

public class CartActivity extends AppCompatActivity {

    Button cv_topayment_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cart_view);
        cv_topayment_btn = findViewById(R.id.cv_topayment_btn);
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
}