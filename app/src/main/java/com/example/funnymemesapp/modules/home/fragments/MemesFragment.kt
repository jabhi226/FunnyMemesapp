package com.example.funnymemesapp.modules.home.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.paging.PagingData
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import com.example.funnymemesapp.R
import com.example.funnymemesapp.databinding.FragmentMemesBinding
import com.example.funnymemesapp.modules.home.adapters.MemesAdapter
import com.example.funnymemesapp.modules.home.helper.SpacePagerSnapHelper
import com.example.funnymemesapp.modules.home.viewmodel.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MemesFragment : Fragment() {

    private var _binding: FragmentMemesBinding? = null
    private val binding get() = _binding!!
    private lateinit var adapter: MemesAdapter

    private val viewModel by viewModels<HomeViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = DataBindingUtil.inflate(inflater, R.layout.fragment_memes, container, false)
        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()
        observeData()
        if (arguments?.getString("page_type") == "NEW_MEME") {
            viewModel.getDataFromApi(true)
        } else {
            viewModel.getDataFromApi(false)
        }
    }

    private fun observeData() {
        CoroutineScope(Dispatchers.Main).launch {
            viewModel.currentMemeList?.collectLatest {
                adapter.submitData(it)
            }
            viewModel.uiEvent.collectLatest {
                when (it) {
                    is HomeViewModel.HomeUiEvents.OnCommonError -> {

                    }

                    is HomeViewModel.HomeUiEvents.OnGetMedicineSuccess -> {

                    }

                    is HomeViewModel.HomeUiEvents.OnMemeSaved -> {
                        Toast.makeText(requireContext(), it.res.toString(), Toast.LENGTH_SHORT)
                            .show()
                    }
                }
            }
        }
    }

    private fun initRecyclerView() {
        adapter = MemesAdapter({
            activity?.startActivity(it)
        }) {
            viewModel.saveMeme(it, this@MemesFragment.adapter.snapshot().items)
        }
        binding.recyclerView.apply {
            this.layoutManager =
                LinearLayoutManager(requireActivity(), LinearLayoutManager.VERTICAL, false)
            this.adapter = this@MemesFragment.adapter
            val pagerSnapHelper = PagerSnapHelper()
            val spacePagerSnapHelper = SpacePagerSnapHelper(pagerSnapHelper)
            pagerSnapHelper.attachToRecyclerView(this)
            spacePagerSnapHelper.attachToRecyclerView(this)
        }
    }

}