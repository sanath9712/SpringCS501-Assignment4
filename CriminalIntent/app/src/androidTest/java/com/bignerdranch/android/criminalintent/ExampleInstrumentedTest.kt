package com.example.myapp

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.bignerdranch.android.criminalintent.Crime
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.util.*

@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {
    private lateinit var crime: Crime
    @Before
    fun setUp() {
        // Create a new Crime object before each test
        for (i in 0 until 100) {
            crime = Crime(
                id = UUID.randomUUID(),
                title = "Crime #$i",
                date = Date(),
                isSolved = i % 2 == 0,
                requiresPolice = when ((0..1).shuffled().first()) {
                    0 -> false
                    else -> true
                }
            )
        }

    }

    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = ApplicationProvider.getApplicationContext<Context>()
        assertEquals("com.bignerdranch.android.criminalintent", appContext.packageName)
    }

    @Test
    fun testCrimeName() {
        crime.title = "Test Crime"
        assertEquals("Test Crime", crime.title)
    }

    @Test
    fun testCrimeDate() {
        val date = Date()
        crime.date = date
        assertEquals(date, crime.date)
    }

    @Test
    fun testSetRequirePolice(){
        val VIEW_TYPE_NORMAL = false
        val VIEW_TYPE_POLICE = true
        if(crime.requiresPolice){
            assertEquals(VIEW_TYPE_POLICE, crime.requiresPolice)
        }else{
            assertEquals(VIEW_TYPE_NORMAL, crime.requiresPolice)
        }
    }

}
