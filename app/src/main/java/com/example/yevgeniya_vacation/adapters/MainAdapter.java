//Student Name: Yevgeniya Anasheva
//Student ID: 119338192

package com.example.yevgeniya_vacation.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.yevgeniya_vacation.databinding.RvItemCountryBinding;
import com.example.yevgeniya_vacation.models.Country;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.MainViewHolder> {
    private final Context context;
    private final ArrayList<Country> countryList;
    private final OnCountryItemClickListener itemClickListener;
    private static final String TAG = "MainAdapter";

    public MainAdapter(Context context, ArrayList<Country> countryList, OnCountryItemClickListener itemClickListener) {
        this.context = context;
        this.countryList = countryList;
        this.itemClickListener = itemClickListener;
    }

    @Override
    public int getItemCount() { return this.countryList.size(); }

    @Override
    public MainAdapter.MainViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MainViewHolder(RvItemCountryBinding.inflate(LayoutInflater.from(context)));
    }

    @Override
    public void onBindViewHolder(MainAdapter.MainViewHolder holder, int position) {
        final Country currentCountryItem = countryList.get(position);
        holder.bind(context, currentCountryItem, itemClickListener);
    }

    public static class MainViewHolder extends RecyclerView.ViewHolder {
        RvItemCountryBinding binding;

        public MainViewHolder(RvItemCountryBinding b) {
            super(b.getRoot());
            this.binding = b;
        }

        public void bind(Context context, final Country currentCountryItem, OnCountryItemClickListener clickListener) {
            Log.d(TAG, "bind(): " + currentCountryItem.toString());
            binding.tvCountryName.setText(currentCountryItem.getName());
            binding.tvCapital.setText(currentCountryItem.getCapital());
            Picasso.get()
                    .load("https://www.countryflags.io/"+currentCountryItem.getCode()+"/flat/64.png")
                    .placeholder(ContextCompat.getDrawable(context, android.R.drawable.ic_media_next))
                    .into(binding.imgFlag);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    clickListener.onCountryItemClicked(currentCountryItem);
                }
            });
        }
    }
}
