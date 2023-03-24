package com.test.a3.di

import android.app.Application
import com.test.a3.MainActivity
import com.test.a3.ui.screens.*
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [DataModule::class, ViewModelModule::class])
interface AppComponent {
    fun inject(app: Application)

    fun inject(activity: MainActivity)
    fun inject(fragment: BetTypeDetailFragment)
    fun inject(fragment: DictionaryFragment)
    fun inject(fragment: OptionsFragment)
    fun inject(fragment: TypeBetFragment)
    fun inject(fragment: DictionaryDetailFragment)
    fun inject(fragment: MainFragment)
    fun inject(fragment: SplashFragment)

    @Component.Factory
    interface Factory {
        fun create(
            @BindsInstance application: Application
        ): AppComponent
    }
}
