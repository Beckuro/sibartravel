package com.travel.sibar.sibartravel;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by ibrahim on 21/07/16.
 */
;import org.w3c.dom.Text;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class SearchResultsAdapter extends ArrayAdapter<String> {

    Context context;
    String[] name = {};
    String[] price = {};
    String [] lat_rad = {};
    String [] long_rad = {};
    String [] imgURL = {};
    LayoutInflater inflater;

    public SearchResultsAdapter(Context context, String[] imgURL, String[] name, String [] price, String[] lat_rad, String[] long_rad){
        super(context, R.layout.search_result_model);

        this.context = context;
        this.name = name;
        this.lat_rad = lat_rad;
        this.long_rad = long_rad;
        this.price = price;
        this.imgURL = imgURL;

        Log.d("Isi di adapter name", toString(this.name));
        Log.d("isi di adapter imgUrl", toString(this.imgURL));
        Log.d("isi di adapter distance", toString(this.long_rad));
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
        //ImageView imgPlace;
        Bitmap bitmap;

       /* public Bitmap getBitmapFromURLHelper(String src){
            try{
                URL url = new URL(src);
                HttpURLConnection connection = (HttpURLConnection)url.openConnection();
                connection.setDoInput(true);
                connection.connect();
                InputStream input = connection.getInputStream();
                Bitmap myBitmap = BitmapFactory.decodeStream(input);
                bitmap = myBitmap;

                Log.d("Bitmap", bitmap.toString());

                return bitmap;

            } catch (Exception e){
                e.printStackTrace();
            }

            return null;
        }

        public ImageView setImageHelper(ImageView iv, Bitmap bmp){

            iv.setImageBitmap(bmp);
            return iv;
        }

        public ImageView setImage(String src){

            bitmap = getBitmapFromURLHelper(src);

            imgPlace = setImageHelper(this.imgPlace, bitmap);

            return imgPlace;
        }*/

    }
    public View getView(int position, View convertView, ViewGroup parent){
        if(convertView == null){
            inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            convertView = inflater.inflate(R.layout.search_result_model, null);
        }

        ViewHolder holder = new ViewHolder();

        holder.namePlace = (TextView) convertView.findViewById(R.id.namePlace);
        holder.distancePlace = (TextView) convertView.findViewById(R.id.distancePlace);
        //holder.imgPlace = (ImageView) convertView.findViewById(R.id.imgPlace);
        holder.pricePlace = (TextView) convertView.findViewById(R.id.pricePlace);

        holder.namePlace.setText(name[position]);
        holder.distancePlace.setText(long_rad[position]);
        holder.pricePlace.setText(price[position]);
        //holder.setImage(imgURL[position]);

        //return super.getView(position, convertView, parent);
        return convertView;
    }

}
