package com.android.alfazvohrapractical.api;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.android.alfazvohrapractical.model.DataResponse;

public class RestaurantViewModel extends ViewModel {

    private MutableLiveData<DataResponse> mutableLiveData;
    private RestaurantRepository restaurantRepository;

    public void init(){
        if (mutableLiveData!=null){
            return;
        }

        restaurantRepository = RestaurantRepository.getInstance();
        mutableLiveData = restaurantRepository.getRestaurants("23.0343561", "72.562385", "EN");
    }

    public LiveData<DataResponse> getRestaurantRepository(){
        return mutableLiveData;
    }

}
