package arie

import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.Espresso.pressBack
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.contrib.RecyclerViewActions
import android.support.test.espresso.matcher.ViewMatchers
import android.support.test.espresso.matcher.ViewMatchers.*
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import arie.footballmatch.MainActivity
import arie.footballmatch.R.id.*
import arie.footballmatch.adapters.LastAdapter
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MyTest {
    @Rule
    @JvmField var activity = ActivityTestRule(MainActivity::class.java)

    @Test
    fun testListDetailFav(){
        onView(withId(spinner_last))
                .check(matches(isDisplayed()))
        onView(withId(spinner_last)).perform(click())
        onView(withText("English Premier League")).perform(click())
        onView(ViewMatchers.withId(rvLastSchedule))
                .perform(RecyclerViewActions.actionOnItemAtPosition<LastAdapter.MyViewHolder>(0, click()))

        onView(withId(txtHomeTeam))
                .check(matches(isDisplayed()))
        onView(withText("Chelsea"))

        onView(withId(add_to_favorite))
                .check(matches(isDisplayed()))
        onView(withId(add_to_favorite)).perform(click())

        onView(withId(add_to_favorite))
                .check(matches(isDisplayed()))
        onView(withId(add_to_favorite)).perform(click())

        pressBack()

        onView(ViewMatchers.withId(rvLastSchedule))
                .perform(RecyclerViewActions.actionOnItemAtPosition<LastAdapter.MyViewHolder>(1, click()))

        onView(withId(txtHomeTeam))
                .check(matches(isDisplayed()))
        onView(withText("Man City"))

        onView(withId(add_to_favorite))
                .check(matches(isDisplayed()))
        onView(withId(add_to_favorite)).perform(click())

        onView(withId(add_to_favorite))
                .check(matches(isDisplayed()))
        onView(withId(add_to_favorite)).perform(click())

        pressBack()
    }
}