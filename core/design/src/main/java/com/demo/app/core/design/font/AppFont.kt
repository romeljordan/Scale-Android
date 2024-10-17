package com.demo.app.core.design.font

import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import com.demo.app.core.design.R

val poppinsFontFamily = FontFamily(
    listOf(
        Font(R.font.poppins_black, weight = FontWeight.Black),
        Font(R.font.poppins_extrabold, weight = FontWeight.ExtraBold),
        Font(R.font.poppins_semibold, weight = FontWeight.SemiBold),
        Font(R.font.poppins_bold, weight = FontWeight.Bold),
        Font(R.font.poppins_medium, weight = FontWeight.Medium),
        Font(R.font.poppins_regular, weight = FontWeight.Normal),
        Font(R.font.poppins_light, weight = FontWeight.Light),
        Font(R.font.poppins_thin, weight = FontWeight.Thin),
    )
)
