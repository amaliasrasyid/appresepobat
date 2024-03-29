package com.kontrakanprojects.appresepobat.view.result

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.kontrakanprojects.appresepobat.R
import com.kontrakanprojects.appresepobat.databinding.FragmentResultBinding
import com.kontrakanprojects.appresepobat.utils.isLoading
import com.kontrakanprojects.appresepobat.utils.showMessage
import www.sanju.motiontoast.MotionToast

class ResultFragment : Fragment() {

    private var _binding: FragmentResultBinding? = null
    private val binding get() = _binding!!
    private val viewModel by activityViewModels<ResultViewModel>()
    private lateinit var resultAdapter: ResultAdapter
    private lateinit var dataIdConsult: String
    private lateinit var dataSelectedIds: Array<String>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentResultBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
    }

    private fun initViews() {
        with(binding) {
            buttonResultChange.setOnClickListener { backToConsultation() }
            buttonResultFinish.setOnClickListener { finishConsultation() }
            resultAdapter = ResultAdapter()
            with(rvResultCase) {
                layoutManager =
                    LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
                setHasFixedSize(true)
                this.adapter = resultAdapter
            }
        }
        //data from navigation
        dataIdConsult = ResultFragmentArgs.fromBundle(arguments as Bundle).idConsultation
        dataSelectedIds = ResultFragmentArgs.fromBundle(arguments as Bundle).listSelectedIds

        observeResult(dataIdConsult, dataSelectedIds)
    }

    private fun backToConsultation() {
        observeResetConsult(dataIdConsult)
        findNavController().navigateUp()
    }

    private fun finishConsultation() {
        findNavController().navigate(R.id.action_resultFragment_to_homeFragment)
    }

    private fun observeResult(idConsult: String, listSelectedIds: Array<String>) {
        viewModel.result(idConsult, listSelectedIds).observe(viewLifecycleOwner, {
            isLoading(false, binding.progressBarResult)
            if (it != null) {
                if (it.code == 200) {
                    resultAdapter.setData(it.result)
                    binding.tvResultDescription.text =
                        convertToSentece(resultAdapter.getDiseaseDiagnosis())
                    observeSolution(resultAdapter.getDiseaseDiagnosis()?.idPenyakit.toString())
                } else {
                    showMessage(
                        requireActivity(),
                        getString(R.string.message_title_failed),
                        it.message ?: "",
                        style = MotionToast.TOAST_ERROR
                    )
                }
            } else {
                showMessage(
                    requireActivity(),
                    getString(R.string.message_title_failed),
                    style = MotionToast.TOAST_ERROR
                )
            }
        })
    }

    private fun observeSolution(idDisease: String) {
        viewModel.solutions(idDisease).observe(viewLifecycleOwner, {
            isLoading(false, binding.progressBarResult)
            if (it != null) {
                if (it.code == 200) {
                    loadListViewSolution(it.result)
                } else {
                    showMessage(
                        requireActivity(),
                        getString(R.string.message_title_failed),
                        it.message ?: "",
                        style = MotionToast.TOAST_ERROR
                    )
                }
            } else {
                showMessage(
                    requireActivity(),
                    getString(R.string.message_title_failed),
                    style = MotionToast.TOAST_ERROR
                )
            }
        })
    }

    private fun observeResetConsult(idConsult: String) {
        viewModel.resetingConsult(idConsult).observe(viewLifecycleOwner, {
            if (it != null) {
                if (it.code == 200) {
                    showMessage(
                        requireActivity(),
                        getString(R.string.message_title_succes),
                        it.message,
                        style = MotionToast.TOAST_SUCCESS
                    )
                } else {
                    showMessage(
                        requireActivity(),
                        getString(R.string.message_title_failed),
                        it.message,
                        style = MotionToast.TOAST_ERROR
                    )
                }
            } else {
                showMessage(
                    requireActivity(),
                    getString(R.string.message_title_failed),
                    style = MotionToast.TOAST_ERROR
                )
            }
        })
    }

    private fun loadListViewSolution(result: List<com.kontrakanprojects.appresepobat.model.disease.DiseaseSolution>?) {
        if (result != null) {
            val arraySolutions = parseToListString(result)
            val listAdapter = ArrayAdapter(
                requireActivity(),
                android.R.layout.simple_list_item_1,
                android.R.id.text1,
                arraySolutions
            )
            binding.listviewDetail.adapter = listAdapter
        }
    }

    private fun parseToListString(result: List<com.kontrakanprojects.appresepobat.model.disease.DiseaseSolution>): List<String> {
        val list: MutableList<String> = mutableListOf()
        result.forEach { item ->
            val stringBuilder = StringBuilder()
            stringBuilder.append("[")
            stringBuilder.append(item.solution?.kdSolusi)
            stringBuilder.append("]")
            stringBuilder.append("\t")
            stringBuilder.append(item.solution?.nmSolusi)
            list.add(stringBuilder.toString())
        }
        return list
    }

    private fun convertToSentece(disease: com.kontrakanprojects.appresepobat.model.disease.Disease?): String {
        var sentece = ""
        if (disease != null) {
            val stringBuilder = StringBuilder()
            stringBuilder.append("Hasil Diagnosa Adalah ")
            stringBuilder.append(disease.nmPenyakit)
            stringBuilder.append(".\t")
            stringBuilder.append(disease.definisi)
            sentece = stringBuilder.toString()
        }
        return sentece
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}