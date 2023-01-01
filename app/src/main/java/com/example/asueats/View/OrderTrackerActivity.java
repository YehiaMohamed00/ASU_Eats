package com.example.asueats.View;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.TextView;

import com.example.asueats.R;

public class OrderTrackerActivity extends AppCompatActivity {

    TextView otv_inpackage_tv, otv_onway_tv_tv, otv_beingserved_tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.order_track_view);
        String orderStatus = getIntent().getStringExtra("orderStatus");
        otv_inpackage_tv = findViewById(R.id.otv_inpackage_tv);
        otv_onway_tv_tv = findViewById(R.id.otv_onway_tv);
        otv_beingserved_tv = findViewById(R.id.otv_beingserved_tv);

        switch (orderStatus) {
            case "packaged":
                makePackageGreen();
                break;
            case "on-way":
                makePackageGreen();
                makeOnWayGreen();
                break;
            case "delivered":
                makePackageGreen();
                makeOnWayGreen();
                makeDeliveredGreen();
                break;
        }
    }

    private void makeDeliveredGreen() {
        otv_beingserved_tv.setTextColor(Color.parseColor("#00AA00"));
        String tmp = otv_beingserved_tv.getText().toString() + " ✓";
        otv_beingserved_tv.setText(tmp);
    }

    private void makeOnWayGreen() {
        otv_onway_tv_tv.setTextColor(Color.parseColor("#00AA00"));
        String tmp = otv_onway_tv_tv.getText().toString() + " ✓";
        otv_onway_tv_tv.setText(tmp);
    }

    private void makePackageGreen() {
        otv_inpackage_tv.setTextColor(Color.parseColor("#00AA00"));
        String tmp = otv_inpackage_tv.getText().toString() + " ✓";
        otv_inpackage_tv.setText(tmp);
    }
}