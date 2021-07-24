package com.kontrakanprojects.appresepobat.view.consult.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ConsultViewModel : ViewModel() {

    private var _consult: MutableLiveData<com.kontrakanprojects.appresepobat.model.consult.ResponseConsult>? =
        null

    fun consult(name: String): LiveData<com.kontrakanprojects.appresepobat.model.consult.ResponseConsult> {
        _consult = MutableLiveData()
        addConsult(name)
        return _consult as MutableLiveData<com.kontrakanprojects.appresepobat.model.consult.ResponseConsult>
    }

    private fun addConsult(name: String) {
        val client =
            com.kontrakanprojects.appresepobat.network.ApiConfig.getApiService().addConsult(name)
        client.enqueue(object :
            Callback<com.kontrakanprojects.appresepobat.model.consult.ResponseConsult> {
            override fun onResponse(
                call: Call<com.kontrakanprojects.appresepobat.model.consult.ResponseConsult>,
                response: Response<com.kontrakanprojects.appresepobat.model.consult.ResponseConsult>
            ) {
                if (response.isSuccessful) {
                    _consult?.postValue(response.body())
                } else {
                    val error =
                        Gson().fromJson(
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