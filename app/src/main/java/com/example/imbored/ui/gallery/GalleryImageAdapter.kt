/**
 * @author Sophie Azula, 4/29/23
 */

package com.example.imbored.ui.gallery

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.imbored.R
import com.example.imbored.database.GalleryImage

/**
 * Adapter for the [RecyclerView] in [GalleryFragment]. Displays [GalleryImage] data object.
 */

class GalleryImageListAdapter(): ListAdapter<GalleryImage, GalleryImageListAdapter.GalleryImageViewHolder>(GalleryImageComparator()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GalleryImageViewHolder {
        return GalleryImageViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: GalleryImageViewHolder, position: Int) {
        val current = getItem(position)
        // Bind LoggedActivities name name
        holder.bind(current.activityName)
    }

    /**
     * View holder for a GalleryImage
     */
    class GalleryImageViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val galleryItemView: TextView = itemView.findViewById(R.id.recycler_text_view)

        fun bind(text: String?) {
            galleryItemView.text = text
        }

        companion object {
            fun create(parent: ViewGroup): GalleryImageViewHolder {
                val view: View = LayoutInflater.from(parent.context)
                    .inflate(R.layout.recyclerview_item, parent, false)
                return GalleryImageViewHolder(view)
            }
        }
    }
    class GalleryImageComparator : DiffUtil.ItemCallback<GalleryImage>() {
        override fun areItemsTheSame(oldItem: GalleryImage, newItem: GalleryImage): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: GalleryImage, newItem: GalleryImage): Boolean {
            return oldItem.activityName == newItem.activityName
        }
    }
}