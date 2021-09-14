package com.picpay.desafio.android.repository.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    version = DataBaseConstants.DATABASE_VERSION,
    entities = [UserEntity::class],
    exportSchema = false
)
abstract class UserDataBase : RoomDatabase() {
    abstract fun userDAO(): UserLocalDataSource
}