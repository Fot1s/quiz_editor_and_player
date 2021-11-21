package com.phinnovation.quizplayer.presentation.application

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.phinnovation.quizplayer.framework.Interactors
import javax.inject.Inject

class QuizPlayerViewModelFactory @Inject constructor(
    private val application: Application,
    private val dependencies: Interactors
) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (QuizPlayerViewModel::class.java.isAssignableFrom(modelClass)) {
            return modelClass.getConstructor(Application::class.java, Interactors::class.java)
                .newInstance(
                    application,
                    dependencies
                )
        } else {
            throw IllegalStateException("ViewModel must extend MajesticViewModel")
        }
    }

}
