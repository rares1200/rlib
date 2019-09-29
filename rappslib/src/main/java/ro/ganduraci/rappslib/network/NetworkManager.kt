package ro.ganduraci.rappslib.network

import android.util.Log
import okhttp3.RequestBody.Companion.toRequestBody
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import org.json.JSONObject
import okhttp3.OkHttpClient
import org.json.JSONException
import ro.ganduraci.rappslib.base.BaseApplication
import java.io.IOException


object NetworkManager {

    const val TAG = "NetworkManager"

    val JSON = "application/json; charset=utf-8".toMediaTypeOrNull()

    private var baseURL: String = ""
    private val networkClient: OkHttpClient = OkHttpClient()

    fun setBaseUrl(url: String) {
        baseURL = url
    }

    fun makeRequest(endpoint: String, method: String): RequestResult {
        return makeRequest(endpoint, method, null)
    }

    fun makeRequest(endpoint: String, method: String, params: JSONObject?) : RequestResult{
        if (BaseApplication.getInstance()?.hasInternetConnection() == false) {
            return RequestResult(null, RequestError(RequestError.NETWORK_ERROR, "No internet connection", ""))
        }
        if (baseURL.isEmpty()) Log.e(TAG, "WARNING!!! Base URL not set")
        val requestUrl = baseURL + endpoint
        val reqData = Request.getRequestData(method, params)
        val reqId = Request.requestId
        Log.i(
            TAG, "Making request with id=[$reqId]" +
            " on endpoint:$requestUrl with data:\n$reqData")
        val body = reqData.toString().toRequestBody(JSON)
        val request = okhttp3.Request.Builder()
            .url(requestUrl)
            .post(body)
            .build()
        var result: JSONObject
        try {
            val response = networkClient.newCall(request).execute()
            val stringResult = response.body?.string()?:""
            Log.i(TAG, "Response for request with id=[$reqId]\n$stringResult")
            result = if (stringResult.isEmpty()) {
                JSONObject()
            } else {
                try {
                    JSONObject(stringResult)
                } catch (ex: JSONException) {
                    Log.e(TAG, "Could not parse response:${ex.message}")
                    JSONObject()
                }
            }
        } catch (ex: IOException) {
            ex.printStackTrace()
            Log.e(TAG, "Network IO exception:${ex.message}")
            result = JSONObject()
        }
        return getRequestResult(result)
    }

    private const val JKEY_RESULT = "result"
    private const val JKEY_ERROR = "error"
    private const val JKEY_CODE = "code"
    private const val JKEY_MESSAGE = "message"
    private const val JKEY_DATA = "data"

    private fun getRequestResult(response: JSONObject): RequestResult {
        if (response.has(JKEY_ERROR)) {
            val error = response.optJSONObject(JKEY_ERROR)
            var code = RequestError.UNKNOWN_ERROR
            var message = ""
            var data = ""
            if (error != null) {
                code = error.optInt(JKEY_CODE, RequestError.UNKNOWN_ERROR)
                message = error.optString(JKEY_MESSAGE)
                data = error.optString(JKEY_DATA)
            }
            return RequestResult(null, RequestError(code, message,data))
        }
        val result = response.opt(JKEY_RESULT)
        return RequestResult(result, null)
    }

}