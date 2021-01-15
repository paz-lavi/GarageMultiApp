package com.paz.common.api;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.paz.common.model.GarageModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class GarageController implements Callback<GarageModel> {

    static final String BASE_URL = "https://pastebin.com/raw/";
    private static final String TAG = "pttt";
    private CallBack_Garage callBack_garage;

    public void fetchGarage(CallBack_Garage callBack_garage) {
        Log.d(TAG, "fetchGarage: ");
        this.callBack_garage = callBack_garage;
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        GarageAPI garageAPI = retrofit.create(GarageAPI.class);

        Call<GarageModel> call = garageAPI.loadGarage();
        call.enqueue(this);
    }

    @Override
    public void onResponse(@NonNull Call<GarageModel> call, Response<GarageModel> response) {
        if (response.isSuccessful()) {
            GarageModel garageModel = response.body();
            Log.d(TAG, "onResponse: " + garageModel);
            callBack_garage.garage(garageModel);
        } else {
            System.out.println(response.errorBody());
        }
    }

    @Override
    public void onFailure(@NonNull Call<GarageModel> call, Throwable t) {
        Log.e(TAG, "onFailure: " + t.getMessage());
        t.printStackTrace();
    }

    public interface CallBack_Garage {
        void garage(GarageModel garage);
    }
}
