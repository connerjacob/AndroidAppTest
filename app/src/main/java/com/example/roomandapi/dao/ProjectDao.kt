package com.example.roomandapi.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.roomandapi.entity.ProjectEntity
import com.example.roomandapi.entity.UserProjects

@Dao
interface ProjectDao {
    @Query("SELECT * FROM project")
    fun selectAll(): LiveData<List<ProjectEntity>>

    @Query("""SELECT * FROM project
                    INNER JOIN team 
                   ON project.userId = team.id
                   WHERE team.firstName = :first_name 
                    AND team.lastName = :last_name""")
    suspend fun selectByUser(first_name: String, last_name: String): List<UserProjects>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addProject(item: ProjectEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addProject(item: List<ProjectEntity>)

    @Update
    suspend fun updateProject(item: ProjectEntity)

    @Delete
    suspend fun delete(item: ProjectEntity)
}