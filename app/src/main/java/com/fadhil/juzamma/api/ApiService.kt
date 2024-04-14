package com.fadhil.juzamma.api


import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET("surah")
    fun getListSurah(): Call<ResponseSurah>

    @GET("surah/{number}/editions/quran-uthmani,ar.alafasy,id.indonesian")
    fun getDetailSurahWithQuranEdition(@Path("number") numberSurah: Int): Call<ResponseAyah>
}