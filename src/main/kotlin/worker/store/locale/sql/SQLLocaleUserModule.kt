package worker.store.locale.sql

import model.UserModel
import worker.store.locale.LocaleUserModule

class SQLLocaleUserModule : LocaleUserModule {

    init {
        SQLUserUtils.createTable()
    }

    override fun getUser(userId: Int): UserModel? = SQLUserUtils.getUser(userId)

    override fun getUser(userName: String, password: String): UserModel? = SQLUserUtils.getUser(userName, password)

    override fun isUserNameAlreadyUsed(userName: String): Boolean = SQLUserUtils.getUserCount(userName) > 0

    override fun addUser(user: UserModel): UserModel? =
            SQLUserUtils.addUser(
                    userName = user.userName,
                    password = user.password,
                    firstName = user.firstName,
                    lastName = user.lastName
            )?.let { id ->
                user.id = id

                user
            }

    override fun editUser(userId: Int, userFirstName: String?, userLastName: String?): UserModel? =
            SQLUserUtils.editUser(
                    userId = userId,
                    userFirstName = userFirstName,
                    userLastName = userLastName
            )

}