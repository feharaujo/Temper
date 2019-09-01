package com.fearaujo.temper.dashboard

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.fearaujo.data.repository.RepositoryState
import com.fearaujo.model.Contractor
import com.fearaujo.temper.R

interface ContractorListener {
    fun onSelect(contractor: Contractor)
}

private val DIFF = object : DiffUtil.ItemCallback<Contractor>() {
    override fun areItemsTheSame(oldItem: Contractor, newItem: Contractor): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Contractor, newItem: Contractor): Boolean {
        return oldItem == newItem
    }
}

class MainAdapter(val listener: ContractorListener) :
        PagedListAdapter<Contractor, RecyclerView.ViewHolder>(DIFF) {

    inner class ViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {

        fun bind(contractor: Contractor?) {
            contractor?.let {
                view.findViewById<ContractorItemView>(R.id.contractorItemView).bind(contractor)
                view.setOnClickListener {
                    listener.onSelect(contractor)
                }
            }
        }
    }

    private var networkState: RepositoryState? = null
    private var loadingViewHolder: ViewHolder? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            R.layout.item_main_cell -> createViewHolder(parent)
            R.layout.item_loading_cell -> createLoadingView(parent)
            else -> throw IllegalArgumentException("unknown view type $viewType")
        }
    }

    override fun getItemCount(): Int {
        return super.getItemCount() + if (hasExtraRow()) 1 else 0
    }

    override fun getItemViewType(position: Int): Int {
        return if (hasExtraRow() && (position == itemCount - 1)) {
            R.layout.item_loading_cell
        } else {
            R.layout.item_main_cell
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (getItemViewType(position)) {
            R.layout.item_main_cell -> (holder as ViewHolder).bind(getItem(position))
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder,
                                  position: Int,
                                  payloads: MutableList<Any>) {
        if (payloads.isNotEmpty()) {
            val item = getItem(position)
            (holder as ViewHolder).bind(item)
        } else {
            onBindViewHolder(holder, position)
        }
    }

    fun setNetworkState(newNetworkState: RepositoryState?) {
        val previousState = this.networkState
        val hadExtraRow = hasExtraRow()
        this.networkState = newNetworkState
        val hasExtraRow = hasExtraRow()
        if (hadExtraRow != hasExtraRow) {
            if (hadExtraRow) {
                notifyItemRemoved(super.getItemCount())
            } else {
                notifyItemInserted(super.getItemCount())
            }
        } else if (hasExtraRow && previousState != newNetworkState) {
            notifyItemChanged(itemCount - 1)
        }
    }

    private fun createViewHolder(parent: ViewGroup): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_main_cell, parent, false)
        return ViewHolder(view)
    }

    private fun createLoadingView(parent: ViewGroup): ViewHolder {
        if (loadingViewHolder == null) {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.item_loading_cell, parent, false)
            loadingViewHolder = ViewHolder(view)
        }

        return loadingViewHolder as ViewHolder
    }

    private fun hasExtraRow() = networkState != null && networkState == RepositoryState.Loading

}