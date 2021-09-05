package com.example.medalcasemock;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;

import com.example.medalcasemock.databinding.FragmentMedalCaseBinding;

import java.util.List;

public class MedalCaseFragment extends Fragment {

    private FragmentMedalCaseBinding binding;
    MedalCaseGridViewAdapter personalRecordsAdapter;
    MedalCaseGridViewAdapter virtualRacesAdapter;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        binding = FragmentMedalCaseBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initUI();

        MedalViewModel personalRecordsViewModel = new MedalViewModel();
        personalRecordsViewModel.getMedals(Constants.MedalType.PERSONAL_RECORD).observe(getActivity(), new Observer<List<MedalModel>>() {
            @Override
            public void onChanged(List<MedalModel> medals) {
                if (medals != null && !medals.isEmpty()) {
                    personalRecordsAdapter.addMedalList(getActivity(), medals);
                    personalRecordsAdapter.notifyDataSetChanged();
                }
            }
        });

        MedalViewModel virtualRacesViewModel = new MedalViewModel();
        virtualRacesViewModel.getMedals(Constants.MedalType.VIRTUAL_RACE).observe(getActivity(), new Observer<List<MedalModel>>() {
            @Override
            public void onChanged(List<MedalModel> medals) {
                if (medals != null && !medals.isEmpty()) {
                    virtualRacesAdapter.addMedalList(getActivity(), medals);
                    virtualRacesAdapter.notifyDataSetChanged();
                }
            }
        });
    }

    void initUI() {
        personalRecordsAdapter = new MedalCaseGridViewAdapter(getActivity());
        virtualRacesAdapter = new MedalCaseGridViewAdapter(getActivity());
        binding.gridviewPersonalRecords.setAdapter(personalRecordsAdapter);
        binding.gridviewVirtualRaces.setAdapter(virtualRacesAdapter);
    }
}