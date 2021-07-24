package com.kontrakanprojects.appresepobat.view.result

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ResultViewModel : ViewModel() {
    private var _result: MutableLiveData<com.kontrakanprojects.appresepobat.model.result.ResponseResult>? =
        null
    private var _solutions: MutableLiveData<com.kontrakanprojects.appresepobat.model.disease.ResponseDiseaseSolution>? =
        null
    private var _consult: MutableLiveData<com.kontrakanprojects.appresepobat.model.consult.ResponseConsult>? =
        null


    fun result(idConsult: String): LiveData<com.kontrakanprojects.appresepobat.model.result.ResponseResult> {
        _result = MutableLiveData()
        getResult(idConsult)
        return _result as MutableLiveData<com.kontrakanprojects.appresepobat.model.result.ResponseResult>
    }

    fun solutions(idDisease: String): LiveData<com.kontrakanprojects.appresepobat.model.disease.ResponseDiseaseSolution> {
        _solutions = MutableLiveData()
        getSolutions(idDisease)
        return _solutions as MutableLiveData<com.kontrakanprojects.appresepobat.model.disease.ResponseDiseaseSolution>
    }


    fun resetingConsult(idConsult: String): LiveData<com.kontrakanprojects.appresepobat.model.consult.ResponseConsult> {
        _consult = MutableLiveData()
        resetConsult(idConsult)
        return _consult as MutableLiveData<com.kontrakanprojects.appresepobat.model.consult.ResponseConsult>
    }


    private fun getSolutions(idDisease: String) {
        val client = com.kontrakanprojects.appresepobat.network.ApiConfig.getApiService()
            .detailDisease(idDisease)
        client.enqueue(object :
            Callback<com.kontrakanprojects.appresepobat.model.disease.ResponseDiseaseSolution> {
            override fun onResponse(
                call: Call<com.kontrakanprojects.appresepobat.model.disease.ResponseDiseaseSolution>,
                response: Response<com.kontrakanprojects.appresepobat.model.disease.ResponseDiseaseSolution>
            ) {
                if (response.isSuccessful) {
                    _solutions?.postValue(response.body())
                } else {
                    val gson = Gson().fromJson(
                        response.errorBody()?.string(),
                        com.kontrakanprojects.appresepobat.model.disease.ResponseDiseaseSolution::class.java
                    )
                    _solutions?.postValue(gson)
                }
            }

            override fun onFailure(
                call: Call<com.kontrakanprojects.appresepobat.model.disease.ResponseDiseaseSolution>,
                t: Throwable
            ) {
                _solutions?.postValue(null)
                Log.e("Response Failure", t.message ?: "")
            }
        })
    }

    private fun getResult(idConsult: String) {
        val client = com.kontrakanprojects.appresepobat.network.ApiConfig.getApiService()
            .resultConsult(idConsult)
        client.enqueue(object :
            Callback<com.kontrakanprojects.appresepobat.model.result.ResponseResult> {
            override fun onResponse(
                call: Call<com.kontrakanprojects.appresepobat.model.result.ResponseResult>,
                response: Response<com.kontrakanprojects.appresepobat.model.result.ResponseResult>
            ) {
                if (response.isSuccessful) {
                    _result?.postValue(response.body())
                } else {
                    val gson =
                        Gson().fromJson(
                            response.errorBody()?.string(),
                            com.kontrakanprojects.appresepobat.model.result.ResponseResult::class.java
                        )
                    _result?.postValue(gson)
                }
            }

            override fun onFailure(
                call: Call<com.kontrakanprojects.appresepobat.model.result.ResponseResult>,
                t: Throwable
            ) {
                _result?.postValue(null)
                Log.e("Failure Response", t.message ?: "")
            }
        })
    }

    private fun resetConsult(idConsult: String) {
        val client = com.kontrakanprojects.appresepobat.network.ApiConfig.getApiService()
            .resetConsult(idConsult)
        client.enqueue(object :
            Callback<com.kontrakanprojects.appresepobat.model.consult.ResponseConsult> {
            override fun onResponse(
                call: Call<com.kontrakanprojects.appresepobat.model.consult.ResponseConsult>,
                response: Response<com.kontrakanprojects.appresepobat.model.consult.ResponseConsult>
            ) {
                if (response.isSuccessful) {
                    _consult?.postValue(response.body())
                } else {
                    val error = Gson().fromJson(
                        response.errorBody()?.string(),
                        com.kontrakanprojects.appresepobat.model.consult.ResponseConsult::class.java
                    )
                    _consult?.postValue(error)
                }
            }

            override fun onFailure(
                call: Call<com.kontrakanprojects.appresepobat.model.consult.ResponseConsult>,
                t: Throwable
            ) {
                _consult?.postValue(null)
                Log.e("Failure Response ", t.message ?: "")
            }

        })
    }


}