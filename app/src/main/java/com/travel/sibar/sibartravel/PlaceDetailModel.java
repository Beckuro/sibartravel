package com.travel.sibar.sibartravel;

import java.util.ArrayList;

/**
 * Created by ibrahim on 26/07/16.
 */
public class PlaceDetailModel {

    ArrayList<String> description;
    ArrayList<String> icon;

    public ArrayList<String> getDescription() {
        return description;
    }

    public void setDescription(ArrayList<String> description) {
        this.description = description;
    }

    public ArrayList<String> getIcon() {
        return icon;
    }

    public void setIcon(ArrayList<String> icon) {
        this.icon = icon;
    }

    public PlaceDetailModel(){
        description = new ArrayList<String>();
        icon = new ArrayList<String>();
    }


}
