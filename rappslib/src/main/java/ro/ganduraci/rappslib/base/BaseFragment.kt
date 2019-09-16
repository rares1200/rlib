package ro.ganduraci.rappslib.base

import android.os.Bundle
import androidx.fragment.app.Fragment

open class BaseFragment : Fragment() {

    var mainActivity: BaseActivity? = null

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        mainActivity = activity as BaseActivity

    }

    override fun onResume() {
        super.onResume()
        setUpPage()
    }

    fun setUpPage() {
        mainActivity?.handleToolbarVisibility(isToolbarVisible())
        mainActivity?.handleFABVisibility(isFabVisible())
    }

    open fun isToolbarVisible(): Boolean {
        return true
    }

    open fun isFabVisible(): Boolean {
        return false
    }

    open fun showLoading() {
        mainActivity?.handleLoading(true)
    }

    open fun hideLoading() {
        mainActivity?.handleLoading(false)
    }

    open fun openFragment(fragment: BaseFragment) {
        openFragment(fragment, Bundle())
    }

    open fun openFragment(fragment: BaseFragment, args: Bundle) {
        mainActivity?.openFragment(fragment, args)
    }

    open fun backPressEvent(): Boolean {
        return false
    }
}