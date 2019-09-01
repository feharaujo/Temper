package com.fearaujo.temper.details

import android.content.Context
import android.util.AttributeSet
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import com.fearaujo.model.Shift
import com.fearaujo.temper.R
import com.fearaujo.temper.ui.EURO
import com.fearaujo.temper.ui.NUMBER_FORMAT
import kotlinx.android.synthetic.main.item_shift.view.*

class ShiftItemView : ConstraintLayout {

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
        View.inflate(context, R.layout.item_shift, this)
    }

    fun bind(shift: Shift) {
        tvDate.text = shift.startDate

        shift.earningsPerHour?.let {
            val value = String.format(NUMBER_FORMAT, it)
            tvEarning.text = context.getString(R.string.earning_per_hour, EURO, value)
        }

        tvTime.text = context.getString(R.string.shift_hours, shift.startTime, shift.endTime)
    }
}