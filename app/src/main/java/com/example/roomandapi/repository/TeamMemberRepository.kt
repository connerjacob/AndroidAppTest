package com.example.roomandapi.repository

import androidx.lifecycle.LiveData
import com.example.roomandapi.dao.TeamMemberDao
import com.example.roomandapi.entity.TeamMember

class TeamMemberRepository (private val teamMemberDao: TeamMemberDao){
    val readALlData: LiveData<List<TeamMember>> = teamMemberDao.getAllTeamMembers()

    suspend fun addTeamMember(item: List<TeamMember>){
        teamMemberDao.insert(item)
    }

    suspend fun updateTeamMember(item: TeamMember){
        teamMemberDao.update(item)
    }

    suspend fun deleteTeamMember(item: TeamMember){
        teamMemberDao.delete(item)
    }

    suspend fun deleteAll(){
        teamMemberDao.deleteAll()
    }
}