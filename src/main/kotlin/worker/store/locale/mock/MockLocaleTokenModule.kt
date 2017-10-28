package worker.store.locale.mock

import model.TokenModel
import worker.store.locale.LocaleTokenModule

class MockLocaleTokenModule : LocaleTokenModule {

    override fun addToken(token: TokenModel) {

        val userId = token.userId

        MockDB.tokens
                .filter { it.userId == userId }
                .forEach { it.isValid = false }

        MockDB.tokens.add(token)

    }

    override fun getToken(access: String): TokenModel?
            = MockDB.tokens.firstOrNull { it.access == access }

}