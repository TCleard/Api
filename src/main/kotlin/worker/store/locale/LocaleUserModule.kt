package worker.store.locale

import model.UserModel

interface LocaleUserModule {

    fun getUser(userId: Int): UserModel?
    fun getUser(userName: String, password: String): UserModel?

    fun isUserNameAlreadyUsed(userName: String): Boolean

    fun addUser(user: UserModel): UserModel?

    fun editUser(userId: Int, userFirstName: String?, userLastName: String?): UserModel?

}