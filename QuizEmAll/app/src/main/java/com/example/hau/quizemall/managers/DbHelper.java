package com.example.hau.quizemall.managers;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.hau.quizemall.models.Pokemon;
import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

/**
 * Created by Hau on 20/11/2016.
 */
public class DbHelper extends SQLiteAssetHelper {

    private static final String DATABASE_NAME = "pokemon.db";
    private static final int DATABASE_VERSION = 1;

    private static final String POKEMON_TABLE_NAME = "pokemon";
    private static final String POKEMON_COLUMN_ID = "id";
    private static final String POKEMON_COLUMN_NAME = "name";
    private static final String POKEMON_COLUMN_TAG = "tag";
    private static final String POKEMON_COLUMN_GEN = "gen";
    private static final String POKEMON_COLUMN_IMAGE = "img";
    private static final String POKEMON_COLUMN_COLOR = "color";

    private static final String[] POKEMON_COLUMNS = new String[] {
            POKEMON_COLUMN_ID,
            POKEMON_COLUMN_NAME,
            POKEMON_COLUMN_GEN,
            POKEMON_COLUMN_TAG,
            POKEMON_COLUMN_IMAGE,
            POKEMON_COLUMN_COLOR
    };

    public DbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public Pokemon selectRandomPokemon() {
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.query(POKEMON_TABLE_NAME,
                POKEMON_COLUMNS,
                null,
                null,
                null,
                null,
                "RANDOM()",
                "1");
        if (cursor.moveToNext()) {
            return createPokemon(cursor);
        }
        db.close();
        return null;
    }

    private Pokemon createPokemon(Cursor cursor) {
        int id = cursor.getInt(cursor.getColumnIndex(POKEMON_COLUMN_ID));
        String name = cursor.getString(cursor.getColumnIndex(POKEMON_COLUMN_NAME));
        String tag = cursor.getString(cursor.getColumnIndex(POKEMON_COLUMN_TAG));
        int gen = cursor.getInt(cursor.getColumnIndex(POKEMON_COLUMN_GEN));
        String image = cursor.getString(cursor.getColumnIndex(POKEMON_COLUMN_IMAGE));
        String color = cursor.getString(cursor.getColumnIndex(POKEMON_COLUMN_COLOR));
        Pokemon pokemon = new Pokemon(id, name, tag, gen, image, color);
        return pokemon;

    }

    private static DbHelper instance = null;

    public static DbHelper getInstance() {
        return instance;
    }

    public static void init(Context context) {
        instance = new DbHelper(context);
    }
}
