//Student Name: Yevgeniya Anasheva
//Student ID: 119338192

package com.example.yevgeniya_vacation.network;

import com.example.yevgeniya_vacation.models.Country;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface API {
    String BASE_URL = "https://restcountries.eu/rest/v2/";

    @GET("./all")
    Call<List<Country>> retrieveCountries();

//    @GET("https://www.countryflags.io/CA/flat/64.png")
//    Call<Country> retrieveCountryFlag(@Query() String countryCode);
}
