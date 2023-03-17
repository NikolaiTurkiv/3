package com.test.a3.ui.screens

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.test.a3.App
import com.test.a3.R
import com.test.a3.databinding.FragmentMainBinding
import com.test.a3.databinding.FragmentOptionsBinding
import com.test.a3.ui.viewmodels.BetViewModel
import com.test.a3.ui.viewmodels.ViewModelFactory
import com.test.a3.utils.setTheme
import com.test.a3.utils.viewBinding
import javax.inject.Inject


class OptionsFragment : Fragment(R.layout.fragment_options) {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    private val binding by viewBinding<FragmentOptionsBinding>()

    private lateinit var viewModel: BetViewModel


    private val component by lazy {
        (requireActivity().application as App).component
    }

    override fun onAttach(context: Context) {
        component.inject(this)
        super.onAttach(context)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this, viewModelFactory)[BetViewModel::class.java]

        changeThemeSwitch()

        with(binding) {
            setTheme(
                viewModel.isDarkTheme,
                requireContext(),
                tvTheme,
                tvNotification,
                tvOptionsTitle
            )
        }

        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, object :
            OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                findNavController().navigate(R.id.action_optionsFragment_to_mainFragment)
            }
        })
    }

    private fun changeThemeSwitch() {
        with(binding) {
            switchTheme.isChecked = viewModel.isDarkTheme
            switchTheme.setOnCheckedChangeListener { compoundButton, b ->
                viewModel.changeTheme(b)
                with(binding) {
                    setTheme(
                        b,
                        requireContext(),
                        tvTheme,
                        tvNotification,
                        tvOptionsTitle
                    )
                }
            }
        }
    }
}


