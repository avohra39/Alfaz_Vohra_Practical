package com.android.alfazvohrapractical.api;

import androidx.lifecycle.MutableLiveData;

import com.android.alfazvohrapractical.model.DataResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RestaurantRepository {

    private static RestaurantRepository repository;

    public static RestaurantRepository getInstance() {
        if (repository == null) {
            repository = new RestaurantRepository();
        }
        return repository;
    }

    private ApiInterface apiInterface;

    public RestaurantRepository() {
        apiInterface = ApiClient.createService(ApiInterface.class);
    }

    public MutableLiveData<DataResponse> getRestaurants(String lat, String lng, String code) {
        final MutableLiveData<DataResponse> data = new MutableLiveData<>();
        apiInterface.getRestaurants(lat, lng, code).enqueue(new Callback<DataResponse>() {
            @Override
            public void onResponse(Call<DataResponse> call, Response<DataResponse> response) {
                if (response.isSuccessful()) {
                    if (response.code() == 200) {
                        data.setValue(response.body());
                    }
                }
            }

            @Override
            public void onFailure(Call<DataResponse> call, Throwable t) {
                data.setValue(null);
            }
        });
        return data;
    }
}
