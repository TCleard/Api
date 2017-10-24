package worker.store.locale.mock

import worker.store.locale.LocaleStore

class MockLocaleStore : LocaleStore() {

    init {
        tokenModule = MockLocaleTokenModule()
        userModule = MockLocaleUserModule()
    }

}