package com.mzarubin.taskscheduler.core.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.mzarubin.taskscheduler.R
import com.mzarubin.taskscheduler.core.repository.IUserDataRepository
import com.mzarubin.taskscheduler.datamodel.UserInfoDataModel
import kotlinx.coroutines.launch
import javax.inject.Inject

class ProfileViewModel @Inject constructor(
    private val userDataRepository: IUserDataRepository
) : BaseViewModel() {
    private val _avatarLiveData: MutableLiveData<Int> = MutableLiveData()
    val avatarLiveData: LiveData<Int> = _avatarLiveData

    private val _userInfoLiveData: MutableLiveData<String> = MutableLiveData()
    val userInfoDataModel: LiveData<String> = _userInfoLiveData

    fun handleOnViewCreated(action: String?) {
        viewModelScope.launch {
            action?.let {
                val userInfo = userDataRepository.getUser(action)
                _avatarLiveData.postValue(R.drawable.default_avatar)
                _userInfoLiveData.postValue("${userInfo.surname} ${userInfo.name} ")
            }
        }
    }
}