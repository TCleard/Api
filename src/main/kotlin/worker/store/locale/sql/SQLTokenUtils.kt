package worker.store.locale.sql

import model.ScopeModel
import model.TokenModel

object SQLTokenUtils {

    val TABLE_NAME = "token"

    val KEY_ACCESS_TOKEN = "access"
    val KEY_REFRESH_TOKEN = "refresh"
    val KEY_SCOPE = "scope"
    val KEY_USER_ID = "user_id"
    val KEY_IS_VALID = "is_valid"
    val KEY_CREATED_AT = "created_at"
    val KEY_UPDATED_AT = "updated_at"

    fun createTable() {

        SQLUtils.execute("CREATE TABLE IF NOT EXISTS $TABLE_NAME(" +
                "$KEY_ACCESS_TOKEN VARCHAR(255), " +
                "$KEY_REFRESH_TOKEN VARCHAR(255), " +
                "$KEY_SCOPE VARCHAR(255), " +
                "$KEY_CREATED_AT TIMESTAMP NOT NULL DEFAULT now(), " +
                "$KEY_UPDATED_AT TIMESTAMP NOT NULL DEFAULT now() ON UPDATE now(), " +
                "$KEY_USER_ID SMALLINT UNSIGNED, " +
                "$KEY_IS_VALID BOOLEAN DEFAULT TRUE, " +
                "FOREIGN KEY ($KEY_USER_ID) REFERENCES ${SQLUserUtils.TABLE_NAME}(${SQLUserUtils.KEY_ID})" +
                ");")

    }

    fun addToken(accessToken: String, refreshToken: String, scope: String, userId: Int) {

        SQLUtils.executeUpdate("UPDATE $TABLE_NAME SET $KEY_IS_VALID=FALSE " +
                "WHERE $KEY_USER_ID=$userId AND $KEY_IS_VALID=TRUE")

        SQLUtils.executeUpdate("INSERT INTO $TABLE_NAME($KEY_ACCESS_TOKEN, $KEY_REFRESH_TOKEN, $KEY_SCOPE, $KEY_USER_ID)" +
                "VALUES ('$accessToken', '$refreshToken', '$scope', '$userId');")

    }

    fun getToken(accessToken: String): TokenModel? =
            SQLUtils.executeQuery("SELECT * " +
                    "FROM $TABLE_NAME " +
                    "WHERE $KEY_ACCESS_TOKEN LIKE '$accessToken'")
                    ?.let { result ->

                        if (result.first()) {

                            val scope = ScopeModel.fromValue(result.getString(KEY_SCOPE))

                            if (scope != null) {
                                TokenModel(
                                        access = result.getString(KEY_ACCESS_TOKEN),
                                        refresh = result.getString(KEY_REFRESH_TOKEN),
                                        scope = scope,
                                        createdAt = result.getTimestamp(KEY_CREATED_AT).time,
                                        userId = result.getInt(KEY_USER_ID),
                                        isValid = result.getBoolean(KEY_IS_VALID)
                                )
                            } else {
                                null
                            }

                        } else {
                            null
                        }

                    }

}