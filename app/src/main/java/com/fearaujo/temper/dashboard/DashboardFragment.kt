package com.fearaujo.temper.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.fearaujo.data.repository.RepositoryState
import com.fearaujo.temper.MainActivity
import com.fearaujo.temper.R
import kotlinx.android.synthetic.main.dashboard_fragment.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class DashboardFragment : Fragment() {

    private val adapter = MainAdapter()

    private val viewModel by viewModel<DashboardViewModel>()

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.dashboard_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        setupRecyclerView()

        viewModel.subscribeRepositoryList().observe(this, Observer {
            adapter.submitList(it)
        })

        viewModel.subscribeStatus().observe(this, Observer { state ->
            adapter.setNetworkState(state)
            onStateChange(state)
        })
    }

    private fun setupRecyclerView() {
        val linearManager = LinearLayoutManager(context)
        recyclerView.layoutManager = linearManager
        recyclerView.adapter = adapter
    }

    private fun onStateChange(repositoryState: RepositoryState) {
        when (repositoryState) {
            is RepositoryState.OnError -> showSnackBar(getString(R.string.error_fetching_data))
        }
    }

    private fun showSnackBar(message: String) {
        (activity as MainActivity).showSnackBar(message)
    }

}
