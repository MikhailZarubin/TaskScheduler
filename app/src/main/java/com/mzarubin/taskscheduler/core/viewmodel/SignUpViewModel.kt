package com.mzarubin.taskscheduler.core.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.mzarubin.taskscheduler.R
import com.mzarubin.taskscheduler.core.repository.IAccountRepository
import com.mzarubin.taskscheduler.datamodel.InternalNavigationDataModel
import com.mzarubin.taskscheduler.datamodel.SignUpFields
import com.mzarubin.taskscheduler.ui.initialization.fragment.SignUpFragmentDirections
import kotlinx.coroutines.launch
import javax.inject.Inject

class SignUpViewModel @Inject constructor(
    private val accountRepository: IAccountRepository
) : BaseViewModel() {
    private val _toastIdLiveData: MutableLiveData<Int> = MutableLiveData()
    val toastIdLiveData: LiveData<Int> = _toastIdLiveData

    fun handleClickingOnDone() {
        viewModelScope.launch {
            var isAccountExist = true
            var isPasswordMatch = false
            var isFieldHandled = false

            SignUpFields.values().forEach {
                isFieldHandled = false

                it.input?.isEmpty() ?: return@forEach
                when (it) {
                    SignUpFields.LOGIN -> {
                        it.input?.let { input ->
                            isAccountExist = accountRepository.isAccountExist(input)
                        }
                    }
                    SignUpFields.PASSWORD_REPEAT -> {
                        isPasswordMatch = SignUpFields.PASSWORD.input == it.input
                    }
                    else -> {}
                }

                isFieldHandled = true
            }

            if (isFieldHandled && isPasswordMatch && !isAccountExist) {
                val login = SignUpFields.LOGIN.input ?: ""
                val password = SignUpFields.PASSWORD.input ?: ""
                accountRepository.createAccount(
                    login,
                    password
                )
                _internalNavigationLiveData.postValue(
                    InternalNavigationDataModel(
                        SignUpFragmentDirections.actionSignUpFragmentBackToAuthorizationFragment()
                    )
                )
            } else {
                _toastIdLiveData.postValue(
                    if (!isFieldHandled) {
                        R.string.fields_not_filled
                    } else {
                        if (isAccountExist) {
                            R.string.account_exist
                        } else {
                            R.string.password_no_match
                        }
                    }
                )
            }
        }
    }
}