package com.example.myapplication.ui.templates

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.myapplication.R

@Composable
fun RepeatErrorBox(
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Box(
            modifier = Modifier
                .clip(CircleShape)
                .background(
                    color = Color(0xFFBF3030),
                    shape = CircleShape
                )
                .shadow(
                    elevation = 2.dp,
                    shape = CircleShape,
                    spotColor = Color.Red,
                )
                .clickable(
                    enabled = true,
                    interactionSource = remember { MutableInteractionSource() },
                    onClick = onClick
                )
        ) {
            Icon(
                modifier = Modifier
                    .padding(8.dp)
                    .size(64.dp),
                painter = painterResource(R.drawable.ic_repeat),
                contentDescription = null,
                tint = Color.White
            )
        }
    }
}