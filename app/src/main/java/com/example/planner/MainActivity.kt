package com.example.planner

import android.os.Build
import android.os.Bundle
import android.view.Window
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.CalendarMonth
import androidx.compose.material.icons.rounded.Checklist
import androidx.compose.material.icons.rounded.MonetizationOn
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.core.view.WindowCompat
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.planner.ui.calendar.CalendarScreen
import com.example.planner.ui.edit.TaskEditScreen
import com.example.planner.ui.expense.ExpenseEditScreen
import com.example.planner.ui.expense.ExpenseListScreen
import com.example.planner.ui.tasklist.TaskListScreen
import com.example.planner.ui.theme.PlannerTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Honor Magic UI requires explicit decor-fitting for reliable edge-to-edge
        WindowCompat.setDecorFitsSystemWindows(window, false)
        enableEdgeToEdge()
        setContent {
            PlannerTheme {
                PlannerApp()
            }
        }
    }
}

private sealed class Screen(val route: String) {
    object Tasks : Screen("tasks")
    object Calendar : Screen("calendar")
    object Expenses : Screen("expenses")
    object Edit : Screen("edit/{taskId}") {
        fun createRoute(taskId: Long) = "edit/$taskId"
    }
    object ExpenseEdit : Screen("expense_edit")
}

private val bottomNavScreens = listOf(Screen.Tasks, Screen.Calendar, Screen.Expenses)

@Composable
@OptIn(ExperimentalLayoutApi::class)
fun PlannerApp() {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    val showBottomBar = bottomNavScreens.any { screen ->
        currentDestination?.hierarchy?.any { it.route == screen.route } == true
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = {
            if (showBottomBar) {
                NavigationBar {
                    bottomNavScreens.forEach { screen ->
                        val selected = currentDestination?.hierarchy?.any {
                            it.route == screen.route
                        } == true
                        NavigationBarItem(
                            selected = selected,
                            onClick = {
                                navController.navigate(screen.route) {
                                    popUpTo(navController.graph.findStartDestination().id) {
                                        saveState = true
                                    }
                                    launchSingleTop = true
                                    restoreState = true
                                }
                            },
                            icon = {
                                when (screen) {
                                    Screen.Tasks -> Icon(Icons.Rounded.Checklist, contentDescription = null)
                                    Screen.Calendar -> Icon(Icons.Rounded.CalendarMonth, contentDescription = null)
                                    Screen.Expenses -> Icon(Icons.Rounded.MonetizationOn, contentDescription = null)
                                    else -> {}
                                }
                            },
                            label = {
                                when (screen) {
                                    Screen.Tasks -> Text("Tasks")
                                    Screen.Calendar -> Text("Calendar")
                                    Screen.Expenses -> Text("Expenses")
                                    else -> {}
                                }
                            }
                        )
                    }
                }
            }
        }
    ) { paddingValues ->
        NavHost(
            navController = navController,
            startDestination = Screen.Tasks.route,
            modifier = Modifier
                .padding(paddingValues)
                .consumeWindowInsets(paddingValues)
        ) {
            composable(Screen.Tasks.route) {
                TaskListScreen(
                    onAddTask = { navController.navigate(Screen.Edit.createRoute(-1)) },
                    onTaskClick = { task -> navController.navigate(Screen.Edit.createRoute(task.id)) }
                )
            }
            composable(Screen.Calendar.route) {
                CalendarScreen(
                    onTaskClick = { task -> navController.navigate(Screen.Edit.createRoute(task.id)) }
                )
            }
            composable(Screen.Expenses.route) {
                ExpenseListScreen(
                    onAddExpense = { navController.navigate(Screen.ExpenseEdit.route) }
                )
            }
            composable(Screen.ExpenseEdit.route) {
                ExpenseEditScreen(
                    onBack = { navController.popBackStack() }
                )
            }
            composable(
                route = Screen.Edit.route,
                arguments = listOf(navArgument("taskId") { type = NavType.LongType })
            ) { backStackEntry ->
                val taskId = backStackEntry.arguments?.getLong("taskId") ?: -1
                TaskEditScreen(
                    taskId = taskId,
                    onBack = { navController.popBackStack() }
                )
            }
        }
    }
}
