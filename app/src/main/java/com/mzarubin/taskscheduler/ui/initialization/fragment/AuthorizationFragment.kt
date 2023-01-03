package com.mzarubin.taskscheduler.ui.initialization.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.mzarubin.taskscheduler.core.viewmodel.AuthorizationViewModel
import com.mzarubin.taskscheduler.core.viewmodel.BaseViewModel
import com.mzarubin.taskscheduler.databinding.FragmentAuthorizationBinding
import com.mzarubin.taskscheduler.ui.NavigationFragment

class AuthorizationFragment : NavigationFragment<AuthorizationViewModel>() {
    private var _binding: FragmentAuthorizationBinding? = null
    private val binding: FragmentAuthorizationBinding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)
        _binding = FragmentAuthorizationBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun initViewModel(): BaseViewModel {
        val viewModel =
            ViewModelProvider(this, viewModelFactory)[AuthorizationViewModel::class.java]
        viewModel.handleOnViewCreated(requireArguments())

        viewModel.toastIdLiveData.observe(viewLifecycleOwner) {
            Toast.makeText(
                requireContext(),
                requireContext().getString(it),
                Toast.LENGTH_SHORT
            ).show()
        }

        binding.signInButton.setOnClickListener {
            viewModel.handleClickingOnSignIn(binding.loginField.text, binding.passwordField.text)
        }
        binding.signUpButton.setOnClickListener {
            viewModel.handleClickingOnSignUp()
        }
        return viewModel
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}