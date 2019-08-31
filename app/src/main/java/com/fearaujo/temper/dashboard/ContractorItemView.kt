package com.fearaujo.temper.dashboard

import android.content.Context
import android.util.AttributeSet
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import com.fearaujo.data.extension.getImagePath
import com.fearaujo.model.Contractor
import com.fearaujo.temper.R
import com.fearaujo.temper.ui.image.ImageLoader
import kotlinx.android.synthetic.main.contractor_view.view.*
import org.koin.core.KoinComponent
import org.koin.core.inject
import org.koin.core.parameter.parametersOf

class ContractorItemView : ConstraintLayout, KoinComponent {

    private val imageLoader: ImageLoader by inject { parametersOf(this.context) }

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
        tvTitle.text = contractor.title
        tvDescription.text = contractor.client?.description
        tvCategory.text = contractor.jobCategory?.description

        imageLoader.loadImage(contractor.getImagePath(), ivPhoto)
    }
}