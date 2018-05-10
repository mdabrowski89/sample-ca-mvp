package pl.mobite.sample.ca.mvp.ui.base.activity

import android.os.Bundle
import android.support.v4.app.Fragment
import pl.mobite.sample.ca.mvp.R


abstract class BaseFragmentActivity<T: Fragment>: BaseActivity() {

    var childFragment: T? = null
    private set

    private val CHILD_FRAGMENT_TAG = "CHILD_FRAGMENT"

    @Suppress("UNCHECKED_CAST")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_simple_fragment)

        childFragment = supportFragmentManager.findFragmentByTag(CHILD_FRAGMENT_TAG) as? T
        if (childFragment == null) {
            childFragment = createChildFragment()

            supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.fragment_container, childFragment, CHILD_FRAGMENT_TAG)
                    .commit()
        }
    }

    protected abstract fun createChildFragment(): T

}
