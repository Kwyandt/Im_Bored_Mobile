package com.example.imbored.database

import androidx.annotation.WorkerThread
import kotlinx.coroutines.flow.Flow

// Declares the DAO as a private property in the constructor. Pass in the DAO
// instead of the whole database, because only need access to the DAO
class ActivityRepository(private val loggedActivityDao: LoggedActivityDao) {

    // Room executes all queries on a separate thread.
    // Observed Flow will notify the observer when the data has changed.
    val allWords: Flow<List<LoggedActivity>> = loggedActivityDao.getAll()

    // By default Room runs suspend queries off the main thread, therefore, we don't need to
    // implement anything else to ensure we're not doing long running database work
    // off the main thread.
    @WorkerThread
    suspend fun insert(loggedActivity: LoggedActivity) {
        loggedActivityDao.insert(loggedActivity)
    }
}