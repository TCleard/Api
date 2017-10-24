package tools.error

import org.jetbrains.ktor.http.HttpStatusCode

class MissingTokenError : JsonError() {

    override val statusCode: HttpStatusCode = HttpStatusCode.Unauthorized
    override val error: String = "missing_token"
    override val message: String = "You need a token to call this resource. Please log in"

}