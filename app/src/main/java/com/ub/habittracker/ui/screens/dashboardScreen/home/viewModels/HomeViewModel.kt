package com.ub.habittracker.ui.screens.dashboardScreen.home.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ub.habittracker.domain.models.DateAndDayModel
import com.ub.habittracker.domain.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val userRepository: UserRepository): ViewModel() {
    private val _userName: MutableStateFlow<String?> = MutableStateFlow("")
    val userName = _userName.asStateFlow()

    fun makeDateAndDayList(): List<DateAndDayModel>{
        return userRepository.makeDateAndDayList()
    }


    fun getUserName(email: String){
        viewModelScope.launch {
            _userName.value = userRepository.getUsernameByEmail(email)

        }
    }


}