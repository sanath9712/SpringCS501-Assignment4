package com.bignerdranch.android.criminalintent


import android.content.Context
import android.view.LayoutInflater
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import androidx.test.core.app.ApplicationProvider
import com.bignerdranch.android.criminalintent.databinding.ListItemCrimePoliceBinding
import kotlinx.coroutines.NonDisposableHandle.parent
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations
import org.junit.Assert.*
import org.robolectric.Robolectric
import org.robolectric.shadows.ShadowToast
import java.util.*

class CrimeUnitTest {

    private lateinit var crime: Crime
    private lateinit var crimeListFragment: CrimeListFragment


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
    fun testSetTitle() {
        // Test setting the title
        val title = "Test Crime"
        crime.title = title
        assertEquals(title, crime.title)
    }

    @Test
    fun testSetDate() {
        // Test setting the date
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