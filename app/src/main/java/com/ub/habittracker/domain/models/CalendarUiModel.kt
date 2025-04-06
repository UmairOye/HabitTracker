package com.ub.habittracker.domain.models

import java.time.LocalDate
import java.time.format.DateTimeFormatter

data class CalendarUiModel(
    val selectedDate: Date,
    val visibleDates: List<Date>
) {

    data class Date(
        val date: LocalDate = LocalDate.now(),
        val isSelected: Boolean = false,
        val isToday: Boolean = false,
        val displayDay: String = ""
    ) {
        val day: String = date.format(DateTimeFormatter.ofPattern("E"))
    }
}
