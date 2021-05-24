package data.utils

open class UtilsFunctions<out T, in A1, in A2, in A3>(private val constructor: (A1, A2, A3) -> T) {
    @Volatile private var instance: T? = null
    fun getInstance(arg1: A1, arg2: A2, arg3: A3): T
    {
        return instance ?: synchronized(this) {
            instance ?: constructor(arg1, arg2, arg3).also { instance = it }
        }
    }
}