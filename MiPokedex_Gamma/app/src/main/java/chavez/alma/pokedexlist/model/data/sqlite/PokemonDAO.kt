package chavez.alma.pokedexlist.model.data.sqlite

import android.database.sqlite.SQLiteDatabase
import chavez.alma.pokedexlist.model.data.interfaces.IPokemonDAO
import chavez.alma.pokedexlist.model.domain.Pokemon
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class PokemonDAO(private val helper: DatabaseHelper) : IPokemonDAO {

    override suspend fun insertPokemon(pokemon: Pokemon): Pokemon =
        withContext(Dispatchers.IO) {
            val db = helper.writableDatabase
            db.insert(DatabaseHelper.TABLE_POKEMONS, null, PokemonMapper.getContentValues(pokemon))
            pokemon
        }

    override suspend fun getAllPokemons(): List<Pokemon> =
        withContext(Dispatchers.IO) {
            val db = helper.readableDatabase
            val cursor = db.query(
                DatabaseHelper.TABLE_POKEMONS,
                null,
                null,
                null,
                null,
                null,
                "${DatabaseHelper.COLUMN_ID} ASC"
            )
            val pokemons = mutableListOf<Pokemon>()
            cursor.use {
                while (it.moveToNext()) {
                    pokemons.add(PokemonMapper.getPokemon(it))
                }
            }
            pokemons
        }

    override suspend fun getPokemonById(id: Int): Pokemon? =
        withContext(Dispatchers.IO) {
            helper.readableDatabase.query(
                DatabaseHelper.TABLE_POKEMONS,
                null,
                "${DatabaseHelper.COLUMN_ID} = ?",
                arrayOf(id.toString()),
                null,
                null,
                null
            ).use { cursor ->
                if (cursor.moveToFirst()) PokemonMapper.getPokemon(cursor) else null
            }
        }

    override suspend fun getFavoritePokemons(): List<Pokemon> =
        withContext(Dispatchers.IO) {
            queryPokemons(
                "${DatabaseHelper.COLUMN_NAME_FAVORITE} = ?",
                arrayOf("1")
            )
        }

    override suspend fun getPokemonsByType(type: String): List<Pokemon> =
        withContext(Dispatchers.IO) {
            queryPokemons(
                "${DatabaseHelper.COLUMN_NAME_TYPE} = ?",
                arrayOf(type)
            )
        }

    override suspend fun updatePokemon(pokemon: Pokemon) =
        withContext(Dispatchers.IO) {
            helper.writableDatabase.update(
                DatabaseHelper.TABLE_POKEMONS,
                PokemonMapper.getContentValues(pokemon),
                "${DatabaseHelper.COLUMN_NAME_NUMBER} = ?",
                arrayOf(pokemon.number.toString())
            )
            Unit
        }

    override suspend fun deletePokemon(id: Int) =
        withContext(Dispatchers.IO) {
            helper.writableDatabase.delete(
                DatabaseHelper.TABLE_POKEMONS,
                "${DatabaseHelper.COLUMN_ID} = ?",
                arrayOf(id.toString())
            )
            Unit
        }

    private fun queryPokemons(selection: String, arguments: Array<String>): List<Pokemon> {
        val cursor = helper.readableDatabase.query(
            DatabaseHelper.TABLE_POKEMONS,
            null,
            selection,
            arguments,
            null,
            null,
            "${DatabaseHelper.COLUMN_ID} ASC"
        )
        val pokemons = mutableListOf<Pokemon>()
        cursor.use {
            while (it.moveToNext()) {
                pokemons.add(PokemonMapper.getPokemon(it))
            }
        }
        return pokemons
    }
}
