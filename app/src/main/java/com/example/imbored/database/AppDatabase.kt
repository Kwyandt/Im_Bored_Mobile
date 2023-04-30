/**
 * @author Sophie Azula, 4/29/23
 */

package com.example.imbored.database

import androidx.room.Database
import androidx.room.RoomDatabase
import android.content.Context
import androidx.room.Room
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Database(entities = arrayOf(GalleryImage::class), version = 1, exportSchema = false)
    abstract class AppDatabase :RoomDatabase() {
    abstract fun galleryImageDao(): GalleryImageDao
    // Companion object to hold singular instance of database

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null
        fun getDatabase(
            context: Context,
            scope: CoroutineScope
        ): AppDatabase {
            // Return existing instance (if it exists)
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "app_database"
                )
                    .addCallback(AppDatabaseCallback(scope))
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
    /**
     * Custom implementation of RoomDatabase.Callback()
     * @param scope CoroutineScope of the instance
     */
    private class AppDatabaseCallback(
        private val scope: CoroutineScope
    ) : RoomDatabase.Callback() {

        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)
            INSTANCE?.let { database ->
                scope.launch {
                    var galleryImageDao = database.galleryImageDao()

                    // Delete all content here.
                    galleryImageDao.deleteAll()
                    // Add sample words.
                    var activity = GalleryImage("Activity date 1", "Name 1", "Path 1")
                    galleryImageDao.insert(activity)
                    activity = GalleryImage("Activity date 2", "Name 2", "Path 2")
                    galleryImageDao.insert(activity)

                    // TODO: Add an example activity to immediately display
                }
            }
        }
    }
}
