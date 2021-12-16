package dev.kibet.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import dev.kibet.data.local.dao.CharactersDao
import dev.kibet.data.local.entity.CharacterEntity

@Database(entities = [CharacterEntity::class], version = 2, exportSchema = false)
abstract class CharactersDatabase : RoomDatabase() {
    abstract fun charactersDao(): CharactersDao
}
