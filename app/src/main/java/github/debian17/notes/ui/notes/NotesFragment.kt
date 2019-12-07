package github.debian17.notes.ui.notes

import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import github.debian17.notes.R
import github.debian17.notes.base.di.ViewModelInjector
import github.debian17.notes.base.extension.hide
import github.debian17.notes.base.extension.show
import github.debian17.notes.base.mvvm.BaseFragment
import kotlinx.android.synthetic.main.notes_fragment.*

class NotesFragment : BaseFragment() {

    companion object {
        fun newInstance() = NotesFragment()
    }

    private lateinit var viewModel: NotesViewModel

    private val addNoteHandler = Handler()

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
                pbLoading.show()
            } else {
                pbLoading.hide()
            }
        })

        viewModel.observeError(viewLifecycleOwner, Observer { error ->
            Log.e("MyTag", error.message)
        })

        viewModel.observeNotes(viewLifecycleOwner, Observer { notes ->
            Log.d("MyTag", "Notes count = ${notes.size}")
        })

        addNoteHandler.postDelayed({
            viewModel.addNote(
                title = "Title",
                content = "Content",
                images = null,
                records = null
            )
        }, 5000L)

    }

}
