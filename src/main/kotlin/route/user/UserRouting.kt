package route.user

import extensions.getParams
import extensions.headers
import extensions.respondError
import extensions.respondObject
import org.jetbrains.ktor.application.ApplicationCall
import org.jetbrains.ktor.application.call
import org.jetbrains.ktor.http.ContentType
import org.jetbrains.ktor.http.HttpStatusCode
import org.jetbrains.ktor.response.respondText
import org.jetbrains.ktor.routing.Route
import org.jetbrains.ktor.routing.get
import route.auth.UserGetMeParams
import tools.ParamValidator
import tools.TokenRetriever
import tools.error.BadRequestError
import tools.error.NotFoundError
import tools.error.UnauthorizedError
import worker.UserWorker

object UserRouting {

    /** PATHS **/
    private val PATH_USER = "/users/"

    /** PARAMS **/
    private val PARAM_GET_USERS_ID = "id"

    fun configure(route: Route) {

        route.get(path = "$PATH_USER{$PARAM_GET_USERS_ID}") {

            TokenRetriever.retrieveToken(call)?.let { token ->

                val userValidator = call.validateUser()

                if (userValidator.params != null) {

                    val userId = userValidator.params!!.userId

                    if (token.userId == userId) {

                        val user = UserWorker.getUser(userId)

                        if (user != null) {
                            call.respondObject(user)
                        } else {
                            // User not found
                            call.respondError(NotFoundError())
                        }

                    } else {
                        // Not Allowed to retrieve a different user that
                        // the one connected
                        call.respondError(UnauthorizedError())
                    }

                } else {
                    // Invalid Params
                    call.respondError(BadRequestError())
                }

            }

        }

    }

    suspend private fun ApplicationCall.validateUser(): ParamValidator<UserGetMeParams>
            = UserGetMeParams.validate(getParams())

}