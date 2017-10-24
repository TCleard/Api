package model

enum class ScopeModel(val value: String) {

    CLIENT("client");

    companion object {

        fun fromValue(value: String?): ScopeModel? {

            values().forEach {

                if (it.value == value) {
                    return it
                }

            }

            return null

        }

    }

}