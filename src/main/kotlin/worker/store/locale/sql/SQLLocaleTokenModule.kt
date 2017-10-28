package worker.store.locale.sql

import model.TokenModel
import worker.store.locale.LocaleTokenModule

class SQLLocaleTokenModule : LocaleTokenModule {

    init {
        SQLTokenUtils.createTable()
    }

    override fun addToken(token: TokenModel) {

        SQLTokenUtils.addToken(
                accessToken = token.access,
                refreshToken = token.refresh,
                scope = token.scope.value,
                userId = token.userId
        )

    }

    override fun getToken(access: String): TokenModel? = SQLTokenUtils.getToken(access)

}