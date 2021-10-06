package com.example.roomandapi.repository

import androidx.lifecycle.LiveData
import com.example.roomandapi.dao.ProjectDao
import com.example.roomandapi.entity.ProjectEntity
import com.example.roomandapi.entity.UserProjects

class ProjectRepository(private val ProjectDao: ProjectDao) {
    val getAll: LiveData<List<ProjectEntity>> = ProjectDao.selectAll()

    suspend fun getProjectsByuUser(firstName: String, lastName: String): List<UserProjects>{
        return ProjectDao.selectByUser(firstName, lastName)
    }

    suspend fun addProject(item: ProjectEntity){
        return ProjectDao.addProject(item)
    }

    suspend fun addProject(item: List<ProjectEntity>){
        return ProjectDao.addProject(item)
    }

    suspend fun updateProject(item: ProjectEntity){
        return ProjectDao.updateProject(item)
    }

    suspend fun deleteProject(item: ProjectEntity){
        return ProjectDao.delete(item)
    }

}
