package com.fearaujo.data.repository.remote

import com.fearaujo.data.repository.remote.PageDateConverter.formatDate
import com.fearaujo.data.repository.remote.PageDateConverter.increaseDateInDays
import org.junit.Assert
import org.junit.Test
import java.text.SimpleDateFormat

class PageDateConverterTest {

    private val pattern = "yyyy-MM-dd"

    @Test
    fun `dates should increase in days`() {
        val format = SimpleDateFormat(pattern)

        val dateString1 = "2019-06-09"
        val dateString2 = "2019-06-30"
        val dateString3 = "2019-12-31"

        val date1 = format.parse(dateString1)
        val date2 = format.parse(dateString2)
        val date3 = format.parse(dateString3)

        val result1 = formatDate(increaseDateInDays(date1!!, 1))
        val result2 = formatDate(increaseDateInDays(date2!!, 1))
        val result3 = formatDate(increaseDateInDays(date3!!, 1))

        Assert.assertEquals("2019-06-10", result1)
        Assert.assertEquals("2019-07-01", result2)
        Assert.assertEquals("2020-01-01", result3)

    }

}