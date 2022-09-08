package com.picpay.desafio.android.ui

import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.picpay.desafio.android.R
import com.picpay.desafio.android.databinding.ActivityMainBinding
import com.picpay.desafio.android.model.User
import com.picpay.desafio.android.viewmodel.UserViewModel
import com.picpay.desafio.android.viewmodel.state.UserState
import org.koin.androidx.viewmodel.ext.android.viewModel



class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val adapter: UserListAdapter by lazy { UserListAdapter() }
    private val viewModel: UserViewModel by viewModel()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.lifecycleOwner = this

        initViews()
        initObservables()
        bindViews()
    }

    private fun initObservables() {
        viewModel.viewState.observe(this, Observer {
            when (it) {
                is UserState.SuccessListUsers -> successListUsersRemote(it.users)
                is UserState.ErrorListUsers -> errorListUsers(it.message)
            }
        })
    }

    private fun bindViews() {
        viewModel.getContacts()
    }

    private fun initViews() {
        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.userListProgressBar.visibility = View.VISIBLE
    }


    private fun successListUsersRemote(users: List<User>) {
        adapter.users = users
        toggleProgressbar(false)
        toggleList(true)
    }

    private fun errorListUsers(message: String) {
        showMessageError(message)
        toggleProgressbar(false)
        toggleList(false)
    }

    private fun toggleList(show: Boolean) {
        binding.recyclerView.visibility = if (show) View.VISIBLE else View.GONE
    }

    private fun toggleProgressbar(show: Boolean) {
        binding.userListProgressBar.visibility = if (show) View.VISIBLE else View.GONE
    }

    private fun showMessageError(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}
