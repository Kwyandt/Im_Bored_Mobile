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
interface GalleryImageDao {
    // Retrieve all of the GalleryImages, shown from most recent to least recent
    @Query("SELECT * FROM gallery_image_table ORDER BY activity_date DESC")
    // Using Flow coroutine to observe when data changes
    fun getAll(): Flow<List<GalleryImage>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(galleryImage: GalleryImage)

    /**
     * Probably won't use this
     */
    @Query("DELETE FROM gallery_image_table")
    fun deleteAll()
}