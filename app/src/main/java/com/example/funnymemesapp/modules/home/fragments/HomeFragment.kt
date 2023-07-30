package com.example.funnymemesapp.modules.home.fragments

import android.R.attr.data
import android.app.Activity
import android.content.Intent
import android.database.Cursor
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.funnymemesapp.R
import com.example.funnymemesapp.databinding.FragmentHomeBinding
import com.example.funnymemesapp.db.memedb.entity.ImageSaver
import com.example.funnymemesapp.modules.home.viewmodel.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


@AndroidEntryPoint
class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding

    private val viewModel by viewModels<HomeViewModel>()

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

            btnSaveImageInDb.setOnClickListener {
                val getIntent = Intent(Intent.ACTION_OPEN_DOCUMENT)
                getIntent.type = "image/*"

//                val pickIntent =
//                    Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
//                pickIntent.type = "image/*"

                val chooserIntent = Intent.createChooser(getIntent, "Select Image")
//                chooserIntent.putExtra(Intent.EXTRA_INITIAL_INTENTS, arrayOf(pickIntent))

                launcherActivity.launch(chooserIntent)
            }
            println("--> ${Thread.currentThread().name}")
            observeData()
        }
    }

    private fun observeData() {
        viewModel.image.observe(viewLifecycleOwner) { imageSaver ->
            imageSaver?.let { it ->
                try {
                    val uri = Uri.parse(it.imageUriString)
                    println("--> " + it.imageUriString)
                    binding?.iv?.setImageURI(uri)
                    CoroutineScope(Dispatchers.Main).launch {
                        delay(2000)
                        println("--> ${Thread.currentThread().name}")
                        binding?.iv?.setImageBitmap(it.image)
                    }
                } catch (e: java.lang.Exception) {
                    e.printStackTrace()
                }
            }
        }
    }

    private val launcherActivity: ActivityResultLauncher<Intent> =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                result.data?.data?.let { it ->
                    try {
                        val takeFlags: Int? = result.data?.flags?.let {
                            (it.and(
                                (Intent.FLAG_GRANT_READ_URI_PERMISSION
                                        or Intent.FLAG_GRANT_WRITE_URI_PERMISSION)
                            ))
                        }
                        takeFlags?.let { it1 ->
                            requireContext().contentResolver.takePersistableUriPermission(it, it1)
                        }
                        println("--> $it")
                        val bitmap =
                            MediaStore.Images.Media.getBitmap(requireActivity().contentResolver, it)
                        binding?.iv?.setImageBitmap(bitmap)
                        viewModel.saveImage(ImageSaver(null, bitmap, it.toString()))
                    } catch (exception: Exception) {
                        exception.printStackTrace()
                    }
                }
            }
        }
}