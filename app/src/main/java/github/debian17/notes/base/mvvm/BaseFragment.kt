package github.debian17.notes.base.mvvm

import android.os.Bundle
import androidx.annotation.CallSuper
import androidx.fragment.app.Fragment

abstract class BaseFragment : Fragment() {

    protected abstract fun inject()

    @CallSuper
    override fun onCreate(savedInstanceState: Bundle?) {
        inject()
        super.onCreate(savedInstanceState)
    }

}