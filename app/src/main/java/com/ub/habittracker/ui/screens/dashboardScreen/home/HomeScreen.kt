package com.ub.habittracker.ui.screens.dashboardScreen.home

import android.os.Build
import androidx.activity.compose.BackHandler
import androidx.annotation.RequiresApi
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ChevronLeft
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.ub.habittracker.R
import com.ub.habittracker.data.local.SharedPref
import com.ub.habittracker.domain.models.RequestState
import com.ub.habittracker.domain.utils.Constants
import com.ub.habittracker.domain.utils.Constants.FIRST_TIME_USER
import com.ub.habittracker.ui.screens.dashboardScreen.composables.WeeklyItems
import com.ub.habittracker.ui.screens.dashboardScreen.home.viewModels.HomeViewModel
import java.time.LocalDate

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun HomeScreen(
    email: String
) {

    val homeViewModel: HomeViewModel = hiltViewModel()
    val userState by homeViewModel.userName.collectAsState()
    var userName = remember { SharedPref.getString(Constants.USER_NAME, "") }
    val currentMonth = remember { mutableStateOf(LocalDate.now()) }
    val calendarDates by homeViewModel.calendarDates.collectAsState()

    BackHandler {
        // nothing to do
    }

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = { },
                containerColor = colorResource(id = R.color.card_color),
                contentColor = colorResource(id = R.color.white),
                elevation = FloatingActionButtonDefaults.elevation(2.dp)
            ) {
                Icon(
                    Icons.Filled.Add, stringResource(R.string.small_floating_action_button),
                    tint = colorResource(R.color.img_tint_color)
                )
            }
        }
    ) { innerPadding ->

        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .background(color = colorResource(id = R.color.bg_color)),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {

            LaunchedEffect(currentMonth.value) {
                homeViewModel.getCalendarList(currentMonth.value)
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {

                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center,
                    modifier = Modifier.weight(1f)
                ) {

                    Text(
                        text = "Good Morning",
                        textAlign = TextAlign.Start,
                        fontFamily = FontFamily(Font(R.font.roboto_semibold, FontWeight.SemiBold)),
                        fontSize = 16.sp,
                        fontWeight = FontWeight.SemiBold,
                        style = TextStyle(MaterialTheme.colorScheme.onBackground),
                        modifier = Modifier
                            .padding(start = 16.dp, top = 16.dp)
                            .fillMaxWidth()
                    )


                    LaunchedEffect(SharedPref.getBool(FIRST_TIME_USER, false).not()) {
                        homeViewModel.getUserName(email)
                        SharedPref.getBool(FIRST_TIME_USER, true)
                    }

                    when (userState) {
                        is RequestState.Error -> {}
                        RequestState.Idle -> {}
                        RequestState.Loading -> {}
                        is RequestState.Success -> {
                            val user = (userState as RequestState.Success).data
                            if (user != null) {
                                userName = user
                            }
                        }
                    }


                    Text(
                        text = userName,
                        textAlign = TextAlign.Start,
                        fontFamily = FontFamily(Font(R.font.roboto_semibold, FontWeight.SemiBold)),
                        fontSize = 24.sp,
                        fontWeight = FontWeight.SemiBold,
                        style = TextStyle(MaterialTheme.colorScheme.onBackground),
                        modifier = Modifier
                            .padding(start = 16.dp, top = 4.dp)
                            .fillMaxWidth()
                    )

                }

                Icon(
                    imageVector = Icons.Default.Settings,
                    contentDescription = stringResource(R.string.settings),
                    modifier = Modifier.padding(end = 16.dp),
                    tint = colorResource(id = R.color.img_tint_color)
                )

            }

            Row(modifier = Modifier.padding(top = 30.dp)) {
                AnimatedContent(
                    targetState = currentMonth.value,
                    transitionSpec = {
                        if (targetState > initialState) {
                            (slideInHorizontally { width -> width } + fadeIn()).togetherWith(
                                slideOutHorizontally { width -> -width } + fadeOut())
                        } else {
                            (slideInHorizontally { width -> -width } + fadeIn()).togetherWith(
                                slideOutHorizontally { width -> width } + fadeOut())
                        }
                    },
                    modifier = Modifier
                        .weight(1f)
                        .align(Alignment.CenterVertically), label = ""
                ) { month ->
                    Text(
                        text = month.format(homeViewModel.monthFormatter),
                        style = TextStyle(MaterialTheme.colorScheme.onBackground),
                        fontSize = 18.sp,
                        fontFamily = FontFamily(Font(R.font.roboto_semibold, FontWeight.SemiBold)),
                        modifier = Modifier.padding(start = 16.dp)
                    )
                }

                IconButton(onClick = {
                    currentMonth.value = currentMonth.value.minusMonths(1)
                }) {
                    Icon(
                        imageVector = Icons.Filled.ChevronLeft,
                        contentDescription = stringResource(R.string.previous)
                    )
                }

                IconButton(onClick = {
                    currentMonth.value = currentMonth.value.plusMonths(1)
                }) {
                    Icon(
                        imageVector = Icons.Filled.ChevronRight,
                        contentDescription = stringResource(R.string.next)
                    )
                }
            }


            when (calendarDates.isNotEmpty()) {
                true -> {
                    LazyRow(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 5.dp, start = 12.dp, end = 16.dp)
                    ) {
                        items(calendarDates) { date ->
                            WeeklyItems(date)
                        }
                    }
                }

                false -> {

                }
            }
        }
    }
}
