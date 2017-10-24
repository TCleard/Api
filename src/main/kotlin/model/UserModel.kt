package model

import com.google.gson.JsonObject

data class UserModel(
        var id: Int = 1,
        val userName: String = "",
        val password: String = "",
        var firstName: String = "",
        var lastName: String = ""
) : JsonModel() {

    companion object {

        val ID = "id"
        val USER_NAME = "uesrName"
        val FIRST_NAME = "firstName"
        val LAST_NAME = "lastName"

    }

    override fun toJson(): JsonObject {

        val json = JsonObject()

        json.addProperty(ID, id)
        json.addProperty(USER_NAME, userName)
        json.addProperty(FIRST_NAME, firstName)
        json.addProperty(LAST_NAME, lastName)

        return json

    }

}