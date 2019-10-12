package ro.ganduraci.rlib


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ro.ganduraci.rappslib.base.BaseFragment

/**
 * A simple [Fragment] subclass.
 */
class CasinoFragment : BaseFragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_casino, container, false)
    }

    override fun enableBackNavigation(): Boolean = false
}
