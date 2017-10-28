package worker.store.locale.sql

import model.UserModel

object SQLUserUtils {

    val TABLE_NAME = "user"

    val KEY_ID = "id"
    val KEY_USER_NAME = "username"
    val KEY_PASSWORD = "password"
    val KEY_FIRST_NAME = "firstname"
    val KEY_LAST_NAME = "lastname"
    val KEY_CREATED_AT = "created_at"
    val KEY_UPDATED_AT = "updated_at"

    fun createTable() {

        SQLUtils.execute("CREATE TABLE IF NOT EXISTS $TABLE_NAME(" +
                "$KEY_ID SMALLINT UNSIGNED NOT NULL AUTO_INCREMENT, " +
                "$KEY_USER_NAME VARCHAR(40) NOT NULL, " +
                "$KEY_PASSWORD VARCHAR(255) NOT NULL, " +
                "$KEY_FIRST_NAME VARCHAR(40), " +
                "$KEY_LAST_NAME VARCHAR(40), " +
                "$KEY_CREATED_AT TIMESTAMP NOT NULL DEFAULT now(), " +
                "$KEY_UPDATED_AT TIMESTAMP NOT NULL DEFAULT now() ON UPDATE now(), " +
                "PRIMARY KEY ($KEY_ID) " +
                ");")

    }

    fun addUser(userName: String, password: String, firstName: String, lastName: String): Int? =
            SQLUtils.executeUpdate("INSERT INTO $TABLE_NAME" +
                    "($KEY_USER_NAME, $KEY_PASSWORD, $KEY_FIRST_NAME, $KEY_LAST_NAME)" +
                    "VALUES " +
                    "('$userName', '$password', '$firstName', '$lastName');")
                    ?.let {
                        getUser(userName, password)?.id
                    }

    fun editUser(userId: Int, userFirstName: String?, userLastName: String?): UserModel? {

        var queries = arrayListOf<String>()

        userFirstName?.let {
            queries.add("$KEY_FIRST_NAME='$it'")
        }
        userLastName?.let {
            queries.add("${KEY_LAST_NAME}='$it'")
        }

        if (queries.isNotEmpty()) {

            val query = queries.joinToString(prefix = "", separator = ", ", postfix = " ")

            return SQLUtils.executeUpdate("UPDATE $TABLE_NAME SET " +
                    "$query" +
                    "WHERE $KEY_ID=$userId")
                    ?.let {
                        getUser(userId)
                    }

        } else {

            return getUser(userId)

        }

    }


    fun getUser(userId: Int): UserModel? =
            SQLUtils.executeQuery("SELECT * " +
                    "FROM $TABLE_NAME " +
                    "WHERE $KEY_ID=$userId")
                    ?.let { result ->

                        if (result.first()) {

                            UserModel(
                                    id = result.getInt(KEY_ID),
                                    userName = result.getString(KEY_USER_NAME),
                                    firstName = result.getString(KEY_FIRST_NAME),
                                    lastName = result.getString(KEY_LAST_NAME)
                            )

                        } else {
                            null
                        }

                    }

    fun getUser(userName: String, password: String): UserModel? =
            SQLUtils.executeQuery("SELECT * " +
                    "FROM $TABLE_NAME " +
                    "WHERE $KEY_USER_NAME LIKE '$userName' AND $KEY_PASSWORD LIKE '$password'")
                    ?.let { result ->

                        if (result.first()) {

                            UserModel(
                                    id = result.getInt(KEY_ID),
                                    userName = result.getString(KEY_USER_NAME),
                                    firstName = result.getString(KEY_FIRST_NAME),
                                    lastName = result.getString(KEY_LAST_NAME)
                            )

                        } else {
                            null
                        }

                    }

    fun getUserCount(userName: String?): Int {

        var query = "SELECT * FROM $TABLE_NAME"

        userName?.let {
            query += " WHERE $KEY_USER_NAME LIKE '$it'"
        }

        SQLUtils.executeQuery(query)?.let { result ->

            if (result.last()) {
                return result.row
            }

        }

        return 0

    }

}