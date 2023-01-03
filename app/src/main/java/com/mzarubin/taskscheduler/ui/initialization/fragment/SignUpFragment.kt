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
import com.mzarubin.taskscheduler.ui.initialization.adapter.SignUpAdapter

class SignUpFragment : NavigationFragment<SignUpViewModel>() {
    private var _binding: FragmentSignUpBinding? = null
    private val binding: FragmentSignUpBinding get() = _binding!!

    private var _adapter: SignUpAdapter? = null
    private val adapter: SignUpAdapter get() = _adapter!!

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

        _adapter = SignUpAdapter()
        binding.signUpFields.layoutManager = LinearLayoutManager(requireContext())
        binding.signUpFields.adapter = adapter
    }

    override fun onDestroyView() {
        _binding = null
        _adapter = null
        super.onDestroyView()
    }

    override fun initViewModel(): BaseViewModel {
        val viewModel = ViewModelProvider(this, viewModelFactory)[SignUpViewModel::class.java]

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