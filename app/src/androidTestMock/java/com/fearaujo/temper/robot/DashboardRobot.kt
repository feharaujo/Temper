package com.fearaujo.temper.robot

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.matcher.ViewMatchers.withText
import com.fearaujo.temper.R
import com.fearaujo.temper.common.checkIfDisplayed
import org.hamcrest.core.AllOf

object DashboardRobot {

    fun contractorsRecyclerViewIsVisible() = R.id.recyclerView.checkIfDisplayed()

    fun performClickOnContractor(text: String) =
        onView(AllOf.allOf(withText(text))).perform(click())


}