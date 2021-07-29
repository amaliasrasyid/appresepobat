package com.kontrakanprojects.appresepobat.model.result

import com.google.gson.annotations.SerializedName
import com.kontrakanprojects.appresepobat.model.disease.Disease

data class ResponseResult(

	@field:SerializedName("result")
	val result: List<ResultItem>? = null,

	@field:SerializedName("code")
	val code: Int? = null,

	@field:SerializedName("message")
	val message: String? = null
)

data class ResultItem(

	@field:SerializedName("id_konsultasi_hasil")
	val idKonsultasiHasil: String? = null,

	@field:SerializedName("disease")
	val disease: Disease? = null,

	@field:SerializedName("nilai")
	val nilai: String? = null,

	@field:SerializedName("medicine")
	val medicine: Medicine? = null,

	@field:SerializedName("status")
	val status: String? = null
)

data class Medicine(

	@field:SerializedName("kd_obat")
	val kdObat: String? = null,

	@field:SerializedName("nm_obat")
	val nmObat: String? = null,

	@field:SerializedName("id_obat")
	val idObat: String? = null,

	@field:SerializedName("gambar")
	val gambar: String? = null
)

