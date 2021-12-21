package dev.kibet.presentation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import dev.kibet.domain.models.state.UiState
import dev.kibet.presentation.databinding.FragmentDetailsBinding
import dev.kibet.presentation.viewmodel.CharactersViewModel
import kotlinx.coroutines.flow.collect
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailsFragment : Fragment() {
    private var _binding: FragmentDetailsBinding? = null
    private val binding get() = _binding!!
    private val viewModel: CharactersViewModel by viewModel()
    private val args by navArgs<DetailsFragmentArgs>()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDetailsBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        subscribeToObservers()
        val id = args.id
        // binding.detailName.text = name
        viewModel.getSingleCharacter(id)
    }

    private fun subscribeToObservers() {
        viewLifecycleOwner.lifecycleScope.launchWhenCreated {
            viewModel.fetchSingleCharacterStatus.collect {
                when (it) {
                    is UiState.Success -> Toast.makeText(context, "${it.data}", Toast.LENGTH_LONG)
                        .show()
                    is UiState.Loading -> Toast.makeText(context, "Loading", Toast.LENGTH_LONG)
                        .show()
                    is UiState.Error -> Toast.makeText(context, "${it.error}", Toast.LENGTH_LONG)
                        .show()
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
