//Student Name: Yevgeniya Anasheva
//Student ID: 119338192

package com.example.yevgeniya_vacation.models;

import com.google.gson.annotations.SerializedName;

public class Country {
    private String name;
    private String capital;
    private @SerializedName("alpha2Code") String code;
    private String countryFlag;

    public Country() {
    }

    public Country(String name, String capital, String code, String countryFlag) {
        this.name = name;
        this.capital = capital;
        this.code = code;
        this.countryFlag = countryFlag;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCapital() {
        return capital;
    }

    public void setCapital(String capital) {
        this.capital = capital;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCountryFlag() {
        return countryFlag;
    }

    public void setCountryFlag(String countryFlag) {
        this.countryFlag = countryFlag;
    }

    @Override
    public String toString() {
        return "Country{" +
                "name='" + name + '\'' +
                ", capital='" + capital + '\'' +
                ", code='" + code + '\'' +
                ", countryFlag='" + countryFlag + '\'' +
                '}';
    }
}
