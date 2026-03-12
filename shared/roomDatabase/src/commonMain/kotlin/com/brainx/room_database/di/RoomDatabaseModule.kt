package com.brainx.room_database.di

import com.brainx.room_database.repository.UserDbRepository
import com.brainx.room_database.setup.AppDatabase
import com.brainx.room_database.setup.DatabaseFactory
import com.brainx.room_database.setup.createAppDatabase
import org.koin.core.module.Module
import org.koin.dsl.module

internal expect val roomPlatformModule: Module

private val roomDatabaseModule = module {
    single { createAppDatabase(get<DatabaseFactory>().createDatabase()) }

}

private val roomDaoModule = module {
    single { get<AppDatabase>().userDao() }
}

private val roomRepositoryModule = module {
    single { UserDbRepository(get()) }
}


val provideRoomDatabaseModule = listOf( roomPlatformModule,roomDatabaseModule,roomDaoModule,roomRepositoryModule )