package ro.ganduraci.rlib

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import ro.ganduraci.rappslib.base.BaseActivity
import ro.ganduraci.rappslib.ui.NavigationDrawerManager
import ro.ganduraci.rappslib.utils.ImageUtils

class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_base)
        setupDrawer()
        openFragment(TestFragment())
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
    }

}
