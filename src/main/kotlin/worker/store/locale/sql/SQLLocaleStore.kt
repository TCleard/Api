package worker.store.locale.sql

import worker.store.locale.LocaleStore
import java.sql.Connection
import java.sql.DriverManager

class SQLLocaleStore : LocaleStore() {

    init {

        SQLUtils.configure()

        userModule = SQLLocaleUserModule()
        tokenModule = SQLLocaleTokenModule()

    }

}