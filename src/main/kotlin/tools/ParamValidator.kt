package tools

class ParamValidator<P> {

    var params: P? = null
    val errors = arrayListOf<Pair<String, Error>>()

    enum class Error {
        MISSING,
        INVALID,
        UNKNOWN
    }

    fun toError() : String {

        var error = "Couldn't perform request"

        errors.forEach {
            error += "\nparam \"${it.first}\" ${it.second}"
        }

        return error

    }

}