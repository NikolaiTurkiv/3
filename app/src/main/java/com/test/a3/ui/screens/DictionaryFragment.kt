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
import com.test.a3.databinding.FragmentDictionaryBinding
import com.test.a3.databinding.FragmentMainBinding
import com.test.a3.ui.adapters.DictionaryAdapter
import com.test.a3.ui.viewmodels.BetViewModel
import com.test.a3.ui.viewmodels.ViewModelFactory
import com.test.a3.utils.setTheme
import com.test.a3.utils.viewBinding
import javax.inject.Inject

class DictionaryFragment : Fragment(R.layout.fragment_dictionary) {

    private val binding by viewBinding<FragmentDictionaryBinding>()
    private lateinit var viewModel: BetViewModel

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val adapter by lazy {
        DictionaryAdapter(LayoutInflater.from(requireContext())){
            val action = DictionaryFragmentDirections.actionDictionaryFragmentToDictionaryDetailFragment().setPosition(it)
            findNavController().navigate(action)
        }
    }

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

        viewModel.getDictionary()
        initRecycler()
        initObservers()

        with(binding) {
            setTheme(
                viewModel.isDarkTheme,
                requireContext(),
                tvTitle,
            )
        }

    }

    private fun initObservers(){
        viewModel.betDictionaryListLD.observe(viewLifecycleOwner){
            adapter.updateList(it)
        }
    }

    private fun initRecycler(){
        binding.rvDictionary.adapter = adapter
    }

}