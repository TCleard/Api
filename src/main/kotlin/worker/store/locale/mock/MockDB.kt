package worker.store.locale.mock

import extensions.hashPassword
import model.TokenModel
import model.UserModel

object MockDB {

    val users = arrayListOf(
            UserModel(
                    id = 1,
                    firstName = "Tanguy",
                    lastName = "Cleard",
                    userName = "tanguy@yurplan.com",
                    password = "testtest".hashPassword()
            )
    )

    val tokens = arrayListOf<TokenModel>()

}