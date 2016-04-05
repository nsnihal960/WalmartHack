package com.example.nsriva1.myapplication;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v4.app.NotificationCompat;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import java.net.URL;

/**
 * Created by nsriva1 on 4/2/16.
 */
public class Poller implements Runnable {

    public static Activity globalContext = null;
    private MainActivity mainAct;
    public static int itemCount = 0;
    public static Boolean flag  = true;
    public Poller(MainActivity mainAct){
        this.mainAct = mainAct;
    }
    @Override
    public void run(){


        Long tm = System.currentTimeMillis();
        while(flag){
        if(System.currentTimeMillis() - tm > 2000 ){




            try {
                String output = "";//{\"count\":4,\"items\":[{\"id\":\"10451514\",\"name\":\"Great Value White Hominy\",\"imageLink\":\"http://i5.walmartimages.com/dfw/dce07b8c-2fdd/k2-_e0e44ecc-3a68-4c90-8c52-7478046ec80d.v2.jpg\",\"price\":2.72},{\"id\":\"22557382\",\"name\":\"Dynasty Cut Baby Corn\",\"imageLink\":\"http://ll-us-i5.wal.co/dfw/dce07b8c-295e/k2-_69dbb72c-34f7-4c34-9530-439bff5e25e3.v1.jpg\",\"price\":28.06},{\"id\":\"14869676\",\"name\":\"Kraft Macaroni & Cheese\",\"imageLink\":\"http://ll-us-i5.wal.co/dfw/dce07b8c-ecdd/k2-_a4e11752-131d-4959-ae06-a328ca682a85.v1.jpg\",\"price\":4.72},{\"id\":\"10312014\",\"name\":\"Hershey's Sugar Free Chocolate Chips\",\"imageLink\":\"http://i5.walmartimages.com/dfw/dce07b8c-d39c/k2-_b70d74f8-69c4-412c-8b41-fd68edffca43.v1.jpg\",\"price\":3.65}]}";
                HttpClient client = new DefaultHttpClient();
                HttpGet get = new HttpGet("http://172.28.125.86:8080/app/api/customer/1/notification");
                HttpResponse resp =  client.execute(get);
                if(resp.getStatusLine().getStatusCode() == 200){
                        output =  EntityUtils.toString(resp.getEntity());
                }

                JSONObject jsonObject = new JSONObject(output);
                String countStr = jsonObject.getString("count");
                Cart.time = jsonObject.getString("slotId");
                itemCount = Integer.parseInt(countStr);
                if( itemCount > 0 && Cart.lever){
                    JSONArray arr = jsonObject.getJSONArray("items");
                    for(int i=0;i<arr.length();i++){
                        JSONObject item = arr.getJSONObject(i);
                        String id = item.getString("id");
                        String name = item.getString("name");
                        String imageLink = item.getString("imageLink");
                        Double price = item.getDouble("price");

                        URL urlConnection = new URL(imageLink);
                        Bitmap bm = BitmapFactory.decodeStream(urlConnection.openConnection().getInputStream());

                        DisplayItem di= new DisplayItem(bm,name,price,new Button(mainAct.getApplicationContext()),new Button(mainAct.getApplicationContext()),new EditText(mainAct.getApplicationContext()));
                        Cart.addItems(di);
                    }
                    flag = false;

                    globalContext = mainAct;
                    //Change the main activity screen

                    mainAct.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            ProgressBar pb = (ProgressBar)globalContext.findViewById(R.id.progressBar);
                            TextView tv = (TextView)globalContext.findViewById(R.id.textView);
                            Button bt = (Button)globalContext.findViewById(R.id.button5);
                            TextView t3 = (TextView)globalContext.findViewById(R.id.textView3);
                            pb.setVisibility(View.INVISIBLE);
                            tv.setVisibility(View.INVISIBLE);
                            bt.setVisibility(View.VISIBLE);
                            t3.setText("Get your favourite " + itemCount + " items on " + Cart.time.split("_")[0] +" by "+Cart.time.split("_")[1]+":00PM");
                            t3.setVisibility(View.VISIBLE);
                            bt.setOnClickListener(new View.OnClickListener() {
                                public void onClick(View v) {
                                    Intent intent = new Intent(mainAct, ShoppingPage.class);
                                    mainAct.startActivity(intent);

                                }
                            });
                        }
                    });



                    NotificationCompat.Builder mBuilder =
                            new NotificationCompat.Builder(this.mainAct)
                                    .setSmallIcon(R.drawable.home)
                                    .setContentTitle("Shopping this Saturday at WALMART??")
                                    .setContentText("Your personal shopping list is ready to be delivered on " + Cart.time.split("_")[0] + " by " + Cart.time.split("_")[1] + ":00PM at discounted price with FREE delivery!!!").setPriority(Notification.PRIORITY_MAX).setDefaults(Notification.DEFAULT_ALL);
                    Intent resultIntent = new Intent(this.mainAct, ShoppingPage.class);
                    TaskStackBuilder stackBuilder = TaskStackBuilder.create(this.mainAct);
                    stackBuilder.addParentStack(ShoppingPage.class);
                    stackBuilder.addNextIntent(resultIntent);
                    PendingIntent resultPendingIntent =
                            stackBuilder.getPendingIntent(
                                    0,
                                    PendingIntent.FLAG_UPDATE_CURRENT
                            );
                    mBuilder.setContentIntent(resultPendingIntent);
                    NotificationManager mNotificationManager =
                            (NotificationManager) this.mainAct.getSystemService(Context.NOTIFICATION_SERVICE);
                    mNotificationManager.notify(1, mBuilder.build());



                }
            } catch(Exception e){

                e.getMessage();
            }
            tm = System.currentTimeMillis();
        }
        }



    }
}
