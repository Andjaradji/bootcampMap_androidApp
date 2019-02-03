package com.rds.andjaradji.bootcampmap.Holders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.rds.andjaradji.bootcampmap.Model.BootcampLocation;
import com.rds.andjaradji.bootcampmap.R;

/**
 * Created by Anjar on 06/03/2018.
 */

public class BootcampLocationHolder extends RecyclerView.ViewHolder {
    public TextView bootcampTitle;
    public TextView bootcampAddress;
    private ImageView bootcampImg;

    public BootcampLocationHolder(View itemView) {
        super(itemView);
        bootcampTitle = itemView.findViewById(R.id.bootcampTitleID);
        bootcampAddress = itemView.findViewById(R.id.bootcampAddressID);
        bootcampImg = itemView.findViewById(R.id.bootcampImgID);
    }

    public void updateHolderView (BootcampLocation bootcamp) {
        bootcampTitle.setText(bootcamp.getBootcampLocationTitle());
        bootcampAddress.setText(bootcamp.getBootcampLocationAddress());

        String uri = bootcamp.getBootcampImgURI();
        int resource = bootcampImg.getResources().getIdentifier(uri,null,bootcampImg.getContext().getPackageName());

        bootcampImg.setImageResource(resource);


    }
}
