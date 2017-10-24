package tools

import worker.store.locale.LocaleStore
import worker.store.locale.mock.MockLocaleStore

object Injector {

    lateinit var localeStore : LocaleStore

    fun configure() {

        localeStore = MockLocaleStore()

    }

}