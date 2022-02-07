package com.example.yevgeniya_vacation.repositories;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.yevgeniya_vacation.database.AppDB;
import com.example.yevgeniya_vacation.database.CountryDAO;
import com.example.yevgeniya_vacation.database.CountryEntity;

import java.util.List;

public class CountryRepository {
    private AppDB db;
    private CountryDAO countryDAO;
    public LiveData<List<CountryEntity>> countries;

    public CountryRepository(Application application) {
        this.db = AppDB.getDatabase(application);
        this.countryDAO = this.db.countryDAO();
        this.countries = this.countryDAO.getAllFavCountries();
    }

    public void insertCountry(CountryEntity newCountry) {
        AppDB.databaseWriteExecutor.execute(()->{
            countryDAO.insert(newCountry);
        });
    }

    public LiveData<List<CountryEntity>> getCountries() { return this.countries; }

}
