package com.phinnovation.quizplayer.presentation.tester.list

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.phinnovation.core.domain.Quiz
import com.phinnovation.core.domain.QuizState
import com.phinnovation.quizplayer.framework.Interactors
import com.phinnovation.quizplayer.framework.QuizPlayerViewModel
import com.phinnovation.quizplayer.presentation.utils.Event
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class TesterQuizListViewModel(application: Application, interactors: Interactors) :
    QuizPlayerViewModel(application, interactors) {

    private val _navigateToQuiz = MutableLiveData<Event<Boolean>>()
    private val _showEmptyQuestionsError = MutableLiveData<Event<Boolean>>()
    private val _showQuizFinishedError = MutableLiveData<Event<Boolean>>()

    val navigateToQuizEvent: LiveData<Event<Boolean>>
        get() = _navigateToQuiz

    val showEmptyQuestionsErrorEvent: LiveData<Event<Boolean>>
        get() = _showEmptyQuestionsError

    val showQuizFinishedErrorEvent: LiveData<Event<Boolean>>
        get() = _showQuizFinishedError

    val quizzes: MutableLiveData<List<Quiz>> = MutableLiveData()

    fun loadQuizzes() {
        GlobalScope.launch {
            quizzes.postValue(interactors.getQuizes())
        }
    }

    fun checkQuizCanBeOpened(quiz: Quiz) {

        if (quiz.state == QuizState.FINISHED) {
            _showQuizFinishedError.value = Event(true)
        } else {
            GlobalScope.launch {
                val questions = interactors.getQuestions(quiz)

                if (questions.isNotEmpty()) {
                    //we will continue to the next screen
                    //set openQuiz and post navigateToQuiz
                    setOpenQuiz(quiz)
                    _navigateToQuiz.postValue(Event(true))
                } else {
                    //no questions, quiz is not setup yet, post error
                    _showEmptyQuestionsError.postValue(Event(true))
                }
            }
        }
    }

    private fun setOpenQuiz(quiz: Quiz) {
        interactors.setOpenQuiz(quiz)
    }
}