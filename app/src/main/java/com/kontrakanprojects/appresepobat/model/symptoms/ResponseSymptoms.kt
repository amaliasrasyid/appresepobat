package com.kontrakanprojects.appresepobat.model.symptoms

import com.google.gson.annotations.SerializedName

data class ResponseSymptoms(

    @field:SerializedName("result")
    val result: List<com.kontrakanprojects.appresepobat.model.symptoms.ResultSymptoms>? = null,

    @field:SerializedName("code")
    val code: Int? = null,

    @field:SerializedName("message")
    val message: String
)

data class ResultSymptoms(

    @field:SerializedName("nm_gejala")
    val nmGejala: String? = null,

    @field:SerializedName("kd_gejala")
    val kdGejala: String? = null,

    @field:SerializedName("id_gejala")
    val idGejala: String? = null,

    @field:SerializedName("bobot_parameter")
    val bobotParameter: String? = null,

    @field:SerializedName("sympCategory")
    val sympCategory: com.kontrakanprojects.appresepobat.model.symptoms.SympCategory? = null,

    @field:SerializedName("isSelected")
    var isSelected: Boolean = false
)

data class SympCategory(

    @field:SerializedName("gejala_kategori")
    val gejalaKategori: String? = null,

    @field:SerializedName("keterangan")
    val keterangan: String? = null,

    @field:SerializedName("id_gejala_kategori")
    val idGejalaKategori: String? = null
)
