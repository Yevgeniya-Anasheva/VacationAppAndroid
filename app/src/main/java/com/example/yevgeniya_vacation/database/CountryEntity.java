package com.example.yevgeniya_vacation.database;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "tbl_fav_countries")
public class CountryEntity {
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "country_code")
    private String countryCode;

    @NonNull
    @ColumnInfo(name = "country_name")
    private String countryName;

    @NonNull
    @ColumnInfo(name = "country_capital")
    private String capital;

    @ColumnInfo(name = "country_img")
    private String countryImg;

    public CountryEntity() {
    }

    public CountryEntity(String countryCode, @NonNull String countryName, @NonNull String capital, String countryImg) {
        this.countryCode = countryCode;
        this.countryName = countryName;
        this.capital = capital;
        this.countryImg = countryImg;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    @NonNull
    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(@NonNull String countryName) {
        this.countryName = countryName;
    }

    @NonNull
    public String getCapital() {
        return capital;
    }

    public void setCapital(@NonNull String capital) {
        this.capital = capital;
    }

    public String getCountryImg() {
        return countryImg;
    }

    public void setCountryImg(String countryImg) {
        this.countryImg = countryImg;
    }

    @Override
    public String toString() {
        return "CountryEntity{" +
                "countryCode='" + countryCode + '\'' +
                ", countryName='" + countryName + '\'' +
                ", capital='" + capital + '\'' +
                ", countryImg='" + countryImg + '\'' +
                '}';
    }
}
