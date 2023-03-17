package com.test.a3.di

import androidx.lifecycle.ViewModel
import com.test.a3.ui.viewmodels.BetViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(BetViewModel::class)
    fun bindTournamentViewModel(viewModel: BetViewModel): ViewModel

}
