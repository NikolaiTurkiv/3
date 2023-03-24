package com.test.a3.ui.screens

import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.onesignal.OneSignal
import com.test.a3.App
import com.test.a3.R
import com.test.a3.databinding.FragmentMainBinding
import com.test.a3.databinding.FragmentOptionsBinding
import com.test.a3.ui.viewmodels.BetViewModel
import com.test.a3.ui.viewmodels.ViewModelFactory
import com.test.a3.utils.setTheme
import com.test.a3.utils.viewBinding
import javax.inject.Inject


class MainFragment : Fragment(R.layout.fragment_main) {

    private val binding by viewBinding<FragmentMainBinding>()

    private val args: MainFragmentArgs by navArgs()

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private lateinit var viewModel: BetViewModel


    private val component by lazy {
        (requireActivity().application as App).component
    }

    override fun onAttach(context: Context) {
        component.inject(this)
        super.onAttach(context)
    }

    private val requestPermissionLauncher =
        registerForActivityResult(
            ActivityResultContracts.RequestPermission()
        ) { isGranted: Boolean ->
            if (isGranted) {
                Log.i("Permission: ", "Granted")
            } else {
                Log.i("Permission: ", "Denied")
            }
        }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        cancelPush()
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            requestPermission(view,android.Manifest.permission.POST_NOTIFICATIONS)
        }

        initClickListeners()
        viewModel = ViewModelProvider(this, viewModelFactory)[BetViewModel::class.java]

        with(binding) {
            setTheme(
                viewModel.isDarkTheme,
                requireContext(),
                tvAZ,
                tvTypesBet,
                tvOptions
            )
        }
    }

    private fun initClickListeners(){
        binding.tvAZ.setOnClickListener {
            findNavController().navigate(R.id.action_mainFragment_to_dictionaryFragment)
        }
        binding.tvOptions.setOnClickListener {
            findNavController().navigate(R.id.action_mainFragment_to_optionsFragment)
        }
        binding.tvTypesBet.setOnClickListener {
            findNavController().navigate(R.id.action_mainFragment_to_typeBetFragment)
        }
    }

    private fun cancelPush(){
        if(args.push == SplashFragment.Companion.NO)
            OneSignal.disablePush(true)
        else
            OneSignal.disablePush(false)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        permissions.forEach {
            Log.d("PERMISSION",it)
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }

    private fun requestPermission(view: View, permission: String){

        when {
            ContextCompat.checkSelfPermission(
                requireActivity(),
                permission
            ) == PackageManager.PERMISSION_GRANTED -> {

            }

            ActivityCompat.shouldShowRequestPermissionRationale(
                requireActivity(),
                permission
            ) -> {
                requestPermissionLauncher.launch(
                    permission
                )
            }

            else -> {
                requestPermissionLauncher.launch(
                    permission
                )
            }
        }
    }

}