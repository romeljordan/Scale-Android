package com.demo.app.feature.core.util

import java.text.SimpleDateFormat
import java.util.Locale

const val FULL_DATE_TIME_12HR_FORMAT = "E, dd MMM yyyy, hh:mm a"
const val TIME_12HR_FORMAT = "hh:mm a"

fun Long.formatDate(format: String): String {
    return SimpleDateFormat(
        format,
        Locale.getDefault()
    ).format(this)
}
