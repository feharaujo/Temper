package com.fearaujo.temper.ui

import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import androidx.test.rule.ActivityTestRule
import com.fearaujo.temper.BaseJourneyTest
import com.fearaujo.temper.MainActivity
import com.fearaujo.temper.robot.DashboardRobot
import com.fearaujo.temper.robot.DetailsRobot
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import java.net.HttpURLConnection

@RunWith(AndroidJUnit4ClassRunner::class)
class ContractorsJourneyTest : BaseJourneyTest() {

    private companion object {
        const val FILE_SUCCESS = "Success_Response.json"

        const val MOCK_TITLE = "(Ervaren) bedienings medewerker"
        const val MOCK_CATEGORY = "Bediening"
        const val MOCK_DESCRIPTION = "Het Steigenberger Airport Hotel Amsterdam bij de luchthaven Schiphol (ca. 10 minuten rijden, gratis shuttleservice) ligt op ongeveer 15 km afstand van de binnenstad. Tegenover het hotel ligt het Amsterdamse Bos, waar u kunt joggen, fietsen en wandelen. Dankzij de geluidsisolerende ramen in 440 kamers en suites geniet u van een ontspannen nachtrust. Warme aardekleuren en natuurlijke materialen zorgen voor een behaaglijke sfeer. Tot de comfortabele uitrusting behoort een flatscreen-tv, airconditioning, bureau, kluisje en minibar."
        const val MOCK_SHIFT_DATE_1 = "2019-09-03"
        const val MOCK_SHIFT_DATE_2 = "2019-09-04"
    }

    @get:Rule
    var activityTestRule = ActivityTestRule(MainActivity::class.java, false, false)

    @Before
    override fun setUp() {
        super.setUp()

        mockHttpResponse(fileName = FILE_SUCCESS, responseCode = HttpURLConnection.HTTP_OK)

        activityTestRule.launchActivity(null)
    }


    @Test
    fun shouldLoadContractors_presentDetailsScreen() {

        DashboardRobot.run {
            contractorsRecyclerViewIsVisible()
            performClickOnContractor(MOCK_TITLE)
        }

        DetailsRobot.run {
            checkIfTextIsVisible(MOCK_CATEGORY)
            checkIfTextIsVisible(MOCK_DESCRIPTION)
            checkIfPhotoIsVisible()
            checkIfShiftLabelIsVisibile()
            checkIfTextIsVisible(MOCK_SHIFT_DATE_1)
            checkIfTextIsVisible(MOCK_SHIFT_DATE_2)
            checkMaxEarningIsVisible()
        }

    }


}