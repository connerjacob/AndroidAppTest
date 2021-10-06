package com.example.roomandapi.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import java.time.LocalDate

@Entity(tableName = "project",
        foreignKeys = [ForeignKey(
                                entity = TeamMember::class,
                                parentColumns = ["id"],
                                childColumns = ["userId"],
                                onDelete = ForeignKey.CASCADE
                        )
                ]
)
data class ProjectEntity (
        @PrimaryKey(autoGenerate = true)
        @ColumnInfo(name = "id")
        val id: Int,

        @ColumnInfo(name = "name")
        val name: String,

        @ColumnInfo(name = "type")
        val type: String,

        @ColumnInfo(name = "difficulty")
        val difficulty: String,

        @ColumnInfo(name = "date")
        val date: LocalDate,


        @ColumnInfo(name = "userId", index = true)
        val userId: Int
        )