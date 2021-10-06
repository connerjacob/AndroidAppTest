package com.example.roomandapi.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "team")
data class TeamMember(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id", index=true)
    var id: Int,

    @ColumnInfo(name = "firstName")
    var firstName: String,

    @ColumnInfo(name = "lastName")
    var lastName: String,

    @ColumnInfo(name = "bio")
    var bio: String,

    @ColumnInfo(name = "imageUrl")
    var imageUrl: String
)
