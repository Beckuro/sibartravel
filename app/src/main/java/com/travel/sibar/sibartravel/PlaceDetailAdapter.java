package com.travel.sibar.sibartravel;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

/**
 * Created by ibrahim on 26/07/16.
 */
public class PlaceDetailAdapter extends ArrayAdapter<String> {

    Context context;
    String[] description;
    String[] iconName;

    LayoutInflater inflater;

    public PlaceDetailAdapter(Context c, String[] d, String[] iN){

        super(c, R.layout.detail_model, d);

        this.context = c;
        this.description = d;
        this.iconName = iN;
    }

    public class ViewHolder {
        TextView description;
        ImageView icon;
        TextView actName;
    }

    public View getView(int position, View convertView, ViewGroup parent){

        if(convertView == null){
            inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            convertView = inflater.inflate(R.layout.detail_model, null);
        }

        ViewHolder holder = new ViewHolder();

        holder.icon = (ImageView) convertView.findViewById(R.id.iconAct);
        holder.description = (TextView) convertView.findViewById(R.id.tvAct);
        holder.actName = (TextView) convertView.findViewById(R.id.tvActName);

        holder.description.setText(description[position]);

        int imageID;
        String actNameStr;
        int activityID;
        if(iconName[position] != null) {
            activityID = Integer.parseInt(iconName[position]);
        } else {
            activityID = 0;
        }


        if(activityID == 1){
            imageID = R.drawable.trekking;
            actNameStr = "TREKKING";
        } else if (activityID == 2) {
            imageID = R.drawable.snorkel;
            actNameStr = "SNORKELING";
        } else if (activityID == 3) {
            imageID = R.drawable.camping;
            actNameStr = "CAMPING";
        } else if (activityID == 4) {
            imageID = R.drawable.mountain;
            actNameStr = "MOUNTAIN";
        } else if (activityID == 5) {
            imageID = R.drawable.hiddentribe;
            actNameStr = "HIDDEN TRIBE";
        } else if (activityID == 6) {
            imageID = R.drawable.elephant;
            actNameStr = "ELEPHANT SEEING";
        } else if (activityID == 7) {
            imageID = R.drawable.paragliding;
            actNameStr = "PARAGLIDING";
        } else if (activityID == 8) {
            imageID = R.drawable.skydiving;
            actNameStr = "SKYDIVING";
        } else if (activityID == 9) {
            imageID = R.drawable.swimming;
            actNameStr = "SWIMMING";
        } else if (activityID == 10) {
            imageID = R.drawable.safari;
            actNameStr = "SAFARI";
        } else {
            imageID = R.drawable.maps;
            actNameStr = "";
        }

        holder.actName.setText(actNameStr);
        holder.icon.setImageResource(imageID);

       /* holder.icon.getLayoutParams().height = 16;
        holder.icon.getLayoutParams().width = 16;*/


        return convertView;


    }

}
