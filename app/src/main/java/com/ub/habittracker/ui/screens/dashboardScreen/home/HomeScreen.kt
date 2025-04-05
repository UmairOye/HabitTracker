package com.ub.habittracker.ui.screens.dashboardScreen.home

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.ub.habittracker.R
import com.ub.habittracker.ui.screens.dashboardScreen.composables.WeeklyItems
import com.ub.habittracker.ui.screens.dashboardScreen.home.viewModels.HomeViewModel

@Composable
@Preview(showSystemUi = true)
fun HomeScreen() {

    val homeViewModel: HomeViewModel = hiltViewModel()

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
                Icon(Icons.Filled.Add, stringResource(R.string.small_floating_action_button))
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

            Text(
                text = "Good Morning",
                textAlign = TextAlign.Start,
                fontFamily = FontFamily(Font(R.font.roboto_semibold, FontWeight.SemiBold)),
                fontSize = 20.sp,
                fontWeight = FontWeight.SemiBold,
                style = TextStyle(MaterialTheme.colorScheme.onBackground),
                modifier = Modifier
                    .padding(start = 16.dp, top = 16.dp)
                    .fillMaxWidth()
            )

            Text(
                text = "Umair",
                textAlign = TextAlign.Start,
                fontFamily = FontFamily(Font(R.font.roboto_semibold, FontWeight.SemiBold)),
                fontSize = 24.sp,
                fontWeight = FontWeight.SemiBold,
                style = TextStyle(MaterialTheme.colorScheme.onBackground),
                modifier = Modifier
                    .padding(start = 16.dp, top = 4.dp)
                    .fillMaxWidth()
            )

            LazyRow(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 35.dp, start = 12.dp, end = 16.dp)
            ) {
                items(homeViewModel.makeDateAndDayList()) { items ->
                    WeeklyItems(items)
                }
            }
        }
    }
}
