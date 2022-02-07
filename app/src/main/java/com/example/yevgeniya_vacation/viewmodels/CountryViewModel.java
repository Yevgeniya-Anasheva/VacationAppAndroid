package com.example.yevgeniya_vacation.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.yevgeniya_vacation.database.CountryEntity;
import com.example.yevgeniya_vacation.repositories.CountryRepository;

import java.util.List;

public class CountryViewModel extends AndroidViewModel {
    private CountryRepository countryRepo;
    private LiveData<List<CountryEntity>> countries;


    public CountryViewModel(Application application) {
        super(application);
        this.countryRepo = new CountryRepository(application);
        this.countries = this.countryRepo.countries;
    }

    public LiveData<List<CountryEntity>> getCountries() { return countries; }

    public void insertCountry(CountryEntity newCountry){
        this.countryRepo.insertCountry(newCountry);
    }
}
