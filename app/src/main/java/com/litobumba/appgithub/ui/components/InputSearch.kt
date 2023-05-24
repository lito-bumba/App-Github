package com.litobumba.appgithub.ui.list_user.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp

@Composable
fun InputSearch(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    clickLeadingIcon: () -> Unit = {},
    onClickClearIcon: () -> Unit,
    placeholderText: String = "",
    fontSize: TextUnit = MaterialTheme.typography.subtitle1.fontSize,
    keyboardActions: () -> Unit = {}
) {
    val backgroundColor = Color.White
    val contentColor = Black
    BasicTextField(
        value = value,
        onValueChange = { onValueChange(it) },
        singleLine = true,
        cursorBrush = SolidColor(Color.Blue),
        textStyle = LocalTextStyle.current.copy(
            color = contentColor,
            fontSize = fontSize
        ),
        decorationBox = { innerTextField ->
            Row(
                modifier = modifier,
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(
                    onClick = { clickLeadingIcon.invoke() },
                    modifier = Modifier.alpha(ContentAlpha.medium)
                ) {
                    Icon(
                        imageVector = Icons.Filled.Search,
                        contentDescription = "Search Icon",
                        tint = contentColor
                    )
                }
                Box(Modifier.weight(1f)) {
                    if (value.isEmpty())
                        Text(
                            text = placeholderText,
                            style = LocalTextStyle.current.copy(
                                color = contentColor.copy(alpha = 0.3f),
                                fontSize = fontSize
                            )
                        )
                    innerTextField()
                }

                if (value.isNotBlank()) {
                    IconButton(
                        onClick = { onClickClearIcon.invoke() }
                    ) {
                        Icon(
                            imageVector = Icons.Default.Close,
                            contentDescription = "Clear Icon",
                            tint = contentColor
                        )
                    }
                }
            }
        },
        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
        keyboardActions = KeyboardActions { keyboardActions() },
        modifier = modifier
            .clip(RoundedCornerShape(10.dp))
            .fillMaxWidth()
            .height(50.dp)
            .background(
                color = backgroundColor,
                shape = MaterialTheme.shapes.small,
            )
    )
}