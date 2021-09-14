package com.picpay.desafio.android.ui

import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.picpay.desafio.android.R
import com.picpay.desafio.android.model.User
import com.picpay.desafio.android.repository.local.UserDataBase
import com.picpay.desafio.android.viewmodel.UserViewModel
import com.picpay.desafio.android.viewmodel.state.UserState
import org.koin.androidx.viewmodel.ext.android.viewModel



class MainActivity : AppCompatActivity(R.layout.activity_main) {
    private val adapter: UserListAdapter by lazy { UserListAdapter() }
    private val viewModel: UserViewModel by viewModel()

    private val recyclerView: RecyclerView by lazy {
        findViewById<RecyclerView>(R.id.recyclerView)
    }
    private val progressBar: ProgressBar by lazy {
        findViewById<ProgressBar>(R.id.user_list_progress_bar)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

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
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)
        progressBar.visibility = View.VISIBLE
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
        recyclerView.visibility = if (show) View.VISIBLE else View.GONE
    }

    private fun toggleProgressbar(show: Boolean) {
        progressBar.visibility = if (show) View.VISIBLE else View.GONE
    }

    private fun showMessageError(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}
