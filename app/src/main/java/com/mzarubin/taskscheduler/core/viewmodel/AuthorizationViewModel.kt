package com.mzarubin.taskscheduler.core.viewmodel

import android.os.Bundle
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.mzarubin.taskscheduler.R
import com.mzarubin.taskscheduler.core.repository.IAccountRepository
import com.mzarubin.taskscheduler.datamodel.InternalNavigationDataModel
import com.mzarubin.taskscheduler.ui.initialization.fragment.AuthorizationFragmentArgs
import com.mzarubin.taskscheduler.ui.initialization.fragment.AuthorizationFragmentDirections
import kotlinx.coroutines.launch
import javax.inject.Inject

class AuthorizationViewModel @Inject constructor(
    private val accountRepository: IAccountRepository
) : BaseViewModel() {
    private val _toastIdLiveData: MutableLiveData<Int> = MutableLiveData()
    val toastIdLiveData: LiveData<Int> = _toastIdLiveData

    fun handleOnViewCreated(args: Bundle) {
        viewModelScope.launch {
            if (AuthorizationFragmentArgs.fromBundle(args).isFirstLaunch) {
                _internalNavigationLiveData.postValue(
                    InternalNavigationDataModel(AuthorizationFragmentDirections.actionAuthorizationFragmentToWelcomeDialog())
                )
            }
        }
    }

    fun handleClickingOnSignIn(login: CharSequence, password: CharSequence) {
        viewModelScope.launch {
            if (!accountRepository.isAccountExist(login.toString())) {
                _toastIdLiveData.postValue(R.string.account_not_found)
            } else {
                //TODO: Need to add password encryption
                if (accountRepository.authorization(password.toString()) != null) {
                    //TODO: open Main Activity
                } else {
                    _toastIdLiveData.postValue(R.string.incorrect_account_info)
                }
            }
        }
    }

    fun handleClickingOnSignUp() {
        _internalNavigationLiveData.postValue(
            InternalNavigationDataModel(
                AuthorizationFragmentDirections.actionAuthorizationFragmentToSignUpFragment()
            )
        )
    }
}