package com.zj.smart.widget

import android.content.Context
import androidx.glance.GlanceId
import androidx.glance.appwidget.GlanceAppWidgetManager
import androidx.glance.appwidget.state.updateAppWidgetState
import androidx.work.CoroutineWorker
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import androidx.work.WorkerParameters
import java.time.Duration

class SmartWorker(private val context: Context, workerParameters: WorkerParameters) :
    CoroutineWorker(context, workerParameters) {

    companion object {

        private val uniqueWorkName = SmartWorker::class.java.simpleName

        /**
         * Enqueues a new worker to refresh weather data only if not enqueued already
         *
         * Note: if you would like to have different workers per widget instance you could provide
         * the unique name based on some criteria (e.g selected weather location).
         *
         * @param force set to true to replace any ongoing work and expedite the request
         */
        fun enqueue(context: Context, force: Boolean = false) {
            val manager = WorkManager.getInstance(context)
            val requestBuilder = PeriodicWorkRequestBuilder<SmartWorker>(
                Duration.ofMinutes(15)
            )
            var workPolicy = ExistingPeriodicWorkPolicy.KEEP

            // Replace any enqueued work and expedite the request
            if (force) {
                workPolicy = ExistingPeriodicWorkPolicy.REPLACE
            }

            manager.enqueueUniquePeriodicWork(
                uniqueWorkName,
                workPolicy,
                requestBuilder.build()
            )
        }

        /**
         * Cancel any ongoing worker
         */
        fun cancel(context: Context) {
            WorkManager.getInstance(context).cancelUniqueWork(uniqueWorkName)
        }
    }

    override suspend fun doWork(): Result {
        val manager = GlanceAppWidgetManager(context)
        val glanceIds = manager.getGlanceIds(SmartWidgetGlance::class.java)
        return try {
            // Update state to indicate loading
            setWidgetState(glanceIds, WeatherInfo.Loading)
            // Update state with new data
//            setWidgetState(glanceIds, WeatherInfo())

            Result.success()
        } catch (e: Exception) {
            setWidgetState(glanceIds, WeatherInfo.Unavailable(e.message.orEmpty()))
            if (runAttemptCount < 10) {
                // Exponential backoff strategy will avoid the request to repeat
                // too fast in case of failures.
                Result.retry()
            } else {
                Result.failure()
            }
        }
    }

    /**
     * Update the state of all widgets and then force update UI
     */
    private suspend fun setWidgetState(glanceIds: List<GlanceId>, newState: WeatherInfo) {
        glanceIds.forEach { glanceId ->
            updateAppWidgetState(
                context = context,
                definition = WeatherInfoStateDefinition,
                glanceId = glanceId,
                updateState = { newState }
            )
        }
//        WeatherGlanceWidget().updateAll(context)
    }
}