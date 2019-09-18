package ro.ganduraci.rappslib.base

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.core.content.ContextCompat
import androidx.fragment.app.FragmentTransaction
import kotlinx.android.synthetic.main.activity_base.*
import ro.ganduraci.rappslib.R

open class BaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_base)
    }

    open fun openFragment(fragment: BaseFragment) {
        openFragment(fragment, Bundle())
        setSupportActionBar(toolbar)
        supportActionBar?.title = " "
    }

    open fun openFragment(fragment: BaseFragment, args: Bundle?) {
        fragment.arguments = args
        val ft = supportFragmentManager.beginTransaction()
        ft.replace(R.id.host_container, fragment, fragment.javaClass.simpleName)
        ft.addToBackStack(fragment.javaClass.simpleName)
        ft.commit()
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
    }

    open fun setToolbarTitle(title: String) {
        supportActionBar?.title = title
    }

    open fun setToolbarColor(colorResId: Int) {
        toolbar.setBackgroundColor(ContextCompat.getColor(this, colorResId))
    }

    override fun onBackPressed() {
        val stackCount = supportFragmentManager.backStackEntryCount
        Log.d("TestDebug", "Fragment count:$stackCount")
        if (stackCount > 0) {
            val fragmentTag = supportFragmentManager.getBackStackEntryAt(stackCount - 1).name?:""
            val fragment = supportFragmentManager.findFragmentByTag(fragmentTag)
            if (fragment is BaseFragment) {
                if (fragment.backPressEvent()) {
                    return
                }
            }
        }
        if (supportFragmentManager.backStackEntryCount <= 1) {
            finish()
            return
        }
        super.onBackPressed()
    }

    open fun handleToolbarVisibility(show: Boolean) {
        appBarLayout.visibility = if (show) View.VISIBLE else View.GONE
    }

    open fun handleFABVisibility(show: Boolean) {
        if (show) {
            fab_btn.show()
        } else {
            fab_btn.hide()
        }
    }

    open fun handleLoading(show: Boolean) {
        loading_layout.visibility = if (show) View.VISIBLE else View.GONE
    }
}
