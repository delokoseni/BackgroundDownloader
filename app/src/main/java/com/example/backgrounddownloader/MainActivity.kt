package com.example.backgrounddownloader

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.backgrounddownloader.ui.theme.BackgroundDownloaderTheme
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                BackgroundDownloaderTheme {
                    Button(
                        onClick = { onDownloadClick() }
                    ) {
                        Text("Скачать")
                    }
                }
            }
        }
    }

    private fun onDownloadClick() {
        val downloadRequest =
            OneTimeWorkRequestBuilder<DownloadWorker>()
                .build()

        WorkManager.getInstance(applicationContext)
            .enqueue(downloadRequest)
    }
}