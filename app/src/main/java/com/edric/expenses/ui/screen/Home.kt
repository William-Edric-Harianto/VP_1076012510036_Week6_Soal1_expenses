package com.edric.expenses.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.edric.expenses.ui.widget.scrollPart
import com.edric.expenses.ui.widget.topPart

@Composable
fun homeView() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(
                start = 20.dp,
                end = 20.dp,
                top = 100.dp,
                bottom = 20.dp
            )
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxSize()
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .weight(1f)
            ) {
                topPart()
            }
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .weight(2f)
            ) {
                scrollPart()
            }
        }
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = 200.dp)
        ) {
            FloatingActionButton(
                shape = CircleShape,
                containerColor = Color.White, // Background color
                contentColor = Color.Black,
                elevation = FloatingActionButtonDefaults.elevation(20.dp),
                modifier = Modifier
                    .align(Alignment.BottomStart),
                onClick = {
                    //I don't know
                }
            ) {
                Icon(
                    imageVector = Icons.Default.Menu,
                    contentDescription = "Ga tau jujur"
                )
            }
        }
    }
}

@Composable
@Preview(showBackground = true, showSystemUi = true)
fun homePreview() {
    homeView()
}