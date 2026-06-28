package com.example.myapplication.ui.templates

import androidx.compose.ui.graphics.Color
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp

enum class Style (
    val Regular: TextStyle = TextStyle (
        fontStyle = FontStyle.Normal,
        fontSize = 14.sp
    )
)

@Composable
fun MovieText(
    modifier: Modifier = Modifier,
    text: String,
    color: Color = Color.Unspecified,
    size: TextUnit = 14.sp,
    maxLines: Int = Int.MAX_VALUE,
    textOverflow: TextOverflow = TextOverflow.Visible
) {
    Text(
        modifier = modifier,
        text = text,
        style = TextStyle(
            color = color,
            fontWeight = FontWeight.Bold,
            fontSize = size
        ),
        maxLines = maxLines,
        overflow = textOverflow
    )
}