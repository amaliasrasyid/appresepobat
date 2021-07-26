package com.kontrakanprojects.appresepobat.view.result

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.kontrakanprojects.appresepobat.databinding.ResultItemBinding
import java.util.*
import kotlin.collections.ArrayList

class ResultAdapter : RecyclerView.Adapter<ResultAdapter.ResultViewHolder>() {
    private val listResult = ArrayList<com.kontrakanprojects.appresepobat.model.result.ResultItem>()
    private var diseaseResult: com.kontrakanprojects.appresepobat.model.disease.Disease? = null

    fun setData(result: List<com.kontrakanprojects.appresepobat.model.result.ResultItem>?) {
        if (result == null) return
        this.listResult.clear()
        this.listResult.addAll(result)
        notifyDataSetChanged()
    }

    fun getDiseaseDiagnosis(): com.kontrakanprojects.appresepobat.model.disease.Disease? {
        selectedDisease()
        return diseaseResult
    }

    private fun selectedDisease() {
        var selected: com.kontrakanprojects.appresepobat.model.disease.Disease? = null
        var value = 0.0
        listResult.forEach {
            val matchValue =
                String.format(Locale.ENGLISH, "%.2f", it.nilai?.toBigDecimal()).toDouble()
            if (value < matchValue) {
                value = matchValue
                selected = it.disease
            }
        }
        diseaseResult = selected
    }

    private fun convertToPercent(nilai: String?): String? {
        val roundedValue = String.format(Locale.ENGLISH, "%.2f", nilai?.toBigDecimal()).toDouble()

        val stringbuilder = StringBuilder()
        stringbuilder.append(roundedValue.toInt().toString())
        stringbuilder.append("%")

        return stringbuilder.toString()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ResultViewHolder {
        val binding = ResultItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ResultViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ResultViewHolder, position: Int) {
        holder.bind(listResult[position])
    }

    override fun getItemCount(): Int {
        return listResult.size
    }

    inner class ResultViewHolder(private val binding: ResultItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(resultItem: com.kontrakanprojects.appresepobat.model.result.ResultItem) {
            with(binding) {
//                tvResultCaseName.text = resultItem.caseMethod?.nama
                tvResultDiseaseName.text = resultItem.disease?.nmPenyakit
                tvResultMatchingCount.text = convertToPercent(resultItem.nilai)
            }
        }
    }
}