package com.fearaujo.temper.common

import android.view.View
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.ViewAction
import androidx.test.espresso.ViewInteraction
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.doesNotExist
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import org.hamcrest.CoreMatchers.not
import org.hamcrest.Matcher


fun Int.checkIfDisplayed(): ViewInteraction =
    onView(withId(this)).check(matches(isDisplayed()))

fun Int.checkIfDisplayedEffective(): ViewInteraction =
    onView(withId(this)).check(matches(withEffectiveVisibility(Visibility.VISIBLE)))

fun Int.checkIfInvisible(): ViewInteraction =
    onView(withId(this)).check(matches(withEffectiveVisibility(Visibility.INVISIBLE)))

fun Int.checkIfGone(): ViewInteraction =
    onView(withId(this)).check(matches(withEffectiveVisibility(Visibility.GONE)))

fun Int.checkIfDoesNotExist(): ViewInteraction =
    onView(withId(this)).check(doesNotExist())

fun Int.checkIfTextDisplayed(): ViewInteraction =
    onView(withText(this)).check(matches(isDisplayed()))

fun Int.checkIfEnabled(): ViewInteraction =
    onView(withId(this)).check(matches(isEnabled()))

fun Int.checkIfDisabled(): ViewInteraction =
    onView(withId(this)).check(matches(not(isEnabled())))

fun Int.checkIfTextDisplayedEffective(): ViewInteraction =
    onView(withText(this)).check(matches(withEffectiveVisibility(Visibility.VISIBLE)))

fun Int.scrollToViewAndCheckIfDisplayed(): ViewInteraction =
    onView(withId(this))
        .perform(scrollTo())
        .check(matches(isDisplayed()))

fun Int.scrollToViewAndCheckIfDisplayedEffective(): ViewInteraction =
    onView(withId(this))
        .perform(scrollTo())
        .check(matches(withEffectiveVisibility(Visibility.VISIBLE)))

fun Int.hasText(text: String): ViewInteraction =
    onView(withId(this))
        .check(matches(withText(text)))

fun Int.hasText(textId: Int): ViewInteraction =
    onView(withId(this))
        .check(matches(withText(textId)))

fun Int.hasText(textMatcher: Matcher<View>): ViewInteraction =
    onView(withId(this))
        .check(matches(textMatcher))

fun Int.checkIfToggled(matcher: Matcher<View>): ViewInteraction =
    onView(withId(this)).check(matches(matcher))

fun Int.toggleSwitch(viewAction: ViewAction): ViewInteraction =
    onView(withId(this)).perform(viewAction)

fun Int.enterText(text: String): ViewInteraction =
    onView(withId(this))
        .perform(scrollTo(), ViewActions.clearText(), ViewActions.typeText(text), closeSoftKeyboard())

fun Int.closeKeyboard(): ViewInteraction =
    onView(withId(this)).perform(ViewActions.closeSoftKeyboard())

fun Int.clickView(): ViewInteraction =
    onView(withId(this)).perform(click())

fun Int.clickViewWithThisText(): ViewInteraction =
    onView(withText(this)).perform(click())

fun Int.checkIfDisplayedAndTextNotEmpty(shouldScroll: Boolean = true): ViewInteraction =
    onView(withId(this)).apply {
        if (shouldScroll) perform(scrollTo())
        check(matches(isDisplayed()))
        check(matches(not(withText(""))))


}