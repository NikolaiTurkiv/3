package com.test.a3.di

import androidx.lifecycle.ViewModel
import com.test.a3.ui.viewmodels.BetViewModel
import com.test.a3.ui.viewmodels.SplashViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(BetViewModel::class)
    fun bindTournamentViewModel(viewModel: BetViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(SplashViewModel::class)
    fun bindSplashViewModel(viewModel: SplashViewModel): ViewModel

}
