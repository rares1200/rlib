package ro.ganduraci.rappslib.utils

import android.app.Activity
import android.content.Intent

object ShareUtils {


    fun shareUrl(activity: Activity, url: String, message: String) {
        val intent = Intent(Intent.ACTION_SEND).apply {
//            putExtra(Intent.EXTRA_TEXT, )
        }
    }

}