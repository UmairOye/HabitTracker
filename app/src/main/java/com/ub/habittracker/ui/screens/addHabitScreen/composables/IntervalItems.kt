package com.ub.habittracker.ui.screens.addHabitScreen.composables

import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ub.habittracker.domain.models.HabitIntervals

@Composable
fun IntervalItems(intervals: HabitIntervals){
    Row (verticalAlignment = Alignment.CenterVertically
        , modifier = Modifier.padding(horizontal = 8.dp).height(IntrinsicSize.Max)){
        Text(text = intervals.name,
            Modifier.padding(start = 8.dp),
            fontSize = 14.sp,
            fontWeight = FontWeight.Medium,
            style = TextStyle(color = MaterialTheme.colorScheme.onBackground)
        )

    }
}
