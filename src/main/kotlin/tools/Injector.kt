package tools

import worker.store.locale.LocaleStore
import worker.store.locale.mock.MockLocaleStore
import worker.store.locale.sql.SQLLocaleStore

object Injector {

    lateinit var localeStore : LocaleStore

    fun configure() {

        localeStore = SQLLocaleStore()

    }

}