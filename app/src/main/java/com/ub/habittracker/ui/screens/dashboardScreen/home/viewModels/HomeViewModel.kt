package com.ub.habittracker.ui.screens.dashboardScreen.home.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ub.habittracker.data.local.SharedPref
import com.ub.habittracker.domain.models.DateAndDayModel
import com.ub.habittracker.domain.models.RequestState
import com.ub.habittracker.domain.repository.UserRepository
import com.ub.habittracker.domain.utils.Constants
import com.ub.habittracker.domain.utils.Utils
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val userRepository: UserRepository): ViewModel() {
    private val _userName: MutableStateFlow<RequestState<String?>> = MutableStateFlow(RequestState.Idle)
    val userName = _userName.asStateFlow()

    fun makeDateAndDayList(): List<DateAndDayModel>{
        return userRepository.makeDateAndDayList()
    }


    fun getUserName(email: String){
        viewModelScope.launch {
            val name = userRepository.getUsernameByEmail(email)
            name?.let {
                SharedPref.setString(Constants.USER_NAME, it)
            }
            _userName.value = RequestState.Success(name)
        }
    }


}