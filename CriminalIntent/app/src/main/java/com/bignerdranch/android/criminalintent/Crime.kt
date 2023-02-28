package com.bignerdranch.android.criminalintent
import java.util.Date
import java.util.UUID

data class Crime(
    val id: UUID,
    var title: String,
    var date: Date,
    val isSolved: Boolean,
    var requiresPolice: Boolean
)
