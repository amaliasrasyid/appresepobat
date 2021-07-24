package com.kontrakanprojects.appresepobat.view.info.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class InfoViewModel : ViewModel() {
    private var _diseases: MutableLiveData<com.kontrakanprojects.appresepobat.model.disease.ResponseDisease>? =
        null
    private var _solutions: MutableLiveData<com.kontrakanprojects.appresepobat.model.solution.ResponseSolution>? =
        null

    fun getListDisease(): LiveData<com.kontrakanprojects.appresepobat.model.disease.ResponseDisease> {
        _diseases =
            MutableLiveData<com.kontrakanprojects.appresepobat.model.disease.ResponseDisease>()
        listDisease()
        return _diseases as MutableLiveData<com.kontrakanprojects.appresepobat.model.disease.ResponseDisease>
    }

    fun getListSolution(): LiveData<com.kontrakanprojects.appresepobat.model.solution.ResponseSolution> {
        _solutions =
            MutableLiveData<com.kontrakanprojects.appresepobat.model.solution.ResponseSolution>()
        listSolution()
        return _solutions as MutableLiveData<com.kontrakanprojects.appresepobat.model.solution.ResponseSolution>
    }

    private fun listSolution() {
        val client = com.kontrakanprojects.appresepobat.network.ApiConfig.getApiService().solution()
        client.enqueue(object :
            Callback<com.kontrakanprojects.appresepobat.model.solution.ResponseSolution> {
            override fun onResponse(
                call: Call<com.kontrakanprojects.appresepobat.model.solution.ResponseSolution>,
                response: Response<com.kontrakanprojects.appresepobat.model.solution.ResponseSolution>
            ) {
                if (response.isSuccessful) {
                    _solutions?.postValue(response.body())
                } else {
                    val error = Gson().fromJson(
                        response.errorBody()?.string(),
                        com.kontrakanprojects.appresepobat.model.solution.ResponseSolution::class.java
                    )
                    _solutions?.postValue(error)
                }
            }

            override fun onFailure(
                call: Call<com.kontrakanprojects.appresepobat.model.solution.ResponseSolution>,
                t: Throwable
            ) {
                _solutions?.postValue(null)
                Log.e("Failure Response ", t.message ?: "")
            }
        })
    }

    private fun listDisease() {
        val client = com.kontrakanprojects.appresepobat.network.ApiConfig.getApiService().disease()
        client.enqueue(object :
            Callback<com.kontrakanprojects.appresepobat.model.disease.ResponseDisease> {
            override fun onResponse(
                call: Call<com.kontrakanprojects.appresepobat.model.disease.ResponseDisease>,
                response: Response<com.kontrakanprojects.appresepobat.model.disease.ResponseDisease>
            ) {
                if (response.isSuccessful) {
                    _diseases?.postValue(response.body())
                } else {
                    val error =
                        Gson().fromJson(
                            response.errorBody()?.string(),
                            com.kontrakanprojects.appresepobat.model.disease.ResponseDisease::class.java
                        )
                    _diseases?.postValue(error)
                    Log.d("error response", error.toString())
                }
            }

            override fun onFailure(
                call: Call<com.kontrakanprojects.appresepobat.model.disease.ResponseDisease>,
                t: Throwable
            ) {
                _diseases?.postValue(null)
                Log.e("Failure Response ", t.message ?: "")
            }
        })
    }
}