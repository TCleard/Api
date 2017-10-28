package route.auth

import extensions.getParams
import org.jetbrains.ktor.application.ApplicationCall
import tools.ParamValidator
import tools.formater.UserFormater

data class UserGetMeParams(
        val userId: Int
) {

    companion object {

        private val USER_ID = UserFormater.ID

        suspend fun validate(call: ApplicationCall): ParamValidator<UserGetMeParams> {

            val userId = call.getParams()[USER_ID]

            val meValidator = ParamValidator<UserGetMeParams>()

            if (userId != null) {

                if (userId.toIntOrNull() != null) {
                    meValidator.params = UserGetMeParams(userId = userId.toInt())
                } else {
                    meValidator.errors.add(Pair(USER_ID, ParamValidator.Error.INVALID))
                }

            } else {
                meValidator.errors.add(Pair(USER_ID, ParamValidator.Error.MISSING))
            }

            return meValidator

        }

    }

}