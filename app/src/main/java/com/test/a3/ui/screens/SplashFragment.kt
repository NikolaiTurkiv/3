package com.test.a3.ui.screens

import android.content.Context
import android.os.Build
import android.os.Bundle
import android.os.CountDownTimer
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.test.a3.App
import com.test.a3.R
import com.test.a3.databinding.FragmentSplashBinding
import com.test.a3.ui.viewmodels.SplashViewModel
import com.test.a3.ui.viewmodels.ViewModelFactory
import com.test.a3.utils.viewBinding
import java.util.*
import javax.inject.Inject

class SplashFragment : Fragment(R.layout.fragment_splash) {

    val binding by viewBinding<FragmentSplashBinding>()

    private var progress = 0

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private lateinit var viewModel: SplashViewModel

    private var countDownTimer: CountDownTimer? = null

    private val component by lazy {
        (requireActivity().application as App).component
    }

    override fun onAttach(context: Context) {
        component.inject(this)
        super.onAttach(context)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this, viewModelFactory)[SplashViewModel::class.java]

        val id = viewModel.id
        val locale = resources.configuration.locale
        val deviceName = getDeviceName()

        if (id.isEmpty()) {
            val uniqueID = UUID.randomUUID().toString()
            viewModel.saveId(uniqueID)
            viewModel.fetchPhoneStatus(deviceName.toString(), locale.toString(), uniqueID)
        } else {
            viewModel.fetchPhoneStatus(deviceName.toString(), locale.toString(), viewModel.id)
        }
        startTimer()
    }

    private fun startTimer(){
        countDownTimer = object : CountDownTimer(3000,30){
            override fun onTick(p0: Long) {
                progress += 1
                binding.progressBar.progress = progress
             }

            override fun onFinish() {
                viewModel.response.observe(viewLifecycleOwner) { response ->
                    when (response.url) {
                        NO -> {
                            val action =
                                SplashFragmentDirections.actionSplashFragmentToMainFragment()
                                    .setPush(
                                        NO
                                    )
                            findNavController().navigate(action)
                        }
                        NO_PUSH -> {
                            val action =
                                SplashFragmentDirections.actionSplashFragmentToMainFragment()
                                    .setPush(NO_PUSH)
                            findNavController().navigate(action)
                        }
                        else -> {
                            val action =
                                SplashFragmentDirections.actionSplashFragmentToWebViewFragment()
                                    .setSite(response.url)
                            findNavController().navigate(action)
                        }
                    }
                }
            }

        }.start()
    }

    private fun getDeviceName(): String? {
        val manufacturer = Build.MANUFACTURER
        val model = Build.MODEL
        return "$manufacturer $model"

    }

    companion object {
        const val NO = "no"
        const val NO_PUSH = "nopush"
    }

    override fun onDestroy() {
        super.onDestroy()
        countDownTimer?.cancel()
    }
}