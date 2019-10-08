package github.debian17.notes.ui.notes

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer

import github.debian17.notes.R
import github.debian17.notes.base.di.ViewModelInjector
import github.debian17.notes.base.mvvm.BaseFragment

class NotesFragment : BaseFragment() {

    companion object {
        fun newInstance() = NotesFragment()
    }

    private lateinit var viewModel: NotesViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.notes_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel = ViewModelInjector.provideNotesViewModel(this)

        viewModel.observeLoading(viewLifecycleOwner, Observer {

        })

        viewModel.observeError(viewLifecycleOwner, Observer {

        })

    }

}
