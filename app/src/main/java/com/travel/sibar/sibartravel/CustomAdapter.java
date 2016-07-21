package com.travel.sibar.sibartravel;

import android.content.Context;
import android.media.Image;
import android.support.annotation.DrawableRes;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.zip.Inflater;

/**
 * Created by murizaro on 21/07/16.
 */
public class CustomAdapter extends ArrayAdapter<String> {

    Context c;
    String[] description;
    int[] icon;
    int iconLoc;
    LayoutInflater inflater;

    public CustomAdapter(Context context, String[] description, int[] icon, int iconLoc) {
        super(context, R.layout.rowmodel, description);

        this.c = context;
        this.description = description;
        this.icon = icon;
        this.iconLoc = iconLoc;
    }

    public class ViewHolder{
        TextView descTv;
        ImageView icon;
        ImageView iconLoc;
    }

    public View getView (int position, View convertView, ViewGroup parent) {
        if (convertView == null){
            inflater = (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.rowmodel, null);
        }

        ViewHolder holder = new ViewHolder();
        holder.descTv = (TextView) convertView.findViewById(R.id.descTv);
        holder.icon = (ImageView) convertView.findViewById(R.id.imageView);
        holder.iconLoc = (ImageView) convertView.findViewById(R.id.imageView2);

        holder.descTv.setText(description[position]);
        holder.icon.setImageResource(icon[position]);
        holder.iconLoc.setImageResource(iconLoc);

        return convertView;
    }
}
