package com.ub.habittracker.ui.screens.addHabitScreen.composables

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ub.habittracker.domain.models.HabitIntervals

@Composable
//@Preview
fun IntervalsDropDown(prioritiesList: Array<HabitIntervals>, onPrioritySelected:(HabitIntervals)-> Unit){

    var isExpanded by remember {
        mutableStateOf(false)
    }

    val angle : Float by animateFloatAsState(targetValue = if(isExpanded) 180f else 0f, label = "")
    var parentSize by remember {
        mutableStateOf(IntSize.Zero)
    }


    Row(modifier = Modifier
        .fillMaxWidth().padding(horizontal = 8.dp)
        .onGloballyPositioned {
            parentSize = it.size
        }
        .background(MaterialTheme.colorScheme.background)
        .height(40.dp)
        .clickable {
            isExpanded = true
        }
        .border(
            width = 1.dp,
            Color.LightGray,
            shape = RoundedCornerShape(size = 8.dp)
        ),
        verticalAlignment = Alignment.CenterVertically) {


        Text(text = "",
            fontSize = 14.sp,
            fontWeight = FontWeight.Medium,
            modifier = Modifier.weight(7f),
            style = TextStyle(color = MaterialTheme.colorScheme.onBackground)
        )

        IconButton(onClick = { isExpanded = true }, modifier = Modifier
            .weight(1f)
            .rotate(angle)) {
            Icon(imageVector = Icons.Default.ArrowDropDown,
                contentDescription = null
            )
        }

        DropdownMenu(
            modifier = Modifier.width(with(LocalDensity.current) { parentSize.width.toDp() }),
            expanded = isExpanded,
            onDismissRequest = { isExpanded = false }
        ) {
            prioritiesList.slice(0..3).forEach { priority ->
                DropdownMenuItem(
                    text = { IntervalItems(priority) },
                    onClick = {
                        isExpanded = false
                        onPrioritySelected(priority)
                    },
                    contentPadding = PaddingValues(0.dp)
                )
            }
        }

    }
}