package com.test.a3.ui.screens

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.test.a3.App
import com.test.a3.R
import com.test.a3.databinding.FragmentBetTypeDetailBinding
import com.test.a3.databinding.FragmentDictionaryBinding
import com.test.a3.databinding.FragmentTypeBetBinding
import com.test.a3.ui.adapters.BetTypeAdapter
import com.test.a3.ui.adapters.DictionaryAdapter
import com.test.a3.ui.viewmodels.BetViewModel
import com.test.a3.ui.viewmodels.ViewModelFactory
import com.test.a3.utils.setTheme
import com.test.a3.utils.viewBinding
import javax.inject.Inject


class TypeBetFragment : Fragment(R.layout.fragment_type_bet) {

    private val binding by viewBinding<FragmentTypeBetBinding>()

    private lateinit var viewModel: BetViewModel

    private val component by lazy {
        (requireActivity().application as App).component
    }

    override fun onAttach(context: Context) {
        component.inject(this)
        super.onAttach(context)
    }

    private val adapter by lazy {
        BetTypeAdapter(LayoutInflater.from(requireContext())) {
            val action = TypeBetFragmentDirections.actionTypeBetFragmentToBetTypeDetailFragment().setPosition(it)
            findNavController().navigate(action)
        }
    }
    @Inject
    lateinit var viewModelFactory: ViewModelFactory


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this, viewModelFactory)[BetViewModel::class.java]
        viewModel.getBetTypes()
        initRecycler()
        initObservers()

        with(binding) {
            setTheme(
                viewModel.isDarkTheme,
                requireContext(),
                tvTypeOfBetTitle
            )
        }

    }

    private fun initObservers() {
        viewModel.betTypeListLD.observe(viewLifecycleOwner){
            adapter.updateList(it)
        }
    }

    private fun initRecycler() {
        binding.rvBetType.adapter = adapter
    }
}