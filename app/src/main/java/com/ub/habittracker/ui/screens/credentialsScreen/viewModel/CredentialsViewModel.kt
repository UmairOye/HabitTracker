package com.ub.habittracker.ui.screens.credentialsScreen.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ub.habittracker.data.database.entity.UserEntity
import com.ub.habittracker.domain.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CredentialsViewModel @Inject constructor(private val repository: UserRepository): ViewModel() {


    fun insertUser(entity: UserEntity){
        viewModelScope.launch {
            repository.insertUser(entity)
        }
    }


    fun isAlreadyUserExist(userName: String, email: String){
        viewModelScope.launch {
            repository.isUserExist(userName, email)
        }
    }

}