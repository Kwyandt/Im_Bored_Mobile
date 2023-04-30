/**
 * @author Sophie Azula, 4/29/23
 * Contain the application scope and database initialization to one instance
 */
package com.example.imbored

import android.app.Application
import com.example.imbored.database.ActivityRepository
import com.example.imbored.database.AppDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

class ActivitiesApplication: Application() {
    // No need to cancel this scope as it'll be torn down with the process
    private val applicationScope = CoroutineScope(SupervisorJob())
    // Using by lazy so the database and the repository are only created when they're needed
    // rather than when the application starts
//    val database:AppDatabase by lazy {AppDatabase.getDatabase(this, applicationScope) }
    val repository by lazy{ ActivityRepository(database.galleryImageDao())}
    val database: AppDatabase by lazy { AppDatabase.getDatabase(this,applicationScope) }

}
