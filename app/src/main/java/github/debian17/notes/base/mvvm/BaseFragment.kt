package github.debian17.notes.base.mvvm

import android.os.Bundle
import android.view.View
import android.widget.FrameLayout
import androidx.annotation.CallSuper
import androidx.fragment.app.Fragment
import github.debian17.notes.base.ui.RetryListener
import github.debian17.notes.base.ui.ViewStateSwitcher

abstract class BaseFragment : Fragment(), RetryListener {

    private lateinit var viewStateSwitcher: ViewStateSwitcher

    protected abstract val containerView: FrameLayout

    @CallSuper
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewStateSwitcher = ViewStateSwitcher(
            requireContext(),
            containerView,
            this
        )
    }

    protected fun switchToLoading() {
        viewStateSwitcher.switchToLoading()
    }

    protected fun switchToMain() {
        viewStateSwitcher.switchToMain()
    }

}