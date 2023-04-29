package com.example.imbored.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.imbored.HomeViewModel
import com.example.imbored.databinding.FragmentHomeBinding

private const val INITIAL_PROMPT: String = "The following is a conversation with an AI assistant. " +
        "The assistant is helpful, creative, clever, very friendly, but never uses more than 10 words to respond." +
        " The assistant should generate unique, brief ideas of activities that the human could do when they are bored. " +
        "\n\nHuman: Hello, I'm bored. What should I do today?\nGo on a bike ride!\nHuman: "

class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private val chatHistory: String = INITIAL_PROMPT

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
            homeViewModel.getCompletion(chatHistory+"Give me another activity, but don't repeat: ")
        }
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
