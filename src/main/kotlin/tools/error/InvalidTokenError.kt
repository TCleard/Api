package tools.error

import org.jetbrains.ktor.http.HttpStatusCode

class InvalidTokenError : JsonError() {

    override val statusCode: HttpStatusCode = HttpStatusCode.Unauthorized
    override val error: String = "invalid_token"
    override val message: String = "This token is invalid or has expired. Please log in"

}