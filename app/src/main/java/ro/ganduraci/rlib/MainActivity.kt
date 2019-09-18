package ro.ganduraci.rlib

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import ro.ganduraci.rappslib.base.BaseActivity

class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_base)
        openFragment(TestFragment())
    }
}
