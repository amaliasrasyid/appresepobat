package com.kontrakanprojects.appresepobat.model.disease

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

data class ResponseDisease(

	@field:SerializedName("result")
	val result: List<com.kontrakanprojects.appresepobat.model.disease.Disease>? = null,

	@field:SerializedName("code")
	val code: Int? = null,

	@field:SerializedName("message")
	val message: String? = null
)

@Parcelize
data class Disease(

	@field:SerializedName("nm_penyakit")
	val nmPenyakit: String? = null,

	@field:SerializedName("definisi")
	val definisi: String? = null,

	@field:SerializedName("kd_penyakit")
	val kdPenyakit: String? = null,

	@field:SerializedName("id_penyakit")
	val idPenyakit: String? = null
) : Parcelable
