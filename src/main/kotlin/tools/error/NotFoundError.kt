package tools.error

import org.jetbrains.ktor.http.HttpStatusCode

class NotFoundError : JsonError() {

    override val statusCode: HttpStatusCode = HttpStatusCode.NotFound
    override val error: String = "not_found"
    override val message: String = "This resource doesn't exist"

}