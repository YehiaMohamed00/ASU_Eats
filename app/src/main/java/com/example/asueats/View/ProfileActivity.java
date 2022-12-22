package com.example.asueats.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.asueats.Model.User;
import com.example.asueats.R;
import com.example.asueats.ViewModel.UserViewModel;
import com.google.firebase.auth.FirebaseAuth;

public class ProfileActivity extends AppCompatActivity {

    Button prv_update_btn, prv_logout_btn;
    EditText prv_firstname_et, prv_lastname_et, prv_address_et;
    private UserViewModel mUserViewModel;
    FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile_view);
        prv_update_btn = findViewById(R.id.prv_update_btn);
        prv_firstname_et = findViewById(R.id.prv_firstname_et);
        prv_lastname_et = findViewById(R.id.prv_lastname_et);
        prv_address_et = findViewById(R.id.prv_address_et);
        prv_logout_btn = findViewById(R.id.prv_logout_btn);

        mAuth = FirebaseAuth.getInstance();
        mUserViewModel = new ViewModelProvider(this).get(UserViewModel.class);

    }

    // TODO
    // Make way to update user's password

    @Override
    protected void onResume() {
        super.onResume();
        prv_logout_btn.setOnClickListener(view -> {
            mAuth.signOut();
            finish();
        });
        prv_update_btn.setOnClickListener(view -> {
            String email = getIntent().getStringExtra("email");
            String password = getIntent().getStringExtra("password");
            new Thread(() -> {
                    User user = mUserViewModel.getUser(email, password);
                    if(user == null){
                        runOnUiThread(() -> Toast.makeText(getApplicationContext(),email + "  " + password, Toast.LENGTH_LONG).show());
                    }else{
                        runOnUiThread(() -> {
                            String firstName = prv_firstname_et.getText().toString();
                            String lastName = prv_lastname_et.getText().toString();
                            String address = prv_address_et.getText().toString();
                            if(!firstName.isEmpty() && !lastName.isEmpty() && !address.isEmpty()){
                                mUserViewModel.updateInfo(email, firstName, lastName, address);
                                user.setFirstName(firstName);
                                user.setLastName(lastName);
                                user.setAddress(address);
                                Toast.makeText(getApplicationContext(),"Profile Updated", Toast.LENGTH_LONG).show();
                            }
                            prv_firstname_et.setText(user.getFirstName());
                            prv_lastname_et.setText(user.getLastName());
                            prv_address_et.setText(user.getAddress());

                        });
                    }
            }).start();
        });
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                prv_update_btn.callOnClick();
            }
        }, 500);


    }
}