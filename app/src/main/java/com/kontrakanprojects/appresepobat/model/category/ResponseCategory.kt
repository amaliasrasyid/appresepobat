package com.kontrakanprojects.appresepobat.model.category

import com.google.gson.annotations.SerializedName

data class ResponseCategory(

	@field:SerializedName("result")
	val result: List<com.kontrakanprojects.appresepobat.model.category.SymptompCategory?>? = null,

	@field:SerializedName("code")
	val code: Int? = null,

	@field:SerializedName("message")
	val message: String? = null
)

data class SymptompCategory(

	@field:SerializedName("gejala_kategori")
	val gejalaKategori: String? = null,

	@field:SerializedName("keterangan")
	val keterangan: String? = null,

	@field:SerializedName("id_gejala_kategori")
	val idGejalaKategori: String? = null
)
