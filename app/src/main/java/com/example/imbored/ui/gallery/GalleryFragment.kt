package com.example.imbored.ui.gallery

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.imbored.CameraActivity
import com.example.imbored.CameraFragment
import com.example.imbored.GalleryViewModel
import com.example.imbored.databinding.FragmentGalleryBinding


class GalleryFragment : Fragment() {
    private var _binding: FragmentGalleryBinding? = null
    private val binding get() = _binding!!

    private lateinit var recyclerView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val galleryViewModel =
            ViewModelProvider(this)[GalleryViewModel::class.java]

        _binding = FragmentGalleryBinding.inflate(inflater, container, false)
        recyclerView = binding.recyclerView

        // TODO: Using binding instead, come back if needed
        //        recyclerView = binding.recyclerView
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        val adapter = GalleryImageListAdapter()
        recyclerView.adapter = adapter

//        val view: View = inflater.inflate(R.layout.fragment_gallery,container, false)
//        recyclerView = view.findViewById(R.id.recycler_view)
        recyclerView.setHasFixedSize(true)
//        recyclerView.layoutManager = LinearLayoutManager(view.context)

        //UI Bound Object


        //not sure if i should use child or parent
        //val fragmentManager = parentFragmentManager
        //pressing camera button opens up the camera activtiy

        val imageButton: ImageButton = binding.imageButton
        imageButton.setOnClickListener{
            val intent =  Intent(context, CameraActivity::class.java)
            startActivity(intent)
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // TODO: Moving to onCreate
////        recyclerView = binding.recyclerView
//        recyclerView.layoutManager = LinearLayoutManager(requireContext())
//        val adapter = LoggedActivityListAdapter()
//        recyclerView.adapter = adapter
        Log.d("GALLERYFRAGMENT", "onViewCreated: IT'S CREATED ")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
