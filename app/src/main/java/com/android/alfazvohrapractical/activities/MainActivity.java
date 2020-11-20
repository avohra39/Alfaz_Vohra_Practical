package com.android.alfazvohrapractical.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.android.alfazvohrapractical.R;
import com.android.alfazvohrapractical.adapter.RestaurantAdapter;
import com.android.alfazvohrapractical.api.RestaurantViewModel;
import com.android.alfazvohrapractical.databinding.ActivityMainBinding;
import com.android.alfazvohrapractical.model.Data;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private RestaurantAdapter adapter;
    private RestaurantViewModel viewModel;
    private ArrayList<Data> dataArrayList = new ArrayList<>();
    private ArrayList<Data> barsList = new ArrayList<>();
    private ArrayList<Data> recommList = new ArrayList<>();
    private ArrayList<Data> freeList = new ArrayList<>();
    private ArrayList<Data> cafeList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        viewModel = ViewModelProviders.of(this).get(RestaurantViewModel.class);
        viewModel.init();
        viewModel.getRestaurantRepository().observe(this, restaurantResponse -> {
            List<Data> topRated = restaurantResponse.getPayload().get(0).getData();
            dataArrayList.addAll(topRated);
            setAdapter(binding.rvTopRated, dataArrayList);
            List<Data> recomm = restaurantResponse.getPayload().get(1).getData();
            recommList.addAll(recomm);
            setAdapter(binding.rvRecommended, recommList);
            List<Data> free = restaurantResponse.getPayload().get(2).getData();
            freeList.addAll(free);
            setAdapter(binding.rvFreeDeliver, freeList);
            List<Data> cafe = restaurantResponse.getPayload().get(3).getData();
            cafeList.addAll(cafe);
            setAdapter(binding.rvCafe, cafeList);
            List<Data> bars = restaurantResponse.getPayload().get(4).getData();
            barsList.addAll(bars);
            setAdapter(binding.rvBars, barsList);
        });

    }

    private void setAdapter(RecyclerView recyclerView, ArrayList<Data> arrayList) {
        adapter = new RestaurantAdapter(MainActivity.this, arrayList);
        recyclerView.setAdapter(adapter);
        recyclerView.setHasFixedSize(true);
    }
}