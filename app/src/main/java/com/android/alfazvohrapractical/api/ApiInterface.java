package com.android.alfazvohrapractical.api;

import com.android.alfazvohrapractical.model.DataResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterface {

    @GET("explore/get-explore-data")
    Call<DataResponse> getRestaurants(@Query("latitude") String lat,
                                      @Query("longitude") String lng,
                                      @Query("code") String code);

}
