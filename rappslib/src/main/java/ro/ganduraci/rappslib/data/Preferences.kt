package ro.ganduraci.rappslib.data

import android.content.Context
import android.content.SharedPreferences
import ro.ganduraci.rappslib.base.BaseApplication

fun Preferences.saveInt(key: String, value: Int) {
    preferences?.edit()?.putInt(key, value)?.apply()
}

fun Preferences.getInt(key: String) : Int = preferences?.getInt(key, Preferences.DEFAULT_INT_VALUE)?:Preferences.DEFAULT_INT_VALUE

fun Preferences.getInt(key: String, defaultValue: Int) : Int = preferences?.getInt(key, defaultValue)?:defaultValue

fun Preferences.saveString(key: String, value: String) {
    preferences?.edit()?.putString(key, value)?.apply()
}

fun Preferences.getString(key: String) : String = preferences?.getString(key, Preferences.DEFAULT_STRING_VALUE)?:Preferences.DEFAULT_STRING_VALUE

fun Preferences.getString(key: String, defaultValue: String): String = preferences?.getString(key, defaultValue)?:defaultValue

fun Preferences.setBoolean(key: String, value: Boolean) {
    preferences?.edit()?.putBoolean(key, value)?.apply()
}

fun Preferences.getBoolean(key: String): Boolean = preferences?.getBoolean(key, Preferences.DEFAULT_BOOLEAN_VALUE)?:Preferences.DEFAULT_BOOLEAN_VALUE

fun Preferences.getBoolean(key: String, defaultValue: Boolean): Boolean = preferences?.getBoolean(key, defaultValue)?:defaultValue

fun Preferences.clearAllData() {
    preferences?.edit()?.clear()?.apply()
}

class Preferences {

    companion object {
        private val BULLETS_PREFS_FILE = BaseApplication.getInstance()?.packageName?:"ro.rares.std.apps.appsbaselib" + ".prefs"

        private var INSTANCE: Preferences? = null

        const val DEFAULT_INT_VALUE = 0
        const val DEFAULT_STRING_VALUE = ""
        const val DEFAULT_BOOLEAN_VALUE = false

        fun getInstance(): Preferences {
            if (INSTANCE == null) {
                INSTANCE = Preferences()
            }
            return INSTANCE!!
        }
    }

    var preferences: SharedPreferences? = null

    private constructor() {
        preferences = BaseApplication.getInstance()?.getSharedPreferences(BULLETS_PREFS_FILE, Context.MODE_PRIVATE)
    }


}