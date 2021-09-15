package com.picpay.desafio.android

import com.picpay.desafio.android.model.User
import com.picpay.desafio.android.repository.local.UserEntity
import com.picpay.desafio.android.util.ResultRepositoryModel
import java.util.*

val mockListUser: ArrayList<User> = arrayListOf(
    User(
        1,
        "x",
        "nome usuario",
        "nomeusuario"
    )
)

val mockListUserEntity: ArrayList<UserEntity> = arrayListOf(
    UserEntity(
        1,
        "x",
        "nome usuario",
        "nomeusuario"
    )
)

val listContactsSuccess: ResultRepositoryModel<List<User>> =
    ResultRepositoryModel(
        data = mockListUser
    )

val listContactsNotSuccess: ResultRepositoryModel<List<User>> =
    ResultRepositoryModel(
        data = arrayListOf(),
        isSuccess = false,
        messageError = "Não foi possível concluir sua solicitação no momento"
    )