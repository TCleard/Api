package tools.error

import org.jetbrains.ktor.http.HttpStatusCode

class BadRequestError : JsonError() {

    override val statusCode: HttpStatusCode = HttpStatusCode.BadRequest
    override val error: String = "bad_request"
    override val message: String = "The server coulnd't handle this request"

}