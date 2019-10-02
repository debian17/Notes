package github.debian17.notes.ui.notes

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import github.debian17.notes.App

import github.debian17.notes.R
import github.debian17.notes.base.mvvm.BaseFragment
import javax.inject.Inject

class NotesFragment : BaseFragment() {

    companion object {
        fun newInstance() = NotesFragment()
    }

    private lateinit var viewModel: NotesViewModel

    @Inject
    lateinit var viewModelFactory: NotesViewModel.NotesViewModelFactory

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.notes_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(NotesViewModel::class.java)

        viewModel.observeLoading(viewLifecycleOwner, Observer {

        })

        viewModel.observeError(viewLifecycleOwner, Observer {

        })

    }

    override fun inject() {
        (activity?.application as App).getViewModelComponent().inject(this)
    }

}
