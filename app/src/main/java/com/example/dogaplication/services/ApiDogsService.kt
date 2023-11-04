package com.example.dogaplication.services;

import com.example.dogaplication.entities.BreedsResponse;
import retrofit2.Call;
import retrofit2.http.GET;

interface ApiDogsService {
    @GET("api/breeds/list/all")
    fun getBreeds(): Call<BreedsResponse>
}
