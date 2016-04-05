package com.example.nsriva1.myapplication;

import android.content.Context;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by nsriva1 on 3/31/16.
 */
public class Extracter {

    public void populateCatalog(Context context, int timeSlot){
        try {

            URL url = new URL("REST-URL goes here" + timeSlot);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");

            if (conn.getResponseCode() != 200) {
                Toast.makeText(context, "No response from rest", Toast.LENGTH_SHORT).show();
                throw new RuntimeException("Failed : HTTP error code : "
                        + conn.getResponseCode());
            }

            BufferedReader br = new BufferedReader(new InputStreamReader(
                    (conn.getInputStream())));

            String output;
            //TODO parse the json string provided by rest here


            conn.disconnect();

        } catch (Exception e) {

            e.printStackTrace();

        }


    }
}
