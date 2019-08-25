package com.fearaujo.data.repository.remote

import java.text.SimpleDateFormat
import java.util.*

object PageDateConverter {

    private const val pattern = "yyyy-MM-dd"

    fun increaseDateInDays(date: Date, days: Int): Date {
        val cal = Calendar.getInstance()
        cal.time = date
        cal.add(Calendar.DATE, days)
        return cal.time
    }

    fun formatDate(date: Date): String {
        val formatter = SimpleDateFormat(pattern, Locale.getDefault())
        return formatter.format(date)
    }


}