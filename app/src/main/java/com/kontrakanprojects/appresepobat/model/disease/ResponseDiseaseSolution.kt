package com.kontrakanprojects.appresepobat.model.disease

import com.google.gson.annotations.SerializedName

data class ResponseDiseaseSolution(

	@field:SerializedName("result")
	val result: List<com.kontrakanprojects.appresepobat.model.disease.DiseaseSolution>? = null,

	@field:SerializedName("code")
	val code: Int? = null,

	@field:SerializedName("message")
	val message: String? = null
)

data class DiseaseSolution(

	@field:SerializedName("id_penyakit_solusi")
	val idPenyakitSolusi: String? = null,

	@field:SerializedName("solution")
	val solution: com.kontrakanprojects.appresepobat.model.solution.Solution? = null,

	@field:SerializedName("id_penyakit")
	val idPenyakit: String? = null
)
