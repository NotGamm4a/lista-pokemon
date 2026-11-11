package chavez.alma.pokedexlist.view.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import chavez.alma.pokedexlist.model.domain.Pokemon
import chavez.alma.pokedexlist.viewmodel.PokemonDetailViewModel

@Composable
fun PokemonDetailScreen(innerPadding: PaddingValues, pokemon: Pokemon, pokemonDetailViewModel: PokemonDetailViewModel) {
    Column(modifier = Modifier.padding(innerPadding)) {
        Text(pokemon.name)
        Image(
            painter = painterResource(pokemon.image),
            contentDescription = "${pokemon.name} image"
        )
        Button({pokemonDetailViewModel.saveCompanion(pokemon)})
        {
            Text("Seleccionar como compañero")
        }
    }
}
