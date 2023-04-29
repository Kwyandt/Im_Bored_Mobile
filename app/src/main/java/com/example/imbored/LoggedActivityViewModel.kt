package com.example.imbored

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.imbored.database.ActivityRepository
import com.example.imbored.database.LoggedActivity
import kotlinx.coroutines.launch

class LoggedActivityViewModel(private val repository: ActivityRepository) : ViewModel() {

    // Using LiveData to cache a list of LoggedActivities
    val allWords: LiveData<List<LoggedActivity>> = repository.allWords.asLiveData()

    /**
     * Launching a new coroutine to insert the data in a non-blocking way
     */
    fun insert(loggedActivity: LoggedActivity) = viewModelScope.launch {
        repository.insert(loggedActivity)
    }
}
class LoggedActivityViewModelFactory(private val repository: ActivityRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(LoggedActivityViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return LoggedActivityViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
