package com.example.medalcasemock;

import androidx.lifecycle.MutableLiveData;

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

public class MedalRepo {

    List<MedalModel> medalModelList = new ArrayList<>();

    public MedalRepo() {
        getAllMedals();
    }

    public void getAllMedals() {
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

        try {
            medalModelList = getAllMedals(jsonArray);  //get the medals from JSONArray
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public MutableLiveData<List<MedalModel>> requestMedals(Constants.MedalType type) throws JSONException {
        MutableLiveData<List<MedalModel>> mutableLiveData = new MutableLiveData<>();
        mutableLiveData.setValue(getMedals(medalModelList, type));
        return mutableLiveData;
    }

    private List<MedalModel> getAllMedals(JSONArray jsonArray) throws JSONException {
        List<MedalModel> medalModelList = new ArrayList<>();

        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject row = jsonArray.getJSONObject(i);
            MedalModel medalModel = new MedalModel();
            medalModel.setMedalId((int)row.get("id"));
            if (row.get("type").equals("PERSONAL_RECORD"))
                medalModel.setMedalType(PERSONAL_RECORD);
            else
                medalModel.setMedalType(VIRTUAL_RACE);
            medalModel.setMedalIcon((int)row.get("icon"));
            medalModel.setMedalTitle((int)row.get("title"));
            medalModel.setMedalRecord((String)row.get("record"));
            medalModelList.add(medalModel);
        }

        return medalModelList;
    }

    private List<MedalModel> getMedals(List<MedalModel> medalModelList, Constants.MedalType medalType) {
        List<MedalModel> resultList = new ArrayList<>();

        Iterator itr = medalModelList.iterator();
        while (itr.hasNext())
        {
            MedalModel medalModel = (MedalModel) itr.next();
            if (medalModel.getMedalType() == medalType)
                resultList.add(medalModel);
        }

        Collections.sort(resultList, new Comparator<MedalModel>() { // sort the resultList by id
            @Override
            public int compare(MedalModel left, MedalModel right) {
                return Integer.compare(left.getMedalId(), right.getMedalId());
            }
        });

        return resultList;
    }

}
