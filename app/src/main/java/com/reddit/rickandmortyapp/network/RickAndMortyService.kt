package com.reddit.rickandmortyapp.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import io.reactivex.Single
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

object RickAndMortyService {

    private val moshi: Moshi = Moshi.Builder().addLast(KotlinJsonAdapterFactory()).build()

    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl("https://rickandmortyapi.com/api/")
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()

    val api: RickAndMortyApi = retrofit.create(RickAndMortyApi::class.java)
}

interface RickAndMortyApi {
    @GET("character")
    fun fetchCharacters(): Call<RickAndMortyCharactersResponse>

    @GET("character")
    fun fetchCharactersRx(): Single<RickAndMortyCharactersResponse>

    @GET("character")
    suspend fun fetchCharactersCoroutine(): RickAndMortyCharactersResponse
}