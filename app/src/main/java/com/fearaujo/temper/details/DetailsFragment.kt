package com.fearaujo.temper.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.fearaujo.data.extension.getImagePath
import com.fearaujo.model.Contractor
import com.fearaujo.model.Shift
import com.fearaujo.temper.R
import com.fearaujo.temper.ui.EURO
import com.fearaujo.temper.ui.NUMBER_FORMAT
import com.fearaujo.temper.ui.image.ImageLoader
import kotlinx.android.synthetic.main.contractor_general_view.*
import kotlinx.android.synthetic.main.fragment_detail.*
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf

class DetailsFragment : Fragment() {

    private val imageLoader: ImageLoader by inject { parametersOf(this.context) }
    private val safeArgs: DetailsFragmentArgs by navArgs()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val contractor = safeArgs.contractor
        setupViewInfo(contractor)
    }

    private fun setupViewInfo(contractor: Contractor) {
        imageLoader.loadImage(contractor.getImagePath(), ivPhoto)
        tvTitle.text = contractor.title
        tvCategory.text = contractor.jobCategory?.description

        contractor.maxEarningHour?.let {
            val value = String.format(NUMBER_FORMAT, it)
            tvMaxEarning.text = tvMaxEarning.context.getString(R.string.earning_per_hour, EURO, value)
        }

        tvDescription.text = contractor.client?.description

        contractor.shifts?.let {
            shiftLabel.visibility = View.VISIBLE
            bindShifts(it)
        }

    }

    private fun bindShifts(shifts: List<Shift>) {
        shifts.forEach {
            val shift = ShiftItemView(context)
            shift.bind(it)
            shiftsContainer.addView(shift)
        }
    }

}
