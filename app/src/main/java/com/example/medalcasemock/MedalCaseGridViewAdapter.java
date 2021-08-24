package com.example.medalcasemock;

import android.content.Context;
import android.graphics.Color;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import static com.example.medalcasemock.Constants.MedalType.PERSONAL_RECORD;

public class MedalCaseGridViewAdapter extends BaseAdapter {

    private final Context mContext;
    private List<Medal> mMedalList;

    public MedalCaseGridViewAdapter(Context context) {
        mContext = context;
    }

    public MedalCaseGridViewAdapter(Context context, List<Medal> medalsList) {
        mContext = context;
        mMedalList = new ArrayList<Medal>(medalsList);
    }

    @Override
    public int getCount() {
        return mMedalList.size();
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

        Medal medal = mMedalList.get(i);

        ImageView imageView = view.findViewById(R.id.imageView_icon);
        imageView.setImageResource(medal.medalIcon);

        TextView textView_title = view.findViewById(R.id.textView_title);
        textView_title.setText(medal.medalTitle);

        if (medal.medalType == PERSONAL_RECORD) {
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            int height = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, -20, mContext.getResources().getDisplayMetrics());
            params.setMargins(0, height, 0, 0);
            textView_title.setLayoutParams(params);
        }

        TextView textView_record = view.findViewById(R.id.textView_record);
        textView_record.setText(medal.medalRecord);

        if (medal.medalRecord.equals("Not Yet")) {
            imageView.setColorFilter(Color.argb(150, 255, 255, 255));
            textView_title.setTextColor(Color.argb(120, 120, 120, 120));
            textView_record.setTextColor(Color.argb(120, 120, 120, 120));
        }

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //do something
            }
        });
        return view;
    }

}
