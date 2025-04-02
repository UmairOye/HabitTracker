package com.ub.habittracker.domain

import androidx.compose.ui.tooling.preview.Preview

@Preview(name = "Dark Mode",
    showBackground = true,
    uiMode = android.content.res.Configuration.UI_MODE_NIGHT_YES)


@Preview(name = "Light Mode",
    showBackground = true,
    uiMode = android.content.res.Configuration.UI_MODE_NIGHT_NO)

annotation class ThemeAnnotation