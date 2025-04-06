package com.ub.habittracker.data.database.repository

import com.ub.habittracker.domain.models.CalendarUiModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.temporal.ChronoUnit
import java.util.stream.Collectors
import java.util.stream.Stream

class CalendarDataSource {
    val today: LocalDate
        get() {
            return LocalDate.now()
        }


    suspend fun getMonthData(monthDate: LocalDate = today, lastSelectedDate: LocalDate): CalendarUiModel = withContext(Dispatchers.IO) {
        val firstDayOfMonth = monthDate.withDayOfMonth(1)
        val lastDayOfMonth = monthDate.withDayOfMonth(monthDate.lengthOfMonth())
        val visibleDates = getDatesBetween(firstDayOfMonth, lastDayOfMonth.plusDays(1)) // +1 to include the last day
        return@withContext toUiModel(visibleDates, lastSelectedDate)
    }

    private suspend fun getDatesBetween(startDate: LocalDate, endDate: LocalDate): List<LocalDate> = withContext(Dispatchers.IO){
        val numOfDays = ChronoUnit.DAYS.between(startDate, endDate)
        return@withContext Stream.iterate(startDate) { date ->
            date.plusDays(/* daysToAdd = */ 1)
        }
            .limit(numOfDays)
            .collect(Collectors.toList())
    }

    suspend fun toUiModel(
        dateList: List<LocalDate>,
        lastSelectedDate: LocalDate
    ): CalendarUiModel = withContext(Dispatchers.IO){
        return@withContext CalendarUiModel(
            selectedDate = toItemUiModel(lastSelectedDate, true),
            visibleDates = dateList.map {
                toItemUiModel(it, it.isEqual(lastSelectedDate))
            },
        )
    }

    private fun toItemUiModel(date: LocalDate, isSelectedDate: Boolean) = CalendarUiModel.Date(
        isSelected = isSelectedDate,
        isToday = date.isEqual(today),
        date = date,
        displayDay = String.format("%02d", date.dayOfMonth))

}
