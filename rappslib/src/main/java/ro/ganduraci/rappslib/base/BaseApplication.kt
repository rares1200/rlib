package ro.ganduraci.rappslib.base

import android.app.Application
import android.widget.Toast

open class BaseApplication : Application(){

    companion object {
        var INSTANCE: BaseApplication? = null

        fun getInstance(): BaseApplication? {
            return INSTANCE
        }
    }

    override fun onCreate() {
        super.onCreate()
        INSTANCE = this
    }

    override fun onTerminate() {
        super.onTerminate()
        INSTANCE = null
    }

    open fun showToastMessage(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }

    open fun showToastMessage(messageResId: Int) {
        Toast.makeText(this, messageResId, Toast.LENGTH_LONG).show()
    }
}