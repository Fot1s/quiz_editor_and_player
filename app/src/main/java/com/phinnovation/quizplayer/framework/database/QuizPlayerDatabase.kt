package com.phinnovation.quizplayer.framework.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    entities = [QuestionEntity::class, QuizEntity::class],
    version = 1,
    exportSchema = false
)
abstract class QuizPlayerDatabase : RoomDatabase() {
    companion object {

        private const val DATABASE_NAME = "quiz_player.db"

        private var instance: QuizPlayerDatabase? = null

        private fun create(context: Context): QuizPlayerDatabase =
            Room.databaseBuilder(context, QuizPlayerDatabase::class.java, DATABASE_NAME)
                .fallbackToDestructiveMigration()
                .build()

        fun getInstance(context: Context): QuizPlayerDatabase =
            (instance ?: create(context)).also { instance = it }
    }

    abstract fun questionDao(): QuestionDao

    abstract fun quizDao(): QuizDao

}