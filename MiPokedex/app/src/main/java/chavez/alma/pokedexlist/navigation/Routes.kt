package chavez.alma.pokedexlist.navigation

import kotlinx.serialization.Serializable

@Serializable
object Login

@Serializable
object PokemonList

@Serializable
data class PokemonDetail(val pokemon: Int)

@Serializable
object PokemonHunt

@Serializable
object Profile
