package tools.error

import com.google.gson.JsonObject
import org.jetbrains.ktor.http.HttpStatusCode

abstract class JsonError {

    private val CODE = "code"
    private val ERROR = "error"
    private val MESSAGE = "message"

    abstract val statusCode: HttpStatusCode
    abstract val error: String
    protected abstract val message: String

    fun print(): String {

        val json = JsonObject()

        json.addProperty(CODE, statusCode.value)
        json.addProperty(ERROR, error)
        json.addProperty(MESSAGE, message)

        return json.toString()

    }

}