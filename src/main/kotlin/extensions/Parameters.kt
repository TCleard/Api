package extensions

import org.jetbrains.ktor.application.ApplicationCall
import org.jetbrains.ktor.util.ValuesMap

suspend fun ApplicationCall.headers(): ValuesMap
        = request.headers

suspend fun ApplicationCall.formParams(): ValuesMap
        = request.receive(ValuesMap::class)

suspend fun ApplicationCall.getParams(): ValuesMap
        = parameters