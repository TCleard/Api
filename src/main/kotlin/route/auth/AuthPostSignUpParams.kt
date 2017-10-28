package route.auth

import extensions.formParams
import model.ScopeModel
import org.jetbrains.ktor.application.ApplicationCall
import org.jetbrains.ktor.application.ApplicationRequest
import org.jetbrains.ktor.util.ValuesMap
import tools.ParamValidator
import tools.formater.UserFormater

data class AuthPostSignUpParams(
        val userName: String,
        val password: String,
        val firstName: String,
        val lastName: String
) {

    companion object {

        private val USER_NAME = UserFormater.USER_NAME
        private val PASSWORD = UserFormater.PASSWORD
        private val FIRST_NAME = UserFormater.FIRST_NAME
        private val LAST_NAME = UserFormater.LAST_NAME


        suspend fun validate(call: ApplicationCall): ParamValidator<AuthPostSignUpParams> {

            val form = call.formParams()

            val userName = form[USER_NAME]
            val password = form[PASSWORD]
            val firstName = form[FIRST_NAME]
            val lastName = form[LAST_NAME]

            val signUpValidator = ParamValidator<AuthPostSignUpParams>()

            if (userName != null && password != null
                    && firstName != null && lastName != null) {

                signUpValidator.params = AuthPostSignUpParams(
                        userName = userName,
                        password = password,
                        firstName = firstName,
                        lastName = lastName
                )

            } else {

                if (userName == null) {
                    signUpValidator.errors.add(Pair(USER_NAME, ParamValidator.Error.MISSING))
                }
                if (password == null) {
                    signUpValidator.errors.add(Pair(PASSWORD, ParamValidator.Error.MISSING))
                }
                if (firstName == null) {
                    signUpValidator.errors.add(Pair(FIRST_NAME, ParamValidator.Error.MISSING))
                }
                if (lastName == null) {
                    signUpValidator.errors.add(Pair(LAST_NAME, ParamValidator.Error.MISSING))
                }

            }

            return signUpValidator

        }

    }

}