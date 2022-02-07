package com.example.yevgeniya_vacation;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.util.Log;

import com.example.yevgeniya_vacation.adapters.MainAdapter;
import com.example.yevgeniya_vacation.adapters.OnCountryItemClickListener;
import com.example.yevgeniya_vacation.database.CountryEntity;
import com.example.yevgeniya_vacation.databinding.ActivityFavCountriesBinding;
import com.example.yevgeniya_vacation.models.Country;
import com.example.yevgeniya_vacation.viewmodels.CountryViewModel;

import java.util.ArrayList;
import java.util.List;

public class FavCountriesActivity extends AppCompatActivity implements OnCountryItemClickListener {
    private ActivityFavCountriesBinding binding;
    private final String TAG = this.getClass().getCanonicalName();
    private ArrayList<Country> favList;
    private MainAdapter adapter;
    private CountryViewModel countryViewModel;
    private Country tempCountryItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.binding = ActivityFavCountriesBinding.inflate(getLayoutInflater());
        setContentView(this.binding.getRoot());

        this.favList = new ArrayList<>();
        this.adapter = new MainAdapter(this, this.favList, this);
        this.binding.rvFavCountries.setAdapter(adapter);
        this.binding.rvFavCountries.setLayoutManager(new LinearLayoutManager(this));
        this.binding.rvFavCountries.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));

        this.countryViewModel = new ViewModelProvider(this).get(CountryViewModel.class);

        this.countryViewModel.getCountries().observe(this, new Observer<List<CountryEntity>>() {
            @Override
            public void onChanged(List<CountryEntity> countryEntities) {
                if(!countryEntities.isEmpty()) {
                    Log.d(TAG, "onChanged: countries received " + countryEntities.toString());
                    favList.clear();

                    for(CountryEntity tempCountry: countryEntities) {
                        tempCountryItem = new Country();
                        tempCountryItem.setCode(tempCountry.getCountryCode());
                        tempCountryItem.setName(tempCountry.getCountryName());
                        tempCountryItem.setCapital(tempCountry.getCapital());
                        tempCountryItem.setCountryFlag(tempCountry.getCountryImg());

                        favList.add(tempCountryItem);
                    }
                    adapter.notifyDataSetChanged();
                }
                else {
                    Log.d(TAG, "onChanged: empty country list");
                }
            }
        });
    }

    @Override
    public void onCountryItemClicked(Country country) {
        //not really needed here but needs to be implemented since the adapter is shared with the main
    }
}