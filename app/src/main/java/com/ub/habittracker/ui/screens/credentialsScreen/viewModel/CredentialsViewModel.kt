package com.ub.habittracker.ui.screens.credentialsScreen.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ub.habittracker.data.database.entity.UserEntity
import com.ub.habittracker.domain.models.RequestState
import com.ub.habittracker.domain.repository.UserRepository
import com.ub.habittracker.domain.utils.Utils
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CredentialsViewModel @Inject constructor(private val repository: UserRepository): ViewModel() {

    private val _userExist : MutableStateFlow<RequestState<Boolean>> = MutableStateFlow(RequestState.Idle)
    val userExist : MutableStateFlow<RequestState<Boolean>> = _userExist

    fun insertUser(entity: UserEntity){
        viewModelScope.launch {
            repository.insertUser(entity)
        }
    }


    fun isAlreadyUserExist(userName: String, email: String){
        _userExist.value = RequestState.Loading
        viewModelScope.launch {
            Utils.logCall("userTag: $userName, $email --- ${repository.isUserExists(userName, email)}")
            _userExist.value = RequestState.Success(repository.isUserExists(userName, email))
        }
    }

}