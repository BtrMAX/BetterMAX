package ru.oneme.app.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val LightColorScheme = lightColorScheme(
    background = AppColors.LightBackground,
    surface = AppColors.LightSurface,
    primary = AppColors.LightPrimary,
    onPrimary = AppColors.LightOnPrimary,
    secondary = AppColors.LightSecondary,
    onSecondary = AppColors.LightOnSecondary,
    onBackground = AppColors.LightTextPrimary,
    onSurface = AppColors.LightTextSecondary
)

private val DarkColorScheme = darkColorScheme(
    background = AppColors.DarkBackground,
    surface = AppColors.DarkSurface,
    primary = AppColors.DarkPrimary,
    onPrimary = AppColors.DarkOnPrimary,
    secondary = AppColors.DarkSecondary,
    onSecondary = AppColors.DarkOnSecondary,
    onBackground = AppColors.DarkTextPrimary,
    onSurface = AppColors.DarkTextSecondary
)

@Composable
fun AppTheme(content: @Composable () -> Unit) {
    val isDarkTheme = isSystemInDarkTheme()
    val colorScheme = if (isDarkTheme) DarkColorScheme else LightColorScheme

    MaterialTheme(
        colorScheme = colorScheme,
        content = content
    )
}