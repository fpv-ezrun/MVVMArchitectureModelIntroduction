package com.mindorks.framework.mvvm.testUtils

import android.annotation.SuppressLint
import org.json.JSONArray
import org.json.JSONObject
import java.io.BufferedReader
import java.nio.file.NoSuchFileException

fun readResourceAsJsonObj(fileName: String) = JSONObject(readResourceAsString(fileName))
fun readResourceAsJsonArray(fileName: String) = JSONArray(readResourceAsString(fileName))

fun readResourceAsString(fileName: String) : String
{
    val classLoader = Thread.currentThread().contextClassLoader!!

    try
    {
        val inputStream = classLoader.getResourceAsStream(fileName)
        return inputStream.bufferedReader().use(BufferedReader::readText)
    }
    catch(th: Throwable)
    {
        th.printStackTrace()
        throw NoSuchFileException(fileName)
    }
}