package com.example.imbored.ui.gallery

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.coroutineScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.imbored.ActivitiesApplication
import com.example.imbored.CameraActivity
import com.example.imbored.GalleryViewModel
import com.example.imbored.databinding.FragmentGalleryBinding
import kotlinx.coroutines.launch


class GalleryFragment : Fragment() {

    companion object {
        var WELCOME_MESSAGE = "HI THERE (Gallery Fragment)"
    }
    private var _binding: FragmentGalleryBinding? = null
    private val binding get() = _binding!!

    private lateinit var recyclerView: RecyclerView
//    //TODO: remove hard coded data
//    // hardcoding data for now
//    val myDataset = listOf<GalleryImage>(
//        GalleryImage("Date 1", "Name 1", "Path 1")
//    )
    private val viewModel: GalleryViewModel by activityViewModels {
        GalleryViewModel.GalleryViewModelFactory(
            (activity?.application as ActivitiesApplication).database.galleryImageDao()
        )
}
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        /**
         * TODO: Create a welcome message?
         */
        arguments?.let {
             var WELCOME_MESSAGE= it.getString(WELCOME_MESSAGE).toString()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentGalleryBinding.inflate(inflater, container, false)

        val imageButton: ImageButton = binding.imageButton
        imageButton.setOnClickListener{
            val intent =  Intent(context, CameraActivity::class.java)
            startActivity(intent)
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //this calls dataset, needs to be changed to load the database
        val myDataset = GallerySource().loadImages()
        recyclerView = binding.recyclerView
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        //val adapter = GalleryImageListAdapter(context, myDataset)
        recyclerView.adapter = context?.let { GalleryImageListAdapter(it, myDataset) }

        recyclerView.setHasFixedSize(true)
        // Submit all of the GalleryImages to the RecyclerView
        /*
        lifecycle.coroutineScope.launch{
            viewModel.allGalleryImages().collect(){
                adapter.submitList(it)
            }
        }
        */

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
