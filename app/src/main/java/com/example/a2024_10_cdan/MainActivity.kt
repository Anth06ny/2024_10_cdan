package com.example.a2024_10_cdan

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.a2024_10_cdan.ui.screens.SearchScreen
import com.example.a2024_10_cdan.ui.theme._2024_10_cdanTheme
import com.example.a2024_10_cdan.viewmodel.MainViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        println("MainActivity.onCreate")
        setContent {
            _2024_10_cdanTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    val mainViewModel: MainViewModel = viewModel()
                    SearchScreen(
                        modifier = Modifier.padding(innerPadding),
                        mainViewModel = mainViewModel

                    )
                }
            }
        }
    }
}
