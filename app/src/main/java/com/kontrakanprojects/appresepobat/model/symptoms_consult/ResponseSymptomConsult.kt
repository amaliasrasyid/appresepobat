package com.kontrakanprojects.appresepobat.model.symptoms_consult

import com.google.gson.annotations.SerializedName

data class ResponseSymptomConsult(

    @field:SerializedName("result")
    val result: List<com.kontrakanprojects.appresepobat.model.symptoms_consult.ResultSymptompConsult?>? = null,

    @field:SerializedName("code")
    val code: Int? = null,

    @field:SerializedName("message")
    val message: String? = null
)

data class ResultSymptompConsult(

    @field:SerializedName("id_konsultasi_gejala")
    val idKonsultasiGejala: String? = null,

    @field:SerializedName("id_konsultasi")
    val idKonsultasi: String? = null,

    @field:SerializedName("symptomp")
    val symptomp: com.kontrakanprojects.appresepobat.model.symptoms_consult.Symptomp? = null,

    @field:SerializedName("status")
    val status: String? = null
)

data class Symptomp(

    @field:SerializedName("nm_gejala")
    val nmGejala: String? = null,

    @field:SerializedName("kd_gejala")
    val kdGejala: String? = null,

    @field:SerializedName("id_gejala")
    val idGejala: String? = null,

    @field:SerializedName("bobot_parameter")
    val bobotParameter: String? = null,

    @field:SerializedName("sympCategory")
    val sympCategory: String? = null,

    @field:SerializedName("isSelected")
    val isSelected: Boolean = false
)
