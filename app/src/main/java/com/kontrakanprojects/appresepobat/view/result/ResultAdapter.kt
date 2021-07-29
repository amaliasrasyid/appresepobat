package com.kontrakanprojects.appresepobat.view.result

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.kontrakanprojects.appresepobat.R
import com.kontrakanprojects.appresepobat.databinding.ResultItemBinding
import com.kontrakanprojects.appresepobat.model.disease.Disease
import com.kontrakanprojects.appresepobat.model.result.ResultItem
import com.kontrakanprojects.appresepobat.network.ApiConfig
import java.util.*
import kotlin.collections.ArrayList

class ResultAdapter : RecyclerView.Adapter<ResultAdapter.ResultViewHolder>() {
    private val listResult = ArrayList<ResultItem>()
    private var diseaseResult: Disease? = null

    fun setData(result: List<ResultItem>?) {
        if (result == null) return
        this.listResult.clear()
        this.listResult.addAll(result)
        notifyDataSetChanged()
    }

    fun getDiseaseDiagnosis(): Disease? {
        selectedDisease()
        return diseaseResult
    }

    private fun selectedDisease() {
        var selected: Disease? = null
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
        fun bind(resultItem: ResultItem) {
            with(binding) {
                tvResultMedicine.text = resultItem.medicine?.nmObat
                tvResultDiseaseName.text = resultItem.disease?.nmPenyakit
                tvResultMatchingCount.text = convertToPercent(resultItem.nilai)

                Glide.with(itemView.context)
                    .load(ApiConfig.ENDPOINT_IMAGES + resultItem.medicine?.gambar)
                    .placeholder(R.drawable.img_not_found)
                    .error(R.drawable.img_not_found)
                    .into(imgResultMedicine)
            }
        }
    }
}