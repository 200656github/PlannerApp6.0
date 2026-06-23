
package com.example.planner.ui.expense

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.planner.PlannerApplication
import com.example.planner.domain.model.Expense
import com.example.planner.domain.model.ExpenseCategory
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.time.LocalDate

class ExpenseEditViewModel(application: Application) : AndroidViewModel(application) {
    private val expenseRepository = (application as PlannerApplication).expenseRepository

    private val _title = MutableStateFlow("")
    val title: StateFlow<String> = _title

    private val _amount = MutableStateFlow("")
    val amount: StateFlow<String> = _amount

    private val _category = MutableStateFlow(ExpenseCategory.FOOD)
    val category: StateFlow<ExpenseCategory> = _category

    private val _note = MutableStateFlow("")
    val note: StateFlow<String> = _note

    private val _date = MutableStateFlow(LocalDate.now())
    val date: StateFlow<LocalDate> = _date

    private val _saveSuccess = MutableStateFlow(false)
    val saveSuccess: StateFlow<Boolean> = _saveSuccess

    fun updateTitle(value: String) { _title.value = value }
    fun updateAmount(value: String) { _amount.value = value }
    fun updateCategory(value: ExpenseCategory) { _category.value = value }
    fun updateNote(value: String) { _note.value = value }
    fun updateDate(value: LocalDate) { _date.value = value }

    fun saveExpense() {
        val titleValue = _title.value.trim()
        val amountValue = _amount.value.toDoubleOrNull()

        if (titleValue.isBlank() || amountValue == null || amountValue <= 0) return

        viewModelScope.launch {
            expenseRepository.insertExpense(
                Expense(
                    title = titleValue,
                    amount = amountValue,
                    category = _category.value,
                    note = _note.value.trim(),
                    date = _date.value
                )
            )
            _saveSuccess.value = true
        }
    }

    fun reset() {
        _title.value = ""
        _amount.value = ""
        _category.value = ExpenseCategory.FOOD
        _note.value = ""
        _date.value = LocalDate.now()
        _saveSuccess.value = false
    }
}