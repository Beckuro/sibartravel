package com.travel.sibar.sibartravel;

/**
 * Created by ibrahim on 21/07/16.
 */
public class SearchResultsModel {


    String[] name;
    String[] imgURL;
    String[] price;
    String[] long_rad;
    String[] lat_rad;
    String[] placeID;

    public SearchResultsModel(){
        name = new String[2];
        imgURL = new String[2];
        price = new String[2];
        long_rad = new String[2];
        lat_rad = new String[2];
        placeID = new String[2];
    }
    public String[] getLat_rad() {
        return lat_rad;
    }

    public void setLat_rad(String[] lat_rad) {
        this.lat_rad = lat_rad;
    }

    public String[] getImgURL() {
        return imgURL;
    }

    public void setImgURL(String[] imgURL) {
        this.imgURL = imgURL;
    }

    public String[] getLong_rad() {
        return long_rad;
    }

    public void setLong_rad(String[] long_rad) {
        this.long_rad = long_rad;
    }

    public String[] getName() {
        return name;
    }

    public void setName(String[] name) {
        this.name = name;
    }

    public String[] getPlaceID() {
        return placeID;
    }

    public void setPlaceID(String[] placeID) {
        this.placeID = placeID;
    }

    public String[] getPrice() {
        return price;
    }

    public void setPrice(String[] price) {
        this.price = price;
    }

}
