package chavez.alma.pokedexlist.model.data.sqlite

import chavez.alma.pokedexlist.model.data.interfaces.IPokemonDAO
import chavez.alma.pokedexlist.model.data.interfaces.IPokemonRepository
import chavez.alma.pokedexlist.model.domain.Pokemon

class PokemonSQLRepository(private val dao: IPokemonDAO) : IPokemonRepository {

    override suspend fun addPokemon(pokemon: Pokemon): Pokemon {
        require(pokemon.name.isNotBlank()) { "El nombre del Pokemon no puede estar vacio" }
        require(pokemon.number > 0) { "El numero del Pokemon debe ser mayor a cero" }
        return dao.insertPokemon(pokemon)
    }

    override suspend fun getPokemons(): List<Pokemon> = dao.getAllPokemons()

    override suspend fun getFavoritePokemons(): List<Pokemon> = dao.getFavoritePokemons()

    override suspend fun getPokemonsByType(type: String): List<Pokemon> =
        dao.getPokemonsByType(type)

    override suspend fun updatePokemon(pokemon: Pokemon) = dao.updatePokemon(pokemon)

    override suspend fun deletePokemon(id: Int) = dao.deletePokemon(id)
}
