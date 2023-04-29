/**
 * @author Sophie Azula, 4/29/23
 */

package com.example.imbored.database

import androidx.room.Database
import androidx.room.RoomDatabase
import android.content.Context
import android.util.Log
import androidx.room.Room
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Database(entities = arrayOf(LoggedActivity::class), version = 1, exportSchema = false)
public abstract class AppDatabase :RoomDatabase() {
    abstract fun loggedActivityDao(): LoggedActivityDao
    // Companion object to hold singular instance of database

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
                    var loggedActivityDao = database.loggedActivityDao()

                    // Delete all content here.
                    loggedActivityDao.deleteAll()
                    // Add sample words.
                    var activity = LoggedActivity("Hello", "", "")
                    loggedActivityDao.insert(activity)
                    activity = LoggedActivity("World", "", "")
                    loggedActivityDao.insert(activity)

                    // TODO: Add an example activity to immediately display
                }
            }
        }
    }


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
}
