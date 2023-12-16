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
import com.example.fragments.databinding.FragmentFirstBinding
import com.example.fragments.viewmodel.countViewModel

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {

    private var _binding: FragmentFirstBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

// two ways to communicate with viewModel -> viewModel() & activityViewModels()
    private val countViewModel: countViewModel by activityViewModels() // keep data until activity dies
//    private val countViewModel: countViewModel by viewModels()// keeps data until fragment dies  --> cannot use viewModel to transfer data from one fragment to another fragment


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonFirst.setOnClickListener {
            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
        }


        countViewModel.getcount().observe(viewLifecycleOwner, Observer { incvalue ->
            Log.i("Rishi","Received Value : "+incvalue)
            Toast.makeText(view.context,"received value "+incvalue ,Toast.LENGTH_SHORT).show()
            binding.textviewFirst.text="Count : "+ incvalue
            // Update the list UI.
        } )

        binding.inc.setOnClickListener {
            countViewModel.increment()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}