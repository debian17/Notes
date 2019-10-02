package github.debian17.notes.ui.navigation

import androidx.annotation.IdRes
import androidx.fragment.app.FragmentManager
import github.debian17.notes.base.mvvm.BaseFragment

class MainNavigator(
    private val fragmentManager: FragmentManager,
    @IdRes val containerId: Int
) {

    fun replaceFragment(fragment: BaseFragment, addToBackStack: Boolean, tag: String?) {
        val transaction = fragmentManager.beginTransaction()
            .replace(containerId, fragment)

        if (addToBackStack) {
            transaction.addToBackStack(tag)
        }

        transaction.commit()

    }

}