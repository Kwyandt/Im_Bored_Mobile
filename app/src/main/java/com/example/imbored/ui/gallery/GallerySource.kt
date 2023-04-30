package com.example.imbored.ui.gallery

import com.example.imbored.R

//TODO this might be temp, need to change to database datasource
class GallerySource () {

    fun loadImages(): List<GalleryModel> {
        return listOf<GalleryModel>(
            GalleryModel(R.string.n1, R.drawable.panda_1),
            GalleryModel(R.string.n2, R.drawable.panda_2),
            GalleryModel(R.string.n3, R.drawable.panda_3),
            GalleryModel(R.string.n4, R.drawable.panda_4),
            GalleryModel(R.string.n5, R.drawable.panda_5),
            GalleryModel(R.string.n6, R.drawable.panda_6),
            GalleryModel(R.string.n7, R.drawable.panda_7),
            GalleryModel(R.string.n8, R.drawable.panda_8)
        )
    }
}
