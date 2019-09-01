package com.fearaujo.temper.dashboard

import android.content.Context
import android.util.AttributeSet
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import com.fearaujo.data.extension.getImagePath
import com.fearaujo.model.Contractor
import com.fearaujo.temper.R
import com.fearaujo.temper.ui.image.ImageLoader
import kotlinx.android.synthetic.main.contractor_general_view.view.*
import kotlinx.android.synthetic.main.contractor_view.*
import kotlinx.android.synthetic.main.contractor_view.view.*
import org.koin.core.KoinComponent
import org.koin.core.inject
import org.koin.core.parameter.parametersOf

class ContractorItemView : ConstraintLayout, KoinComponent {

    private companion object {
        const val EURO = '\u20ac'
        const val NUMBER_FORMAT = "%.2f"
    }

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
        tvCategory.text = contractor.jobCategory?.description

        contractor.maxEarningHour?.let {
            val value = String.format(NUMBER_FORMAT, it)
            tvEarning.text = context.getString(R.string.earning_per_hour, EURO, value)
        }

        contractor.shifts?.get(0)?.let {
            tvShift.text = tvShift.context.getString(R.string.shift_hours, it.startTime, it.endTime)
        }

        imageLoader.loadImage(contractor.getImagePath(), ivPhoto)
    }
}