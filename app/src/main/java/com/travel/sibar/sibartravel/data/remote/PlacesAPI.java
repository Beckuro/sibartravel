package com.travel.sibar.sibartravel.data.remote;

import com.travel.sibar.sibartravel.data.model.Result;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;

/**
 * Created by satriabagus on 7/21/16.
 */
public interface PlacesAPI {

    String BASE_URL = "http://maps.googleapis.com/maps/api/geocode/";

    @GET("json?latlng=-6.3267556,106.8511782&sensor=true") Call<Result> getPlaces();

    class Factory {
        private static PlacesAPI service;

        public static PlacesAPI getInstance(){
            if (service == null){

                Retrofit retrofit = new Retrofit.Builder().addConverterFactory(GsonConverterFactory.create())
                        .baseUrl(BASE_URL)
                        .build();

                 service = retrofit.create(PlacesAPI.class);
                return  service;
            } else {
                return  service;
            }


        }
    }

}
