package com.test.a3.ui.screens

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavArgs
import androidx.navigation.fragment.navArgs
import com.test.a3.App
import com.test.a3.R
import com.test.a3.databinding.FragmentDictionaryDetailBinding
import com.test.a3.ui.viewmodels.BetViewModel
import com.test.a3.ui.viewmodels.ViewModelFactory
import com.test.a3.utils.setTheme
import com.test.a3.utils.viewBinding
import javax.inject.Inject


class DictionaryDetailFragment : Fragment(R.layout.fragment_dictionary_detail) {
    private val binding by viewBinding<FragmentDictionaryDetailBinding>()
    private lateinit var viewModel: BetViewModel

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val component by lazy {
        (requireActivity().application as App).component
    }
    val args: DictionaryDetailFragmentArgs by navArgs()

    override fun onAttach(context: Context) {
        component.inject(this)
        super.onAttach(context)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this, viewModelFactory)[BetViewModel::class.java]

        viewModel.getDictionary()
        initObservers()

        with(binding) {
            setTheme(
                viewModel.isDarkTheme,
                requireContext(),
                tvTitle,
                tvText,
                tvAdditional
            )
        }
    }

    private fun initObservers() {
        viewModel.betDictionaryListLD.observe(viewLifecycleOwner) {
            val item = it[args.position]

            with(binding) {
                tvAdditional.text = item.additional
                tvText.text = item.text
                tvTitle.text = item.title
            }
        }
    }
}