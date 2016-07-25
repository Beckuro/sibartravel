package com.travel.sibar.sibartravel;

import java.util.ArrayList;

/**
 * Created by ibrahim on 21/07/16.
 */
public class SearchResultsModel {


    ArrayList<String> name;
    ArrayList<String> imgURL;
    ArrayList<String> price;
    ArrayList<String> coordinates;
    ArrayList<String> placeID;
    ArrayList<String> distance;

    public SearchResultsModel(){
        name = new ArrayList<String>();
        imgURL = new ArrayList<String>();
        price = new ArrayList<String>();
        placeID = new ArrayList<String>();
        distance = new ArrayList<String>();
        coordinates = new ArrayList<String>();
    }

    public ArrayList<String> getImgURL() {
        return imgURL;
    }

    public void setImgURL(ArrayList<String> imgURL) {
        this.imgURL = imgURL;
    }

    public ArrayList<String> getCoordinates(){
        return this.coordinates;
    }

    public ArrayList<String> getName() {
        return name;
    }

    public void setName(ArrayList<String> name) {
        this.name = name;
    }

    public ArrayList<String> getPlaceID() {
        return placeID;
    }

    public void setPlaceID(ArrayList<String> placeID) {
        this.placeID = placeID;
    }

    public ArrayList<String> getPrice() {
        return price;
    }

    public void setPrice(ArrayList<String> price) {
        this.price = price;
    }


    public ArrayList<String> getDistance() {
        return distance;
    }

    public void setCoordinates(ArrayList<String> c){
        this.coordinates = c;
    }

    public void setDistance(ArrayList<String> distance) {
        this.distance = distance;
    }
}
