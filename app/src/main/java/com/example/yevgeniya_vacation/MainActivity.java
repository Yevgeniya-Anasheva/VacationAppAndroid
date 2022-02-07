//Student Name: Yevgeniya Anasheva
//Student ID: 119338192

package com.example.yevgeniya_vacation;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.example.yevgeniya_vacation.adapters.MainAdapter;
import com.example.yevgeniya_vacation.adapters.OnCountryItemClickListener;
import com.example.yevgeniya_vacation.database.CountryEntity;
import com.example.yevgeniya_vacation.databinding.ActivityMainBinding;
import com.example.yevgeniya_vacation.models.Country;
import com.example.yevgeniya_vacation.network.RetrofitClient;
import com.example.yevgeniya_vacation.viewmodels.CountryViewModel;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements OnCountryItemClickListener {
    private ActivityMainBinding binding;
    private final String TAG = this.getClass().getCanonicalName();
    private ArrayList<Country> countryList;
    private MainAdapter adapter;
    private CountryViewModel countryViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(this.binding.getRoot());

        //initialize country list and view models
        this.countryList = new ArrayList<>();
        this.countryViewModel = new ViewModelProvider(this).get(CountryViewModel.class);

        //create adapter for recycler view
        adapter = new MainAdapter(this, countryList, this);
        this.binding.rvCountryList.setAdapter(adapter);
        this.binding.rvCountryList.setLayoutManager(new LinearLayoutManager(this));
        this.binding.rvCountryList.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));

        this.getCountryList();
    }

    private void getCountryList() {
        Call<List<Country>> call = RetrofitClient.getInstance().getApi().retrieveCountries();
        try {
            call.enqueue(new Callback<List<Country>>() {
                @Override
                public void onResponse(Call<List<Country>> call, Response<List<Country>> response) {
                    if(response.code() == 200 && response.body() != null) {
                         ArrayList<Country> main_response = (ArrayList<Country>) response.body();
                         Log.d(TAG, "onResponse: " + main_response.size() + " country objects");
                         countryList.clear();
                         countryList.addAll(main_response);
                         adapter.notifyDataSetChanged();
                    }
                    else {
                        Log.e(TAG, "onResponse: nothing received");
                    }
                    call.cancel();
                }

                @Override
                public void onFailure(Call<List<Country>> call, Throwable t) {
                    Log.e(TAG, "onFailure: error while fetching data " + t.getLocalizedMessage());
                }
            });
        }
        catch (Exception ex) {
            Log.e(TAG, "getCountryList: " + ex.getLocalizedMessage());
        }
    }

    @Override
    public void onCountryItemClicked(Country country) {
        //selected country, add to favourites
        CountryEntity newCountry = new CountryEntity();
        newCountry.setCountryCode(country.getCode());
        newCountry.setCountryName(country.getName());
        newCountry.setCapital(country.getCapital());
        newCountry.setCountryImg(country.getCountryFlag());

        this.countryViewModel.insertCountry(newCountry);
        Log.d(TAG, "onCountryItemClicked: " + newCountry.toString());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_option, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_show_fav:{
                //open favourited countries
                Intent favIntent = new Intent(this, FavCountriesActivity.class);
                startActivity(favIntent);
                break;
            }
        }
        return super.onOptionsItemSelected(item);
    }
}