/**
 * @author Sophie Azula, 4/29/23
 */

package com.example.imbored.ui.gallery

import android.content.Context
import android.content.res.Resources
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.imbored.R
import com.example.imbored.database.GalleryImage

/**
 * Adapter for the [RecyclerView] in [GalleryFragment]. Displays [GalleryImage] data object.
 */

//class GalleryImageListAdapter(galleryFragment: GalleryFragment, myDataset: List<GalleryModel>) : ListAdapter<GalleryImage, GalleryImageListAdapter.GalleryImageViewHolder>(GalleryImageComparator()) {
class GalleryImageListAdapter (
    private val context: Context,
    private val dataset: List<GalleryModel>
) : RecyclerView.Adapter<GalleryImageListAdapter.GalleryItemViewHolder>() {
    class GalleryItemViewHolder(private val view: View) : RecyclerView.ViewHolder(view){
        //TODO will change from find by viewID to somethign else to retrieve from database
        val textView: TextView = view.findViewById(R.id.item_date)
        val imageView: ImageView = view.findViewById(R.id.item_image)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GalleryItemViewHolder{
        val adapterLayout = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_item, parent, false)
        return GalleryItemViewHolder(adapterLayout)
    }

    override fun getItemCount() = dataset.size


    override fun onBindViewHolder(holder: GalleryItemViewHolder, position: Int) {
        val item = dataset[position]
        holder.textView.text = context.resources.getString(item.stringResourceId)
        holder.imageView.setImageResource(item.imageResourceId)
    }


    /**
     * View holder for a GalleryImage

    class GalleryImageViewHolder(itemView: View, imageView: ImageView) : RecyclerView.ViewHolder(itemView, imageView) {
        private val galleryItemView:
                TextView = itemView.findViewById(R.id.item_date)
                //ImageView = imageView.findViewById(R.id.item_image)


        fun bind(text: String?) {
            galleryItemView.text = text
            //galleryItemView.image = image
        }

        companion object {
            fun create(parent: ViewGroup): GalleryImageViewHolder {
                val view: View = LayoutInflater.from(parent.context)
                    .inflate(R.layout.list_item, parent, false)
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
    */
}
