package com.example.imbored.database

/**
 * @author Sophie Azula, 4/29/23
 */

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

/**
 * Provides access to read/write operations on the schedule table.
 * Used by the view models to format the query results for use in the UI.
 */
@Dao
interface LoggedActivityDao {
    // Retrieve all of the LoggedActivities, shown from most recent to least recent
    @Query("SELECT * FROM logged_activity_table ORDER BY activity_date DESC")
    // Using Flow coroutine to observe when data changes
    fun getAll(): Flow<List<LoggedActivity>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(loggedActivity: LoggedActivity)

    @Query("DELETE FROM logged_activity_table")
    fun deleteAll()
}