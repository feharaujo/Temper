package com.fearaujo.temper.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.fearaujo.data.extension.getImagePath
import com.fearaujo.model.Contractor
import com.fearaujo.model.Shift

abstract class DetailsViewModel : ViewModel() {

    abstract fun bind(contractor: Contractor)

    abstract fun subscribePhoto(): LiveData<String>

    abstract fun subscribeTitle(): LiveData<String>

    abstract fun subscribeCategory(): LiveData<String>

    abstract fun subscribeMaxEarningHour(): LiveData<Float>

    abstract fun subscribeDescription(): LiveData<String>

    abstract fun subscribeShift(): LiveData<List<Shift>>

    abstract fun subscribeShiftLabelVisibility(): LiveData<Unit>

}

class DetailsViewModelImpl : DetailsViewModel() {

    private val photoMutableLiveData = MutableLiveData<String>()
    private val titleMutableLiveData = MutableLiveData<String>()
    private val categoryMutableLiveData = MutableLiveData<String>()
    private val maxEarningHourMutableLiveData = MutableLiveData<Float>()
    private val descriptionMutableLiveData = MutableLiveData<String>()
    private val shiftMutableLiveData = MutableLiveData<List<Shift>>()
    private val shiftLabelVisibilityMutableLiveData = MutableLiveData<Unit>()

    override fun bind(contractor: Contractor) {
        photoMutableLiveData.value = contractor.getImagePath()
        titleMutableLiveData.value = contractor.title
        categoryMutableLiveData.value = contractor.jobCategory?.description
        maxEarningHourMutableLiveData.value = contractor.maxEarningHour
        descriptionMutableLiveData.value = contractor.client?.description

        contractor.shifts?.let { list ->
            shiftLabelVisibilityMutableLiveData.value = Unit
            shiftMutableLiveData.value = list
        }
    }

    override fun subscribeTitle(): LiveData<String> = titleMutableLiveData

    override fun subscribeCategory(): LiveData<String> = categoryMutableLiveData

    override fun subscribeMaxEarningHour(): LiveData<Float> = maxEarningHourMutableLiveData

    override fun subscribeDescription(): LiveData<String> = descriptionMutableLiveData

    override fun subscribeShift(): LiveData<List<Shift>> = shiftMutableLiveData

    override fun subscribePhoto(): LiveData<String> = photoMutableLiveData

    override fun subscribeShiftLabelVisibility(): LiveData<Unit> =
        shiftLabelVisibilityMutableLiveData

}