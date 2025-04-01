package com.ub.habittracker.ui.theme.screens.credentialsScreen.login

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ub.habittracker.R

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun LoginScreen(){

    var emailValue by remember {
        mutableStateOf("")
    }

    var passwordValue by remember {
        mutableStateOf("")
    }


    Scaffold { innerPadding ->

        Column(modifier = Modifier
            .padding(innerPadding)
            .fillMaxSize()
            .background(color = colorResource(id = R.color.bg_color)),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top) {


            Text(text = stringResource(R.string.sign_in),
                fontFamily = FontFamily(Font(R.font.roboto_semibold, FontWeight.SemiBold)),
                fontSize = 22.sp,
                style = TextStyle(MaterialTheme.colorScheme.onBackground),
                textAlign = TextAlign.Center
            )

            Card(
                colors = CardDefaults.cardColors(
                    colorResource(id = R.color.white)
                ),
                elevation = CardDefaults.cardElevation(
                    0.dp
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        top = 60.dp,
                        start = 20.dp, end = 20.dp
                    )
                    .height(250.dp)
            ) {

                Text(text = stringResource(R.string.welcome_back),
                    color = colorResource(id = R.color.blue_color),
                    fontFamily = FontFamily(Font(R.font.roboto_semibold, FontWeight.SemiBold)),
                    fontSize = 18.sp,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 20.dp),
                    textAlign = TextAlign.Center)

                Text(text = stringResource(R.string.sign_in_to_continue),
                    color = Color.Gray,
                    fontFamily = FontFamily(Font(R.font.roboto_regular, FontWeight.Normal)),
                    fontSize = 12.sp,
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center)



                TextField(value = emailValue, onValueChange = {email -> emailValue = email},
                    placeholder = {
                        Text(
                            text = "xyz@gmail.com",
                            style = TextStyle(color = MaterialTheme.colorScheme.onBackground),
                            fontFamily = FontFamily(Font(R.font.roboto_regular, FontWeight.Normal)),
                            color = Color.Gray
                        )
                    },

                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = colorResource(id = R.color.bg_color),
                        unfocusedContainerColor = colorResource(id = R.color.bg_color),
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent
                    ), singleLine = true, leadingIcon = {
                        Icon(imageVector = Icons.Filled.Email, contentDescription = stringResource(R.string.email_icon),
                            tint = colorResource(id = R.color.blue_color))
                    },

                    shape = RoundedCornerShape(13.dp),

                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            top = 16.dp,
                            end = 16.dp, start = 16.dp
                        )
                )



                TextField(value = passwordValue, onValueChange = {password -> passwordValue = password},
                    placeholder = {
                        Text(
                            text = stringResource(R.string.password),
                            style = TextStyle(color = MaterialTheme.colorScheme.onBackground),
                            fontFamily = FontFamily(Font(R.font.roboto_regular, FontWeight.Normal)),
                            color = Color.Gray
                        )
                    },

                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = colorResource(id = R.color.bg_color),
                        unfocusedContainerColor = colorResource(id = R.color.bg_color),
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent
                    ), singleLine = true, leadingIcon = {
                        Icon(imageVector = Icons.Filled.Lock, contentDescription = stringResource(R.string.lock),
                            tint = colorResource(id = R.color.blue_color))
                    },

                    shape = RoundedCornerShape(13.dp),

                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            top = 16.dp,
                            end = 16.dp, start = 16.dp
                        )
                )
            }
        }
    }

}