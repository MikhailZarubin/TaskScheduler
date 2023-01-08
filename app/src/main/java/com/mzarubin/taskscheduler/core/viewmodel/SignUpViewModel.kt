package com.mzarubin.taskscheduler.core.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.mzarubin.taskscheduler.R
import com.mzarubin.taskscheduler.core.repository.IAccountRepository
import com.mzarubin.taskscheduler.datamodel.InternalNavigationDataModel
import com.mzarubin.taskscheduler.datamodel.SignUpFieldDataModel
import com.mzarubin.taskscheduler.datamodel.SignUpFields
import com.mzarubin.taskscheduler.ui.initialization.adapter.SignUpAdapter
import com.mzarubin.taskscheduler.ui.initialization.fragment.SignUpFragmentDirections
import kotlinx.coroutines.launch
import javax.inject.Inject

class SignUpViewModel @Inject constructor(
    private val accountRepository: IAccountRepository
) : BaseViewModel() {
    private val _adapterLiveData: MutableLiveData<SignUpAdapter> = MutableLiveData()
    val adapterLiveData: LiveData<SignUpAdapter> = _adapterLiveData

    private val _toastIdLiveData: MutableLiveData<Int> = MutableLiveData()
    val toastIdLiveData: LiveData<Int> = _toastIdLiveData

    private lateinit var adapter: SignUpAdapter
    private var errorCode: Int? = null
        set(value) {
            if (field == null) {
                field = value
            }
        }

    fun handleOnViewCreated() {
        adapter = SignUpAdapter(SignUpFields.values().map {
            SignUpFieldDataModel(it)
        })
        _adapterLiveData.postValue(adapter)
    }

    fun handleClickingOnDone() {
        viewModelScope.launch {
            val signUpFieldsData: Map<SignUpFields, String> = adapter.getSignUpFields().associate {
                if (it.data == null) {
                    errorCode = NULL_FIELD_ERR
                }
                it.info to (it.data ?: "")
            }

            if (accountRepository.isAccountExist(signUpFieldsData.getValue(SignUpFields.LOGIN))) {
                errorCode = ACCOUNT_EXIST_ERR
            }
            if (signUpFieldsData[SignUpFields.PASSWORD] != signUpFieldsData[SignUpFields.PASSWORD_REPEAT]) {
                errorCode = PASSWORD_NOT_MATCH_ERR
            }

            errorCode?.let {
                when (it) {
                    NULL_FIELD_ERR -> _toastIdLiveData.postValue(R.string.fields_not_filled)
                    ACCOUNT_EXIST_ERR -> _toastIdLiveData.postValue(R.string.account_exist)
                    PASSWORD_NOT_MATCH_ERR -> _toastIdLiveData.postValue(R.string.password_no_match)
                    else -> {}
                }
                return@launch
            }

            accountRepository.createAccount(
                signUpFieldsData.getValue(SignUpFields.LOGIN),
                signUpFieldsData.getValue(SignUpFields.PASSWORD)
            )
            _internalNavigationLiveData.postValue(
                InternalNavigationDataModel(
                    SignUpFragmentDirections.actionSignUpFragmentBackToAuthorizationFragment()
                )
            )
        }
    }

    private companion object {
        const val NULL_FIELD_ERR = 0
        const val ACCOUNT_EXIST_ERR = 1
        const val PASSWORD_NOT_MATCH_ERR = 2
    }
}