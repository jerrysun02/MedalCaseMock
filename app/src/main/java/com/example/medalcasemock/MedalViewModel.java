package com.example.medalcasemock;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import org.json.JSONException;

import java.util.List;

public class MedalViewModel extends ViewModel {

    private MedalRepo medalRepo;
    private MutableLiveData<List<MedalModel>> mutableLiveData;

    public MedalViewModel() {
        medalRepo = new MedalRepo();
    }

    public LiveData<List<MedalModel>> getMedals(Constants.MedalType type) {
        if (mutableLiveData == null) {
            try {
                mutableLiveData = medalRepo.requestMedals(type);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return mutableLiveData;
    }
}