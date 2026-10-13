package com.example.backgrounddownloader

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.backgrounddownloader.ui.theme.BackgroundDownloaderTheme
import androidx.compose.material3.Button
import androidx.compose.material3.Text

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BackgroundDownloaderTheme {
                Button(
                    onClick = { onDownloadClick() }
                ) {
                    Text("Скачать")
                }
            }
        }
    }

    private fun onDownloadClick() {
        println("Нажатие кнопки")
    }
}