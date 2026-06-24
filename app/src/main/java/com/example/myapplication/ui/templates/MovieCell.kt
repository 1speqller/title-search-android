package com.example.myapplication.ui.templates

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage


@Composable
fun MovieCell(
    title: String?,
    genres: List<String>?,
    photoPath: String?
) {
    Card(
        modifier = Modifier
            .padding(4.dp)
            .fillMaxSize(),
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Column(
            modifier = Modifier
                .padding(start = 8.dp, bottom = 8.dp, top = 8.dp)
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.Start
        ) {
            AsyncImage(
                modifier = Modifier
                    .border(
                        width = 1.dp,
                        color = Color.Gray,
                        shape = RoundedCornerShape(12.dp)
                    )
                    .clip(RoundedCornerShape(12.dp))
                    .size(164.dp),
                alignment = Alignment.Center,
                model = photoPath,
                contentDescription = null,
                contentScale = ContentScale.Crop
            )
            MovieText(
                modifier = Modifier.padding(top = 4.dp),
                text = title ?: "Без названия",
                size = 16.sp
            )
            genres?.let {
                FlowRow(
                    modifier = Modifier
                        .padding(bottom = 8.dp)
                        .widthIn(max = 128.dp)
                ) {
                    genres.forEach {
                        MovieText(
                            modifier = Modifier.padding(end = 4.dp),
                            text = it,
                            size = 12.sp,
                            color = Color.Gray
                        )
                    }
                }
            }
        }
    }
}