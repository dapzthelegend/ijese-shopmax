package com.example.shopmax

import androidx.navigation.Navigation
import androidx.navigation.testing.TestNavHostController
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.ViewAction
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.filters.MediumTest
import assertk.assertThat
import assertk.assertions.isEqualTo
import com.example.shopmax.ui.SplashFragment
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Rule
import org.junit.Test



@HiltAndroidTest
@MediumTest
class SplashFragmentTest {


    @get:Rule
    public val hiltRule = HiltAndroidRule(this)

    @Test
    fun clickGetStarted_navigateToHomeFragment(){
        val navController = TestNavHostController(ApplicationProvider.getApplicationContext())


        val splashFragment = launchFragmentInHiltContainer<SplashFragment> {
            navController.setGraph(R.navigation.nav_graph)
            Navigation.setViewNavController(this.requireView(), navController)
            navController.setCurrentDestination(R.id.splashFragment)
        }



        onView(withId(R.id.splash_get_started_extended_fab)).perform(click())
        onView(withId(R.id.splash_get_started_extended_fab)).perform(click())


        assertThat(navController.currentDestination?.id).isEqualTo(R.id.homeFragment)
    }


}