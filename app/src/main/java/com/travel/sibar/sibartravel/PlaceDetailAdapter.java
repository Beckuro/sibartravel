package com.travel.sibar.sibartravel;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

/**
 * Created by ibrahim on 26/07/16.
 */
public class PlaceDetailAdapter extends ArrayAdapter<String> {

    Context context;
    String[] description;
    String[] iconName;

    LayoutInflater inflater;

    public PlaceDetailAdapter(Context c, String[] d, String[] iN){

        super(c, R.layout.detail_model);

        this.context = c;
        this.description = d;
        this.iconName = iN;
    }

    public class ViewHolder {
        TextView description;
        ImageView icon;
    }

    public View getView(int position, View convertView, ViewGroup parent){

        if(convertView == null){
            inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            convertView = inflater.inflate(R.layout.detail_model, null);
        }

        ViewHolder holder = new ViewHolder();

        holder.icon = (ImageView) convertView.findViewById(R.id.iconAct);
        holder.description = (TextView) convertView.findViewById(R.id.tvAct);

        holder.description.setText(description[position]);

        int imageID = R.drawable.camping;

        holder.icon.setImageResource(imageID);


        return convertView;


    }

}
