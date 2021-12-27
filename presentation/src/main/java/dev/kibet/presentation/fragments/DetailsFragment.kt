package dev.kibet.presentation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import dev.kibet.domain.models.state.UiState
import dev.kibet.domain.utils.Status
import dev.kibet.presentation.databinding.FragmentDetailsBinding
import dev.kibet.presentation.viewmodel.CharactersViewModel
import kotlinx.coroutines.flow.collect
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailsFragment : Fragment() {
    private var _binding: FragmentDetailsBinding? = null
    private val binding get() = _binding!!
    private val viewModel: CharactersViewModel by viewModel()
    private val args by navArgs<DetailsFragmentArgs>()
    private lateinit var progressBar: ProgressBar
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
        progressBar = binding.detailsProgress
        val id = args.id
        // binding.detailName.text = name
        viewModel.getSingleCharacter(id)
    }

    private fun subscribeToObservers() {
            viewModel.fetchSingleCharacterStatus.observe(viewLifecycleOwner, {
                when (it.status) {
                    Status.SUCCESS -> {
                        Glide.with(requireContext()).load(it.data?.image).diskCacheStrategy(
                            DiskCacheStrategy.ALL).into(binding.detailImage)
                        progressBar.isVisible = false
                    }
                    Status.LOADING -> {
                        progressBar.isVisible = true
                    }
                    Status.ERROR -> {
                        Toast.makeText(context, "${it.message}", Toast.LENGTH_LONG)
                            .show()
                        progressBar.isVisible = false
                    }
                }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
