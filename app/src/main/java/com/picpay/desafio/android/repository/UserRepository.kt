package com.picpay.desafio.android.repository

import com.picpay.desafio.android.model.User
import com.picpay.desafio.android.repository.local.UserEntity
import com.picpay.desafio.android.repository.local.UserLocalDataSource
import com.picpay.desafio.android.repository.remote.UserRemoteDataSource
import com.picpay.desafio.android.util.ResultRepositoryModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class UserRepository(
    private val userRemoteDataSource: UserRemoteDataSource,
    private val userLocalDataSource: UserLocalDataSource
) {
    suspend fun getContacts(): ResultRepositoryModel<List<User>> = withContext(Dispatchers.IO) {
        try {
            val response = userRemoteDataSource.getUsers()
            response.isEmpty()
            saveListInLocal(response)
            ResultRepositoryModel(
                isSuccess = true,
                data = response
            )
        } catch (exception: Exception) {
            if (!getContactsLocal().isNullOrEmpty()) {
                ResultRepositoryModel(
                    isSuccess = true,
                    data = getContactsLocal()
                )
            } else {
                ResultRepositoryModel(
                    isSuccess = false,
                    data = getContactsLocal(),
                    messageError = exception.message.toString()
                )
            }
        }
    }

    suspend fun getContactsLocal(): List<User> {
        return userLocalDataSource.getAllUsers().map {
            User(
                it.id,
                it.img,
                it.name,
                it.username
            )
        }
    }

    private suspend fun saveListInLocal(users: List<User>) {
        userLocalDataSource.removeLocalAndInsertAllUsers(
            users.map {
                UserEntity(
                    it.id,
                    it.img,
                    it.name,
                    it.username
                )
            }
        )
    }
}
