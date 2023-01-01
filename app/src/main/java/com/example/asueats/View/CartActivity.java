package com.example.asueats.View;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.asueats.Model.CartAdapter;
import com.example.asueats.Model.Dish;
import com.example.asueats.Model.Order;
import com.example.asueats.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;
import java.util.List;
import java.util.UUID;

public class CartActivity extends AppCompatActivity implements CartAdapter.OnDishListener {

    Button cv_topayment_btn, cv_back_btn;
    RecyclerView recyclerView;
    LinearLayoutManager layoutManager;
    List<Dish> dishList;
    CartAdapter cartAdapter;
    RadioGroup cv_deliveryGate_rg, cv_deliveryTime_rg;
    Boolean isAcceptablePeriod = false;
    String timeSelected = "";
    String gateSelected = "";

    Calendar calendar;

    Double totalPrice = 0.0;
    Double itemsPrice;
    Double deliveryCost;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cart_view);
        cv_topayment_btn = findViewById(R.id.cv_topayment_btn);
        cv_back_btn = findViewById(R.id.cv_back_btn);
        cv_deliveryGate_rg = findViewById(R.id.cv_deliveryGate_rg);
        cv_deliveryTime_rg = findViewById(R.id.cv_deliveryTime_rg);


        // initRecyclerView
        recyclerView = findViewById(R.id.cv_recyclerview);
        layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        cartAdapter = new CartAdapter(RestaurantsActivity.cartList, this);
        recyclerView.setAdapter(cartAdapter);
        cartAdapter.notifyDataSetChanged();
    }

    @Override
    protected void onResume() {
        super.onResume();
        cartAdapter.notifyDataSetChanged();
        cv_topayment_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int selectedGate = cv_deliveryGate_rg.getCheckedRadioButtonId();
                if (selectedGate == R.id.cv_radioG3_rb){
                    gateSelected = "Gate 3";
                } else {
                    gateSelected = "Gate 4";
                }

                int selectedTime = cv_deliveryTime_rg.getCheckedRadioButtonId();
                if (selectedTime == R.id.cv_radioP12_rb){
                    timeSelected = "12AM";

                } else {
                    timeSelected = "3PM";
                }

                calendar = Calendar.getInstance();
                int currHour = calendar.get(Calendar.HOUR_OF_DAY);
                if (timeSelected.equals("12AM")) {
                    //                        Toast.makeText(this, "Please Select a Valid Period", Toast.LENGTH_SHORT).show();
                    isAcceptablePeriod = currHour <= 10;
                } else {
                    //                        Toast.makeText(this, "Please Select a Valid Period", Toast.LENGTH_SHORT).show();
                    isAcceptablePeriod = currHour <= 13;
                }

                if (isAcceptablePeriod){
                    for (Dish d: RestaurantsActivity.cartList){
                        totalPrice += d.getDishPrice();
                    }
                    if (totalPrice != 0){
                        String orderID = UUID.randomUUID().toString();
//                        String orderID = generateOrderID();
                        String userID = FirebaseAuth.getInstance().getCurrentUser().getUid();
                        String orderStatus = "placed";
                        String orderGate = gateSelected;
                        String orderTimePeriod = timeSelected;
                        int currMin = calendar.get(Calendar.MINUTE);
                        int currSec = calendar.get(Calendar.SECOND);
                        String hour = "" + currHour, minute = "" + currMin;
                        String second = "" + currSec;
                        if(currHour < 10){
                            hour = "0" + currHour;
                        } else if(currMin < 10){
                            minute = "0" + currMin;
                        } else if(currSec < 10){
                            minute = "0" + currSec;
                        }
                        String orderDate = hour + ":" + minute + ":" + second + " "
                                + calendar.get(Calendar.DAY_OF_MONTH)
                                + "/" + calendar.get(Calendar.MONTH)
                                + "/" + calendar.get(Calendar.YEAR);
//                        String orderDate = calendar.get(Calendar.LONG_FORMAT) + "";


                        // Creating Order Object
                        Order order = new Order(orderID, userID, orderDate, orderStatus, orderGate, orderTimePeriod, totalPrice, RestaurantsActivity.cartList);
                        // Adding Order to Firebase
                        FirebaseDatabase.getInstance().getReference("orders").child(userID).child(orderID).setValue(order);
                        Toast.makeText(CartActivity.this, "Order Successfully placed", Toast.LENGTH_SHORT).show();
                        RestaurantsActivity.cartList.clear();
                        cartAdapter.notifyDataSetChanged();
                        finish();
//                        Intent i = new Intent(getApplicationContext(), PaymentActivity.class);
//                        startActivity(i);
                    }
                } else {
                    Toast.makeText(CartActivity.this, "you can't order now, sorry", Toast.LENGTH_SHORT).show();
                }
            }
        });

        cv_back_btn.setOnClickListener(view -> finish());
    }

    private String generateOrderID() {
        Calendar calendar = Calendar.getInstance();
        String id = String.valueOf(calendar.get(Calendar.DAY_OF_MONTH) + calendar.get(Calendar.MONTH) + calendar.get(Calendar.YEAR)
                + calendar.get(Calendar.HOUR) + calendar.get(Calendar.MINUTE) + calendar.get(Calendar.SECOND));

        return id;
    }

    @Override
    public void onDishClick(int position) {
    }
}