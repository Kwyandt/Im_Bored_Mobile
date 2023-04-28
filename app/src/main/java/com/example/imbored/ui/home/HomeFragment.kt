package com.example.imbored.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.imbored.databinding.FragmentHomeBinding

private const val COMPLETION_PROMPT: String = "Generate a random activity that I could do when I'm bored. Be brief, very creative. End the response with an exclamation point!"
class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

//        UI bound objects
        val generatedTextView: TextView = binding.generatedIdeaTextView
        val imageView: ImageView = binding.pandaImageView

        imageView.setOnClickListener{
            homeViewModel.getCompletion(COMPLETION_PROMPT)
        }

//        // TODO replace with a button "OnClick" functionality
//        // Generate initial idea
//        homeViewModel.getCompletion(COMPLETION_PROMPT)

        homeViewModel.answerString.observe(viewLifecycleOwner) {
            generatedTextView.text = it
        }


        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
