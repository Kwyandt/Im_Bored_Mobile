package com.example.imbored.ui.gallery

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.fragment.app.commit
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.imbored.CameraFragment
import com.example.imbored.HomeViewModel
import com.example.imbored.R
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
            ViewModelProvider(this).get(GalleryViewModel::class.java)
        _binding = FragmentGalleryBinding.inflate(inflater, container, false)
        val root: View = binding.root

        //UI Bound Object

        val cameraView: ConstraintLayout = binding.cameraButton
        //not sure if i should use child or parent
        //val fragmentManager = parentFragmentManager
        //pressing camera button opens up the camera activtiy
        cameraView.setOnClickListener{
            //FragmentTransaction
            TODO("Implement camera activity, delete fragments")
            val cameraFragment = CameraFragment()
            //fragmentManager.beginTransaction()
            //    .replace(this, cameraFragment)
            //    .addToBackStack(null)
           //     .commit()
            //childFragmentManager.commit{
           //     replace(R.id.fragment_container_view, cameraFragment)
            //setReorderingAllowed(true)
            //    addToBackStack("GalleryFragment")
            //}
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView = binding.recyclerView
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        val adapter = LoggedActivityListAdapter()
        recyclerView.adapter = adapter
        Log.d("GALLERYFRAGMENT", "onViewCreated: IT'S CREATED ")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

//i think i no longer need this

private fun FragmentTransaction.replace(
    galleryFragment: GalleryFragment,
    cameraFragment: CameraFragment
): FragmentTransaction {
    TODO("Not yet implemented")
}
