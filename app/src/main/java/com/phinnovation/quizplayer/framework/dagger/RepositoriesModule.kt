package com.phinnovation.quizplayer.framework.dagger

import android.app.Application
import com.phinnovation.core.data.QuestionRepository
import com.phinnovation.core.data.QuizRepository
import com.phinnovation.quizplayer.framework.*
import com.phinnovation.quizplayer.framework.database.QuestionDao
import com.phinnovation.quizplayer.framework.database.QuizDao
import com.phinnovation.quizplayer.framework.database.QuizPlayerDatabase
import com.phinnovation.quizplayer.framework.datasources.RoomQuestionDataSource
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.Dispatchers
import javax.inject.Singleton

@Module
class RepositoriesModule {
    @Provides
    @Singleton
    fun provideQuizRepository(application: Application, quizDao: QuizDao) = QuizRepository(
        RoomQuizDataSource(application, quizDao ),
        InMemoryOpenQuizDataSource(),
        Dispatchers.IO
    )

    @Provides
    @Singleton
    fun provideQuestionRepository(application: Application, questionDao:QuestionDao) = QuestionRepository(
        RoomQuestionDataSource(application,questionDao),
        InMemoryOpenQuestionDataSource(),
        Dispatchers.IO
    )

    @Provides
    @Singleton
    fun provideQuizPlayerDatabase(context: Application): QuizPlayerDatabase {
        return QuizPlayerDatabase.getInstance(context)
    }

    @Provides
    fun provideQuizDao(appDatabase: QuizPlayerDatabase): QuizDao {
        return appDatabase.quizDao()
    }

    @Provides
    fun provideQuestionDao(appDatabase: QuizPlayerDatabase): QuestionDao {
        return appDatabase.questionDao()
    }

}