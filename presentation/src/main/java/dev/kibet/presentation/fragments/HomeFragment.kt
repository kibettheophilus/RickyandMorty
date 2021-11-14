package dev.kibet.presentation.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import dev.kibet.domain.models.state.UiState
import dev.kibet.presentation.R
import dev.kibet.presentation.viewmodel.CharactersViewModel
import kotlinx.coroutines.flow.collect
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : Fragment() {
    private val viewModel: CharactersViewModel by viewModel()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewLifecycleOwner.lifecycleScope.launchWhenCreated {
            viewModel.fetchCharactersStatus.collect {
                when (it) {
                    is UiState.Success -> Log.d("CHARS","${it.data}")
                    is UiState.Loading -> print("")
                    is UiState.Error -> Toast.makeText(context, "${it.error}", Toast.LENGTH_LONG)
                        .show()
                }
            }
        }
    }
}
