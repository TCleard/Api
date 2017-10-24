package route.auth

import extensions.postParams
import extensions.respondError
import extensions.respondObject
import org.jetbrains.ktor.application.ApplicationCall
import org.jetbrains.ktor.application.call
import org.jetbrains.ktor.http.ContentType
import org.jetbrains.ktor.response.respondText
import org.jetbrains.ktor.routing.Route
import org.jetbrains.ktor.routing.post
import tools.ParamValidator
import tools.error.AlreadyExists
import tools.error.InvalidParamError
import tools.error.NotFoundError
import worker.TokenWorker
import worker.UserWorker

object AuthRouting {

    /** PATHS **/
    private val PATH_LOGIN = "/token/"
    private val PATH_SIGN_UP = "/register/"

    fun configure(route: Route) {

        route.post(path = "${PATH_LOGIN}") {

            val loginValidator = call.validateLogin()

            if (loginValidator.params != null) {

                val loginParams = loginValidator.params!!

                val user = UserWorker.login(
                        userName = loginParams.userName,
                        password = loginParams.password
                )

                if (user != null) {

                    // User exists

                    val token = TokenWorker.createToken(
                            userId = user.id,
                            scope = loginParams.scope
                    )

                    call.respondObject(token)

                } else {
                    call.respondError(NotFoundError())
                }

            } else {
                call.respondError(InvalidParamError(loginValidator))
            }

        }

        route.post(path = "$PATH_SIGN_UP") {

            val signUpValidator = call.validateSignUp()

            if (signUpValidator.params != null) {

                val signUpParams = signUpValidator.params!!

                if (!UserWorker.isUserNameAlreadyUsed(signUpParams.userName)) {

                    val user = UserWorker.createUser(
                            userName = signUpParams.userName,
                            password = signUpParams.password,
                            firstName = signUpParams.firstName,
                            lastName = signUpParams.lastName
                    )

                    call.respondObject(user)

                } else {

                    // UserName already exists

                    call.respondError(AlreadyExists(signUpParams.userName))
                }

            } else {
                call.respondError(InvalidParamError(signUpValidator))
            }

        }

    }

    suspend private fun ApplicationCall.validateLogin(): ParamValidator<AuthPostLoginParams>
            = AuthPostLoginParams.validate(postParams())

    suspend private fun ApplicationCall.validateSignUp(): ParamValidator<AuthPostSignUpParams>
            = AuthPostSignUpParams.validate(postParams())

}