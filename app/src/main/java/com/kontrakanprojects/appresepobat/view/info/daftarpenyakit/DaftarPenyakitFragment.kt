package com.kontrakanprojects.appresepobat.view.info.daftarpenyakit

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.kontrakanprojects.appresepobat.R
import com.kontrakanprojects.appresepobat.databinding.FragmentDaftarPenyakitBinding
import com.kontrakanprojects.appresepobat.utils.EXTRA_OBJECT_TYPE
import com.kontrakanprojects.appresepobat.utils.dataNotFound
import com.kontrakanprojects.appresepobat.utils.isLoading
import com.kontrakanprojects.appresepobat.utils.showMessage
import com.kontrakanprojects.appresepobat.view.info.detail.DetailActivity
import com.kontrakanprojects.appresepobat.view.info.viewmodel.InfoViewModel
import www.sanju.motiontoast.MotionToast

class DaftarPenyakitFragment : Fragment() {

    private var _binding: FragmentDaftarPenyakitBinding? = null
    private val binding get() = _binding!!
    private val viewmodel: InfoViewModel by activityViewModels()
    private lateinit var diseasesAdapter: DaftarPenyakitAdapter

    companion object {
        fun newInstance(): DaftarPenyakitFragment {
            return DaftarPenyakitFragment()
        }

        const val EXTRA_OBJECT_DISEASE = "EXTRA_OBJECT_DISEASE"
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentDaftarPenyakitBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {
            diseasesAdapter = DaftarPenyakitAdapter()
            with(rvDaftarPenyakitList) {
                layoutManager = LinearLayoutManager(requireContext())
                setHasFixedSize(true)
                this.adapter = diseasesAdapter
            }

            observeDisease()
            diseasesAdapter.setOnItemClickCallBack(object :
                DaftarPenyakitAdapter.OnItemClickCallBack {
                override fun onItemClicked(disease: com.kontrakanprojects.appresepobat.model.disease.Disease) {
                    // send to db parcel
                    val dataDisease = com.kontrakanprojects.appresepobat.model.disease.Disease(
                        disease.nmPenyakit,
                        disease.definisi,
                        disease.kdPenyakit,
                        disease.idPenyakit,
                    )
                    val intent = Intent(requireActivity(), DetailActivity::class.java)
                    intent.putExtra(EXTRA_OBJECT_TYPE, "disease")
                    intent.putExtra(EXTRA_OBJECT_DISEASE, dataDisease)
                    startActivity(intent)
                }
            })
        }

    }

    private fun observeDisease() {
        with(binding) {
            viewmodel.getListDisease().observe(viewLifecycleOwner, { response ->
                isLoading(false, progressBar)
                if (response != null) {
                    if (response.code == 200) {
                        val result = response.result
                        diseasesAdapter.setData(result)
                    } else {
                        dataNotFound(
                            penyakitEmptyView.imgEmptyData,
                            penyakitEmptyView.tvEmptyMessage,
                            response.message
                        )
                        showMessage(
                            requireActivity(),
                            getString(R.string.message_title_failed),
                            response.message.toString(),
                            style = MotionToast.TOAST_ERROR
                        )
                    }
                } else {
                    dataNotFound(penyakitEmptyView.imgEmptyData, penyakitEmptyView.tvEmptyMessage)
                    showMessage(
                        requireActivity(),
                        getString(R.string.message_title_failed),
                        style = MotionToast.TOAST_ERROR
                    )
                }
            })
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}