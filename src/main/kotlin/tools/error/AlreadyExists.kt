package tools.error

import org.jetbrains.ktor.http.HttpStatusCode

class AlreadyExists(val resourceName : String) : JsonError() {

    override val statusCode: HttpStatusCode = HttpStatusCode.Conflict
    override val error: String = "already_exists"
    override val message: String = "$resourceName already exists"

}