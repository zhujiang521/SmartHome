package com.zj.smart.widget

import android.content.Context
import androidx.datastore.core.CorruptionException
import androidx.datastore.core.DataStore
import androidx.datastore.core.Serializer
import androidx.datastore.dataStore
import androidx.datastore.dataStoreFile
import androidx.glance.state.GlanceStateDefinition
import kotlinx.serialization.SerializationException
import kotlinx.serialization.json.Json
import java.io.File
import java.io.InputStream
import java.io.OutputStream

/**
 * Provides our own definition of "Glance state" using Kotlin serialization.
 */
object WeatherInfoStateDefinition : GlanceStateDefinition<WeatherInfo> {

    private const val DATA_STORE_FILENAME = "weatherInfo"

    /**
     * Use the same file name regardless of the widget instance to share data between them
     *
     * If you need different state/data for each instance, create a store using the provided fileKey
     */
    private val Context.datastore by dataStore(DATA_STORE_FILENAME, WeatherInfoSerializer)

    override suspend fun getDataStore(context: Context, fileKey: String): DataStore<WeatherInfo> {
        return context.datastore
    }

    override fun getLocation(context: Context, fileKey: String): File {
        return context.dataStoreFile(DATA_STORE_FILENAME)
    }

    /**
     * Custom serializer for WeatherInfo using Json.
     */
    object WeatherInfoSerializer : Serializer<WeatherInfo> {
        override val defaultValue = WeatherInfo.Unavailable("no place found")

        override suspend fun readFrom(input: InputStream): WeatherInfo = try {
//            Json.decodeFromString(
//                WeatherInfo.serializer(),
//                input.readBytes().decodeToString()
//            )
            WeatherInfo.Loading
        } catch (exception: SerializationException) {
            throw CorruptionException("Could not read weather data: ${exception.message}")
        }

        override suspend fun writeTo(t: WeatherInfo, output: OutputStream) {
            output.use {
//                it.write(
//                    Json.encodeToString(WeatherInfo.serializer(), t).encodeToByteArray()
//                )
            }
        }
    }
}