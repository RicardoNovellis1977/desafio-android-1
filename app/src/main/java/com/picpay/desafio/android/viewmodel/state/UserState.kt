package com.picpay.desafio.android.viewmodel.state

import com.picpay.desafio.android.model.User

sealed class UserState {
    data class SuccessListUsers(val users: List<User>) : UserState()
    data class ErrorListUsers(val message: String) : UserState()
}