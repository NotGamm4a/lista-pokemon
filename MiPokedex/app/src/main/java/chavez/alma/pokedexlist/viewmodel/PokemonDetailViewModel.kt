package chavez.alma.pokedexlist.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import chavez.alma.pokedexlist.model.data.CompanionPrefecences
import chavez.alma.pokedexlist.model.domain.Pokemon
import com.tu_paquete.pokedexlist.model.data.Users
import kotlinx.coroutines.launch

class PokemonDetailViewModel(
    private val companitionDataStore: CompanionPrefecences
): ViewModel(){

    fun saveCompanion(pokemon: Pokemon){
        viewModelScope.launch {
            companitionDataStore.saveCompanion(pokemon)
        }

    }


}