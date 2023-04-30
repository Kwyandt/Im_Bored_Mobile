/**
 * @author Sophie Azula, 4/29/23
 */
package com.example.imbored.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

// Recognize the class is used to define database tables
@Entity(tableName = "gallery_image_table")
data class GalleryImage (
    // Primary key to identify the table, using the Date to identify
    // TODO: Date should be to the second to ensure unique ID's
    @PrimaryKey @ColumnInfo (name="activity_date") val activityDate: String,
    @ColumnInfo(name="activity_name") val activityName: String,
    // store the file path URI rather than the actual image
    @ColumnInfo(name="photo_path") val photoPath: String
)