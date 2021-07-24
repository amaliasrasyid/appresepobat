package com.kontrakanprojects.appresepobat.model.solution

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

data class ResponseSolution(

	@field:SerializedName("result")
	val result: List<com.kontrakanprojects.appresepobat.model.solution.Solution>? = null,

	@field:SerializedName("code")
	val code: Int? = null,

	@field:SerializedName("message")
	val message: String? = null
)

@Parcelize
data class Solution(

	@field:SerializedName("keterangan")
	val keterangan: String? = null,

	@field:SerializedName("id_solusi")
	val idSolusi: String? = null,

	@field:SerializedName("kd_solusi")
	val kdSolusi: String? = null,

	@field:SerializedName("nm_solusi")
	val nmSolusi: String? = null
) : Parcelable
