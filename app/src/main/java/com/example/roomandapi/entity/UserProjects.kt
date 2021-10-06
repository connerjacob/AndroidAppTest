package com.example.roomandapi.entity

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.Relation
import com.example.roomandapi.repository.TeamMemberRepository

@Entity
data class UserProjects (
    @Embedded val project: ProjectEntity,
    @Relation(
        parentColumn = "userId",
        entityColumn = "id"
    )
    val teamMember: TeamMember
    )
