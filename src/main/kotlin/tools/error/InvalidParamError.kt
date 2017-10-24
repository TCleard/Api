package tools.error

import org.jetbrains.ktor.http.HttpStatusCode
import tools.ParamValidator

class InvalidParamError(private val paramValidator: ParamValidator<*>) : JsonError() {

    override val statusCode: HttpStatusCode = HttpStatusCode.Forbidden
    override val error: String = "invalid_params"
    override val message: String
        get() {
            var message = "Those params are missing/invalid :"

            paramValidator.errors.forEach {
                message += " ${it.first} (${it.second})"
            }

            return message
        }

}