package com.travel.sibar.sibartravel;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

/**
 * Created by ibrahim on 21/07/16.
 */
;

public class SearchResultsAdapter extends ArrayAdapter<String> {

    Context context;
    String[] name;
    String[] price;
    String [] lat_rad;
    String [] long_rad;
    String [] imgURL;
    String[] distance;

    LayoutInflater inflater;

    public SearchResultsAdapter(Context context, String[] imgURL, String[] name, String [] price, String[] lat_rad, String[] long_rad, String[] distance){
        super(context, R.layout.search_result_model, name);

        this.context = context;
        this.name = name;
        this.lat_rad = lat_rad;
        this.long_rad = long_rad;
        this.price = price;
        this.imgURL = imgURL;
        this.distance = distance;

        Log.d("Isi di adapter name", toString(this.name));
        Log.d("isi di adapter imgUrl", toString(this.imgURL));
        Log.d("isi di adapter distance", toString(this.distance));
        Log.d("isi di adapter price", toString(this.price));
    }

    public String toString(String[] arr){

        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < arr.length; i++){
            sb.append(arr[i] + " ");
        }
        return sb.toString();
    }

    public class ViewHolder {
        TextView namePlace;
        TextView pricePlace;
        TextView distancePlace;
        ImageView imgPlace;
    }
    public View getView(int position, View convertView, ViewGroup parent){
        if(convertView == null){
            inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            convertView = inflater.inflate(R.layout.search_result_model, null);
        }

        ViewHolder holder = new ViewHolder();

        holder.namePlace = (TextView) convertView.findViewById(R.id.namePlace);
        holder.distancePlace = (TextView) convertView.findViewById(R.id.distancePlace);
        holder.pricePlace = (TextView) convertView.findViewById(R.id.pricePlace);
        holder.imgPlace = (ImageView) convertView.findViewById(R.id.imgPlace);

        holder.namePlace.setText(name[position]);
        holder.distancePlace.setText(distance[position]);
        holder.pricePlace.setText(price[position]);

        Picasso.with(context).load(imgURL[position]).fit().into(holder.imgPlace);



        return convertView;
    }

}
