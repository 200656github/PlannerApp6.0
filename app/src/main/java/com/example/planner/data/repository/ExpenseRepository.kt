
package com.example.planner.data.repository

import com.example.planner.data.local.ExpenseDao
import com.example.planner.data.local.entity.ExpenseEntity
import com.example.planner.domain.model.Expense
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.time.LocalDate

class ExpenseRepository(private val expenseDao: ExpenseDao) {

    fun getAllExpenses(): Flow<List<Expense>> =
        expenseDao.getAllExpenses().map { entities ->
            entities.map { it.toExpense() }
        }

    fun getExpensesByDate(date: LocalDate): Flow<List<Expense>> =
        expenseDao.getExpensesByDate(
            date.toEpochDay() * 86400000L,
            date.toEpochDay() * 86400000L + 86400000L - 1
        ).map { entities ->
            entities.map { it.toExpense() }
        }

    fun getExpensesByDateRange(startDate: LocalDate, endDate: LocalDate): Flow<List<Expense>> =
        expenseDao.getExpensesByDateRange(
            startDate.toEpochDay() * 86400000L,
            endDate.toEpochDay() * 86400000L + 86400000L - 1
        ).map { entities ->
            entities.map { it.toExpense() }
        }

    suspend fun getExpenseById(id: Long): Expense? =
        expenseDao.getExpenseById(id)?.toExpense()

    suspend fun insertExpense(expense: Expense): Long =
        expenseDao.insertExpense(ExpenseEntity.fromExpense(expense))

    suspend fun updateExpense(expense: Expense) =
        expenseDao.updateExpense(ExpenseEntity.fromExpense(expense))

    suspend fun deleteExpense(expense: Expense) =
        expenseDao.deleteExpense(ExpenseEntity.fromExpense(expense))

    suspend fun deleteExpenseById(id: Long) =
        expenseDao.deleteExpenseById(id)

    fun getDailyTotal(date: LocalDate): Flow<Double?> =
        expenseDao.getDailyTotal(
            date.toEpochDay() * 86400000L,
            date.toEpochDay() * 86400000L + 86400000L - 1
        )

    fun getPeriodTotal(startDate: LocalDate, endDate: LocalDate): Flow<Double?> =
        expenseDao.getPeriodTotal(
            startDate.toEpochDay() * 86400000L,
            endDate.toEpochDay() * 86400000L + 86400000L - 1
        )

    fun getCategorySummary(startDate: LocalDate, endDate: LocalDate) =
        expenseDao.getCategorySummary(
            startDate.toEpochDay() * 86400000L,
            endDate.toEpochDay() * 86400000L + 86400000L - 1
        )
}
