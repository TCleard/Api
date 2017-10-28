package model

data class TokenModel(
        val access: String,
        val refresh: String,
        val scope: ScopeModel,
        val createdAt: Long,
        val userId: Int,
        var isValid: Boolean
)