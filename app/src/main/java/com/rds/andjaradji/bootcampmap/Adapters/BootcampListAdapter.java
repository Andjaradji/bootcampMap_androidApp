package com.rds.andjaradji.bootcampmap.Adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rds.andjaradji.bootcampmap.Holders.BootcampLocationHolder;
import com.rds.andjaradji.bootcampmap.Model.BootcampLocation;
import com.rds.andjaradji.bootcampmap.R;

import java.util.ArrayList;

/**
 * Created by Anjar on 06/03/2018.
 */

public class BootcampListAdapter extends RecyclerView.Adapter<BootcampLocationHolder> {
    private ArrayList<BootcampLocation> bootcampAdapterLocationList;

    public BootcampListAdapter(ArrayList<BootcampLocation> bootcampAdapterLocationList) {
        this.bootcampAdapterLocationList = bootcampAdapterLocationList;
    }

    @Override
    public BootcampLocationHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_bootcamp_detail_template,parent,false);

        return new BootcampLocationHolder(v);
    }

    @Override
    public void onBindViewHolder(BootcampLocationHolder holder, int position) {
        BootcampLocation bootcamp = bootcampAdapterLocationList.get(position);

        holder.updateHolderView(bootcamp);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Click Listener for each item
            }
        });

    }

    @Override
    public int getItemCount() {
        return bootcampAdapterLocationList.size();
    }
}
