package com.ub.habittracker.ui.screens.dashboardScreen.home.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ub.habittracker.data.database.repository.CalendarDataSource
import com.ub.habittracker.data.local.SharedPref
import com.ub.habittracker.domain.models.CalendarUiModel
import com.ub.habittracker.domain.models.RequestState
import com.ub.habittracker.domain.repository.UserRepository
import com.ub.habittracker.domain.utils.Constants
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val userRepository: UserRepository): ViewModel() {
    private val _userName: MutableStateFlow<RequestState<String?>> = MutableStateFlow(RequestState.Idle)
    val userName = _userName.asStateFlow()
    val monthFormatter: DateTimeFormatter = DateTimeFormatter.ofPattern("MMMM")
    private val dataSource = CalendarDataSource()


    private val _calendarDates : MutableStateFlow<List<CalendarUiModel.Date>> = MutableStateFlow(
        listOf(CalendarUiModel.Date())
    )
    val calendarDates = _calendarDates.asStateFlow()

    fun getUserName(email: String){
        viewModelScope.launch {
            val name = userRepository.getUsernameByEmail(email)
            name?.let {
                SharedPref.setString(Constants.USER_NAME, it)
            }
            _userName.value = RequestState.Success(name)
        }
    }


    fun getCalendarList(date: LocalDate) {
        viewModelScope.launch {
            val calendarUiModel = dataSource.getMonthData(
                monthDate = date,
                lastSelectedDate = dataSource.today
            )
            _calendarDates.value = calendarUiModel.visibleDates
        }
    }



}