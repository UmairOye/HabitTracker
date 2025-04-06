package com.ub.habittracker.domain.models

import java.time.LocalDate
import java.time.format.DateTimeFormatter

data class CalendarUiModel(
    val selectedDate: Date,
    val visibleDates: List<Date>
) {

    data class Date(
        val date: LocalDate,
        val isSelected: Boolean,
        val isToday: Boolean,
        val displayDay: String
    ) {
        val day: String = date.format(DateTimeFormatter.ofPattern("E"))
    }
}
