package chavez.alma.pokedexlist.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import chavez.alma.pokedexlist.model.data.interfaces.IPokemonRepository
import chavez.alma.pokedexlist.model.domain.Pokemon
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class AddPokemonViewModel(private val pokemonRepository: IPokemonRepository) : ViewModel() {
    private val pokemonsMutable = MutableStateFlow<List<Pokemon>>(emptyList())
    val pokemons: StateFlow<List<Pokemon>> = pokemonsMutable.asStateFlow()

    var pokemonSaved by mutableStateOf(false)
        private set

    fun loadPokemons() {
        viewModelScope.launch {
            pokemonsMutable.value = pokemonRepository.getPokemons()
        }
    }

    fun savePokemon(name: String, type: String, number: Int, description: String) {
        viewModelScope.launch {
            pokemonRepository.addPokemon(
                Pokemon(
                    name = name,
                    number = number,
                    type = type,
                    description = description,
                    height = 0f,
                    weight = 0f,
                    favorite = false,
                    ability = "",
                    image = 0
                )
            )
            pokemonSaved = true
            loadPokemons()
        }
    }
}
