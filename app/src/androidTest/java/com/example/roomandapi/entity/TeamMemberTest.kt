package com.example.roomandapi.entity

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.example.roomandapi.dao.TeamMemberDao
import com.example.roomandapi.database.WCDatabase
import junit.framework.TestCase
import kotlinx.coroutines.runBlocking
import org.hamcrest.CoreMatchers
import org.hamcrest.MatcherAssert
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class TeamMemberTest : TestCase(){
    private lateinit var db: WCDatabase
    private lateinit var dao: TeamMemberDao

    @Before
    public override fun setUp(){
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(context, WCDatabase::class.java).build()
        dao = db.teamMemberDao()
    }

    @After
    fun closeDB(){
        db.close()
    }

    @Test
    fun TestWriteAndReadUser() = runBlocking(){
        val members: List<TeamMember> =  listOf(
            TeamMember(1,
            "Jacob",
            "Conner",
            "Jacob Conner is a senior at Old Dominion University majoring in Computer Science. Currently, he lives in Blacksburg, Virginia, and works in a chat-based technical support role for Dish Network. Some of his hobbies include archaeology, learning Japanese and browsing Linkedin Learning.")
        )
        dao.insert()
        val byName = dao.getByName("Jacob", "Conner")
        MatcherAssert.assertThat(byName, CoreMatchers.equalTo(members[0]))
    }
}

