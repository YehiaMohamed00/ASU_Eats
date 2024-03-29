package com.example.asueats.View;


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.asueats.Model.Dish;
import com.example.asueats.Model.Restaurant;
import com.example.asueats.Model.User;
import com.example.asueats.R;
import com.example.asueats.ViewModel.UserViewModel;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LoginActivity extends AppCompatActivity {


    User loggedUser;
    TextView lv_signup_tv;
    Button lv_login_btn;
    EditText lv_email_et, lv_pass_et;
    private UserViewModel mUserViewModel;
    Pattern pattern;
    FirebaseAuth mAuth;

    List<Dish>dishList;
//    List<Restaurant>restList;
//    ProfileRoomDatabase userDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_view);
        lv_signup_tv = findViewById(R.id.lv_signup_tv);
        lv_login_btn = findViewById(R.id.lv_login_btn);
        lv_email_et = findViewById(R.id.lv_email_et);
        lv_pass_et = findViewById(R.id.lv_pass_et);

        mAuth = FirebaseAuth.getInstance();
        new UserViewModel(getApplication());

        String regex = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";
        pattern = Pattern.compile(regex);

        mUserViewModel = new ViewModelProvider(this).get(UserViewModel.class);
//        mUserViewModel.getAllUsers().observe(this, user -> {
//            // Update the cached copy of the words in the adapter.
//            //adapter.submitList(words);
//            if (user == null){
//                Toast.makeText(
//                        getApplicationContext(),
//                        "No Users registered",
//                        Toast.LENGTH_LONG).show();
//            }else
//            {
////                String tmp = lv_signup_tv.getText().toString() + "  " + user.size();
////                lv_signup_tv.setText(tmp);
//            }
//        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        dishList = new ArrayList<>();

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference dishRef = database.getReference("dishes");

        dishRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                int i = 0;
                for (DataSnapshot sp: snapshot.getChildren()){
                    for (DataSnapshot so: sp.getChildren()){
                        HashMap dish = (HashMap)so.getValue();
                        double tmp = Double.parseDouble(dish.get("price").toString());
                        if(dish.get("availability").toString().equals("Available")){
                            dishList.add(new Dish(dish.get("img").toString(), dish.get("name").toString(),
                                    "placeholder",tmp
                                    , "Available"));
                        }else{
                            dishList.add(new Dish(dish.get("img").toString(), dish.get("name").toString(),
                                    "placeholder",tmp
                                    , "N/A"));
                        }
                    }
                    MainActivity.restaurantList.get(i).setDishList(dishList);
                    Log.d("yehiaaDebug = dish", MainActivity.restaurantList.get(i).toString());
                    dishList = new ArrayList<>();
                    Log.d("yehiaaDebug = dish", MainActivity.restaurantList.get(i).toString());
                    i++;
                }
                Log.d("yehiaaDebug = dish", dishList.toString());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        lv_signup_tv.setOnClickListener(view -> {
            Intent i = new Intent(getApplicationContext(), SignupActivity.class);
            startActivity(i);
        });

        lv_login_btn.setOnClickListener(view -> {
            String email = lv_email_et.getText().toString();
            String password = lv_pass_et.getText().toString();
            Matcher matcher = pattern.matcher(email);
            if(!password.isEmpty()){
                if(matcher.matches()){
                    mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(task -> {
                        if(task.isSuccessful()){
                            Toast.makeText(LoginActivity.this, "Logged in Successfully",Toast.LENGTH_LONG).show();
                            Intent i = new Intent(getApplicationContext(), RestaurantsActivity.class);
                            // TODO: get user cartlist
                            i.putExtra("email", email);
                            i.putExtra("password", password);
                            startActivity(i);
                            finish();
                        }else{
                            Toast.makeText(LoginActivity.this, "Login error",Toast.LENGTH_LONG).show();
                        }
                    });
                }
                else{
                    Toast.makeText(getApplicationContext(),"invalid Email format", Toast.LENGTH_LONG).show();
                }
            }
            else{
                Toast.makeText(getApplicationContext(),"password can't be empty", Toast.LENGTH_LONG).show();
            }
        });
    }
}