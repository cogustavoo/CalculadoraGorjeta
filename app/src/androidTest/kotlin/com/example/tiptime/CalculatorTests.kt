package com.example.tiptime

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.calculadoragorjeta.MainActivity
import com.example.calculadoragorjeta.R
import org.hamcrest.CoreMatchers.containsString
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


// executor de testes JUnit4
@RunWith(AndroidJUnit4::class)
class CalculatorTests {

    //A ActivityScenarioRule é parte da biblioteca JUnit. Ela orienta o dispositivo a iniciar
    // uma atividade especificada pelo desenvolvedor e precisa incluir a anotação @get:Rule(),
    // que especifica que a regra subsequente, nesse caso, o início de uma atividade, precisa
    // ser executada antes de cada teste na classe.
    @get:Rule()
    val activity = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun calculate_20_percent_tip() {
        onView(withId(R.id.cost_of_service_edit_text))
            .perform(typeText("50.00"))

        onView(withId(R.id.btn_calculate)).perform(click())

        onView(withId(R.id.tip_result))
            .check(matches(withText(containsString("$10.00"))))
    }

    @Test
    fun calculate_18_percent_tip() {
        onView(withId(R.id.cost_of_service_edit_text))
            .perform(typeText("100"))

        onView(withId(R.id.option_eighteen_percent)).perform(click())

        onView(withId(R.id.btn_calculate)).perform(click())

        onView((withId(R.id.tip_result)))
            .check(matches(withText(containsString("$18.00"))))
    }


    @Test
    fun calculate_15_percent_tip() {
        onView(withId(R.id.cost_of_service_edit_text))
            .perform(typeText("100.50"))

        onView(withId(R.id.option_fifteen_percent)).perform(click())

        onView(withId(R.id.btn_calculate)).perform(click())

        onView((withId(R.id.tip_result)))
            .check(matches(withText(containsString("$16.00"))))

        onView(withId(R.id.round_up_switch)).perform(click())

        onView(withId(R.id.btn_calculate)).perform(click())

        onView((withId(R.id.tip_result)))
            .check(matches(withText(containsString("$15.08"))))
    }

    @Test
    fun calculate_rounded_tip_btn() {
        onView(withId(R.id.cost_of_service_edit_text))
            .perform(typeText("100.50"))

        onView(withId(R.id.round_up_switch)).perform(click())

        onView(withId(R.id.btn_calculate)).perform(click())

        onView((withId(R.id.tip_result)))
            .check(matches(withText(containsString("$20.10"))))
    }
}