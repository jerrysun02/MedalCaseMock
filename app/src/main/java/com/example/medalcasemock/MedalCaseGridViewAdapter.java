package com.example.medalcasemock;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;

public class MedalCaseGridViewAdapter extends BaseAdapter {

    private final Context mContext;


    public MedalCaseGridViewAdapter(Context context) {
        mContext = context;
        //Log.d("icons", "00size=*");
    }

    public MedalCaseGridViewAdapter(Context context, Integer[] icons, Integer[] strings) {
        Log.d("icons", "00size=#"+mIcons.length+ " ="+icons.length);
        mContext = context;
        mIcons = Arrays.copyOf(icons, icons.length);
        Log.d("icons", "00size=@"+mIcons.length+ " ="+icons.length);
        //testNames = strings;
    }

    @Override
    public int getCount() {
        return mIcons.length;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        if (view == null) {  // if it's not recycled, initialize some attributes
            final LayoutInflater layoutInflater = LayoutInflater.from(mContext);
            view = layoutInflater.inflate(R.layout.layout_medal_case_icon, null);
        }

        ImageView imageView = (ImageView)view.findViewById(R.id.imageView_icon);
        TextView textView = (TextView)view.findViewById(R.id.textView_test_name);


        //Log.d("landingNew", "position="+position+" id="+wlTest.getId()+" name="+wlTest.getTestName());
        imageView.setImageResource(mIcons[i]);
        textView.setText(testNames[i]);

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //do something
            }
        });
        return view;
    }

    private Integer[] mIcons = {
            R.drawable.ic_fastest_5k, R.drawable.ic_fastest_10k, R.drawable.ic_fastest_half_marathon,
            R.drawable.ic_fastest_marathon
    };

    private String[] testNames = {
            "Auto System", "Sensor Test", "Multitouch",
            "Touch Screen", "Sensor Test", "Multitouch"
    };

}
