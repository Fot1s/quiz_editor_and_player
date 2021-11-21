package com.phinnovation.quizplayer.framework.dagger

import androidx.lifecycle.ViewModelProvider
import com.phinnovation.quizplayer.presentation.application.QuizPlayerViewModelFactory
import dagger.Binds
import dagger.Module

@Module
abstract class ViewModelFactoryModule {
    @Binds
    abstract fun bindViewModelFactory(viewModelFactory: QuizPlayerViewModelFactory): ViewModelProvider.Factory
}
