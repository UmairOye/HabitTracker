package com.ub.habittracker.data.database.dbInjection

import android.content.Context
import androidx.room.Room
import com.ub.habittracker.data.database.repository.UserRepositoryImplementation
import com.ub.habittracker.data.database.userDatabase.HabitTrackerDb
import com.ub.habittracker.domain.repository.UserRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideRoomDb(@ApplicationContext context: Context): HabitTrackerDb{
        return Room.databaseBuilder(context, HabitTrackerDb::class.java, "habit_tracker_db")
            .fallbackToDestructiveMigrationFrom().build()
    }


    @Provides
    @Singleton
    fun provideUserRepository(implementation: UserRepositoryImplementation): UserRepository{
        return implementation
    }

    @Provides
    @Singleton
    fun provideUserDao(habitTrackerDb: HabitTrackerDb) = habitTrackerDb.userDao()

}