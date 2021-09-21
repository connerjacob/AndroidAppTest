package com.example.roomandapi.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.roomandapi.entity.TeamMember

@Dao
interface TeamMemberDao {
    @Query("SELECT * FROM team")
    fun getAllTeamMembers(): LiveData<List<TeamMember>>

    @Query("SELECT * FROM team WHERE id = :id")
    suspend fun getById(id: Int): TeamMember

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(item: List<TeamMember>)

    @Update
    suspend fun update(item: TeamMember)

    @Delete
    suspend fun delete(item: TeamMember)

    @Query("DELETE FROM team")
    suspend fun deleteAll()
}