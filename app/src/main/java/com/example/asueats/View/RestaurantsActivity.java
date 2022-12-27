package com.example.asueats.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.Button;

import com.example.asueats.Model.Dish;
import com.example.asueats.Model.Restaurant;
import com.example.asueats.Model.RestaurantAdapter;
import com.example.asueats.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class RestaurantsActivity extends AppCompatActivity implements RestaurantAdapter.OnRestaurantListener {

    Button rv_cart_btn, rv_account_btn;
    RecyclerView recyclerView;
    LinearLayoutManager layoutManager;
//    List<Restaurant>restaurantList;
    RestaurantAdapter restaurantAdapter;

//    List<Dish>dishList1;
//    List<Dish>dishList2;
//    List<Dish>dishList3;

    FirebaseAuth mAuth;
    Boolean updated = false;
//    public List<Restaurant> restaurantList;
    List<Dish> dishList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.restaurants_view);
        rv_cart_btn = findViewById(R.id.rv_cart_btn);
        rv_account_btn = findViewById(R.id.rv_account_btn);

//        restaurantList = MainActivity.restaurantList;

        mAuth = FirebaseAuth.getInstance();

        recyclerView = findViewById(R.id.rv_recyclerview);
        layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        restaurantAdapter = new RestaurantAdapter(MainActivity.restaurantList, this);
        recyclerView.setAdapter(restaurantAdapter);
        restaurantAdapter.notifyDataSetChanged();

//        FirebaseDatabase database = FirebaseDatabase.getInstance();
//        DatabaseReference dishesRef = database.getReference("dishes");
//        DatabaseReference restRef = database.getReference("restaurants");

//        /////////////////////
//        dishList1 = new ArrayList<>();
//        dishList1.add(new Dish(R.drawable.foodcorner, "negresco",
//                "negresco macaroni",15, 3));
//        dishList1.add(new Dish(R.drawable.momen, "akl 2", "desc 2", 20, 4));
//
//        dishList1.add(new Dish(R.drawable.foodcorner, "negresco",
//                "alfredo macaroni",40, 1));
//        dishList1.add(new Dish(R.drawable.momen, "akl 3", "desc 5", 105, 0));
//
//
//        Log.d("dishList1", dishList1.get(0).getDishName());
//        //////////////////////
//
//        /////////////////////
//        dishList2 = new ArrayList<>();
//        dishList2.add(new Dish(R.drawable.foodcorner, "negresco2",
//                "negresco macaroni",15, 3));
//        dishList2.add(new Dish(R.drawable.momen, "akl 2", "desc 2", 20, 4));
//
//        dishList2.add(new Dish(R.drawable.momen, "akl 3", "desc 5", 105, 0));
//
//        dishList2.add(new Dish(R.drawable.foodcorner, "negresco",
//                "alfredo macaroni",40, 1));
//        dishList2.add(new Dish(R.drawable.momen, "akl 3", "desc 5", 105, 0));
//        //////////////////////
//
//        /////////////////////
//        dishList3 = new ArrayList<>();
//        dishList3.add(new Dish(R.drawable.foodcorner, "negresco3",
//                "negresco macaroni",15, 3));
//        dishList3.add(new Dish(R.drawable.momen, "akl 2", "desc 2", 20, 4));
//
//        dishList3.add(new Dish(R.drawable.foodcorner, "negresco",
//                "alfredo macaroni",40, 1));
//        dishList3.add(new Dish(R.drawable.momen, "akl 3", "desc 5", 105, 0));
//
//        dishList3.add(new Dish(R.drawable.foodcorner, "negresco",
//                "alfredo macaroni",40, 1));
//        dishList3.add(new Dish(R.drawable.momen, "akl 3", "desc 5", 105, 0));
//        //////////////////////

//        restaurantList.add(new Restaurant(R.drawable.foodcorner, "Food Corner",
//                "Pasta", "$$", dishList1));
//        restaurantList.add(new Restaurant(R.drawable.momen, "Momen", "Sandwich", "$$$", dishList2));
//
//        restaurantList.add(new Restaurant(R.drawable.foodcorner, "City Crepe",
//                "Crepe", "$",dishList3));
//        restaurantList.add(new Restaurant(R.drawable.momen, "Pizza King", "Pizza", "$$", dishList1));
//
//        Log.d("forme", restaurantList.get(0).getDishList().get(0).getDishName());


        // initRecyclerView



//        Log.d("yehiaaDebug", restaurantList.toString());
    }

    @Override
    protected void onStart() {
        super.onStart();

        FirebaseUser fUser = mAuth.getCurrentUser();
        if(fUser == null){
            startActivity(new Intent(this, LoginActivity.class));
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

        rv_cart_btn.setOnClickListener(view -> {
            Intent i = new Intent(getApplicationContext(), CartActivity.class);

            startActivity(i);
        });

        rv_account_btn.setOnClickListener(view -> {
            Intent tmp = getIntent();
            Intent i = new Intent(getApplicationContext(), ProfileActivity.class);
            i.putExtra("email", tmp.getStringExtra("email"));
            i.putExtra("password", tmp.getStringExtra("password"));
            Log.d("rest", i.getStringExtra("email") + "  " + i.getStringExtra("password"));
            startActivity(i);
        });
    }

    @Override
    public void onRestClick(int position) {
//        Log.d("yehiaaDebug = rest ", MainActivity.restaurantList.get(position).toString());
        Intent i = new Intent(this, MenuActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("restSelected", MainActivity.restaurantList.get(position));

//        Log.d("forMe", restaurantList.get(position).getDishList().toString());
        i.putExtra("restaurant", bundle);
//        Log.d("works?", ((Restaurant)bundle.getSerializable("restSelected")).getRestName());
        startActivity(i);
    }
}