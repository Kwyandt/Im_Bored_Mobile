package com.example.imbored

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.imbored.database.ActivityRepository
import com.example.imbored.database.GalleryImage
import kotlinx.coroutines.launch

class GalleryImageViewModel(private val repository: ActivityRepository) : ViewModel() {

    // Using LiveData to cache a list of GalleryImages
    val allWords: LiveData<List<GalleryImage>> = repository.allWords.asLiveData()

    /**
     * Launching a new coroutine to insert the data in a non-blocking way
     */
    fun insert(galleryImage: GalleryImage) = viewModelScope.launch {
        repository.insert(galleryImage)
    }
}
class GalleryImageViewModelFactory(private val repository: ActivityRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(GalleryImageViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return GalleryImageViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
