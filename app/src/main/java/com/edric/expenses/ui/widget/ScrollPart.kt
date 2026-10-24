package com.edric.expenses.ui.widget

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.edric.expenses.viewmodel.HomeViewModel

@Composable
fun scrollPart(
    viewModel: HomeViewModel = viewModel()
){
    val expenses by viewModel.expenses.collectAsState()

    LazyColumn (
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top,
        modifier = Modifier.fillMaxSize(),
    ) {
        items(expenses.size) { index ->
            val expense = expenses[index]
            expenseCard(
                index = index
            )
        }
    }
}