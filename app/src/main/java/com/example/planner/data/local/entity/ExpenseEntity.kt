package com.example.planner.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.planner.domain.model.Expense
import com.example.planner.domain.model.ExpenseCategory
import java.time.LocalDate

@Entity(tableName = "expenses")
data class ExpenseEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val title: String,
    val amount: Double,
    val category: String = ExpenseCategory.OTHER.name,
    val note: String = "",
    val date: Long = System.currentTimeMillis(),
    val createdAt: Long = System.currentTimeMillis()
) {
    fun toExpense(): Expense = Expense(
        id = id, title = title, amount = amount,
        category = ExpenseCategory.valueOf(category), note = note,
        date = LocalDate.ofEpochDay(date / 86400000L), createdAt = createdAt
    )
    companion object {
        fun fromExpense(expense: Expense): ExpenseEntity = ExpenseEntity(
            id = expense.id, title = expense.title, amount = expense.amount,
            category = expense.category.name, note = expense.note,
            date = expense.date.toEpochDay() * 86400000L, createdAt = expense.createdAt
        )
    }
}
