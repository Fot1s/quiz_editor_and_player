package com.phinnovation.quizplayer.framework.dagger

import android.app.Application
import com.phinnovation.core.data.QuestionRepository
import com.phinnovation.core.data.QuizRepository
import com.phinnovation.quizplayer.framework.*
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepositoriesModule {
    @Provides
    @Singleton
    fun provideQuizRepository(application: Application) = QuizRepository(
        RoomQuizDataSource(application),
        InMemoryOpenQuizDataSource()
    )

    @Provides
    @Singleton
    fun provideQuestionRepository(application: Application) = QuestionRepository(
        RoomQuestionDataSource(application),
        InMemoryOpenQuestionDataSource()
    )



}