package com.kontrakanprojects.appresepobat.network

import retrofit2.Call
import retrofit2.http.*

interface ApiService {

    /** DISEASE ROUTE */

    @GET("disease/read.php")
    fun disease(): Call<com.kontrakanprojects.appresepobat.model.disease.ResponseDisease>

    @GET("disease/single-read.php")
    fun detailDisease(@Query("id") id: String): Call<com.kontrakanprojects.appresepobat.model.disease.ResponseDiseaseSolution>

    /** SOLUTION/TERAPI ROUTE */

    @GET("solution/read.php")
    fun solution(): Call<com.kontrakanprojects.appresepobat.model.solution.ResponseSolution>

    /** SYMPTOMP ROUTE */

    @GET("symptomp/read.php")
    fun symptompsByCategory(
        @Query("kategori") id_kategori: String,
        @Query("konsultasi") id_konsultasi: String
    ): Call<com.kontrakanprojects.appresepobat.model.symptoms.ResponseSymptoms>

    /** SYMPTOMP-CATEGORY ROUTE */
    @GET("symptomp_category/read.php")
    fun symptompCategory(): Call<com.kontrakanprojects.appresepobat.model.category.ResponseCategory>

    /** GEJALA CONSULT ROUTE */

    @GET("symptomp_consultation/read.php")
    fun symptompConsult(@Query("id_konsultasi") id: String): Call<com.kontrakanprojects.appresepobat.model.consult.ResponseConsult>

    @FormUrlEncoded
    @POST("symptomp_consultation/create.php")
    fun addOrUpdateSymptompConsult(
        @Field("list_id_gejala") listIdSymptoms: ArrayList<String>,
        @Field("id_konsultasi") id: String
    ): Call<com.kontrakanprojects.appresepobat.model.symptoms_consult.ResponseSymptomConsult>

    @DELETE("symptomp_consultation/delete.php")
    fun resetConsult(
        @Query("konsultasi") id_konsultasi: String
    ): Call<com.kontrakanprojects.appresepobat.model.consult.ResponseConsult>


    /** CONSULT ROUTE */

    @GET("consultation-result/read.php")
    fun resultConsult(@Query("konsultasi") idConsult: String): Call<com.kontrakanprojects.appresepobat.model.result.ResponseResult>

    @FormUrlEncoded
    @POST("consultation/create.php")
    fun addConsult(@Field("nama_konsul") consultName: String): Call<com.kontrakanprojects.appresepobat.model.consult.ResponseConsult>
}