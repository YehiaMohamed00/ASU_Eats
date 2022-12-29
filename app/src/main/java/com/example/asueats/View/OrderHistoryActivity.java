package com.example.asueats.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.example.asueats.Model.Order;
import com.example.asueats.Model.OrderAdapter;
import com.example.asueats.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class OrderHistoryActivity extends AppCompatActivity implements OrderAdapter.OnOrderListener  {

    List<Order> ordersList;
    RecyclerView recyclerView;
    LinearLayoutManager layoutManager;
    OrderAdapter orderAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.order_history);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        String userID = FirebaseAuth.getInstance().getCurrentUser().getUid();
        DatabaseReference orderRef = database.getReference("orders").child(userID);


        ordersList = new ArrayList<>();

        recyclerView = findViewById(R.id.am_recyclerview);
        layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        orderAdapter = new OrderAdapter(ordersList, this);
        recyclerView.setAdapter(orderAdapter);
        orderAdapter.notifyDataSetChanged();

        orderRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                for (DataSnapshot sp: snapshot.getChildren()){
                    for (DataSnapshot so: snapshot.getChildren()){
                        HashMap order = (HashMap)so.getValue();
                        ordersList.add(new Order(order.get("orderID").toString(), order.get("userID").toString(),
                                order.get("orderDate").toString(), order.get("orderStatus").toString(),
                                order.get("orderGate").toString(), order.get("orderTimePeriod").toString(), Double.parseDouble(order.get("orderPrice").toString())));
                    }
//                }
                Log.d("yehiaaDebug = dish", ordersList.toString());
                orderAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    @Override
    public void onOrderClick(int position) {
        Intent i = new Intent(this, OrderTrackerActivity.class);
        i.putExtra("orderStatus", ordersList.get(position).getOrderStatus());
        startActivity(i);
    }
}