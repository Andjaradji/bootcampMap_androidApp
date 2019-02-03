package com.rds.andjaradji.bootcampmap.Model;

/**
 * Created by Anjar on 05/03/2018.
 */

public class BootcampLocation  {
    final String DRAWABLE = "drawable/";

    private float  bootcampLatitude;
    private float  bootcampLongitude;
    private String bootcampLocationTitle;
    private String bootcampLocationAddress;
    private String bootcampImgURI;

    public BootcampLocation(float bootcampLatitude, float bootcampLongitude, String bootcampLocationTitle, String bootcampLocationAddress, String bootcampImgURI) {
        this.bootcampLatitude = bootcampLatitude;
        this.bootcampLongitude = bootcampLongitude;
        this.bootcampLocationTitle = bootcampLocationTitle;
        this.bootcampLocationAddress = bootcampLocationAddress;
        this.bootcampImgURI = bootcampImgURI;
    }

    public float getBootcampLatitude() {
        return bootcampLatitude;
    }

    public float getBootcampLongitude() {
        return bootcampLongitude;
    }

    public String getBootcampLocationTitle() {
        return bootcampLocationTitle;
    }

    public String getBootcampLocationAddress() {
        return bootcampLocationAddress;
    }

    public String getBootcampImgURI() {
        return DRAWABLE + bootcampImgURI;
    }
}
