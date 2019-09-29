package ro.ganduraci.rlib

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_test.*
import ro.ganduraci.rappslib.base.BaseFragment

class TestFragment: BaseFragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_test, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        mainActivity?.setToolbarColor(R.color.colorAccent)
        mainActivity?.setToolbarTitle("Test Title")

        btn_content.setOnClickListener {
            openFragment(TestContentFragment())
        }
    }

    override fun enableBackNavigation(): Boolean = false


}