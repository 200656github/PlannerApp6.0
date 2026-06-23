package com.example.planner.ui.calendar

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ChevronLeft
import androidx.compose.material.icons.rounded.ChevronRight
import androidx.compose.material.icons.rounded.DateRange
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.planner.domain.model.Task
import com.example.planner.ui.components.TaskCard
import com.example.planner.ui.theme.OchrePrimary
import com.example.planner.ui.theme.OchreContainer
import com.example.planner.ui.theme.OchreOnContainer
import com.example.planner.ui.theme.WarmBackground
import com.example.planner.ui.theme.WarmElevated
import com.example.planner.ui.theme.WarmOnSurfaceVariant
import com.example.planner.ui.theme.WarmOutline
import com.example.planner.ui.theme.SageSecondary
import java.time.format.DateTimeFormatter

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CalendarScreen(
    onTaskClick: (Task) -> Unit = {},
    viewModel: CalendarViewModel = viewModel()
) {
    val weekDates by viewModel.weekDates.collectAsState()
    val tasksForDate by viewModel.tasksForDate.collectAsState()
    val selectedDate by viewModel.selectedDate.collectAsState()

    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .background(WarmBackground),
        topBar = {
            TopAppBar(
                title = {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text(
                            text = selectedDate.format(
                                DateTimeFormatter.ofPattern("yyyy年M月")
                            ),
                            style = MaterialTheme.typography.titleLarge
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        TextButton(onClick = { viewModel.goToToday() }) {
                            Text(
                                "今天",
                                style = MaterialTheme.typography.bodySmall,
                                color = OchrePrimary
                            )
                        }
                    }
                },
                navigationIcon = {
                    IconButton(onClick = { viewModel.goToPreviousWeek() }) {
                        Icon(Icons.Rounded.ChevronLeft, contentDescription = "上一周")
                    }
                },
                actions = {
                    IconButton(onClick = { viewModel.goToNextWeek() }) {
                        Icon(Icons.Rounded.ChevronRight, contentDescription = "下一周")
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = WarmBackground,
                    scrolledContainerColor = WarmBackground
                )
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            // Week row
            LazyRow(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                contentPadding = PaddingValues(horizontal = 8.dp),
                horizontalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                items(weekDates) { cell ->
                    DateItem(cell = cell, onClick = { viewModel.selectDate(cell.date) })
                }
            }

            Divider(
                color = MaterialTheme.colorScheme.outline.copy(alpha = 0.5f),
                thickness = 0.5.dp
            )

            if (tasksForDate.isEmpty()) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Icon(
                            Icons.Rounded.DateRange,
                            contentDescription = null,
                            modifier = Modifier.size(64.dp),
                            tint = MaterialTheme.colorScheme.outline
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                        Text(
                            text = "这一天还没有任务",
                            style = MaterialTheme.typography.bodyLarge,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                }
            } else {
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    contentPadding = PaddingValues(
                        start = 16.dp, end = 16.dp, top = 8.dp, bottom = 16.dp
                    ),
                    verticalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    items(tasksForDate, key = { it.id }) { task ->
                        TaskCard(
                            task = task,
                            onToggleComplete = {},
                            onClick = { onTaskClick(task) }
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun DateItem(cell: DateCell, onClick: () -> Unit) {
    val bgColor by animateColorAsState(
        targetValue = when {
            cell.isSelected -> OchrePrimary
            cell.isToday -> OchreContainer
            else -> WarmBackground
        },
        animationSpec = tween(300), label = "dateBg"
    )
    val textColor by animateColorAsState(
        targetValue = when {
            cell.isSelected -> MaterialTheme.colorScheme.onPrimary
            cell.isToday -> OchreOnContainer
            else -> MaterialTheme.colorScheme.onSurfaceVariant
        },
        animationSpec = tween(300), label = "dateText"
    )

    Column(
        modifier = Modifier
            .width(44.dp)
            .clip(RoundedCornerShape(12.dp))
            .background(bgColor)
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = null, onClick = onClick
            )
            .padding(vertical = 6.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = cell.dayName,
            style = MaterialTheme.typography.bodySmall.copy(fontSize = 11.sp),
            color = if (cell.isSelected) MaterialTheme.colorScheme.onPrimary.copy(alpha = 0.7f)
                    else WarmOnSurfaceVariant
        )
        Spacer(modifier = Modifier.height(2.dp))
        Text(
            text = cell.dayNumber.toString(),
            style = MaterialTheme.typography.bodyMedium.copy(
                fontWeight = if (cell.isToday || cell.isSelected) FontWeight.SemiBold
                            else FontWeight.Normal
            ),
            color = textColor
        )
    }
}
