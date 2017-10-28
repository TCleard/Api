package route.auth

import extensions.formParams
import model.ScopeModel
import org.jetbrains.ktor.application.ApplicationCall
import org.jetbrains.ktor.application.ApplicationRequest
import org.jetbrains.ktor.util.ValuesMap
import tools.ParamValidator
import tools.formater.TokenFormater
import tools.formater.UserFormater

data class AuthPostLoginParams(
        val userName: String,
        val password: String,
        val scope: ScopeModel
) {

    companion object {

        private val USER_NAME = UserFormater.USER_NAME
        private val PASSWORD = UserFormater.PASSWORD
        private val SCOPE = TokenFormater.SCOPE


        suspend fun validate(call: ApplicationCall): ParamValidator<AuthPostLoginParams> {

            val form = call.formParams()

            val userName = form[USER_NAME]
            val password = form[PASSWORD]
            val scopeValue = form[SCOPE]

            val scope = ScopeModel.fromValue(scopeValue)

            val loginValidator = ParamValidator<AuthPostLoginParams>()

            if (userName != null && password != null && scope != null) {

                loginValidator.params = AuthPostLoginParams(
                        userName = userName,
                        password = password,
                        scope = scope
                )

            } else {

                if (userName == null) {
                    loginValidator.errors.add(Pair(USER_NAME, ParamValidator.Error.MISSING))
                }
                if (password == null) {
                    loginValidator.errors.add(Pair(PASSWORD, ParamValidator.Error.MISSING))
                }
                if (scopeValue == null) {
                    loginValidator.errors.add(Pair(SCOPE, ParamValidator.Error.MISSING))
                } else if (scope == null) {
                    loginValidator.errors.add(Pair(SCOPE, ParamValidator.Error.UNKNOWN))
                }

            }

            return loginValidator

        }

    }

}