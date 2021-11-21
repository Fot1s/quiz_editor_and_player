package com.phinnovation.quizplayer.presentation.screens.customcomponents

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.phinnovation.quizplayer.R
import com.phinnovation.quizplayer.presentation.components.RotaryKnobComponent
import kotlinx.android.synthetic.main.fragment_custom_components.*

class CustomComponentsFragment : Fragment () {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_custom_components, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        vuMeter.value = knob.value
        knob.callback = {
            vuMeter.value = it
        }

        vuMeter2.value = knob2.value
        knob2.callback = {
            vuMeter2.value = it
        }

    }
}