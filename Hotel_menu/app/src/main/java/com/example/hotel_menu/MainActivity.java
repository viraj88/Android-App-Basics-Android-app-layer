package com.example.yourappname;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Find TextViews
        TextView vegItem1 = findViewById(R.id.vegItem1);
        TextView vegItem2 = findViewById(R.id.vegItem2);
        TextView vegItem3 = findViewById(R.id.vegItem3);

        TextView nonVegItem1 = findViewById(R.id.nonVegItem1);
        TextView nonVegItem2 = findViewById(R.id.nonVegItem2);
        TextView nonVegItem3 = findViewById(R.id.nonVegItem3);

        TextView beverage1 = findViewById(R.id.beverage1);
        TextView beverage2 = findViewById(R.id.beverage2);
        TextView beverage3 = findViewById(R.id.beverage3);

        // Set text for each item
        vegItem1.setText("Veg Dish 1");
        vegItem2.setText("Veg Dish 2");
        vegItem3.setText("Veg Dish 3");

        nonVegItem1.setText("Non-Veg Dish 1");
        nonVegItem2.setText("Non-Veg Dish 2");
        nonVegItem3.setText("Non-Veg Dish 3");

        beverage1.setText("Beverage 1");
        beverage2.setText("Beverage 2");
        beverage3.setText("Beverage 3");
    }
}
