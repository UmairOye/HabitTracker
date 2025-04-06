package com.ub.habittracker.data.database.repository

import com.ub.habittracker.domain.models.CalendarUiModel
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


    fun getMonthData(monthDate: LocalDate = today, lastSelectedDate: LocalDate): CalendarUiModel {
        val firstDayOfMonth = monthDate.withDayOfMonth(1)
        val lastDayOfMonth = monthDate.withDayOfMonth(monthDate.lengthOfMonth())
        val visibleDates = getDatesBetween(firstDayOfMonth, lastDayOfMonth.plusDays(1)) // +1 to include the last day
        return toUiModel(visibleDates, lastSelectedDate)
    }


    fun getData(startDate: LocalDate = today, lastSelectedDate: LocalDate): CalendarUiModel {
        val firstDayOfWeek = startDate.with(DayOfWeek.MONDAY)
        val endDayOfWeek = firstDayOfWeek.plusDays(7)
        val visibleDates = getDatesBetween(firstDayOfWeek, endDayOfWeek)
        return toUiModel(visibleDates, lastSelectedDate)
    }

    private fun getDatesBetween(startDate: LocalDate, endDate: LocalDate): List<LocalDate> {
        val numOfDays = ChronoUnit.DAYS.between(startDate, endDate)
        return Stream.iterate(startDate) { date ->
            date.plusDays(/* daysToAdd = */ 1)
        }
            .limit(numOfDays)
            .collect(Collectors.toList())
    }

    private fun toUiModel(
        dateList: List<LocalDate>,
        lastSelectedDate: LocalDate
    ): CalendarUiModel {
        return CalendarUiModel(
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
