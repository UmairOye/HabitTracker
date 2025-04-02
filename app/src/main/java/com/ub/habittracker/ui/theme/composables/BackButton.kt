package com.ub.habittracker.ui.theme.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.ub.habittracker.R

@Composable
fun BackButton(heading: String = "Habit Tracker", color: androidx.compose.ui.graphics.Color, onBackClick: () -> Unit){

    Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement =
    Arrangement.Start,
        modifier = Modifier
            .fillMaxWidth()) {

        IconButton(onClick = { onBackClick()}) {
            Icon(imageVector = Icons.Default.ArrowBack,
                contentDescription = null,
                tint = color)
        }

        Text(
            text = heading,
            fontSize = 22.sp,
            fontFamily = FontFamily(Font(R.font.roboto_semibold, FontWeight.SemiBold)),
            fontWeight = FontWeight.SemiBold,
            color = color,
        )
    }
}


@Composable
@Preview(showSystemUi = false, heightDp = 50, showBackground = false)
fun PreviewBackButton(){
    BackButton(color = colorResource(id = R.color.blue_color)) {
    }
}