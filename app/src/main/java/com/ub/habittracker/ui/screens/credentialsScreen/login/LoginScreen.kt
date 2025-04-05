package com.ub.habittracker.ui.theme.screens.credentialsScreen.login

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
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
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
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.ub.habittracker.R
import com.ub.habittracker.data.local.SharedPref
import com.ub.habittracker.domain.models.RequestState
import com.ub.habittracker.domain.utils.Constants
import com.ub.habittracker.domain.utils.Constants.REMEMBER_ME
import com.ub.habittracker.domain.utils.Utils
import com.ub.habittracker.domain.utils.isValidEmail
import com.ub.habittracker.domain.utils.showSnackBar
import com.ub.habittracker.ui.navigation.NavigationItems
import com.ub.habittracker.ui.screens.credentialsScreen.viewModel.CredentialsViewModel
import com.ub.habittracker.ui.screens.dashboardScreen.home.viewModels.HomeViewModel
import com.ub.habittracker.ui.theme.composables.CheckBox
import kotlinx.coroutines.launch

@Composable
fun LoginScreen(
    onBackPressed: () -> Unit,
    onSignUpClicked: (String) -> Unit,
    onSignInClicked: (String) -> Unit
) {
    var emailValue by remember { mutableStateOf("") }
    val passwordVisible = remember { mutableStateOf(false) }
    var passwordValue by remember { mutableStateOf("") }
    var rememberMe by remember { mutableStateOf(false) }

    val userViewModel: CredentialsViewModel = hiltViewModel()
    val userExist = userViewModel.userExist.collectAsState()
    val snackBarHostState = remember { SnackbarHostState() }
    val coroutineScope = rememberCoroutineScope()
    val context = LocalContext.current

    LaunchedEffect(userExist.value) {
        when (val state = userExist.value) {
            is RequestState.Error -> {}
            RequestState.Idle -> {}
            RequestState.Loading -> {}
            is RequestState.Success -> {
                val isUserExist = state.data
                if (isUserExist) {
                    onSignInClicked(NavigationItems.HOME.route+"/$emailValue")
                } else {
                    coroutineScope.showSnackBar(
                        snackBarHostState,
                        context.getString(R.string.email_or_password_is_incorrect)
                    )
                }
            }
        }
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

            Text(
                text = stringResource(R.string.sign_in),
                fontFamily = FontFamily(Font(R.font.roboto_semibold, FontWeight.SemiBold)),
                fontSize = 22.sp,
                color = colorResource(id = R.color.blue_color),
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

                Text(
                    text = stringResource(R.string.welcome_back),
                    color = colorResource(id = R.color.blue_color),
                    fontFamily = FontFamily(Font(R.font.roboto_semibold, FontWeight.SemiBold)),
                    fontSize = 18.sp,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 25.dp),
                    textAlign = TextAlign.Center
                )

                Text(
                    text = stringResource(R.string.sign_in_to_continue),
                    color = Color.Gray,
                    fontFamily = FontFamily(Font(R.font.roboto_regular, FontWeight.Normal)),
                    fontSize = 12.sp,
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center
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
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),

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
                        Icon(
                            imageVector = Icons.Filled.Lock,
                            contentDescription = stringResource(R.string.lock),
                            tint = colorResource(id = R.color.blue_color)
                        )
                    },

                    shape = RoundedCornerShape(13.dp),
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
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                    visualTransformation = if (passwordVisible.value) VisualTransformation.None else PasswordVisualTransformation(),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            top = 16.dp,
                            end = 16.dp, start = 16.dp
                        )
                )


                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .padding(top = 8.dp, start = 16.dp, end = 16.dp)
                        .fillMaxWidth()
                ) {

                    CheckBox(modifier = Modifier, checked = rememberMe, onClick = { isChecked ->
                        SharedPref.setBool(REMEMBER_ME, isChecked)
                        rememberMe = isChecked
                    })

                    Text(
                        text = stringResource(R.string.remember_me),
                        fontFamily = FontFamily(
                            Font(
                                R.font.roboto_regular,
                                FontWeight.Normal
                            )
                        ),
                        fontSize = 12.sp,
                        color = colorResource(id = R.color.text_color),
                        modifier = Modifier
                            .wrapContentWidth()
                            .padding(start = 8.dp)
                    )


                    Text(
                        text = stringResource(R.string.forgot_password),
                        color = colorResource(id = R.color.blue_color),
                        fontFamily = FontFamily(
                            Font(
                                R.font.roboto_regular,
                                FontWeight.Normal
                            )
                        ),
                        fontSize = 12.sp,
                        textAlign = TextAlign.End,
                        fontStyle = FontStyle.Italic,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 8.dp)
                    )
                }


                Button(
                    onClick = {
                        if (emailValue.isEmpty() || passwordValue.isEmpty()) {
                            coroutineScope.showSnackBar(
                                snackBarHostState,
                                context.getString(R.string.email_or_password_field_is_empty)
                            )
                            return@Button
                        } else if (emailValue.isValidEmail()) {
                            userViewModel.isAlreadyUserExist(emailValue, passwordValue)
                            when (userExist.value) {
                                is RequestState.Error -> {}
                                RequestState.Idle -> {}
                                RequestState.Loading -> {}
                                is RequestState.Success -> {

                                    val isUserExist = (userExist.value as RequestState.Success<Boolean>).data
                                    when (isUserExist) {
                                        true -> {
                                            onSignInClicked(NavigationItems.HOME.route+"/$emailValue")
                                        }

                                        false -> {
                                            coroutineScope.showSnackBar(
                                                snackBarHostState,
                                                context.getString(R.string.email_or_password_is_incorrect)
                                            )
                                        }
                                    }
                                }
                            }
                        } else {
                            coroutineScope.showSnackBar(
                                snackBarHostState,
                                context.getString(R.string.email_format_is_invalid)
                            )
                            return@Button
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
                        text = stringResource(id = R.string.sign_in),
                        fontFamily = FontFamily(Font(R.font.roboto_semibold, FontWeight.SemiBold)),
                        fontSize = 16.sp,
                        color = Color.White
                    )

                }


                Text(
                    text = stringResource(R.string.else_),
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

                    Text(text = stringResource(R.string.sign_up),
                        fontFamily = FontFamily(Font(R.font.roboto_semibold, FontWeight.SemiBold)),
                        fontSize = 16.sp,
                        textAlign = TextAlign.Center,
                        color = colorResource(id = R.color.blue_color),
                        modifier = Modifier.clickable {
                            onSignUpClicked(NavigationItems.SIGNUP.route)
                        })

                    HorizontalDivider(
                        modifier = Modifier.width(100.dp)
                    )
                }
            }

        }
    }

}