package com.kontrakanprojects.appresepobat.model.result

import com.google.gson.annotations.SerializedName

data class ResponseResult(

	@field:SerializedName("result")
	val result: List<com.kontrakanprojects.appresepobat.model.result.ResultItem>? = null,

	@field:SerializedName("code")
	val code: Int? = null,

	@field:SerializedName("message")
	val message: String? = null
)

data class JsonMemberCase(

	@field:SerializedName("disease")
	val disease: Any? = null,

	@field:SerializedName("nama")
	val nama: String? = null,

	@field:SerializedName("tanggal")
	val tanggal: String? = null,

	@field:SerializedName("id_kasus")
	val idKasus: String? = null,

	@field:SerializedName("status")
	val status: String? = null
)

data class ResultItem(

	@field:SerializedName("id_konsultasi_hasil")
	val idKonsultasiHasil: String? = null,

	@field:SerializedName("disease")
	val disease: com.kontrakanprojects.appresepobat.model.disease.Disease? = null,

	@field:SerializedName("nilai")
	val nilai: String? = null,

	@field:SerializedName("id_konsultasi")
	val idKonsultasi: Any? = null,

	@field:SerializedName("case")
	val jsonMemberCase: com.kontrakanprojects.appresepobat.model.result.JsonMemberCase? = null,

	@field:SerializedName("status")
	val status: String? = null
)
