package com.example.proyectoandroid.view

import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.example.proyectoandroid.R

import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class MainActivityTest {

    @get:Rule
    var rule: ActivityScenarioRule<*> = ActivityScenarioRule(MainActivity::class.java)

    @Before
    fun setUp() {
    }

    @After
    fun tearDown() {
    }

    @Test
    fun mainactivity_EqualStrings() {
        Espresso.onView(
            ViewMatchers.withId(R.id.firstField)
        ).perform(
            ViewActions.typeText("abc")
        )
        Espresso.onView(
            ViewMatchers.withId(R.id.secondField)
        ).perform(
            ViewActions.typeText("abc")
        )
        Espresso.onView(
            ViewMatchers.withId(R.id.compareButton)
        ).perform(
            ViewActions.click()
        )

        Espresso.onView(
            ViewMatchers.withId(R.id.compareResultView)
        ).check(
            ViewAssertions.matches(
                ViewMatchers.withText(R.string.compare_result_success)
            )
        )
    }

    @Test
    fun mainactivity_UnequalStrings() {
        Espresso.onView(
            ViewMatchers.withId(R.id.firstField)
        ).perform(
            ViewActions.typeText("abc")
        )
        Espresso.onView(
            ViewMatchers.withId(R.id.secondField)
        ).perform(
            ViewActions.typeText("def")
        )
        Espresso.onView(
            ViewMatchers.withId(R.id.compareButton)
        ).perform(
            ViewActions.click()
        )

        Espresso.onView(
            ViewMatchers.withId(R.id.compareResultView)
        ).check(
            ViewAssertions.matches(
                ViewMatchers.withText(R.string.compare_result_failure)
            )
        )
    }
}