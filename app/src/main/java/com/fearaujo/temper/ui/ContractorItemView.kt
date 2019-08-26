package com.fearaujo.temper.ui

import android.content.Context
import android.util.AttributeSet
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import com.fearaujo.model.Contractor
import com.fearaujo.temper.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.contractor_view.view.*
import org.koin.core.KoinComponent
import org.koin.core.inject

class ContractorItemView : ConstraintLayout, KoinComponent {

    private val picasso: Picasso by inject()

    constructor(context: Context?) : super(context) {
        inflateLayout(context)
    }

    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs) {
        inflateLayout(context)
    }

    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        inflateLayout(context)
    }

    private fun inflateLayout(context: Context?) {
        View.inflate(context, R.layout.contractor_view, this)
    }

    fun bind(contractor: Contractor) {
        val photo = getPhotoPath(contractor)

        picasso.load(photo)
                .into(ivPhoto)

        tvTitle.text = contractor.title
        tvDescription.text = contractor.client?.description
    }

    // TODO : REFACTOR
    private fun getPhotoPath(repo: Contractor): String {
        var path = ""

        repo.client?.photos?.get(0)?.formats?.get(0)?.cdnUrl?.let {
            path = it
        }

        repo.photo?.let {
            path = it
        }

        return path
    }
}