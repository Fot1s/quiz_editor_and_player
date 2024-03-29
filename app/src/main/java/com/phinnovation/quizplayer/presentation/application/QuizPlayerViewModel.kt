package com.phinnovation.quizplayer.framework

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.phinnovation.quizplayer.presentation.application.QuizPlayerApplication

open class QuizPlayerViewModel(application: Application, protected val interactors: Interactors) :
    AndroidViewModel(application) {

    protected val application: QuizPlayerApplication = getApplication()

}
