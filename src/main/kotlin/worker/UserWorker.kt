package worker

import extensions.hashPassword
import model.UserModel
import tools.Injector
import worker.store.locale.LocaleUserModule

object UserWorker {

    private val localeUserModule: LocaleUserModule by lazy {
        Injector.localeStore.userModule
    }

    fun login(userName: String, password: String): UserModel?
            = localeUserModule.getUser(userName = userName, password = password.hashPassword())

    fun createUser(userName: String, password: String, firstName: String, lastName: String): UserModel
            = localeUserModule.addUser(
            UserModel(
                    userName = userName,
                    password = password.hashPassword(),
                    firstName = firstName,
                    lastName = lastName)
    )

    fun isUserNameAlreadyUsed(userName: String): Boolean
            = localeUserModule.isUserNameAlreadyUsed(userName = userName)

    fun getUser(userId: Int): UserModel?
            = localeUserModule.getUser(userId)


}