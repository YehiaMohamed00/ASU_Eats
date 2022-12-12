package com.example.asueats;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class LoginActivity extends AppCompatActivity {

    TextView lv_signup_tv;
    Button lv_login_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_view);
        lv_signup_tv = findViewById(R.id.lv_signup_tv);
        lv_login_btn = findViewById(R.id.lv_login_btn);
    }

    @Override
    protected void onResume() {
        super.onResume();
        lv_signup_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), SignupActivity.class);
                startActivity(i);
            }
        });

        lv_login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), RestaurantsActivity.class);
                startActivity(i);
            }
        });
    }
}