package com.example.nsriva1.myapplication;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.graphics.Bitmap;
import android.widget.LinearLayout;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.Toast;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;


public class ShoppingPage extends AppCompatActivity {

    GridView gridView;
    ArrayList<DisplayItem> gridArray = new ArrayList<DisplayItem>();
    CustomGridViewAdapter customGridAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_page);

        //Permission to run interenet on main thread
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        for(DisplayItem item : Cart.getItems()){
            gridArray.add(item);
        }
        Cart.lever = false;

//
//        Button b1 = new Button(getApplicationContext());
//        Button b2= new Button(getApplicationContext());
//        EditText ed = new EditText(getApplicationContext());
//        ed.setText("0");
//        for(int it = 0; it<8 ; it++) {
//            Bitmap bm;
//            try {
//                String url = "http://i5.walmartimages.com/dfw/dce07b8c-32e7/k2-_b99c43f7-f6a0-43f2-95ec-1ecbc23acef2.v1.jpg?a=" + it;
//                URL urlConnection = new URL(url);
//                bm = BitmapFactory.decodeStream(urlConnection.openConnection().getInputStream());
//
//            } catch (Throwable ae) {
//                //default image
//                Toast toast = Toast.makeText(getApplicationContext(), "MSG:" + ae.getMessage(), Toast.LENGTH_SHORT);
//                toast.show();
//                bm = BitmapFactory.decodeResource(this.getResources(), R.drawable.home);
//            }
//            gridArray.add(new DisplayItem(bm, "Daddy-O-Daddy", 24.43,b1, b2,ed ));
//        }
        gridView = (GridView) findViewById(R.id.gridView1);
        customGridAdapter = new CustomGridViewAdapter(this, R.layout.row_grid,gridArray);
        gridView.setAdapter(customGridAdapter);

    }

    public void onCheckout(View view){

        Button bt = (Button)findViewById(R.id.button);
        String size = bt.getText().toString();
        size = size.substring(5,size.length()-1);
        int cartCount = Integer.parseInt(size);
        if(cartCount > 0) {
            TextView tv = (TextView)findViewById(R.id.textView4);
            if(tv != null){
                tv.setText("Success! Your items will be delivered on " + Cart.time.split("_")[0] +" by "+Cart.time.split("_")[1]+":00PM");
            }
            Cart.lever = true;
            Intent intent = new Intent(getApplicationContext(), CartPage.class);
            this.startActivity(intent);
        }
        else {
            Toast toast = Toast.makeText(getApplicationContext(),"Cart empty!! Please add items to proceed",Toast.LENGTH_SHORT);
            toast.show();
        }
    }
}
