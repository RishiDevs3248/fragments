package com.example.fragments.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.fragments.R
import com.example.fragments.databinding.FragmentSecondBinding
import com.example.fragments.viewmodel.countViewModel

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class SecondFragment : Fragment() {

    private var _binding: FragmentSecondBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!


    private val countViewModel: countViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentSecondBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonSecond.setOnClickListener {
            findNavController().navigate(R.id.action_SecondFragment_to_FirstFragment)
        }


        countViewModel.getcount().observe(viewLifecycleOwner, Observer { decvalue ->
            Log.i("Rishi","Received Value : "+decvalue)
            Toast.makeText(view.context,"received value "+decvalue , Toast.LENGTH_SHORT).show()
            binding.textviewSecond.text="Count : "+ decvalue
            // Update the list UI.
        } )

        binding.dec.setOnClickListener {
            countViewModel.decrement()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}