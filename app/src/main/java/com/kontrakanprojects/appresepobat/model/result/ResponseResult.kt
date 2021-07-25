package com.kontrakanprojects.appresepobat.model.result

import com.kontrakanprojects.appresepobat.model.disease.Disease

data class ResponseResult(
	val result: List<ResultItem>? = null,
	val code: Int? = null,
	val message: String? = null
)

data class ResultItem(
	val idKonsultasiHasil: String? = null,
	val disease: Disease? = null,
	val nilai: String? = null,
	val medicine: Medicine? = null,
	val status: String? = null
)

data class Medicine(
	val kdObat: String? = null,
	val nmObat: String? = null,
	val idObat: String? = null
)

