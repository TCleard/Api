package tools

import extensions.respondError
import model.TokenModel
import org.jetbrains.ktor.application.ApplicationCall
import org.jetbrains.ktor.util.ValuesMap
import tools.error.InvalidTokenError
import tools.error.MissingTokenError
import worker.TokenWorker

object TokenRetriever {

    private val HEADER_AUTHORIZATION = "Authorization"
    private val TOKEN_TYPE = "Bearer"

    // 1 hour
    private val TOKEN_VALIDITY_DURATION = 60 * 60 * 1000

    private fun hasToken(headers: ValuesMap): Boolean
            = headers.contains(HEADER_AUTHORIZATION)

    private fun getToken(headers: ValuesMap): TokenModel? {

        headers[HEADER_AUTHORIZATION]
                ?.split(delimiters = " ")
                ?.let { authorizationCouple ->

                    if (authorizationCouple.size == 2) {

                        val tokenType = authorizationCouple.elementAtOrNull(index = 0)
                        val accessToken = authorizationCouple.elementAtOrNull(index = 1)

                        if (tokenType == TOKEN_TYPE && accessToken != null) {
                            return TokenWorker.getToken(accessToken = accessToken)
                        }

                    }

                }

        return null

    }

    suspend fun retrieveToken(call: ApplicationCall): TokenModel? {

        val headers = call.request.headers

        if (TokenRetriever.hasToken(headers)) {

            val token = TokenRetriever.getToken(headers)

            if (token != null
                    && token.isValid
                    && System.currentTimeMillis() - token.createdAt < TOKEN_VALIDITY_DURATION) {
                return token
            } else {
                // Invalid Token
                call.respondError(InvalidTokenError())
            }

        } else {
            // Missing Token
            call.respondError(MissingTokenError())
        }

        return null

    }

}