package com.ub.habittracker.ui.screens.addHabitScreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ub.habittracker.R
import com.ub.habittracker.domain.models.HabitIntervals
import com.ub.habittracker.ui.screens.addHabitScreen.composables.IntervalsDropDown
import com.ub.habittracker.ui.theme.composables.BackButton

@Preview(showSystemUi = true)
@Composable
fun AddHabit() {

    var addHabit by remember { mutableStateOf("") }
    var habitDesc by remember { mutableStateOf("") }
    val habitsList = HabitIntervals.entries.toTypedArray()

    Scaffold { innerPadding ->

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.padding(innerPadding)
        ) {
            BackButton(
                color = colorResource(id = R.color.img_tint_color),
                heading = stringResource(R.string.add_habit),
            ) {
//                onBackPressed()
            }


            HabitTextView(text = stringResource(R.string.name))
            HabitTextFields(placeHolder = stringResource(R.string.describe_your_habit),
                addHabit = addHabit,
                onValueChanged = { addHabit = it })

//

            HabitTextView(text = stringResource(R.string.description))
            HabitTextFields(placeHolder = stringResource(R.string.describe_a_habit),
                addHabit = habitDesc,
                onValueChanged = { habitDesc = it })

            HabitTextView(text = stringResource(R.string.intervals))

            IntervalsDropDown(habitsList) { selectedPriority->
            }

        }

    }

}


@Composable
fun HabitTextFields(placeHolder: String,
                    addHabit:String,
                    onValueChanged:(String) -> Unit){
    TextField(
        value = addHabit, onValueChange = { onValueChanged(it)},
        placeholder = {
            Text(
                text = placeHolder,
                style = TextStyle(color = MaterialTheme.colorScheme.onBackground),
                fontFamily = FontFamily(Font(R.font.roboto_regular, FontWeight.Normal)),
                color = Color.Gray
            )
        },

        colors = TextFieldDefaults.colors(
            focusedContainerColor = colorResource(id = R.color.bg_color),
            unfocusedContainerColor = Color.Gray.copy(alpha = 0.3f),
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent
        ), singleLine = true,
        shape = RoundedCornerShape(13.dp),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),

        modifier = Modifier
            .fillMaxWidth()
            .padding(
                top = 16.dp,
                end = 16.dp, start = 16.dp
            )
    )
}


@Composable
fun HabitTextView(text: String){
    Text(
        text = text,
        fontSize = 15.sp,
        fontWeight = FontWeight.Medium,
        color = colorResource(id = R.color.img_tint_color),
        fontFamily = FontFamily(Font(R.font.roboto_regular, FontWeight.Normal)),
        textAlign = TextAlign.Start,
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 16.dp, top = 30.dp)
    )

}