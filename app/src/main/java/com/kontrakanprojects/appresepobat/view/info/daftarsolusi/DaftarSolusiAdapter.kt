package com.kontrakanprojects.appresepobat.view.info.daftarsolusi

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.kontrakanprojects.appresepobat.databinding.InfoItemBinding

class DaftarSolusiAdapter : RecyclerView.Adapter<DaftarSolusiAdapter.DaftarSolusiViewHolder>() {
    private val listSolution =
        ArrayList<com.kontrakanprojects.appresepobat.model.solution.Solution>()
    private var onItemClickCallBack: OnItemClickCallBack? = null

    fun setData(listSolution: List<com.kontrakanprojects.appresepobat.model.solution.Solution>?) {
        if (listSolution == null) return
        this.listSolution.clear()
        this.listSolution.addAll(listSolution)
        notifyDataSetChanged()
    }

    fun setOnItemClickCallBack(onItemClickCallBack: OnItemClickCallBack) {
        this.onItemClickCallBack = onItemClickCallBack
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DaftarSolusiViewHolder {
        val binding = InfoItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return DaftarSolusiViewHolder(binding)
    }

    override fun onBindViewHolder(holder: DaftarSolusiViewHolder, position: Int) {
        holder.bind(listSolution.get(position))
    }

    override fun getItemCount(): Int {
        return listSolution.size
    }

    inner class DaftarSolusiViewHolder(private val binding: InfoItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(solution: com.kontrakanprojects.appresepobat.model.solution.Solution) {
            with(binding) {
                tvInfoKode.text = solution.kdSolusi
                tvInfoName.text = solution.nmSolusi

                clInfoItem.setOnClickListener { onItemClickCallBack?.onItemClicked(solution) }
            }
        }
    }

    interface OnItemClickCallBack {
        fun onItemClicked(solution: com.kontrakanprojects.appresepobat.model.solution.Solution)
    }
}