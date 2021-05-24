package data.utils

import java.io.BufferedReader

class ConvertJson {
    fun readResourceAsString(fileName: String): String {
        val classLoader = Thread.currentThread().contextClassLoader!!

        try {
            val inputStream = classLoader.getResourceAsStream(fileName)
            return inputStream.bufferedReader().use(BufferedReader::readText)
        } catch (th: Throwable) {
            print("erreur lecture du fichier")
            return "toto"
        }
    }
}