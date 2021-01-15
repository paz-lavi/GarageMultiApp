package com.paz.common.api;

import com.paz.common.model.GarageModel;


import retrofit2.Call;
import retrofit2.http.GET;

public interface GarageAPI {
    @GET("WypPzJCt")
    Call<GarageModel> loadGarage();
}
