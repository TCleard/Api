package route.auth

import org.jetbrains.ktor.util.ValuesMap
import tools.ParamValidator

data class UserGetMeParams(
        val userId: Int
) {

    companion object {

        private const val USER_ID = "id"

        fun validate(map: ValuesMap): ParamValidator<UserGetMeParams> {

            val userId = map[USER_ID]

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