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

    private Context mContext;
    private List<MedalModel> mMedalList;

    public MedalCaseGridViewAdapter(Context context) {
        mContext = context;
        mMedalList = new ArrayList<>();
    }

    public void addMedalList(Context context, List<MedalModel> medalList) {
        mContext = context;
        mMedalList = medalList;
    }

    @Override
    public int getCount() {
        return mMedalList != null ? mMedalList.size() : 0;
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

        MedalModel medalModel = mMedalList.get(i);

        ImageView imageView = view.findViewById(R.id.imageView_icon);
        imageView.setImageResource(medalModel.getMedalIcon());

        TextView textView_title = view.findViewById(R.id.textView_title);
        textView_title.setText(medalModel.getMedalTitle());

        if (medalModel.getMedalType() == PERSONAL_RECORD) {
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            int height = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, -20, mContext.getResources().getDisplayMetrics());
            params.setMargins(0, height, 0, 0);
            textView_title.setLayoutParams(params);
        }

        TextView textView_record = view.findViewById(R.id.textView_record);
        textView_record.setText(medalModel.getMedalRecord());

        if (medalModel.getMedalRecord().equals("Not Yet")) {
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
