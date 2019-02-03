package com.rds.andjaradji.bootcampmap.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rds.andjaradji.bootcampmap.Adapters.BootcampListAdapter;
import com.rds.andjaradji.bootcampmap.R;
import com.rds.andjaradji.bootcampmap.Services.DataService;


public class LocationListFragment extends Fragment {



    public LocationListFragment() {
        // Required empty public constructor
    }


    public static LocationListFragment newInstance() {
        LocationListFragment fragment = new LocationListFragment();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =inflater.inflate(R.layout.fragment_location_list, container, false);
        RecyclerView rv = view.findViewById(R.id.bootcampLocationRecyclerID);
        rv.setHasFixedSize(true);
        BootcampListAdapter adapter = new BootcampListAdapter(DataService.getInstance().getBootcampNearBy(13550));
        rv.setAdapter(adapter);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rv.setLayoutManager(layoutManager);

        return view;
    }

}
