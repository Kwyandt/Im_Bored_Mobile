/**
 * @author Sophie Azula, 4/29/23
 */
package com.example.imbored.database

import androidx.annotation.WorkerThread
import kotlinx.coroutines.flow.Flow

// Declares the DAO as a private property in the constructor. Pass in the DAO
// instead of the whole database, because only need access to the DAO
class ActivityRepository(private val galleryImageDao: GalleryImageDao) {

    // Room executes all queries on a separate thread.
    // Observed Flow will notify the observer when the data has changed.
    val allWords: Flow<List<GalleryImage>> = galleryImageDao.getAll()

    // By default Room runs suspend queries off the main thread, therefore, we don't need to
    // implement anything else to ensure we're not doing long running database work
    // off the main thread.
    @WorkerThread
    suspend fun insert(galleryImage: GalleryImage) {
        galleryImageDao.insert(galleryImage)
    }
}