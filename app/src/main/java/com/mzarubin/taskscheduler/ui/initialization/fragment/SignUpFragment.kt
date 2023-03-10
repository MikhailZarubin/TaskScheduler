package com.mzarubin.taskscheduler.ui.initialization.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.mzarubin.taskscheduler.core.viewmodel.BaseViewModel
import com.mzarubin.taskscheduler.core.viewmodel.SignUpViewModel
import com.mzarubin.taskscheduler.databinding.FragmentSignUpBinding
import com.mzarubin.taskscheduler.ui.NavigationFragment

class SignUpFragment : NavigationFragment<SignUpViewModel>() {
    private var _binding: FragmentSignUpBinding? = null
    private val binding: FragmentSignUpBinding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)
        _binding = FragmentSignUpBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.signUpFields.layoutManager = LinearLayoutManager(requireContext())
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

    override fun initViewModel(): BaseViewModel {
        val viewModel = ViewModelProvider(this, viewModelFactory)[SignUpViewModel::class.java]
        viewModel.handleOnViewCreated()

        viewModel.adapterLiveData.observe(viewLifecycleOwner) {
            binding.signUpFields.adapter = it
        }
        viewModel.toastIdLiveData.observe(viewLifecycleOwner) {
            Toast.makeText(
                requireContext(),
                requireContext().getString(it),
                Toast.LENGTH_SHORT
            ).show()
        }

        binding.doneButton.setOnClickListener {
            viewModel.handleClickingOnDone()
        }
        return viewModel
    }
}