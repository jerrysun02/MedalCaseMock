package com.example.medalcasemock;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

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

        MedalCaseGridViewAdapter medalCaseGridViewAdapter;
        String medalType;

        medalType = "personal records";
        medalCaseGridViewAdapter = new MedalCaseGridViewAdapter(getActivity(), recordIcons, recordStrings, personalRecords, medalType);
        GridView gridView = view.findViewById(R.id.gridview_personal_records);
        gridView.setAdapter(medalCaseGridViewAdapter);

        medalType = "virtual races";
        medalCaseGridViewAdapter = new MedalCaseGridViewAdapter(getActivity(), raceIcons, raceStrings, raceRecords, medalType);
        gridView = view.findViewById(R.id.gridview_virtual_races);
        gridView.setAdapter(medalCaseGridViewAdapter);
    }

    private int[] recordIcons = {
            R.drawable.ic_longest_run, R.drawable.ic_highest_elevation,
            R.drawable.ic_fastest_5k, R.drawable.ic_fastest_10k,
            R.drawable.ic_fastest_half_marathon, R.drawable.ic_fastest_marathon
    };

    private int[] raceIcons = {
            R.drawable.virtual_half_marathon_race, R.drawable.tokyo_kakone_ekiden,
            R.drawable.virtual_10k_race, R.drawable.hakone_ekiden,
            R.drawable.mizuno_singapore_ekiden, R.drawable.virtual_5k_race
    };

    private int[] recordStrings = {
            R.string.longest_run, R.string.highest_elevation,
            R.string.fastest_5k, R.string.fastest_10k,
            R.string.half_marathon, R.string.marathon
    };

    private int[] raceStrings = {
            R.string.virtual_half_marathon_race, R.string.tokyo_hakone_ekiden_2020,
            R.string.virtual_10k_race, R.string.hakone_ekiden,
            R.string.mizuno_singapore_ekiden_2015, R.string.virtual_5k_race
    };

    private String[] personalRecords = {
            "00:00", "2095ft", "00:00",
            "00:00:00", "00:00", "Not Yet"
    };

    private String[] raceRecords = {
            "00:00", "00:00:00", "00:00:00",
            "00:00:00", "00:00:00", "23:07"
    };
}