package com.fearaujo.temper

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.fearaujo.model.Contractor
import com.fearaujo.model.Shift
import com.fearaujo.temper.details.DetailsViewModel
import com.fearaujo.temper.details.DetailsViewModelImpl
import com.google.gson.Gson
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import java.io.InputStream
import java.util.*

class DetailsViewModelTest {

    companion object {
        const val JSON_FILE = "Contractor.json"
    }

    @get:Rule
    val taskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var titleObserver: Observer<String>
    @Mock
    private lateinit var categoryObserver: Observer<String>
    @Mock
    private lateinit var photoObserver: Observer<String>
    @Mock
    private lateinit var shiftsObserver: Observer<List<Shift>>
    @Mock
    private lateinit var maxEarningObserver: Observer<Float>
    @Mock
    private lateinit var descriptionObserver: Observer<String>
    @Mock
    private lateinit var shiftLabelVisibilityObserver: Observer<Unit>

    private lateinit var viewModel: DetailsViewModel

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        viewModel = DetailsViewModelImpl()

        viewModel.subscribeShiftLabelVisibility().observeForever(shiftLabelVisibilityObserver)
        viewModel.subscribeCategory().observeForever(categoryObserver)
        viewModel.subscribeShift().observeForever(shiftsObserver)
        viewModel.subscribeTitle().observeForever(titleObserver)
        viewModel.subscribePhoto().observeForever(photoObserver)
        viewModel.subscribeMaxEarningHour().observeForever(maxEarningObserver)
        viewModel.subscribeDescription().observeForever(descriptionObserver)

    }

    @Test
    fun `should notify all observers on bind`() {
        val jsonFile = this.javaClass.classLoader!!.getResourceAsStream(JSON_FILE)
        val jsonString = convertStreamToString(jsonFile)

        val contractor = Gson().fromJson(jsonString, Contractor::class.java)
        viewModel.bind(contractor)

        verify(titleObserver, times(1)).onChanged(any())
        verify(categoryObserver, times(1)).onChanged(any())
        verify(photoObserver, times(1)).onChanged(any())
        verify(shiftsObserver, times(1)).onChanged(any())
        verify(maxEarningObserver, times(1)).onChanged(any())
        verify(descriptionObserver, times(1)).onChanged(any())
        verify(shiftLabelVisibilityObserver, times(1)).onChanged(any())

    }

    private fun convertStreamToString(inputStream: InputStream): String {
        val s = Scanner(inputStream).useDelimiter("\\A")
        return if (s.hasNext()) s.next() else ""
    }
}