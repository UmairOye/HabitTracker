package com.ub.habittracker.ui.theme.screens.credentialsScreen.login

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
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
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ub.habittracker.R
import com.ub.habittracker.domain.ThemeAnnotation
import com.ub.habittracker.ui.theme.composables.CheckBox

@Preview
@Composable
fun LoginScreen(){

    var emailValue by remember {
        mutableStateOf("")
    }

    var passwordValue by remember {
        mutableStateOf("")
    }

    var rememberMe by remember {
        mutableStateOf(false)
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
                color = colorResource(id = R.color.text_color),
                textAlign = TextAlign.Center
            )

            Card(
                colors = CardDefaults.cardColors(
                    colorResource(id = R.color.card_color)
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
                    .wrapContentHeight()
            ) {

                Text(text = stringResource(R.string.welcome_back),
                    color = colorResource(id = R.color.blue_color),
                    fontFamily = FontFamily(Font(R.font.roboto_semibold, FontWeight.SemiBold)),
                    fontSize = 18.sp,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 25.dp),
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


                Row (verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .padding(top = 8.dp, start = 16.dp, end = 16.dp)
                        .fillMaxWidth()){

                    CheckBox(modifier = Modifier, checked = rememberMe, onClick = {isChecked->
                        rememberMe = isChecked
                    })

                    Text(text = stringResource(R.string.remember_me),
                        fontFamily = FontFamily(Font(R.font.roboto_regular,
                            FontWeight.Normal)),
                        fontSize = 12.sp,
                        color = colorResource(id = R.color.text_color),
                        modifier = Modifier
                            .wrapContentWidth()
                            .padding(start = 8.dp))


                    Text(text = stringResource(R.string.forgot_password),
                        color = colorResource(id = R.color.blue_color),
                        fontFamily = FontFamily(Font(R.font.roboto_regular,
                            FontWeight.Normal)),
                        fontSize = 12.sp,
                        textAlign = TextAlign.End,
                        fontStyle = FontStyle.Italic,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 8.dp))
                }


                Button(onClick = { },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            horizontal = 20.dp,
                            vertical = 25.dp
                        )
                        .height(45.dp)
                        .shadow(
                            elevation = 10.dp,
                            shape = RoundedCornerShape(12.dp)
                        ),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = colorResource(id = R.color.blue_color)
                    ),
                    shape = RoundedCornerShape(12.dp)
                ) {

                    Text(text = stringResource(id = R.string.sign_in),
                        fontFamily = FontFamily(Font(R.font.roboto_semibold, FontWeight.SemiBold)),
                        fontSize = 16.sp,
                        color = Color.White)

                }


                Text(text = stringResource(R.string.else_),
                    fontFamily = FontFamily(Font(R.font.roboto_regular, FontWeight.Normal)),
                    fontSize = 12.sp,
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center,
                    color = colorResource(id = R.color.text_color))


                Row(verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier
                        .padding(vertical = 25.dp, horizontal = 16.dp)
                        .fillMaxWidth()) {
                    HorizontalDivider(
                        modifier = Modifier.width(100.dp)
                    )
                    
                    Text(text = stringResource(R.string.sign_up),
                        fontFamily = FontFamily(Font(R.font.roboto_semibold, FontWeight.SemiBold)),
                        fontSize = 16.sp,
                        textAlign = TextAlign.Center,
                        color = colorResource(id = R.color.blue_color))

                    HorizontalDivider(
                        modifier = Modifier.width(100.dp)
                    )
                }
            }

        }
    }

}