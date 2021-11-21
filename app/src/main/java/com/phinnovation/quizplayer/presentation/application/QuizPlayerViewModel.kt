package com.phinnovation.quizplayer.presentation.application

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.phinnovation.quizplayer.framework.Interactors

open class QuizPlayerViewModel(application: Application, protected val interactors: Interactors) :
    AndroidViewModel(application) {

    protected val application: QuizPlayerApplication = getApplication()

}
