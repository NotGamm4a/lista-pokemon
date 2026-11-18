package chavez.alma.pokedexlist.model.data.sqlite

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DatabaseHelper(context: Context) :
    SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        const val DATABASE_NAME = "pokedex.db"
        const val DATABASE_VERSION = 1
        const val TABLE_POKEMONS = "Pokemons"
        const val COLUMN_ID = "id"
        const val COLUMN_NAME_NUMBER = "number"
        const val COLUMN_NAME_NAME = "name"
        const val COLUMN_NAME_DESCRIPTION = "description"
        const val COLUMN_NAME_TYPE = "type"
        const val COLUMN_NAME_HEIGHT = "height"
        const val COLUMN_NAME_WEIGHT = "weight"
        const val COLUMN_NAME_FAVORITE = "favorite"
        const val COLUMN_NAME_ABILITY = "ability"
        const val COLUMN_NAME_IMAGE = "image"
    }

    override fun onCreate(db: SQLiteDatabase) {
        val createPokemons = """
            CREATE TABLE $TABLE_POKEMONS (
                $COLUMN_ID INTEGER PRIMARY KEY AUTOINCREMENT,
                $COLUMN_NAME_NUMBER INTEGER,
                $COLUMN_NAME_NAME TEXT,
                $COLUMN_NAME_DESCRIPTION TEXT,
                $COLUMN_NAME_TYPE TEXT,
                $COLUMN_NAME_HEIGHT REAL,
                $COLUMN_NAME_WEIGHT REAL,
                $COLUMN_NAME_FAVORITE INTEGER,
                $COLUMN_NAME_ABILITY TEXT,
                $COLUMN_NAME_IMAGE INTEGER
            )
        """.trimIndent()

        db.execSQL(createPokemons)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS $TABLE_POKEMONS")
        onCreate(db)
    }
}
