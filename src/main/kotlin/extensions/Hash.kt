package extensions

import java.security.MessageDigest

private val SHA_512 = "SHA-512"

fun String.hashPassword(): String {

    val HEX_CHARS = "0123456789ABCDEF"

    val bytes = MessageDigest
            .getInstance(SHA_512)
            .digest(toByteArray())

    val result = StringBuilder(bytes.size * 2)

    bytes.forEach {
        val i = it.toInt()
        result.append(HEX_CHARS[i shr 4 and 0x0f])
        result.append(HEX_CHARS[i and 0x0f])
    }

    return result.toString()

}