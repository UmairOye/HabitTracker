package com.ub.habittracker.ui.screens.dashboardScreen.home

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ub.habittracker.R

@Composable
@Preview(showSystemUi = true)
fun HomeScreen() {

    BackHandler {
        //nothing to do
    }

    Scaffold { innerPadding ->

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
                modifier = Modifier.padding(start = 16.dp, top = 16.dp).fillMaxWidth()
            )

            Text(
                text = "Umair",
                textAlign = TextAlign.Start,
                fontFamily = FontFamily(Font(R.font.roboto_semibold, FontWeight.SemiBold)),
                fontSize = 24.sp,
                fontWeight = FontWeight.SemiBold,
                style = TextStyle(MaterialTheme.colorScheme.onBackground),
                modifier = Modifier.padding(start = 16.dp, top = 4.dp).fillMaxWidth()
            )
        }
    }
}