package org.lico.core.network

import android.net.ParseException
import android.util.Log
import android.util.MalformedJsonException
import com.google.gson.JsonParseException
import org.json.JSONException
import retrofit2.HttpException
import java.net.ConnectException

/**
 * @author: lzp
 * @create：2020/5/25
 * @describe：
 */
object ExceptionHandle {

    fun handleException(e: Throwable): ResponseThrowable {
        var ex: ResponseThrowable? = null
        if (e is HttpException) {
            ex = ResponseThrowable(ERROR.HTTP_ERROR, e)
        } else if (e is JsonParseException
            || e is JSONException
            || e is ParseException || e is MalformedJsonException
        ) {
            ex = ResponseThrowable(ERROR.PARSE_ERROR, e)
        } else if (e is ConnectException) {
            ex = ResponseThrowable(ERROR.NETWORD_ERROR, e)
        } else if (e is javax.net.ssl.SSLException) {
            ex = ResponseThrowable(ERROR.SSL_ERROR, e)
        } else if (e is java.net.SocketTimeoutException) {
            ex = ResponseThrowable(ERROR.TIMEOUT_ERROR, e)
        } else if (e is java.net.UnknownHostException) {
            ex = ResponseThrowable(ERROR.TIMEOUT_ERROR, e)
        } else {
            try {
                ex = if (e.message.isNullOrEmpty()) ResponseThrowable(1000, e.message!!, e)
                else ResponseThrowable(ERROR.UNKNOWN, e)
            } catch (e: Exception) {
                e.printStackTrace()
            }
            if (ex == null) ex = ResponseThrowable(ERROR.UNKNOWN, e)

        }
        return ex
    }
}