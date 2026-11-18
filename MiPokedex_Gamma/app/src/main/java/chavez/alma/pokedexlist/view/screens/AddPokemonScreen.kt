package chavez.alma.pokedexlist.view.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import chavez.alma.pokedexlist.viewmodel.AddPokemonViewModel

@Composable
fun AddPokemonScreen(
    innerPadding: PaddingValues,
    viewModel: AddPokemonViewModel
) {
    var name by remember { mutableStateOf("") }
    var number by remember { mutableStateOf("") }
    var type by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    val pokemons by viewModel.pokemons.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.loadPokemons()
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(innerPadding)
            .padding(16.dp)
    ) {
        TextField(
            value = name,
            onValueChange = { name = it },
            label = { Text("Nombre") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(Modifier.size(8.dp))
        TextField(
            value = number,
            onValueChange = { number = it },
            label = { Text("Numero") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(Modifier.size(8.dp))
        TextField(
            value = type,
            onValueChange = { type = it },
            label = { Text("Tipo") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(Modifier.size(8.dp))
        TextField(
            value = description,
            onValueChange = { description = it },
            label = { Text("Descripcion") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(Modifier.size(8.dp))
        Button(
            onClick = {
                number.toIntOrNull()?.let { parsedNumber ->
                    viewModel.savePokemon(
                        name = name,
                        type = type,
                        number = parsedNumber,
                        description = description
                    )
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Guardar Pokemon")
        }

        if (viewModel.pokemonSaved) {
            Text("Pokemon guardado")
        }

        Spacer(Modifier.size(16.dp))
        Text("Pokemones guardados")
        LazyColumn {
            items(pokemons) { pokemon ->
                Text(pokemon.toString())
            }
        }
    }
}
