package com.ub.habittracker.ui.screens.dashboardScreen.home.viewModels

import android.content.Context
import androidx.lifecycle.ViewModel
import com.ub.habittracker.domain.models.DateAndDayModel
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(@ApplicationContext context: Context): ViewModel() {


    fun makeDateAndDayList(): List<DateAndDayModel> {
        val list = mutableListOf<DateAndDayModel>().apply {
            add(DateAndDayModel("Mon", "05"))
            add(DateAndDayModel("Tue", "06"))
            add(DateAndDayModel("Wed", "07"))
            add(DateAndDayModel("Thu", "08"))
            add(DateAndDayModel("Fri", "09"))
            add(DateAndDayModel("Sat", "10"))
            add(DateAndDayModel("Sun", "11"))
            add(DateAndDayModel("Mon", "12"))
            add(DateAndDayModel("Fri", "13"))
            add(DateAndDayModel("Sat", "14"))
        }

        return list
    }

}