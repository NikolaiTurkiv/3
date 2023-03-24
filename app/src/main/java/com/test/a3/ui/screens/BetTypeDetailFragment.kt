package com.test.a3.ui.screens

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.squareup.picasso.Picasso
import com.test.a3.App
import com.test.a3.R
import com.test.a3.databinding.FragmentBetTypeDetailBinding
import com.test.a3.ui.viewmodels.BetViewModel
import com.test.a3.ui.viewmodels.ViewModelFactory
import com.test.a3.utils.setTheme
import com.test.a3.utils.viewBinding
import javax.inject.Inject


class BetTypeDetailFragment : Fragment(R.layout.fragment_bet_type_detail) {
    private val binding by viewBinding<FragmentBetTypeDetailBinding>()
    private lateinit var viewModel: BetViewModel

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val component by lazy {
        (requireActivity().application as App).component
    }

    private val args: BetTypeDetailFragmentArgs by navArgs()

    override fun onAttach(context: Context) {
        component.inject(this)
        super.onAttach(context)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this, viewModelFactory)[BetViewModel::class.java]

        viewModel.getBetTypes()
         initObservers()

        with(binding) {
            setTheme(
                viewModel.isDarkTheme,
                requireContext(),
                tvTitle,
                tvText
            )
        }

    }

    private fun initObservers(){
        viewModel.betTypeListLD.observe(viewLifecycleOwner){
            val item = it[args.position]

            with(binding){
                if (item.image.isNotEmpty())
                    Picasso.get().load(item.image).into(imBetPicture)

                tvText.text = item.text
                tvTitle.text = item.title
            }

         }
    }

}
