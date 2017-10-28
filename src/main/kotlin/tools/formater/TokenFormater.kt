package tools.formater

import com.google.gson.JsonObject
import model.TokenModel

object TokenFormater {

    const val ACCESS = "access_token"
    const val REFRESH = "refresh_token"
    const val SCOPE = "scope"

    fun format(token: TokenModel): JsonObject {

        val json = JsonObject()

        json.addProperty(ACCESS, token.access)
        json.addProperty(REFRESH, token.refresh)
        json.addProperty(SCOPE, token.scope.value)

        return json

    }

}