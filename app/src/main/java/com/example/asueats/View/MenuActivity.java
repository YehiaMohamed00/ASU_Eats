package com.example.asueats.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.asueats.Model.Dish;
import com.example.asueats.Model.MenuAdapter;
import com.example.asueats.Model.Restaurant;
import com.example.asueats.Model.RestaurantAdapter;
import com.example.asueats.R;

import java.util.ArrayList;
import java.util.List;

public class MenuActivity extends AppCompatActivity implements MenuAdapter.OnDishListener {

    RecyclerView recyclerView;
    LinearLayoutManager layoutManager;
    List<Dish> dishList;
    MenuAdapter menuAdapter;
    Button mv_back_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_view);

        mv_back_btn = findViewById(R.id.mv_back_btn);

        // initData
        dishList = new ArrayList<>();
        Bundle bundle = getIntent().getBundleExtra("restaurant");
        dishList = ((Restaurant)bundle.getSerializable("restSelected")).getDishList();
        Log.d("yehiaaDebug = Menu ", dishList.toString());
//        dishList.add(new Dish(R.drawable.foodcorner, "negresco",
//                "negresco macaroni",15, 3));
//        dishList.add(new Dish(R.drawable.momen, "akl 2", "desc 2", 20, 4));
//
//        dishList.add(new Dish(R.drawable.foodcorner, "negresco",
//                "alfredo macaroni",40, 1));
//        dishList.add(new Dish(R.drawable.momen, "akl 3", "desc 5", 105, 0));
//
//        dishList.add(new Dish(R.drawable.foodcorner, "negresco",
//                "alfredo macaroni",40, 1));
//        dishList.add(new Dish(R.drawable.momen, "akl 3", "desc 5", 105, 0));

        // initRecyclerView
        recyclerView = findViewById(R.id.mv_recyclerview);
        layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        menuAdapter = new MenuAdapter(dishList, this);
        recyclerView.setAdapter(menuAdapter);
        menuAdapter.notifyDataSetChanged();

    }

    @Override
    public void onDishClick(int position) {
        if(dishList.get(position).getDishAvailability().equals("Available")){
            Intent i = new Intent(this, DishAddActivity.class);
            Bundle bundle = new Bundle();
            bundle.putSerializable("dishSelected", dishList.get(position));
            i.putExtra("dish", bundle);
            startActivity(i);
        }else {
            Toast.makeText(this, "Dish not available", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        mv_back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}