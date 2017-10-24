package worker.store.locale.mock

import model.UserModel
import worker.store.locale.LocaleUserModule

class MockLocaleUserModule : LocaleUserModule {

    override fun getUser(userId: Int): UserModel?
            = MockDB.users.firstOrNull { it.id == userId }

    override fun getUser(userName: String, password: String): UserModel?
            = MockDB.users.firstOrNull { it.userName == userName && it.password == password }

    override fun isUserNameAlreadyUsed(userName: String): Boolean
            = MockDB.users.any { it.userName == userName }

    override fun addUser(user: UserModel): UserModel {

        // AutoIncrement des enfers
        user.id = MockDB.users.map { it.id }.max()?.plus(1) ?: 1

        MockDB.users.add(user)

        return user

    }

}