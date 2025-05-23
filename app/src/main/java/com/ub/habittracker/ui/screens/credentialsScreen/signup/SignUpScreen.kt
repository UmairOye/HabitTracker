package com.ub.habittracker.ui.screens.credentialsScreen.signup

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
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
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.ub.habittracker.R
import com.ub.habittracker.data.database.entity.UserEntity
import com.ub.habittracker.domain.utils.isValidEmail
import com.ub.habittracker.domain.utils.showSnackBar
import com.ub.habittracker.ui.navigation.NavigationItems
import com.ub.habittracker.ui.screens.credentialsScreen.viewModel.CredentialsViewModel
import com.ub.habittracker.ui.theme.composables.BackButton

@Composable
fun SignUpScreen(
    onSignUpClicked: (String) -> Unit,
    onSignInClicked: (String) -> Unit,
    onBackPressed: () -> Unit
) {

    val snackBarHostState = remember { SnackbarHostState() }
    val coroutineScope = rememberCoroutineScope()
    val context = LocalContext.current
    val viewModel: CredentialsViewModel = hiltViewModel()
    val passwordVisible = remember { mutableStateOf(false) }
    val confirmPasswordVisible = remember { mutableStateOf(false) }


    var emailValue by remember {
        mutableStateOf("")
    }

    var passwordValue by remember {
        mutableStateOf("")
    }

    var confirmPassword by remember {
        mutableStateOf("")
    }

    var name by remember {
        mutableStateOf("")
    }

    Scaffold(snackbarHost = { SnackbarHost(snackBarHostState) }) { innerPadding ->

        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .background(color = colorResource(id = R.color.bg_color)),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {

            BackButton(
                color = colorResource(id = R.color.blue_color),
                heading = stringResource(R.string.sign_up)
            ) {
                onBackPressed()
            }

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

                Text(
                    text = stringResource(R.string.create_account),
                    color = colorResource(id = R.color.blue_color),
                    fontFamily = FontFamily(Font(R.font.roboto_semibold, FontWeight.SemiBold)),
                    fontSize = 18.sp,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 25.dp),
                    textAlign = TextAlign.Center
                )

                Text(
                    text = stringResource(R.string.sign_up_to_create_new_account),
                    color = Color.Gray,
                    fontFamily = FontFamily(Font(R.font.roboto_regular, FontWeight.Normal)),
                    fontSize = 12.sp,
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center
                )


                TextField(
                    value = name, onValueChange = { username -> name = username },
                    placeholder = {
                        Text(
                            text = stringResource(R.string.username),
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
                        Icon(
                            imageVector = Icons.Filled.AccountCircle,
                            contentDescription = stringResource(R.string.email_icon),
                            tint = colorResource(id = R.color.blue_color)
                        )
                    },

                    shape = RoundedCornerShape(13.dp),

                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            top = 16.dp,
                            end = 16.dp, start = 16.dp
                        )
                )


                TextField(
                    value = emailValue, onValueChange = { email -> emailValue = email },
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
                        Icon(
                            imageVector = Icons.Filled.Email,
                            contentDescription = stringResource(R.string.email_icon),
                            tint = colorResource(id = R.color.blue_color)
                        )
                    },

                    shape = RoundedCornerShape(13.dp),

                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            top = 16.dp,
                            end = 16.dp, start = 16.dp
                        )
                )



                TextField(
                    value = passwordValue, onValueChange = { password -> passwordValue = password },
                    placeholder = {
                        Text(
                            text = stringResource(R.string.password_),
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
                        Icon(
                            imageVector = Icons.Filled.Lock,
                            contentDescription = stringResource(R.string.lock),
                            tint = colorResource(id = R.color.blue_color)
                        )
                    },

                    trailingIcon = {
                        val image = if (passwordVisible.value)
                            R.drawable.visibility
                        else R.drawable.visibility_off

                        val description = if (passwordVisible.value)
                            stringResource(R.string.hide_password) else stringResource(R.string.show_password)


                        Image(painter = painterResource(id = image),
                            contentDescription = description,
                            modifier = Modifier.clickable {
                                passwordVisible.value = !passwordVisible.value
                            })
                    },

                    shape = RoundedCornerShape(13.dp),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                    visualTransformation = if (passwordVisible.value) VisualTransformation.None else PasswordVisualTransformation(),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            top = 16.dp,
                            end = 16.dp, start = 16.dp
                        )
                )


                TextField(
                    value = confirmPassword,
                    onValueChange = { password -> confirmPassword = password },
                    placeholder = {
                        Text(
                            text = stringResource(R.string.confirm_password),
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
                    ),
                    singleLine = true,
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Filled.Lock,
                            contentDescription = stringResource(R.string.lock),
                            tint = colorResource(id = R.color.blue_color)
                        )
                    },

                    trailingIcon = {
                        val image = if (confirmPasswordVisible.value)
                            R.drawable.visibility
                        else R.drawable.visibility_off

                        val description = if (confirmPasswordVisible.value)
                            stringResource(R.string.hide_password) else stringResource(R.string.show_password)


                        Image(painter = painterResource(id = image),
                            contentDescription = description,
                            modifier = Modifier.clickable {
                                confirmPasswordVisible.value = !confirmPasswordVisible.value
                            })
                    },
                    shape = RoundedCornerShape(13.dp),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                    visualTransformation = if (confirmPasswordVisible.value) VisualTransformation.None else PasswordVisualTransformation(),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            top = 16.dp,
                            end = 16.dp, start = 16.dp
                        )
                )

                Button(
                    onClick = {

                        if (emailValue.isEmpty() || name.isEmpty()
                            || passwordValue.isEmpty() || confirmPassword.isEmpty()
                        ) {

                            coroutineScope.showSnackBar(
                                snackBarHostState,
                                context.getString(R.string.all_fields_must_be_filled)
                            )
                            return@Button
                        } else if (emailValue.isValidEmail().not()) {
                            coroutineScope.showSnackBar(
                                snackBarHostState,
                                context.getString(R.string.email_format_is_invalid)
                            )
                            return@Button
                        } else if (passwordValue != confirmPassword) {
                            coroutineScope.showSnackBar(
                                snackBarHostState,
                                context.getString(R.string.password_and_confirm_password_must_be_same)
                            )
                            return@Button
                        } else {
                            val userModel =
                                UserEntity(
                                    userName = name,
                                    email = emailValue,
                                    password = passwordValue
                                )
                            viewModel.insertUser(userModel)
                            emailValue = ""
                            passwordValue = ""
                            confirmPassword = ""
                            name = ""
                            coroutineScope.showSnackBar(
                                snackBarHostState,
                                context.getString(R.string.user_registered_successfully)
                            )
                            onSignInClicked(NavigationItems.LOGIN.route)
                        }
                    },
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

                    Text(
                        text = stringResource(id = R.string.sign_up),
                        fontFamily = FontFamily(Font(R.font.roboto_semibold, FontWeight.SemiBold)),
                        fontSize = 16.sp,
                        color = Color.White
                    )

                }


                Text(
                    text = stringResource(R.string.else_already_have_an_account),
                    fontFamily = FontFamily(Font(R.font.roboto_regular, FontWeight.Normal)),
                    fontSize = 12.sp,
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center,
                    color = colorResource(id = R.color.text_color)
                )


                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier
                        .padding(vertical = 25.dp, horizontal = 16.dp)
                        .fillMaxWidth()
                ) {
                    HorizontalDivider(
                        modifier = Modifier.width(100.dp)
                    )

                    Text(text = stringResource(R.string.sign_in),
                        fontFamily = FontFamily(Font(R.font.roboto_semibold, FontWeight.SemiBold)),
                        fontSize = 16.sp,
                        textAlign = TextAlign.Center,
                        color = colorResource(id = R.color.blue_color),
                        modifier = Modifier.clickable {
                            onSignInClicked(NavigationItems.LOGIN.route)
                        }
                    )

                    HorizontalDivider(
                        modifier = Modifier.width(100.dp)
                    )
                }
            }

        }
    }


}