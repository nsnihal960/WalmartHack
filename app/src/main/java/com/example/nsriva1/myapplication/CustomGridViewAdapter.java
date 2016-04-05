package com.example.nsriva1.myapplication;

import android.app.Activity;
import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by nsriva1 on 4/1/16.
 */
public class CustomGridViewAdapter extends ArrayAdapter<DisplayItem> {

    Context context;
    int layoutResourceId;
    ArrayList<DisplayItem> data = new ArrayList<DisplayItem>();
    public CustomGridViewAdapter(Context context, int layoutResourceId, ArrayList<DisplayItem> data) {
        super(context, layoutResourceId, data);
        this.layoutResourceId = layoutResourceId;
        this.context = context;
        this.data = data;
    }
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View row = convertView;
        final RecordHolder holder;
        if (row == null) {
            LayoutInflater inflater = ((Activity) context).getLayoutInflater();
            row = inflater.inflate(layoutResourceId, parent, false);
            holder = new RecordHolder();

        } else {
            holder = (RecordHolder) row.getTag();
        }
        holder.txtTitle = (TextView) row.findViewById(R.id.item_text);
        holder.imageItem = (ImageView) row.findViewById(R.id.item_image);
        holder.price = (TextView) row.findViewById(R.id.item_text2);
        holder.e1 = (EditText) row.findViewById(R.id.editText);
        holder.b1 = (Button) row.findViewById(R.id.button3);
        holder.b2 = (Button) row.findViewById(R.id.button4);

        View x = (View)parent.getParent();
        holder.cart = (Button)x.findViewById(R.id.button);
//        //access button outside this view
//        ViewGroup parentV = (ViewGroup)row.getParent();
//        int childCount = parentV.getChildCount();
//        for(int j=0;j<childCount;j++){
//            View temp = parentV.getChildAt(j);
//            Button x1 = (Button)temp.findViewById(R.id.button);
//            if(x1 != null ){
//                holder.cart = x1;
//            }
//        }

        holder.b2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Integer num = Integer.parseInt(holder.e1.getText().toString());
                ++num;
                String size = holder.cart.getText().toString();
                size = size.substring(5,size.length()-1);

                int cartCount = Integer.parseInt(size);
                holder.e1.setText(num.toString());
                holder.cart.setText("Cart(" + (cartCount + 1)+ ")");
                Toast toast = Toast.makeText(context, "Added " + data.get(position).getTitle() + "!!", Toast.LENGTH_SHORT);
                toast.show();
            }
        });
        holder.b1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Integer num = Integer.parseInt(holder.e1.getText().toString());

                if(num > 0) {
                    --num;
                    holder.e1.setText(num.toString());
                    String size = holder.cart.getText().toString();
                    size = size.substring(5,size.length()-1);

                    int cartCount = Integer.parseInt(size);
                    holder.e1.setText(num.toString());
                    holder.cart.setText("Cart(" + (cartCount - 1) + ")");
                    Toast toast = Toast.makeText(context, "Removed " + data.get(position).getTitle() + "!!", Toast.LENGTH_SHORT);
                    toast.show();
                }

            }
        });
        row.setTag(holder);
        DisplayItem item = data.get(position);
        if( holder.e1 == null || holder.e1.getText() == null || holder.e1.getText().toString().equals("")) {
            holder.e1.setText(item.getE1().getText());
        }
        holder.e1.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if (hasFocus)
                    ((EditText) view).selectAll();
            }
        });
        holder.txtTitle.setText(item.getTitle());
        holder.imageItem.setImageBitmap(item.getBitmap());
        holder.price.setText("$" + item.getPrice().toString());
        return row;
    }
    static class RecordHolder {
        TextView txtTitle;
        ImageView imageItem;
        TextView price;
        Button b1;
        Button b2;
        EditText e1;
        Button cart;
        Button reset;
    }

    @Override
    public int getViewTypeCount() {

        return getCount();
    }

    @Override
    public int getItemViewType(int position) {

        return position;
    }


}
