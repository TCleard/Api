package worker.store.locale

import model.TokenModel

interface LocaleTokenModule {

    fun addToken(token: TokenModel)

    fun getToken(access: String): TokenModel?

}