package com.example.nsriva1.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class CartPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart_page);
    }

    public void onConfirm(View view){
        Cart.clear();
        Cart.lever = true;
        Cart.setCartCount(0);
        Poller.flag = true;
        Intent intent= new Intent(getApplicationContext(),MainActivity.class);
        this.startActivity(intent);

    }
}
