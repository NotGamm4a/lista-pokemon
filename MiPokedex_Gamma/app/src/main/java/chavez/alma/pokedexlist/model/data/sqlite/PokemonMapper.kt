package chavez.alma.pokedexlist.model.data.sqlite

import android.content.ContentValues
import android.database.Cursor
import chavez.alma.pokedexlist.model.domain.Pokemon

object PokemonMapper {
    fun getPokemon(cursor: Cursor): Pokemon {
        val number = cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_NAME_NUMBER))
        val name = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_NAME_NAME))
        val description = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_NAME_DESCRIPTION))
        val type = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_NAME_TYPE))
        val height = cursor.getFloat(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_NAME_HEIGHT))
        val weight = cursor.getFloat(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_NAME_WEIGHT))
        val favorite = cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_NAME_FAVORITE)) == 1
        val ability = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_NAME_ABILITY))
        val image = cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_NAME_IMAGE))

        return Pokemon(
            name = name,
            number = number,
            type = type,
            description = description,
            height = height,
            weight = weight,
            favorite = favorite,
            ability = ability,
            image = image
        )
    }

    fun getContentValues(pokemon: Pokemon): ContentValues {
        return ContentValues().apply {
            put(DatabaseHelper.COLUMN_NAME_NUMBER, pokemon.number)
            put(DatabaseHelper.COLUMN_NAME_NAME, pokemon.name)
            put(DatabaseHelper.COLUMN_NAME_DESCRIPTION, pokemon.description)
            put(DatabaseHelper.COLUMN_NAME_TYPE, pokemon.type)
            put(DatabaseHelper.COLUMN_NAME_HEIGHT, pokemon.height)
            put(DatabaseHelper.COLUMN_NAME_WEIGHT, pokemon.weight)
            put(DatabaseHelper.COLUMN_NAME_FAVORITE, if (pokemon.favorite) 1 else 0)
            put(DatabaseHelper.COLUMN_NAME_ABILITY, pokemon.ability)
            put(DatabaseHelper.COLUMN_NAME_IMAGE, pokemon.image)
        }
    }
}
