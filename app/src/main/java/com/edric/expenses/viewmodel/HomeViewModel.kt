package com.edric.expenses.viewmodel

import android.graphics.Bitmap
import androidx.compose.runtime.collectAsState
import androidx.lifecycle.ViewModel
import com.edric.expenses.model.Expense
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class HomeViewModel : ViewModel() {
    private val _expenses = MutableStateFlow<List<Expense>>(emptyList())
    val expenses: StateFlow<List<Expense>> = _expenses.asStateFlow()
    private val _totalExpenses = MutableStateFlow(0)
    val totalExpenses = _totalExpenses.asStateFlow()

    fun addExpense(name: String, amount: Int) {
        _expenses.update { it + Expense(name, amount, false) }
        _totalExpenses.update { it + amount }
    }

    fun editExpense(index: Int, newAmount: Int): Boolean {
        if (
            newAmount > 0 &&
            index >= 0 &&
            index < _expenses.value.size
        ) { // lebih gede ya dari 0
            _expenses.update { list ->
                list.toMutableList().apply {
                    this[index] = this[index].copy(amount = newAmount)
                }
            }
            calculateTotalExpense()
            return true
        }
        return false
    }

    fun deleteExpense(index: Int): Boolean {
        if (index >= 0 && index < _expenses.value.size) {
            val amount = _expenses.value[index].amount
            _expenses.update { list ->
                if (index in list.indices) {
                    list.toMutableList().apply { removeAt(index) }
                } else {
                    list
                }
            }
            calculateTotalExpense()
            return true
        }
        return false
    }

    fun calculateTotalExpense() {
        var newTotal = 0
        for (expense in _expenses.value) {
            newTotal += expense.amount
        }
        _totalExpenses.update { newTotal }
    }

    fun editToggle(index: Int): Boolean{ // kayak editExpense
        if (
            index >= 0 &&
            index < _expenses.value.size
        ) {
            _expenses.update { list ->
                list.toMutableList().apply {
                    this[index] = this[index].copy(toggle = !this[index].toggle)
                }
            }
            calculateTotalExpense()
            return true
        }
        return false
    }
}