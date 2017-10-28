package model

data class UserModel(
        var id: Int = 1,
        val userName: String = "",
        val password: String = "",
        var firstName: String = "",
        var lastName: String = ""
)