package com.kontrakanprojects.appresepobat.view.info.detail

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Response

class DetailViewModel : ViewModel() {
    private var _diseaseSolution: MutableLiveData<com.kontrakanprojects.appresepobat.model.disease.ResponseDiseaseSolution>? =
        null

    fun getDiseaseSolution(idDisease: String): LiveData<com.kontrakanprojects.appresepobat.model.disease.ResponseDiseaseSolution> {
        _diseaseSolution =
            MutableLiveData<com.kontrakanprojects.appresepobat.model.disease.ResponseDiseaseSolution>()
        diseaseSolution(idDisease)
        return _diseaseSolution as MutableLiveData<com.kontrakanprojects.appresepobat.model.disease.ResponseDiseaseSolution>
    }

    private fun diseaseSolution(idDisease: String) {
        val client = com.kontrakanprojects.appresepobat.network.ApiConfig.getApiService()
            .detailDisease(idDisease)
        client.enqueue(object :
            retrofit2.Callback<com.kontrakanprojects.appresepobat.model.disease.ResponseDiseaseSolution> {
            override fun onResponse(
                call: Call<com.kontrakanprojects.appresepobat.model.disease.ResponseDiseaseSolution>,
                response: Response<com.kontrakanprojects.appresepobat.model.disease.ResponseDiseaseSolution>
            ) {
                if (response.isSuccessful) {
                    _diseaseSolution?.postValue(response.body())
                } else {
                    val error = Gson().fromJson(
                        response.errorBody()?.string(),
                        com.kontrakanprojects.appresepobat.model.disease.ResponseDiseaseSolution::class.java
                    )
                    _diseaseSolution?.postValue(error)
                }
            }

            override fun onFailure(
                call: Call<com.kontrakanprojects.appresepobat.model.disease.ResponseDiseaseSolution>,
                t: Throwable
            ) {
                _diseaseSolution?.postValue(null)
                Log.e("Failure Response ", t.message ?: "")
            }
        })
    }
}