package com.example.roomandapi.viewmodel

import android.app.Application
import androidx.lifecycle.*
import com.example.roomandapi.database.WCDatabase
import com.example.roomandapi.entity.TeamMember
import com.example.roomandapi.repository.TeamMemberRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.IllegalArgumentException

class TeamMemberViewModel(application: Application): AndroidViewModel(application) {
    val readAllTeamMembers: LiveData<List<TeamMember>>
    private val repository: TeamMemberRepository

    init{
        val teamMemberDao = WCDatabase.getInstance(application).teamMemberDao()
        repository = TeamMemberRepository(teamMemberDao = teamMemberDao)
        readAllTeamMembers = repository.readALlData
    }

    fun addTeamMember(item: List<TeamMember>){
        viewModelScope.launch(Dispatchers.IO){
            repository.addTeamMember(item = item)
        }
    }

    fun updateTeamMember(item: TeamMember){
        viewModelScope.launch(Dispatchers.IO){
            repository.updateTeamMember(item = item)
        }
    }

    fun deleteTeamMember(item: TeamMember){
        viewModelScope.launch(Dispatchers.IO){
            repository.deleteTeamMember(item = item)
        }
    }

    fun deleteAllTeamMembers(){
        viewModelScope.launch(Dispatchers.IO){
            repository.deleteAll()
        }
    }
}

class TeamMemberViewModelFactory(
    private val application: Application
): ViewModelProvider.Factory{
    override fun <T: ViewModel?> create(modelClass: Class<T>): T{
        @Suppress("UNCHECKED_CAST")
        if(modelClass.isAssignableFrom(TeamMemberViewModel::class.java)){
            return TeamMemberViewModel(application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}