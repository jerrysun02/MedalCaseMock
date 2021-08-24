package com.example.medalcasemock;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

import static com.example.medalcasemock.Constants.MedalType.PERSONAL_RECORD;
import static com.example.medalcasemock.Constants.MedalType.VIRTUAL_RACE;

public class MedalCaseFragment extends Fragment {

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_medal_case, container, false);
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        String[] medals = new String[12];
        medals[5] = "{\"id\":1, \"type\":\"PERSONAL_RECORD\", \"icon\":"+R.drawable.ic_longest_run+", \"title\":" + R.string.longest_run + ", \"record\":\"00:00\"}";
        medals[11] = "{\"id\":2, \"type\":\"PERSONAL_RECORD\", \"icon\":"+R.drawable.ic_highest_elevation+", \"title\":" + R.string.highest_elevation + ", \"record\":\"2095ft\"}";
        medals[6] = "{\"id\":3, \"type\":\"PERSONAL_RECORD\", \"icon\":"+R.drawable.ic_fastest_5k+", \"title\":" + R.string.fastest_5k + ", \"record\":\"00:00\"}";
        medals[1] = "{\"id\":4, \"type\":\"PERSONAL_RECORD\", \"icon\":"+R.drawable.ic_fastest_10k+", \"title\":" + R.string.fastest_10k + ", \"record\":\"00:00:00\"}";
        medals[8] = "{\"id\":5, \"type\":\"PERSONAL_RECORD\", \"icon\":"+R.drawable.ic_fastest_half_marathon+", \"title\":" + R.string.half_marathon + ", \"record\":\"00:00\"}";
        medals[7] = "{\"id\":6, \"type\":\"PERSONAL_RECORD\", \"icon\":"+R.drawable.ic_fastest_marathon+", \"title\":" + R.string.marathon + ", \"record\":\"Not Yet\"}";
        medals[0] = "{\"id\":7, \"type\":\"VIRTUAL_RACE\", \"icon\":"+R.drawable.virtual_half_marathon_race+", \"title\":" + R.string.virtual_half_marathon_race + ", \"record\":\"00:00\"}";
        medals[4] = "{\"id\":8, \"type\":\"VIRTUAL_RACE\", \"icon\":"+R.drawable.tokyo_kakone_ekiden+", \"title\":" + R.string.tokyo_hakone_ekiden_2020 + ", \"record\":\"00:00:00\"}";
        medals[9] = "{\"id\":9, \"type\":\"VIRTUAL_RACE\", \"icon\":"+R.drawable.virtual_10k_race+", \"title\":" + R.string.virtual_10k_race + ", \"record\":\"00:00:00\"}";
        medals[2] = "{\"id\":10, \"type\":\"VIRTUAL_RACE\", \"icon\":"+R.drawable.hakone_ekiden+", \"title\":" + R.string.hakone_ekiden + ", \"record\":\"00:00:00\"}";
        medals[10] = "{\"id\":11, \"type\":\"VIRTUAL_RACE\", \"icon\":"+R.drawable.mizuno_singapore_ekiden+", \"title\":" + R.string.mizuno_singapore_ekiden_2015 + ", \"record\":\"00:00:00\"}";
        medals[3] = "{\"id\":12, \"type\":\"VIRTUAL_RACE\", \"icon\":"+R.drawable.virtual_5k_race+", \"title\":" + R.string.virtual_5k_race + ", \"record\":\"23:07\"}";

        JSONObject jsonObject = null;
        JSONArray jsonArray = new JSONArray();

        for (int i = 0; i < medals.length; i++) {
            try {
                jsonObject = new JSONObject(medals[i]);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            jsonArray.put(jsonObject); //put the medals into JSONArray
        }

        List<Medal> medalList = new ArrayList<>();
        try {
            medalList = getAllMedals(jsonArray);  //get the medals from JSONArray
        } catch (JSONException e) {
            e.printStackTrace();
        }

        GridView gridView;
        MedalCaseGridViewAdapter medalCaseGridViewAdapter;

        medalCaseGridViewAdapter = new MedalCaseGridViewAdapter(getActivity(), getMedals(medalList, PERSONAL_RECORD));
        gridView = view.findViewById(R.id.gridview_personal_records);
        gridView.setAdapter(medalCaseGridViewAdapter);

        medalCaseGridViewAdapter = new MedalCaseGridViewAdapter(getActivity(), getMedals(medalList, VIRTUAL_RACE));
        gridView = view.findViewById(R.id.gridview_virtual_races);
        gridView.setAdapter(medalCaseGridViewAdapter);
    }

    private List<Medal> getAllMedals(JSONArray jsonArray) throws JSONException {
        List<Medal> medalList = new ArrayList<>();

        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject row = jsonArray.getJSONObject(i);
            Medal medal = new Medal();
            medal.medalId = (int) row.get("id");
            if (row.get("type").equals("PERSONAL_RECORD"))
                medal.medalType = PERSONAL_RECORD;
            else
                medal.medalType = VIRTUAL_RACE;
            medal.medalIcon = (int) row.get("icon");
            medal.medalTitle = (int) row.get("title");
            medal.medalRecord = (String) row.get("record");
            medalList.add(medal);
        }

        return medalList;
    }

    private List<Medal> getMedals(List<Medal> medalList, Constants.MedalType medalType) {
        List<Medal> resultList = new ArrayList<>();

        Iterator itr = medalList.iterator();
        while (itr.hasNext())
        {
            Medal medal = (Medal) itr.next();
            if (medal.medalType == medalType)
                resultList.add(medal);
        }

        Collections.sort(resultList, new Comparator<Medal>() { // sort the resultList by id
            @Override
            public int compare(Medal left, Medal right) {
                return Integer.compare(left.medalId, right.medalId);
            }
        });

        return resultList;
    }
}