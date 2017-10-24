package worker

import model.ScopeModel
import model.TokenModel
import tools.Injector
import worker.store.locale.LocaleTokenModule
import java.util.*

object TokenWorker {

    private val localeTokenModule: LocaleTokenModule by lazy {
        Injector.localeStore.tokenModule
    }

    fun createToken(userId: Int, scope: ScopeModel): TokenModel {

        val token = TokenModel(
                access = UUID.randomUUID().toString(),
                refresh = UUID.randomUUID().toString(),
                createdAt = System.currentTimeMillis(),
                scope = scope,
                userId = userId,
                isValid = true
        )

        localeTokenModule.addToken(token)

        return token

    }

    fun getToken(accessToken: String): TokenModel?
            = localeTokenModule.getToken(access = accessToken)

}