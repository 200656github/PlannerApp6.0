
package com.example.planner.domain.model

import java.time.LocalDate

/**
 * 日常消费领域模型
 */
data class Expense(
    val id: Long = 0,
    val title: String,           // 消费标题/名称
    val amount: Double,          // 金额
    val category: ExpenseCategory, // 消费类别
    val note: String = "",       // 备注
    val date: LocalDate = LocalDate.now(), // 消费日期
    val createdAt: Long = System.currentTimeMillis()
)

/**
 * 消费类别枚举
 */
enum class ExpenseCategory(val displayName: String, val icon: String) {
    FOOD("餐饮", "\uD83C\uDF54"),        // 🍔
    TRANSPORT("交通", "\uD83D\uDE8C"),   // 🚌
    SHOPPING("购物", "\uD83D\uDED2"),    // 🛒
    ENTERTAINMENT("娱乐", "\uD83C\uDFB5"), // 🎵
    HOUSING("住房", "\uD83C\uDFE0"),     // 🏠
    MEDICAL("医疗", "\uD83C\uDFE5"),     // 🏥
    EDUCATION("教育", "\uD83D\uDCDA"),   // 📚
    OTHER("其他", "\uD83D\uDCCB");       // 📋

    companion object {
        fun fromDisplayName(name: String): ExpenseCategory =
            entries.find { it.displayName == name } ?: OTHER
    }
}