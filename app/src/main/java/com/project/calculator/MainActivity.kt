package com.project.calculator

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.SideEffect
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.project.calculator.presentation.CalculatorScreen
import com.project.calculator.ui.theme.MaterialCalculatorTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialCalculatorTheme {

                val systemUiController = rememberSystemUiController()
                val useDarkIcons = isSystemInDarkTheme()
                val statusBarColor = MaterialTheme.colorScheme.secondaryContainer

                SideEffect {
                    //systemUiController.setSystemBarsColor(statusBarColor, darkIcons = useDarkIcons)
                    Log.d("MaterialCalculatorTheme", "statusBarColor: $statusBarColor.")
                    systemUiController.statusBarDarkContentEnabled = false
                    systemUiController.setStatusBarColor(statusBarColor, darkIcons = !useDarkIcons)
                }

                CalculatorScreen()
            }
        }
    }
}

