package com.whiterabbit.wrgemployees.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import com.whiterabbit.wrgemployees.local.MachineTestWRGDatabase
import com.whiterabbit.wrgemployees.local.MachineTestWRGDao
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataBaseModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): MachineTestWRGDatabase {
        return Room.databaseBuilder(
            context.applicationContext,
            MachineTestWRGDatabase::class.java,
            "machine_test_wrb_database.db"
        )
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun provideDao(database: MachineTestWRGDatabase): MachineTestWRGDao {
        return database.machineTestDao()
    }
}
