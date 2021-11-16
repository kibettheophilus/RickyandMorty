package dev.kibet.presentation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import dev.kibet.domain.models.Characters
import dev.kibet.domain.models.state.UiState
import dev.kibet.presentation.adapters.CharactersAdapter
import dev.kibet.presentation.databinding.FragmentHomeBinding
import dev.kibet.presentation.viewmodel.CharactersViewModel
import kotlinx.coroutines.flow.collect
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : Fragment() {
    private val viewModel: CharactersViewModel by viewModel()
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private lateinit var charactersAdapter: CharactersAdapter
    private lateinit var recyclerView: RecyclerView
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentHomeBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        charactersAdapter = CharactersAdapter()
        recyclerView = binding.charactersRecyclerview
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = charactersAdapter

        charactersAdapter.setOnItemClickListener {
            val action = HomeFragmentDirections.actionHomeFragmentToDetailsFragment(it.name)
            findNavController().navigate(action)
        }

        viewLifecycleOwner.lifecycleScope.launchWhenCreated {
            viewModel.fetchCharactersStatus.collect {
                when (it) {
                    is UiState.Success -> charactersAdapter.differ.submitList(it.data as List<Characters>)
                    is UiState.Loading -> print("")
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
