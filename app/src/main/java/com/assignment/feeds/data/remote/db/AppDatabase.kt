package com.assignment.feeds.data.remote.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.assignment.feeds.data.remote.db.AppDatabase.Companion.DB_VERSION
import com.assignment.feeds.data.remote.db.dao.RowDataDao
import com.assignment.feeds.data.remote.db.entity.RowData

@Database(entities = [RowData::class], version = DB_VERSION, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun getRowDataDao(): RowDataDao

    companion object {
        const val DB_VERSION = 1
        private const val DB_NAME = "mvvm_demo.db"
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: build(context).also { INSTANCE = it }
            }

        private fun build(context: Context) =
            Room.databaseBuilder(context.applicationContext, AppDatabase::class.java, DB_NAME)
                .addMigrations(MIGRATION_1_TO_2)
                .build()

        private val MIGRATION_1_TO_2 = object : Migration(1, 2) {
            override fun migrate(database: SupportSQLiteDatabase) {

            }
        }
    }
}