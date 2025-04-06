package com.ub.habittracker.ui.screens.dashboardScreen.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
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
import com.ub.habittracker.domain.models.CalendarUiModel
import com.ub.habittracker.domain.models.DateAndDayModel
import java.time.LocalDate

@Composable
fun WeeklyItems(date: CalendarUiModel.Date){
    Card(onClick = { },
        colors = CardDefaults.cardColors(
            containerColor = colorResource(id = R.color.card_color)
        ),
        elevation = CardDefaults.cardElevation(
            1.dp
        ),
        modifier = Modifier
            .height(60.dp)
            .width(54.dp)
            .padding(horizontal = 4.dp)) {

        Column(horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxSize()) {

            Text(text = date.day,
                style = TextStyle(MaterialTheme.colorScheme.onBackground),
                fontSize = 15.sp,
                textAlign = TextAlign.Center,
                fontFamily = FontFamily(Font(R.font.poppins_semibold, FontWeight.SemiBold))
            )

            Text(text = date.displayDay,
                style = TextStyle(MaterialTheme.colorScheme.onBackground),
                fontSize = 11.sp,
                textAlign = TextAlign.Center,
                fontFamily = FontFamily(Font(R.font.roboto_regular, FontWeight.Normal))
            )
        }
    }
}


@Preview
@Composable
fun PreviewWeeklyItems(){
    val dateAndDayModel = CalendarUiModel.Date(LocalDate.now(),
        isSelected = false,
        isToday = false,
        displayDay = "20"
    )
    WeeklyItems(dateAndDayModel)
}