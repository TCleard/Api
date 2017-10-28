package worker.store.locale.sql

import java.sql.Connection
import java.sql.DriverManager
import java.sql.ResultSet

object SQLUtils {

    private var connection: Connection? = null

    fun configure() {

        try {

            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/api", "geckoz", "test")


        } catch (e: Exception) {

            e.printStackTrace()

        }

    }

    fun execute(sql: String): Boolean? = connection?.createStatement()?.execute(sql)

    fun executeQuery(sql: String): ResultSet? = connection?.createStatement()?.executeQuery(sql)

    fun executeUpdate(sql: String): Int? = connection?.createStatement()?.executeUpdate(sql)

}