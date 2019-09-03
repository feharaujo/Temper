package com.fearaujo.temper.details

import android.os.Build
import android.os.Bundle
import android.text.Layout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import com.fearaujo.model.Shift
import com.fearaujo.temper.R
import com.fearaujo.temper.ui.EURO
import com.fearaujo.temper.ui.NUMBER_FORMAT
import com.fearaujo.temper.ui.image.ImageLoader
import kotlinx.android.synthetic.main.contractor_general_view.*
import kotlinx.android.synthetic.main.fragment_detail.*
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class DetailsFragment : Fragment() {

    private val viewModel by viewModel<DetailsViewModel>()
    private val imageLoader: ImageLoader by inject { parametersOf(this.context) }
    private val safeArgs: DetailsFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        subscribeViewModel()

        val contractor = safeArgs.contractor
        viewModel.bind(contractor)
    }

    private fun subscribeViewModel() {
        viewModel.subscribeCategory().observe(this, Observer {
            tvCategory.text = it
        })

        viewModel.subscribeDescription().observe(this, Observer {
            bindDescription(it)
        })

        viewModel.subscribeMaxEarningHour().observe(this, Observer {
            bindMaxEarning(it)
        })

        viewModel.subscribePhoto().observe(this, Observer {
            imageLoader.loadImage(it, ivPhoto)
        })

        viewModel.subscribeShift().observe(this, Observer {
            bindShift(it)
        })

        viewModel.subscribeTitle().observe(this, Observer {
            tvTitle.text = it
        })

        viewModel.subscribeShiftLabelVisibility().observe(this, Observer {
            shiftLabel.visibility = View.VISIBLE
        })
    }

    private fun bindShift(shifts: List<Shift>) {
        shifts.forEach {
            val shift = ShiftItemView(context)
            shift.bind(it)
            shiftsContainer.addView(shift)
        }
    }

    private fun bindMaxEarning(it: Float?) {
        val value = String.format(NUMBER_FORMAT, it)
        tvMaxEarning.text = tvMaxEarning.context.getString(R.string.earning_per_hour, EURO, value)
    }

    private fun bindDescription(it: String?) {
        tvDescription.text = it

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            tvDescription.justificationMode = Layout.JUSTIFICATION_MODE_INTER_WORD
        }
    }

}
