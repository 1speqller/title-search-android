package com.example.myapplication.ui.templates

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.Placeholder
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication.R

@Composable
fun MovieInputText(
    modifier: Modifier = Modifier,
    onChange: (String) -> Unit,
    value: String? = "",
    enabled: Boolean = true,
    placeholder: String? = ""
) {
    var input by remember { mutableStateOf(value ?: "") }

    OutlinedTextField(
        modifier = modifier
            .clip(RoundedCornerShape(16.dp))
            .background(
                color = Color.White,
                shape = RoundedCornerShape(16.dp)
            )
            .border(
                width = 2.dp,
                color = Color.Gray,
                shape = RoundedCornerShape(16.dp)
            ),
        value = input,
        onValueChange = {
            input = it
            onChange(it)
        },
        prefix = {
            Icon(
                modifier = Modifier
                    .padding(end = 4.dp),
                painter = painterResource(R.drawable.ic_search),
                contentDescription = null
            )
        },
        textStyle = TextStyle(
            fontSize = 16.sp
        ),
        maxLines = 1,
        placeholder = {
            placeholder?.let {
                Text(placeholder)
            }
        }
    )
}

@Preview(showBackground = true)
@Composable
private fun Preview() {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        MovieInputText(
            modifier = Modifier
                .padding(20.dp),
            value = "",
            onChange = { },
            placeholder = "Поиск"
        )
    }

}