
package com.example.planner.ui.expense

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.planner.PlannerApplication
import com.example.planner.data.local.CategoryTotal
import com.example.planner.domain.model.Expense
import com.example.planner.domain.model.ExpenseCategory
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.YearMonth

@OptIn(ExperimentalCoroutinesApi::class)
class ExpenseListViewModel(application: Application) : AndroidViewModel(application) {
    private val expenseRepository = (application as PlannerApplication).expenseRepository

    private val _selectedDate = MutableStateFlow(LocalDate.now())
    val selectedDate: StateFlow<LocalDate> = _selectedDate

    val expenses: StateFlow<List<Expense>> = _selectedDate
        .flatMapLatest { date -> expenseRepository.getExpensesByDate(date) }
        .stateIn(
            viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList()
        )

    val dailyTotal: StateFlow<Double> = expenses.map { list ->
        list.sumOf { it.amount }
    }.stateIn(
        viewModelScope, SharingStarted.WhileSubscribed(5000), 0.0
    )

    // 月度总览：当前所选日期所属月份的总支出
    val monthlyTotal: StateFlow<Double> = _selectedDate
        .map { YearMonth.from(it) }
        .distinctUntilChanged()
        .flatMapLatest { month ->
            expenseRepository.getPeriodTotal(month.atDay(1), month.atEndOfMonth())
                .map { it ?: 0.0 }
        }
        .stateIn(
            viewModelScope, SharingStarted.WhileSubscribed(5000), 0.0
        )

    // 月度总览：当月按类别汇总（用于分类占比条与图例）
    val monthlyCategorySummary: StateFlow<List<CategoryTotal>> = _selectedDate
        .map { YearMonth.from(it) }
        .distinctUntilChanged()
        .flatMapLatest { month ->
            expenseRepository.getCategorySummary(month.atDay(1), month.atEndOfMonth())
        }
        .stateIn(
            viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList()
        )

    fun selectDate(date: LocalDate) {
        _selectedDate.value = date
    }

    fun goToPreviousDay() {
        _selectedDate.value = _selectedDate.value.minusDays(1)
    }

    fun goToNextDay() {
        _selectedDate.value = _selectedDate.value.plusDays(1)
    }

    fun deleteExpense(expense: Expense) {
        viewModelScope.launch {
            expenseRepository.deleteExpense(expense)
        }
    }
}
