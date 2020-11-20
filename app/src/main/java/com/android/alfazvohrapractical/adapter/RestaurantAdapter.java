package com.android.alfazvohrapractical.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.alfazvohrapractical.R;
import com.android.alfazvohrapractical.core.CommonUtils;
import com.android.alfazvohrapractical.databinding.LayoutTopBinding;
import com.android.alfazvohrapractical.model.Data;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class RestaurantAdapter extends RecyclerView.Adapter<RestaurantAdapter.MyViewHolder> {

    private Context context;
    private ArrayList<Data> data;

    public RestaurantAdapter(Context context, ArrayList<Data> data) {
        this.context = context;
        this.data = data;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_top, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.binding.tvTitle.setText(data.get(position).getName());
        holder.binding.tvDesc.setText(data.get(position).getAddress());
        holder.binding.tvTime.setText(data.get(position).getPreparationTime() + " mins");
        holder.binding.rating.setRating(data.get(position).getAvgRatingByConsumer());
        if (data.get(position).getLogo() != null){
            CommonUtils.loadImage(context, holder.binding.ivImage, data.get(position).getLogo());
           // Picasso.with(context).load("https://cdn.journaldev.com/wp-content/uploads/2016/11/android-image-picker-project-structure.png").into(holder.binding.ivImage);
        }
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        LayoutTopBinding binding;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            binding = LayoutTopBinding.bind(itemView);
        }
    }

}
