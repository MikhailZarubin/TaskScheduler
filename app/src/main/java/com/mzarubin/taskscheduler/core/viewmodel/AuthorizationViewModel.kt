package com.mzarubin.taskscheduler.core.viewmodel

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.mzarubin.taskscheduler.R
import com.mzarubin.taskscheduler.core.repository.IAccountRepository
import com.mzarubin.taskscheduler.datamodel.ExternalNavigationDataModel
import com.mzarubin.taskscheduler.datamodel.InternalNavigationDataModel
import com.mzarubin.taskscheduler.ui.initialization.fragment.AuthorizationFragmentArgs
import com.mzarubin.taskscheduler.ui.initialization.fragment.AuthorizationFragmentDirections
import com.mzarubin.taskscheduler.ui.main.MainActivity
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
                val userId = accountRepository.authorization(password.toString())
                Log.d("Misha", userId.toString())
                if (userId != null) {
                    _externalNavigationLivaData.postValue(
                        ExternalNavigationDataModel(
                            MainActivity::class,
                            Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK,
                            userId
                        )
                    )
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