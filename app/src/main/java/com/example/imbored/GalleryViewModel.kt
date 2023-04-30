/**
 * Sophie Azula, 4/29/30
 * ViewModel for the [GalleryFragment] functionality
 */
package com.example.imbored
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras
import com.example.imbored.database.ActivityRepository
import com.example.imbored.database.GalleryImage
import com.example.imbored.database.GalleryImageDao
import kotlinx.coroutines.flow.Flow

    class GalleryViewModel(private val galleryImageDao: GalleryImageDao) : ViewModel() {
        fun allGalleryImages(): Flow<List<GalleryImage>> = galleryImageDao.getAll()
//    private val _text = MutableLiveData<String>().apply {
//        value = "This is Gallery Fragment"
//    }
//    val text: LiveData<String> = _text
//    private val mRepository: ActivityRepository? = null
//    // - Repository is completely separated from the UI through the ViewModel.

        class GalleryViewModelFactory(
            private val galleryImageDao: GalleryImageDao
        ) : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                if (modelClass.isAssignableFrom(GalleryViewModel::class.java)){
                    @Suppress("UNCHECKED_CAST")
                    return GalleryViewModel(galleryImageDao) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}

/**
 * TODO: When the Camera takes a picture, store it as Live Data and update the Views
 * (Live Data should trigger UI and DB implementation)
 */

//class HomeViewModel : ViewModel() {
//    // Variable to hold the Completion answer, as a String
//    private val _answerString = MutableLiveData<String>()
//    var answerString: LiveData<String> = _answerString
//    private var previousResponses: List<String> = listOf("")
//}