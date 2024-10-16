package com.demo.app.scale

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.demo.app.scale.ui.theme.ScaleApp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ScaleApp()
        }
    }
}

/*
    Feature
        Home (Bottom Bar)
        Weather
        Records
        Login
        Account

 */
