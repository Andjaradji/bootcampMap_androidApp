package com.rds.andjaradji.bootcampmap.Services;

import com.rds.andjaradji.bootcampmap.Model.BootcampLocation;

import java.util.ArrayList;

/**
 * Created by Anjar on 05/03/2018.
 */

public class DataService {
    public static final DataService instance = new DataService();

   public static DataService getInstance() {
        return instance;
    }

    public DataService() {

    }

    public ArrayList<BootcampLocation>getBootcampNearBy (int postalCode){
        ArrayList<BootcampLocation> locList = new ArrayList<>();
        locList.add(new BootcampLocation(35.279f,-120.663f,"Downtown", "762 Higuera Street, San Luis Obispo, CA 93401","pic_1"));
        locList.add(new BootcampLocation(35.302f,-120.658f,"On The Campus", "1 Grand Ave, San Luis Obispo, CA 93401","pic_2"));
        locList.add(new BootcampLocation(35.267f,-120.652f,"East Side Tower", "2494 Victoria Ave, San Luis Obispo, CA 93401","pic_3"));

        return locList;
    }
}
