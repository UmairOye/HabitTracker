package com.ub.habittracker.domain.utils

import android.util.Patterns
import androidx.compose.material3.SnackbarHostState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

fun CoroutineScope.showSnackBar(snackBarHostState: SnackbarHostState, message: String) {
    this.launch {
        snackBarHostState.showSnackbar(message)
    }
}

fun String.isValidEmail(): Boolean {
    return this.isNotEmpty() && Patterns.EMAIL_ADDRESS.matcher(this).matches()
}