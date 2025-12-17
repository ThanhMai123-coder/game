package com.example.testthu.data

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import android.content.Context

@Database(entities = [com.example.testthu.data.User::class], version = 2) // Increment the version here
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): com.example.testthu.data.UserDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "user_database"
                )
                    .addMigrations(MIGRATION_1_2) // Add the migration
                    .build()
                INSTANCE = instance
                instance
            }
        }

        val MIGRATION_1_2 = object : Migration(1, 2) {
            override fun migrate(database: SupportSQLiteDatabase) {
                // Drop the old table
                database.execSQL("DROP TABLE IF EXISTS user")
                // Create the new table with the current schema
                database.execSQL("""
                    CREATE TABLE user (
                        username TEXT PRIMARY KEY NOT NULL,
                        password TEXT,
                        role TEXT
                    )
                """.trimIndent())
            }
        }
    }
}