package github.debian17.notes.ui.reminders

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout

import github.debian17.notes.R
import github.debian17.notes.base.mvvm.BaseFragment
import kotlinx.android.synthetic.main.reminders_fragment.*

class RemindersFragment : BaseFragment() {

    override val containerView: FrameLayout
        get() = flMain

    private lateinit var viewModel: RemindersViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.reminders_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)


        viewModel = ViewModelProviders.of(this).get(RemindersViewModel::class.java)
    }

    override fun retry() {

    }

}
