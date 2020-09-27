package com.ismin.android

import android.app.Activity.RESULT_OK
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.intent.Intents
import androidx.test.espresso.matcher.ViewMatchers.withHint
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class CreateBookActivityInstrumentedTest {

    @get:Rule
    val activityRule = ActivityScenarioRule(CreateBookActivity::class.java)

    @Before
    fun setUp() {
        Intents.init();
    }

    @Test
    fun fillAllFieldsAndReturnTheData() {
        onView(withHint("Title")).perform(replaceText("The Lord of the Rings"))
        onView(withHint("Author")).perform(replaceText("J. R. R. Tolkien"))
        onView(withHint("Date")).perform(replaceText("1954-02-15"))

        onView(withId(R.id.a_create_book_btn_save)).perform(click())

        assertEquals(RESULT_OK, activityRule.scenario.result.resultCode)

        val expectedBook = Book("The Lord of the Rings", "J. R. R. Tolkien", "1954-02-15")
        assertEquals(expectedBook, activityRule.scenario.result.resultData.extras?.getSerializable(CREATED_BOOK))
    }
}