package com.example.roomandapi.viewmodel

import android.app.Application
import androidx.lifecycle.*
import com.example.roomandapi.dao.ProjectDao
import com.example.roomandapi.database.WCDatabase
import com.example.roomandapi.entity.ProjectEntity
import com.example.roomandapi.entity.TeamMember
import com.example.roomandapi.entity.UserProjects
import com.example.roomandapi.repository.ProjectRepository
import com.example.roomandapi.repository.TeamMemberRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.IllegalArgumentException

class ProjectViewModel(application: Application): AndroidViewModel(application) {
    val getAllProjects: LiveData<List<ProjectEntity>>
    private val repository: ProjectRepository

    init{
        val projectDao = WCDatabase.getInstance(application).projectDao()
        repository = ProjectRepository(projectDao)
        getAllProjects = repository.getAll
    }

    fun getProjectsByName(firstName: String, lastName: String): List<UserProjects> {
        var userProjectList: List<UserProjects> = listOf()
        viewModelScope.launch(Dispatchers.IO) {
            userProjectList =  repository.getProjectsByuUser(firstName, lastName)
        }
        return userProjectList
    }

    fun addProject(item: List<ProjectEntity>){
        viewModelScope.launch(Dispatchers.IO) {
            repository.addProject(item)
        }
    }

    fun addProject(item: ProjectEntity){
        viewModelScope.launch(Dispatchers.IO) {
            repository.addProject(item)
        }
    }

    fun updateProject(item: ProjectEntity){
        viewModelScope.launch(Dispatchers.IO){
            repository.updateProject(item = item)
        }
    }

    fun deleteProject(item: ProjectEntity){
        viewModelScope.launch(Dispatchers.IO){
            repository.deleteProject(item = item)
        }
    }
}

class ProjectViewModelFactory(
    private val application: Application
): ViewModelProvider.Factory{
    override fun <T: ViewModel?> create(modelClass: Class<T>): T{
        @Suppress("UNCHECKED_CAST")
        if(modelClass.isAssignableFrom(ProjectViewModel::class.java)){
            return ProjectViewModel(application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}