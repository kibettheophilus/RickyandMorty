package dev.kibet.presentation.fragments

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import dev.kibet.presentation.R
import dev.kibet.presentation.activities.MainActivity
import org.junit.Rule
import org.junit.Test

class HomeFragmentTest {

    @Rule
    @JvmField
    var activityScenarioRule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun recyclerview_is_showing() {
        onView(withId(R.id.characters_recyclerview)).check(matches(isDisplayed()))
    }
}