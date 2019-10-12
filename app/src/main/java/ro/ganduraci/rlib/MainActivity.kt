package ro.ganduraci.rlib

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import androidx.core.content.ContextCompat
import ro.ganduraci.rappslib.base.BaseActivity
import ro.ganduraci.rappslib.base.BaseApplication
import ro.ganduraci.rappslib.ui.NavigationDrawerManager

class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_base)
        setupDrawer()
        openFragment(HomeFragment())
    }

    fun setupDrawer() {
        setNavDrawerMenu(R.menu.menu_drawer)
        getNavigationView().itemTextColor = ContextCompat.getColorStateList(this, R.color.colorAccent)
        getNavigationView().itemIconTintList = ContextCompat.getColorStateList(this, R.color.colorAccent)

        val headerView = NavigationDrawerManager.HeaderBuilder(LayoutInflater.from(this))
            .backgroundResId(R.drawable.bg_nav_header)
            .userImageResId(R.drawable.ic_user)
            .textRow1("First Name", R.color.colorAccent)
            .textRow2("email@test.com", R.color.black)
            .build()
        getNavigationView().addHeaderView(headerView)
        NavigationDrawerManager.addItem(R.id.nav_home, HomeFragment(), true)
        NavigationDrawerManager.addItem(R.id.nav_beach, BeachFragment())
        NavigationDrawerManager.addItem(R.id.nav_casino, CasinoFragment())
        NavigationDrawerManager.addItem(R.id.nav_close, null, itemCallback = object : NavigationDrawerManager.MenuItemCallback {
            override fun onItemSelected() {
                BaseApplication.getInstance()?.showToastMessage("Pressed close item")
            }
        })
    }

}
