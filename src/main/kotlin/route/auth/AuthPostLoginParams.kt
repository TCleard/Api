package route.auth

import model.ScopeModel
import org.jetbrains.ktor.util.ValuesMap
import tools.ParamValidator

data class AuthPostLoginParams(
        val userName: String,
        val password: String,
        val scope: ScopeModel
) {

    companion object {

        private const val USER_NAME = "username"
        private const val PASSWORD = "password"
        private const val SCOPE = "scope"


        fun validate(map: ValuesMap): ParamValidator<AuthPostLoginParams> {

            val userName = map[USER_NAME]
            val password = map[PASSWORD]
            val scopeValue = map[SCOPE]

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