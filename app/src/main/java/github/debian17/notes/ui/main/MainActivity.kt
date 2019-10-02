package github.debian17.notes.ui.main

import android.os.Bundle
import github.debian17.notes.R
import github.debian17.notes.base.mvvm.BaseActivity
import github.debian17.notes.ui.navigation.MainNavigator
import github.debian17.notes.ui.navigation.MainNavigatorProvider
import github.debian17.notes.ui.notes.NotesFragment

class MainActivity : BaseActivity(), MainNavigatorProvider {

    private lateinit var mainNavigator: MainNavigator

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mainNavigator = MainNavigator(supportFragmentManager, R.id.flMainContainer)

        if (savedInstanceState == null) {
            val notesFragment = NotesFragment.newInstance()
            mainNavigator.replaceFragment(notesFragment, false, null)
        }

    }

    override fun provideMainNavigator(): MainNavigator {
        return mainNavigator
    }

}
