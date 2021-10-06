package com.example.roomandapi.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.roomandapi.dao.ProjectDao
import com.example.roomandapi.dao.TeamMemberDao
import com.example.roomandapi.entity.ProjectEntity
import com.example.roomandapi.entity.TeamMember
import com.example.workout_companion.utility.DateTimeConverter

@Database(entities = [
    TeamMember::class,
    ProjectEntity::class
                     ],
    version = 5,
    exportSchema = false)
@TypeConverters(DateTimeConverter::class)
abstract class WCDatabase: RoomDatabase() {
    abstract fun teamMemberDao(): TeamMemberDao
    abstract fun projectDao(): ProjectDao

    companion object{
        @Volatile
        private var INSTANCE: WCDatabase? = null

        fun getInstance(context: Context): WCDatabase {
            val sampleInstance = INSTANCE
            if(sampleInstance != null){
                return sampleInstance
            }
            synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    WCDatabase::class.java,
                    "workout_companion_database"
                ).fallbackToDestructiveMigration().build()

                INSTANCE = instance
                return instance
            }


        }
    }
}