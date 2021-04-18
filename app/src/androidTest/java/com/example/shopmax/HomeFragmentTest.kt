package com.example.shopmax

import androidx.navigation.Navigation
import androidx.navigation.testing.TestNavHostController
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click

import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.filters.MediumTest
import assertk.assertThat
import assertk.assertions.isEqualTo
import com.example.shopmax.data.ShipmentService
import com.example.shopmax.ui.HomeFragment
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import javax.inject.Inject


@HiltAndroidTest
@MediumTest
class HomeFragmentTest {

    @get:Rule
    val hiltRule = HiltAndroidRule(this)



    @Test
    fun displayTask_whenRepositoryHasData(){
        val navController = TestNavHostController(ApplicationProvider.getApplicationContext())
        val homefragment = launchFragmentInHiltContainer<HomeFragment> {
            navController.setGraph(R.navigation.nav_graph)
            Navigation.setViewNavController(this.requireView(), navController)
            navController.setCurrentDestination(R.id.homeFragment)


        }

        onView(withId(R.id.home_make_shipment_fab)).perform(click())

        assertThat(navController.currentDestination?.id).isEqualTo(R.id.shipmentFragment)
    }





}