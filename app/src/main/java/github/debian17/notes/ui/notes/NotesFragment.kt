package github.debian17.notes.ui.notes

import android.os.Bundle
import android.os.Handler
import android.os.HandlerThread
import android.util.Log
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.ProgressBar
import androidx.lifecycle.Observer
import github.debian17.notes.R
import github.debian17.notes.base.di.ViewModelInjector
import github.debian17.notes.base.mvvm.BaseFragment
import kotlinx.android.synthetic.main.notes_fragment.*

class NotesFragment : BaseFragment() {

    private lateinit var viewModel: NotesViewModel

    override val containerView: FrameLayout
        get() = flMain

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.notes_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel = ViewModelInjector.provideNotesViewModel(this)

        viewModel.observeLoading(viewLifecycleOwner, Observer { isLoading ->
            if (isLoading) {
                switchToLoading()

                Handler().postDelayed({
                    switchToMain()

                    Handler().postDelayed({
                        switchToLoading()
                    }, 10000L)


                }, 5000L)

                //pbLoading.show()
            } else {
                //pbLoading.hide()
            }
        })

        viewModel.observeError(viewLifecycleOwner, Observer { error ->
            Log.e("MyTag", error.message)
        })

        viewModel.observeNotes(viewLifecycleOwner, Observer { notes ->
            Log.d("MyTag", "Notes count = ${notes.size}")
        })

    }

    override fun retry() {

    }

}
