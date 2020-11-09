package com.ea.Client;

/**
 * This is Retrofit API Interface to call the API endpoints
 */

import com.ea.Model.EmirateAuctionCarsResponse;


import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterface {

    /**
     * GET ALL CARS API
     */

    @GET("carsonline")
    Call<EmirateAuctionCarsResponse> getCars(@Query("Ticks") String Ticks);
}