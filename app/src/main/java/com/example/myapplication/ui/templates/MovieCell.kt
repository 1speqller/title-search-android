package com.example.myapplication.ui.templates

import androidx.compose.foundation.Indication
import androidx.compose.foundation.LocalIndication
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage


@Composable
fun MovieCell(
    modifier: Modifier = Modifier,
    title: String?,
    genres: List<String>?,
    photoPath: String?,
    onClick: () -> Unit
) {
    Card(
        modifier = modifier
            .padding(4.dp)
            .fillMaxSize()
            .clickable(
                enabled = true,
                interactionSource = remember { MutableInteractionSource() },
                onClick = onClick
            )
            .heightIn(min = 226.dp),
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Column(
            modifier = Modifier
                .padding(8.dp)
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.Start
        ) {
            AsyncImage(
                modifier = Modifier
                    .padding(end = 8.dp)
                    .border(
                        width = 1.dp,
                        color = Color.Gray,
                        shape = RoundedCornerShape(12.dp)
                    )

                    .clip(RoundedCornerShape(12.dp))
                    .fillMaxWidth()
                    .aspectRatio(1f),
                alignment = Alignment.Center,
                model = photoPath,
                contentDescription = null,
                contentScale = ContentScale.Crop
            )
            MovieText(
                modifier = Modifier.padding(top = 4.dp),
                text = title ?: "Без названия",
                size = 16.sp,
                maxLines = 1,
                textOverflow = TextOverflow.Ellipsis
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