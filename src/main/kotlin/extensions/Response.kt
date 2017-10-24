package extensions

import model.JsonModel
import org.jetbrains.ktor.application.ApplicationCall
import org.jetbrains.ktor.http.ContentType
import org.jetbrains.ktor.http.HttpStatusCode
import org.jetbrains.ktor.response.respondText
import tools.error.JsonError

suspend fun ApplicationCall.respondObject(obj: JsonModel, status: HttpStatusCode = HttpStatusCode.OK) {

    response.status(status)

    respondText(obj.print(), ContentType.Application.Json)

}

suspend fun ApplicationCall.respondError(error: JsonError) {

    response.status(error.statusCode)

    respondText(error.print(), ContentType.Application.Json)

}