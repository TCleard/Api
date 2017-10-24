package model

import com.google.gson.JsonObject

data class TokenModel(
        val access: String,
        val refresh: String,
        val scope: ScopeModel,
        val createdAt: Long,
        val userId: Int,
        var isValid: Boolean
) : JsonModel() {

    companion object {

        private val ACCESS = "access_token"
        private val REFRESH = "refresh_token"
        private val SCOPE = "scope"

    }

    override fun toJson(): JsonObject {

        val json = JsonObject()

        json.addProperty(ACCESS, access)
        json.addProperty(REFRESH, refresh)
        json.addProperty(SCOPE, scope.value)

        return json

    }

}