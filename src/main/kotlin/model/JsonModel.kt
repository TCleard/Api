package model

import com.google.gson.JsonObject

abstract class JsonModel  {

    protected abstract fun toJson(): JsonObject

    fun print(): String = toJson().toString()

}