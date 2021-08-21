package com.example.medalcasemock;

import android.content.Context;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.Arrays;

public class MedalCaseGridViewAdapter extends BaseAdapter {

    private final Context mContext;
    private int[] mIcons, mTitles;
    private String[] mRecords;
    private String mMedalType;

    public MedalCaseGridViewAdapter(Context context) {
        mContext = context;
    }

    public MedalCaseGridViewAdapter(Context context, int[] icons, int[] titles, String[] records, String type) {
        mContext = context;
        mIcons = Arrays.copyOf(icons, icons.length);
        mTitles = Arrays.copyOf(titles, titles.length);
        mRecords = Arrays.copyOf(records, records.length);
        mMedalType = type;
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

        ImageView imageView = view.findViewById(R.id.imageView_icon);
        imageView.setImageResource(mIcons[i]);

        TextView textView_title = view.findViewById(R.id.textView_title);
        textView_title.setText(mContext.getResources().getString(mTitles[i]));

        if (mMedalType.equals("personal records")) {
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            int height = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, -20, mContext.getResources().getDisplayMetrics());
            params.setMargins(0, height, 0, 0);
            textView_title.setLayoutParams(params);
        }

        TextView textView_record = view.findViewById(R.id.textView_record);
        textView_record.setText(mRecords[i]);

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //do something
            }
        });
        return view;
    }

}
