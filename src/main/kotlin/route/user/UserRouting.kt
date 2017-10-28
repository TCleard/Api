package route.user

import extensions.respondError
import extensions.respondObject
import org.jetbrains.ktor.application.ApplicationCall
import org.jetbrains.ktor.application.call
import org.jetbrains.ktor.routing.Route
import org.jetbrains.ktor.routing.get
import org.jetbrains.ktor.routing.put
import route.auth.UserGetMeParams
import tools.ParamValidator
import tools.TokenRetriever
import tools.error.BadRequestError
import tools.error.NotFoundError
import tools.error.UnauthorizedError
import tools.formater.UserFormater
import worker.UserWorker

object UserRouting {

    /** PATHS **/
    private val PATH_USER = "/users/"

    /** PARAMS **/
    private val PARAM_GET_USER_ID = "id"

    private val PARAM_PUT_USER_ID = "id"

    fun configure(route: Route) {

        route.get(path = "$PATH_USER{$PARAM_GET_USER_ID}") {

            TokenRetriever.retrieveToken(call)?.let { token ->

                val userValidator = call.validateGetUser()

                if (userValidator.params != null) {

                    val userId = userValidator.params!!.userId

                    if (token.userId == userId) {

                        val user = UserWorker.getUser(userId)

                        if (user != null) {
                            call.respondObject(UserFormater.format(user))
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

        route.put(path = "$PATH_USER{$PARAM_PUT_USER_ID}") {

            TokenRetriever.retrieveToken(call)?.let { token ->

                val userValidator = call.validatePutUser()

                if (userValidator.params != null) {

                    val userId = userValidator.params!!.userId

                    if (token.userId == userId) {

                        if (UserWorker.getUser(userId) != null) {

                            val user = UserWorker.editUser(
                                    userId = userId,
                                    userFirstName = userValidator.params?.userFirstName,
                                    userLastName = userValidator.params?.userLastName)

                            if (user != null) {
                                call.respondObject(UserFormater.format(user))
                            } else {

                                // Error while editing user
                                call.respondError(BadRequestError())

                            }

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

    suspend private fun ApplicationCall.validateGetUser(): ParamValidator<UserGetMeParams>
            = UserGetMeParams.validate(this)

    suspend private fun ApplicationCall.validatePutUser(): ParamValidator<UserPutMeParams>
            = UserPutMeParams.validate(this)

}