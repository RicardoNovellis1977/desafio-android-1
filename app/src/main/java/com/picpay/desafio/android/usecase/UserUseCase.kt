package com.picpay.desafio.android.usecase

import com.picpay.desafio.android.model.User
import com.picpay.desafio.android.repository.UserRepository
import com.picpay.desafio.android.util.ResultRepositoryModel


class UserUseCase(private val repository: UserRepository) {
    suspend fun getContacts(): ResultRepositoryModel<List<User>> = repository.getContacts()
}