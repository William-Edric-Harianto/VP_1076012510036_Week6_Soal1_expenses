package com.edric.expenses.ui.widget

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Save
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.edric.expenses.viewmodel.HomeViewModel

@Composable
fun expenseCard(
    index: Int,
    viewModel: HomeViewModel = viewModel()
) {
    val expenses by viewModel.expenses.collectAsState()

    val expense = expenses.getOrNull(index) ?: return

    var expenseAmount by remember { mutableStateOf(expense.amount.toString()) }
    var isAmountError by remember { mutableStateOf(false) }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFFE4E9F7)
        )
    ) {
        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = "Name : ${expense.name}",
                        fontWeight = FontWeight.Bold,
                        color = Color.DarkGray
                    )
                    Text(
                        text = "Rp %,d".format(expense.amount).replace(',', '.'),
                        color = Color.Gray
                    )
                }

                Row {
                    IconButton(onClick = {
                        if (expense.toggle) {
                            val amountInt = expenseAmount.toIntOrNull() ?: 0
                            val isSuccess = viewModel.editExpense(index, amountInt)

                            if (isSuccess) {
                                viewModel.editToggle(index)
                                isAmountError = false
                            } else {
                                isAmountError = true
                            }
                        } else {
                            expenseAmount = expense.amount.toString()
                            viewModel.editToggle(index)
                            isAmountError = false
                        }
                    }) {
                        Icon(
                            imageVector = if (expense.toggle) Icons.Default.Save else Icons.Default.Edit,
                            contentDescription = if (expense.toggle) "Save Expense" else "Edit Expense",
                            tint = if (expense.toggle) Color.Green else Color(0xFFC99700)
                        )
                    }

                    IconButton(onClick = {
                        val isDeleted = viewModel.deleteExpense(index)
                    }) {
                        Icon(
                            imageVector = Icons.Default.Delete,
                            contentDescription = "Delete Expense",
                            tint = Color(0xFFB00020)
                        )
                    }
                }
            }

            if (expense.toggle) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 8.dp)
                ) {
                    OutlinedTextField(
                        value = expenseAmount,
                        onValueChange = { input ->
                            expenseAmount = input
                            val amountInt = input.toIntOrNull() ?: 0
                            isAmountError = amountInt <= 0
                        },
                        label = { Text("Amount of Expense") },
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                        isError = isAmountError,
                        supportingText = {
                            if (isAmountError) {
                                Text("Amount must be greater than 0", color = Color.Red)
                            }
                        },
                        singleLine = true,
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            }
        }
    }
}