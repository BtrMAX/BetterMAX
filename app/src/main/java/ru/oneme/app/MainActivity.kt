package ru.oneme.app

import android.content.pm.ActivityInfo
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import ru.oneme.app.ui.screens.fake_max_login.loginScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        setContent {
            loginScreen()
        }
    }
}