package com.example.backgrounddownloader

import android.content.Context
import androidx.work.Worker
import androidx.work.WorkerParameters
import java.io.File
import java.io.FileOutputStream
import java.net.HttpURLConnection
import java.net.URL
import android.util.Log

class DownloadWorker(
    context: Context,
    params: WorkerParameters
) : Worker(context, params) {

    override fun doWork(): Result {
        val imageUrl =
            "https://upload.wikimedia.org/wikipedia/commons/c/c3/Doug_the_Pug_NYC.jpg"

        val file = File(applicationContext.filesDir, "downloaded_photo.jpg")

        try {
            val connection =
                URL(imageUrl).openConnection() as HttpURLConnection

            connection.connectTimeout = 15000
            connection.readTimeout = 15000
            connection.requestMethod = "GET"

            try {
                if (connection.responseCode != HttpURLConnection.HTTP_OK) {
                    return Result.failure()
                }

                connection.inputStream.use { input ->
                    FileOutputStream(file).use { output ->
                        input.copyTo(output)
                    }
                }
            } finally {
                connection.disconnect()
            }

            Log.d(
                "DownloadWorker",
                "Файл сохранён: ${file.absolutePath}, размер: ${file.length()} байт"
            )

            return Result.success()

        } catch (e: Exception) {
            file.delete()
            return Result.failure()
        }
    }
}
