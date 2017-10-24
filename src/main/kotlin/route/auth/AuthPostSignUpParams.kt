package route.auth

import model.ScopeModel
import org.jetbrains.ktor.util.ValuesMap
import tools.ParamValidator

data class AuthPostSignUpParams(
        val userName: String,
        val password: String,
        val firstName: String,
        val lastName: String
) {

    companion object {

        private const val USER_NAME = "username"
        private const val PASSWORD = "password"
        private const val FIRST_NAME = "firstname"
        private const val LAST_NAME = "lastname"


        fun validate(map: ValuesMap): ParamValidator<AuthPostSignUpParams> {

            val userName = map[USER_NAME]
            val password = map[PASSWORD]
            val firstName = map[FIRST_NAME]
            val lastName = map[LAST_NAME]

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