package com.test.a3.ui.screens

import android.os.Bundle
import android.os.CountDownTimer
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.test.a3.R
import com.test.a3.databinding.FragmentSplashBinding
import com.test.a3.utils.viewBinding

class SplashFragment : Fragment(R.layout.fragment_splash) {

    val binding by viewBinding<FragmentSplashBinding>()

    private var progress = 0

    private var countDownTimer: CountDownTimer? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        startTimer()
    }

    private fun startTimer(){
        countDownTimer = object : CountDownTimer(3000,30){
            override fun onTick(p0: Long) {
                progress += 1
                binding.progressBar.progress = progress
             }

            override fun onFinish() {
                findNavController().navigate(R.id.action_splashFragment_to_mainFragment)
            }

        }.start()
    }

    override fun onDestroy() {
        super.onDestroy()
        countDownTimer?.cancel()
    }
}