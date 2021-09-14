package com.picpay.desafio.android.di

import androidx.room.Room
import com.picpay.desafio.android.repository.UserRepository
import com.picpay.desafio.android.repository.local.DataBaseConstants
import com.picpay.desafio.android.repository.local.UserDataBase
import com.picpay.desafio.android.repository.remote.ApiService
import com.picpay.desafio.android.repository.remote.UserRemoteDataSource
import com.picpay.desafio.android.usecase.UserUseCase
import com.picpay.desafio.android.viewmodel.UserViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module

object UserDI {
    val userModules: Module = module {
        viewModel { UserViewModel(useCase = get()) }
        factory { UserUseCase(repository = get()) }
        factory { UserRepository(get(), get()) }
        single { ApiService.instance.create(UserRemoteDataSource::class.java) }
        single {
            Room.databaseBuilder(
                get(),
                UserDataBase::class.java,
                DataBaseConstants.DATABASE_NAME
            ).build()
        }
        single { get<UserDataBase>().userDAO() }
    }
}