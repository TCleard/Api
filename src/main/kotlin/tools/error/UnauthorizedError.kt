package tools.error

import org.jetbrains.ktor.http.HttpStatusCode

class UnauthorizedError : JsonError() {

    override val statusCode: HttpStatusCode = HttpStatusCode.Unauthorized
    override val error: String = "unauthorized"
    override val message: String = "You are not authorized to see this resource"

}