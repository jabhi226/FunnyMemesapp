package com.example.funnymemesapp.modules.home.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import com.example.funnymemesapp.R
import com.example.funnymemesapp.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)
        return binding?.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.apply {
            val b = Bundle()
            tvNewMemes.setOnClickListener {
                b.putString("page_type", "NEW_MEME")
                findNavController().navigate(R.id.action_homeFragment_to_memesFragment, b)
            }
            tvFevoriteMemes.setOnClickListener {
                b.putString("page_type", "SAVED_MEME")
                findNavController().navigate(R.id.action_homeFragment_to_memesFragment, b)
            }
        }
    }
}