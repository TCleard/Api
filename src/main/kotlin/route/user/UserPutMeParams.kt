package route.user

import extensions.formParams
import extensions.getParams
import org.jetbrains.ktor.application.ApplicationCall
import tools.ParamValidator
import tools.formater.UserFormater

data class UserPutMeParams(
        val userId: Int,
        var userFirstName: String? = null,
        var userLastName: String? = null
) {

    companion object {

        private val USER_ID = UserFormater.ID
        private val FIRST_NAME = UserFormater.FIRST_NAME
        private val LAST_NAME = UserFormater.LAST_NAME

        suspend fun validate(call: ApplicationCall): ParamValidator<UserPutMeParams> {

            val userId = call.getParams()[USER_ID]

            val meValidator = ParamValidator<UserPutMeParams>()

            if (userId != null) {

                if (userId.toIntOrNull() != null) {

                    val putParams = UserPutMeParams(userId = userId.toInt())

                    val form = call.formParams()

                    form[FIRST_NAME]?.let { firstName ->
                        putParams.userFirstName = firstName
                    }

                    form[LAST_NAME]?.let { lastName ->
                        putParams.userLastName = lastName
                    }

                    meValidator.params = putParams

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