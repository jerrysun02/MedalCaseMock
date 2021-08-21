package com.example.medalcasemock;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

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

        medalCaseGridViewAdapter = new MedalCaseGridViewAdapter(getActivity(), recordIcons, recordStrings);

        GridView gridView = (GridView) view.findViewById(R.id.gridview_personal_records);
        gridView.setAdapter(medalCaseGridViewAdapter);

        medalCaseGridViewAdapter = new MedalCaseGridViewAdapter(getActivity(), raceIcons, raceStrings);
        gridView = (GridView) view.findViewById(R.id.gridview_virtual_races);
        gridView.setAdapter(medalCaseGridViewAdapter);
    }

    private Integer[] recordIcons = {
            R.drawable.ic_longest_run, R.drawable.ic_highest_elevation,
            R.drawable.ic_fastest_5k, R.drawable.ic_fastest_10k,
            R.drawable.ic_fastest_half_marathon, R.drawable.ic_fastest_marathon
    };

    private Integer[] raceIcons = {
            R.drawable.virtual_half_marathon_race, R.drawable.tokyo_kakone_ekiden,
            R.drawable.virtual_10k_race, R.drawable.hakone_ekiden,
            R.drawable.mizuno_singapore_ekiden, R.drawable.virtual_5k_race
    };

    private Integer[] recordStrings = {
            R.string.longest_run, R.string.highest_elevation,
            R.string.fastest_5k, R.string.fastest_10k,
            R.string.half_marathon, R.string.marathon
    };

    private Integer[] raceStrings = {
            R.string.longest_run, R.string.highest_elevation,
            R.string.fastest_5k, R.string.fastest_10k,
            R.string.half_marathon, R.string.marathon
    };

}