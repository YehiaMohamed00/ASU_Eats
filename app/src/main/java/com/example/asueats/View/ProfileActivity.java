package com.example.asueats.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.asueats.Model.User;
import com.example.asueats.R;
import com.example.asueats.ViewModel.UserViewModel;

public class ProfileActivity extends AppCompatActivity {

    Button prv_update_btn;
    EditText prv_firstname_et, prv_lastname_et, prv_address_et;
    private UserViewModel mUserViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile_view);
        prv_update_btn = findViewById(R.id.prv_update_btn);
        prv_firstname_et = findViewById(R.id.prv_firstname_et);
        prv_lastname_et = findViewById(R.id.prv_lastname_et);
        prv_address_et = findViewById(R.id.prv_address_et);
        mUserViewModel = new ViewModelProvider(this).get(UserViewModel.class);

    }

    @Override
    protected void onResume() {
        super.onResume();
        prv_update_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = getIntent().getStringExtra("email");
                String password = getIntent().getStringExtra("password");
                new Thread(new Runnable() {
                    @Override
                    public void run() {
//                        Toast.makeText(getApplicationContext(),"the thread runs", Toast.LENGTH_LONG).show();
                            User user = mUserViewModel.getUser(email, password);
                            if(user == null){
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {

                                        Toast.makeText(getApplicationContext(),email + "  " + password, Toast.LENGTH_LONG).show();
                                    }
                                });
                            }else{
                                runOnUiThread(() -> {
                                    prv_firstname_et.setText(user.getFirstName());
                                    prv_lastname_et.setText(user.getLastName());
                                    prv_address_et.setText(user.getAddress());
                                });

//                                Intent i = new Intent(getApplicationContext(), RestaurantsActivity.class);
//                                startActivity(i);
                            }
                    }
                }).start();
//                mUserViewModel.insert();
//                finish();
            }
        });

        prv_update_btn.callOnClick();
    }
}