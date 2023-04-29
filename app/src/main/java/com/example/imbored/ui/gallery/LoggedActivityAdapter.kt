package com.example.imbored.ui.gallery

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.imbored.R
import com.example.imbored.database.LoggedActivity

/**
 * Adapter for the [RecyclerView] in [GalleryFragment]. Displays [LoggedActivity] data object.
 */

class LoggedActivityListAdapter : ListAdapter<LoggedActivity, LoggedActivityListAdapter.LoggedActivityViewHolder>(LoggedActivityComparator()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LoggedActivityViewHolder {
        return LoggedActivityViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: LoggedActivityViewHolder, position: Int) {
        val current = getItem(position)
        // Bind by activity name
        holder.bind(current.activityName)
    }

    class LoggedActivityViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val loggedActivityItemView: TextView = itemView.findViewById(R.id.recycler_text_view)

        fun bind(text: String?) {
            loggedActivityItemView.text = text
        }

        companion object {
            fun create(parent: ViewGroup): LoggedActivityViewHolder {
                val view: View = LayoutInflater.from(parent.context)
                    .inflate(R.layout.recyclerview_item, parent, false)
                return LoggedActivityViewHolder(view)
            }
        }
    }

    class LoggedActivityComparator : DiffUtil.ItemCallback<LoggedActivity>() {
        override fun areItemsTheSame(oldItem: LoggedActivity, newItem: LoggedActivity): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: LoggedActivity, newItem: LoggedActivity): Boolean {
            return oldItem.activityName == newItem.activityName
        }
    }
}