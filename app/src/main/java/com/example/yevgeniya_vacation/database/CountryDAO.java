//Student Name: Yevgeniya Anasheva
//Student ID: 119338192
package com.example.yevgeniya_vacation.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface CountryDAO {
    @Query("SELECT * FROM tbl_fav_countries")
    LiveData<List<CountryEntity>> getAllFavCountries();

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(CountryEntity countryEntity);
}
