package tools.formater

import com.google.gson.JsonObject
import model.UserModel

object UserFormater {

    const val ID = "id"
    const val USER_NAME = "username"
    const val FIRST_NAME = "firstname"
    const val LAST_NAME = "lastname"
    const val PASSWORD = "password"

    fun format(user: UserModel): JsonObject {

        val json = JsonObject()

        json.addProperty(ID, user.id)
        json.addProperty(USER_NAME, user.userName)
        json.addProperty(FIRST_NAME, user.firstName)
        json.addProperty(LAST_NAME, user.lastName)

        return json

    }

}