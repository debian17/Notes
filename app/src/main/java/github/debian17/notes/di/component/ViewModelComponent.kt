package github.debian17.notes.di.component

import dagger.Component
import github.debian17.notes.di.module.ViewModelFactoryModule
import github.debian17.notes.ui.notes.NotesFragment

@Component(modules = [ViewModelFactoryModule::class])
interface ViewModelComponent {

    fun inject(fragment: NotesFragment)

}