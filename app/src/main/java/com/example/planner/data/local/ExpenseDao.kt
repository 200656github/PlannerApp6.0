
package com.example.planner.data.local

import androidx.room.Dao
import androidx.room.TypeConverters
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.planner.data.local.entity.ExpenseEntity
import kotlinx.coroutines.flow.Flow

@Dao
@TypeConverters(Converters::class)
interface ExpenseDao {
@Query("SELECT * FROM expenses ORDER BY date DESC, createdAt DESC")
fun getAllExpenses(): Flow<List<ExpenseEntity>>

@Query("SELECT * FROM expenses WHERE date BETWEEN :dateStart AND :dateEnd ORDER BY createdAt DESC")
fun getExpensesByDate(dateStart: Long, dateEnd: Long): Flow<List<ExpenseEntity>>

@Query("SELECT * FROM expenses WHERE id = :id")
suspend fun getExpenseById(id: Long): ExpenseEntity?

@Query("SELECT * FROM expenses WHERE date BETWEEN :startDate AND :endDate ORDER BY date DESC, createdAt DESC")
fun getExpensesByDateRange(startDate: Long, endDate: Long): Flow<List<ExpenseEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertExpense(expense: ExpenseEntity): Long

    @Update
    suspend fun updateExpense(expense: ExpenseEntity)

@Delete
suspend fun deleteExpense(expense: ExpenseEntity)

@Query("DELETE FROM expenses WHERE id = :id")
suspend fun deleteExpenseById(id: Long)

@Query("SELECT SUM(amount) FROM expenses WHERE date BETWEEN :dateStart AND :dateEnd")
fun getDailyTotal(dateStart: Long, dateEnd: Long): Flow<Double?>

@Query("SELECT SUM(amount) FROM expenses WHERE date BETWEEN :startDate AND :endDate")
fun getPeriodTotal(startDate: Long, endDate: Long): Flow<Double?>

@Query("SELECT category, SUM(amount) as total FROM expenses WHERE date BETWEEN :startDate AND :endDate GROUP BY category ORDER BY total DESC")
fun getCategorySummary(startDate: Long, endDate: Long): Flow<List<CategoryTotal>>
}

data class CategoryTotal(
    val category: String,
    val total: Double
)
