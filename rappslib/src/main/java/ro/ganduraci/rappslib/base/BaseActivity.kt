package ro.ganduraci.rappslib.base

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.FragmentTransaction
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.activity_base.*
import ro.ganduraci.rappslib.R

open class BaseActivity : AppCompatActivity() {

    private val TAG = BaseActivity::class.java.simpleName

    private var mActionBarDrawerToggle: ActionBarDrawerToggle? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_base)
        supportFragmentManager.addOnBackStackChangedListener {
            getTopFragment()?.let {
                setUpNavigation(it)
            }

        }

    }

    open fun openFragment(fragment: BaseFragment) {
        openFragment(fragment, Bundle())
    }

    open fun openFragment(fragment: BaseFragment, args: Bundle?) {
        setSupportActionBar(toolbar)
        supportActionBar?.title = " "
        fragment.arguments = args
        val ft = supportFragmentManager.beginTransaction()
        ft.replace(R.id.host_container, fragment, fragment.javaClass.simpleName)
        ft.addToBackStack(fragment.javaClass.simpleName)
        ft.commit()
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
    }

    fun setUpNavigation(fragment: BaseFragment) {
        Log.d(TAG, "Setting up navigation")
        supportActionBar?.setDisplayShowHomeEnabled(true)
        mActionBarDrawerToggle = object : ActionBarDrawerToggle(this, drawer_layout, toolbar, R.string.openDrawer, R.string.closeDrawer){}
        if (fragment.enableBackNavigation()) {
            setNavigationHomeButton(true)
        } else {
            setNavigationHomeButton(false)
            mActionBarDrawerToggle?.let {
                drawer_layout.addDrawerListener(it)
                syncNavBarToggle()
            }
        }
    }

    open fun setToolbarTitle(title: String) {
        supportActionBar?.title = title
    }

    open fun setToolbarColor(colorResId: Int) {
        toolbar.setBackgroundColor(ContextCompat.getColor(this, colorResId))
    }

    override fun onBackPressed() {
        val topFragment = getTopFragment()
        if (topFragment != null && topFragment.backPressEvent()) {
            return
        }
        if (supportFragmentManager.backStackEntryCount <= 1) {
            finish()
            return
        }
        super.onBackPressed()
    }

    fun getTopFragment(): BaseFragment? {
        val stackCount = supportFragmentManager.backStackEntryCount
        Log.d("TestDebug", "Fragment count:$stackCount")
        if (stackCount > 0) {
            val fragmentTag = supportFragmentManager.getBackStackEntryAt(stackCount - 1).name?:""
            val fragment = supportFragmentManager.findFragmentByTag(fragmentTag)
            if (fragment is BaseFragment) {
                return fragment
            }
        }
        return null
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

    fun getToolbar(): Toolbar = toolbar


    fun getNavigationView(): NavigationView = nav_view

    fun setNavDrawerMenu(menuResId: Int) {
        nav_view.menu?.clear()
        nav_view.inflateMenu(menuResId)
    }

    fun syncNavBarToggle() {
        mActionBarDrawerToggle?.syncState()
    }

   private fun setNavigationHomeButton(showBack: Boolean) {
       if (showBack) {
           mActionBarDrawerToggle?.isDrawerIndicatorEnabled = false
           drawer_layout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED)
           mActionBarDrawerToggle?.setToolbarNavigationClickListener { onBackPressed() }
           supportActionBar?.setDisplayHomeAsUpEnabled(true)
           supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_arrow_back_white_24dp)
           mActionBarDrawerToggle?.syncState()
       } else {
           supportActionBar?.setDisplayHomeAsUpEnabled(false)
           drawer_layout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED)
           mActionBarDrawerToggle?.isDrawerIndicatorEnabled = true
           mActionBarDrawerToggle?.toolbarNavigationClickListener = null
           mActionBarDrawerToggle?.syncState()
       }
   }
}
