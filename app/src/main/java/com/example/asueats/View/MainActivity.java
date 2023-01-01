package com.example.asueats.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Database;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

import com.example.asueats.Model.Dish;
import com.example.asueats.Model.Restaurant;
import com.example.asueats.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    public static List<Restaurant> restaurantList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        new Handler().postDelayed(() -> {
            Intent i=new Intent(getApplicationContext(), LoginActivity.class);
            startActivity(i);
        }, 1500);
    }

    @Override
    protected void onStart() {
        super.onStart();
        restaurantList = new ArrayList<>();

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference restRef = database.getReference("restaurants");
        restRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dsp: snapshot.getChildren()){
                    HashMap rest = (HashMap)dsp.getValue();
                    restaurantList.add(new Restaurant(rest.get("img").toString(), rest.get("name").toString(),
                            rest.get("type").toString(), rest.get("range").toString()));
                    Log.d("yehiaaDebug", rest.get("name").toString());
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}