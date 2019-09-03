package com.fearaujo.temper.robot

import androidx.test.espresso.Espresso
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers.*
import com.fearaujo.temper.R
import com.fearaujo.temper.common.checkIfDisplayed
import com.fearaujo.temper.common.scrollToViewAndCheckIfDisplayedEffective
import org.hamcrest.core.AllOf

object DetailsRobot {

    fun checkIfTextIsVisible(text: String) =
        Espresso.onView(AllOf.allOf(withText(text))).check(
            ViewAssertions.matches(
                withEffectiveVisibility(Visibility.VISIBLE)
            )
        )

    fun checkIfShiftLabelIsVisibile() = R.id.shiftLabel.scrollToViewAndCheckIfDisplayedEffective()

    fun checkIfPhotoIsVisible() = R.id.ivPhoto.checkIfDisplayed()

    fun checkMaxEarningIsVisible() = R.id.tvMaxEarning.checkIfDisplayed()


}