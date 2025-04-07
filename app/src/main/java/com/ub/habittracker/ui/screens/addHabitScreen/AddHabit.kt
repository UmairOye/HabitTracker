package com.ub.habittracker.ui.screens.addHabitScreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.ub.habittracker.R
import com.ub.habittracker.ui.theme.composables.BackButton

@Preview(showSystemUi = true)
@Composable
fun AddHabit() {

    Scaffold { innerPadding ->

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.padding(innerPadding)
        ) {
            BackButton(
                color = colorResource(id = R.color.blue_color),
                heading = stringResource(R.string.sign_up)
            ) {
//                onBackPressed()
            }
        }

    }

}