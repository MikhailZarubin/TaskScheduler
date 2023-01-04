package com.mzarubin.taskscheduler.ui.main.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import com.mzarubin.taskscheduler.core.viewmodel.BaseViewModel
import com.mzarubin.taskscheduler.core.viewmodel.ProfileViewModel
import com.mzarubin.taskscheduler.databinding.FragmentProfileBinding
import com.mzarubin.taskscheduler.ui.NavigationFragment

class ProfileFragment : NavigationFragment<ProfileViewModel>() {
    private var _binding: FragmentProfileBinding? = null
    private val binding: FragmentProfileBinding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

    override fun initViewModel(): BaseViewModel {
        val viewModel = ViewModelProvider(this, viewModelFactory)[ProfileViewModel::class.java]
        viewModel.handleOnViewCreated(activity?.intent?.action)

        viewModel.avatarLiveData.observe(viewLifecycleOwner) {
            binding.userAvatar.setImageDrawable(ContextCompat.getDrawable(requireContext(), it))
        }
        viewModel.userInfoDataModel.observe(viewLifecycleOwner) {
            binding.userInfo.text = it
        }
        return viewModel
    }
}